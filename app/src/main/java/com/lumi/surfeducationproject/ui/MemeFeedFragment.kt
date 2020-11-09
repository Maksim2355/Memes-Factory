package com.lumi.surfeducationproject.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.RefresherOwner
import com.lumi.surfeducationproject.common.base_view.BaseFragment
import com.lumi.surfeducationproject.common.managers.InputModeManager
import com.lumi.surfeducationproject.common.managers.SnackBarManager
import com.lumi.surfeducationproject.common.managers.StyleManager
import com.lumi.surfeducationproject.controllers.MemeController
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.navigation.NavigationMemeDetails
import com.lumi.surfeducationproject.presenters.MemesFeedPresenter
import com.lumi.surfeducationproject.ui.custom_view.ToolbarSearchView
import com.lumi.surfeducationproject.views.MemeFeedView
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.fragment_meme_feed.view.*
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject
import javax.inject.Provider


class MemeFeedFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, MemeFeedView,
    RefresherOwner {

    private lateinit var searchToolbar: ToolbarSearchView
    private lateinit var refreshContainer: SwipeRefreshLayout
    private lateinit var memeListRv: RecyclerView
    private lateinit var stateErrorView: TextView
    private lateinit var stateLoadView: FrameLayout


    @Inject
    lateinit var presenterProvider: Provider<MemesFeedPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    @Inject
    lateinit var styleManager: StyleManager

    @Inject
    lateinit var inputModeManager: InputModeManager

    @Inject
    lateinit var snackBarManager: SnackBarManager

    @Inject
    lateinit var navMemeDetailsFragment: NavigationMemeDetails

    @Inject
    lateinit var easyAdapter: EasyAdapter

    @Inject
    lateinit var memeController: MemeController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.getFragmentContentComponentOrCreateIfNull().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        styleManager.setColorStatusBar(R.color.colorPrimaryContent)
        return inflater.inflate(R.layout.fragment_meme_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
        initView(view)
        initRecyclerView()
    }

    private fun initToolbar(view: View) {
        searchToolbar = view.meme_feed_Stoolbar
        searchToolbar.onChangeSearchMode = object : ToolbarSearchView.OnChangeSearchModeListener {
            override fun onStartSearch() {
                presenter.startFilter()
                openKeyboard()
            }

            override fun onStopSearch() {
                presenter.stopFilter()
                hideKeyboard()
            }

        }
        presenter.observableSearchText = Observable.create { subscriber ->
            searchToolbar.onChangeSearchText  = {
                subscriber.onNext(it)
            }
        }

    }

    private fun openKeyboard() {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
    private fun hideKeyboard(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun initView(view: View) {
        memeListRv = view.meme_list_rv
        stateErrorView = view.state_error_tv
        stateLoadView = view.state_progress_container
        refreshContainer = view.refresh_container
        with(refreshContainer) {
            setColorSchemeColors(Color.BLACK)
            setProgressBackgroundColorSchemeColor(resources.getColor(R.color.colorAccent))
        }
        refreshContainer.setOnRefreshListener(this)
    }

    private fun initRecyclerView() {
        with(memeListRv) {
            adapter = easyAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        memeController.memeDetailsClickListener = { presenter.openDetails(it) }
        memeController.shareClickListener = {
            val shareMeme = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it.title)
                putExtra(Intent.EXTRA_STREAM, it.photoUrl)
                type = "image/*"
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }, null)
            startActivity(shareMeme)
        }
    }


    override fun showMemes(memesList: List<Meme>) {
        val itemList = ItemList.create().apply {
            addAll(memesList, memeController)
        }
        easyAdapter.setItems(itemList)
        memeListRv.visibility = View.VISIBLE
        stateErrorView.visibility = View.GONE
    }

    override fun showErrorState() {
        memeListRv.visibility = View.GONE
        stateErrorView.text = getString(R.string.errorDownloadMemeState_message)
        stateErrorView.visibility = View.VISIBLE
    }

    override fun showEmptyFilterErrorState() {
        memeListRv.visibility = View.GONE
        stateErrorView.visibility = View.VISIBLE
        stateErrorView.text = getString(R.string.meme_feed_empty_filter_message)
    }

    override fun showLoadState() {
        stateLoadView.visibility = View.VISIBLE
    }

    override fun hideLoadState() {
        stateLoadView.visibility = View.GONE
    }

    override fun showRefresh() {
        setRefresherState(true)
    }

    override fun hideRefresh() {
        setRefresherState(false)
    }

    override fun showErrorSnackbar(message: String) {
        snackBarManager.showErrorMessage(message)
    }

    override fun openMemeDetails(data: Meme) {
        navMemeDetailsFragment.startMemeDetailsScreen(data)
    }

    override fun hideSearch() {
        searchToolbar.disableSearchMode()
    }

    override fun onRefresh() {
        presenter.updateMemes()
    }

    override fun setRefresherState(refresherState: Boolean) {
        refreshContainer.post { refreshContainer.isRefreshing = refresherState }
    }

    override fun onStart() {
        super.onStart()
        inputModeManager.setAdjustPan()
    }

    override fun onStop() {
        super.onStop()
        inputModeManager.setAdjustResize()
        presenter.stopFilter()
    }

    override fun getActionBar() = (activity as AppCompatActivity).supportActionBar


}
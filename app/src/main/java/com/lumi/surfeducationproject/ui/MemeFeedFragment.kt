package com.lumi.surfeducationproject.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.*
import com.lumi.surfeducationproject.common.base_view.BaseFragment
import com.lumi.surfeducationproject.common.managers.SnackBarManager
import com.lumi.surfeducationproject.common.managers.StyleManager
import com.lumi.surfeducationproject.controllers.MemeController
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.navigation.NavigationMemeDetails
import com.lumi.surfeducationproject.presenters.MemesFeedPresenter
import com.lumi.surfeducationproject.ui.custom_view.ToolbarSearchView
import com.lumi.surfeducationproject.views.MemeFeedView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import kotlinx.android.synthetic.main.fragment_meme_feed.view.*
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import java.util.*
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

    override fun onResume() {
        super.onResume()
        searchToolbar.observableIsSearchMode.subscribe({}, {})
        searchToolbar.observableSearchText?.subscribe({}, {})
    }

    private fun initRecyclerView() {
        with(memeListRv) {
            adapter = easyAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        memeController.memeDetailsClickListener = { presenter.openDetails(it) }
        memeController.shareClickListener = { presenter.shareMeme(it) }
    }


    override fun showMemes(memesList: List<Meme>) {
        initRecyclerView()
        val itemList = ItemList.create().apply {
            addAll(memesList, memeController)
        }
        easyAdapter.setItems(itemList)
        memeListRv.visibility = View.VISIBLE
        stateErrorView.visibility = View.GONE
    }

    override fun showErrorState() {
        memeListRv.visibility = View.GONE
        stateErrorView.visibility = View.VISIBLE
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

    override fun enableSearchView() {

    }

    override fun disableSearchView() {

    }

    override fun onRefresh() {
        presenter.updateMemes()
    }

    override fun setRefresherState(refresherState: Boolean) {
        refreshContainer.post { refreshContainer.isRefreshing = refresherState }
    }

    override fun getActionBar() = (activity as AppCompatActivity).supportActionBar


}
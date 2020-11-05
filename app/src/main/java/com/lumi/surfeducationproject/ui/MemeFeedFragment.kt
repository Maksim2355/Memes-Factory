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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.*
import com.lumi.surfeducationproject.controllers.MemeController
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.navigation.NavigationMemeDetails
import com.lumi.surfeducationproject.presenters.MemesFeedPresenter
import com.lumi.surfeducationproject.views.MemeFeedView
import kotlinx.android.synthetic.main.fragment_meme_feed.view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject
import javax.inject.Provider


class MemeFeedFragment : MvpAppCompatFragment(), SwipeRefreshLayout.OnRefreshListener, MemeFeedView,
    RefresherOwner {

    private lateinit var toolbar: Toolbar
    private lateinit var refreshContainer: SwipeRefreshLayout
    private lateinit var memeListRv: RecyclerView
    private lateinit var stateErrorView: TextView
    private lateinit var stateLoadView: FrameLayout

    @Inject
    lateinit var easyAdapter: EasyAdapter

    @Inject
    lateinit var memeController: MemeController

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.startFragmentComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
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
        toolbar = view.findViewById(R.id.meme_feed_toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        getActionBar()?.title = "Популярные мемы"
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
        memeController.shareClickListener = { presenter.shareMeme(it) }
    }


    override fun onResume() {
        super.onResume()
        presenter.loadMemes()
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
        refreshContainer.isRefreshing = true
        setRefresherState(true)
    }

    override fun hideRefresh() {
        refreshContainer.isRefreshing = false
        setRefresherState(false)
    }

    override fun showErrorSnackbar(message: String) {
        snackBarManager.showErrorMessage(message)
    }

    override fun openMemeDetails(data: Meme) {
        navMemeDetailsFragment.startMemeDetailsScreen(data)
    }

    override fun onRefresh() {
        presenter.updateMemes()
    }

    override fun setRefresherState(refresherState: Boolean) {
        refreshContainer.post { refreshContainer.isRefreshing = refresherState }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_feed, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchView: SearchView = menu.findItem(R.id.action_search).actionView as SearchView

//        searchView.setOnQueryTextFocusChangeListener { view: View, b: Boolean ->
//            getActionBar()?.title = ""
//            toolbar.setNavigationIcon(R.drawable.ic_back)
//        }
//        searchView.setOnCloseListener {
//            getActionBar()?.title = TITLE_ACTION_BAR
//            toolbar.navigationIcon = null
//            false
//        }

//        Observable.create(ObservableOnSubscribe<String> { subscriber ->
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    subscriber.onNext(query!!)
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    subscriber.onNext(newText!!)
//                    return false
//                }
//
//            })
//        }).subscribe {
//            presenter.filterMemeList(it)
//        }

    }

    private fun getActionBar() = (activity as AppCompatActivity).supportActionBar

    override fun onDetach() {
        super.onDetach()
        App.instance.clearFragmentComponent()
    }
}
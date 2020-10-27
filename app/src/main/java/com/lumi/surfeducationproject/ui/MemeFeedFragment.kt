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
import com.google.android.material.snackbar.Snackbar
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.RefresherOwner
import com.lumi.surfeducationproject.controllers.MemeController
import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.navigation.NavigationMemeDetails
import com.lumi.surfeducationproject.presenters.MemesFeedPresenter
import com.lumi.surfeducationproject.views.MemeFeedView
import kotlinx.android.synthetic.main.fragment_meme_feed.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList


class MemeFeedFragment : MvpAppCompatFragment(), SwipeRefreshLayout.OnRefreshListener, MemeFeedView,
    RefresherOwner {

    private val presenter by moxyPresenter { MemesFeedPresenter() }

    private lateinit var toolbar: Toolbar

    private lateinit var refresh: SwipeRefreshLayout

    private lateinit var memesRecyclerView: RecyclerView
    private val easyAdapter = EasyAdapter()
    private val memeController = MemeController()

    private lateinit var errorView: TextView
    private lateinit var loadView: FrameLayout

    private val TITLE_ACTION_BAR = "Популярные мемы"

    private lateinit var navMemeDetailsFragment: NavigationMemeDetails

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navMemeDetailsFragment = context as NavigationMemeDetails
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_meme_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.toolbar_meme_feed)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        getActionBar()?.title = TITLE_ACTION_BAR

        refresh = view.findViewById(R.id.refresh_srl)
        with(refresh) {
            setColorSchemeColors(Color.BLACK)
            setProgressBackgroundColorSchemeColor(resources.getColor(R.color.colorAccent))
        }
        refresh.setOnRefreshListener(this)


        memeController.memeDetailsClickListener = {
            presenter.openDetails(it)
        }
        memeController.shareClickListener = { presenter.shareMeme(it) }

        memesRecyclerView = view.findViewById(R.id.state_meme_list_rv)
        initRecyclerView()

        errorView = view.findViewById(R.id.state_error_tv)
        loadView = view.findViewById(R.id.state_progress_pb)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadMemes()
    }

    private fun initRecyclerView() {
        with(memesRecyclerView) {
            adapter = easyAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun showMemes(memesList: List<NetworkMeme>) {
        val itemList = ItemList.create().apply {
            addAll(memesList, memeController)
        }
        easyAdapter.setItems(itemList)
        memesRecyclerView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
    }

    override fun showErrorState() {
        memesRecyclerView.visibility = View.GONE
        errorView.visibility = View.VISIBLE
    }

    override fun showLoadState() {
        loadView.visibility = View.VISIBLE
    }

    override fun hideLoadState() {
        loadView.visibility = View.GONE
    }

    override fun showRefresh() {
        refresh.isRefreshing = true
        setRefresherState(true)
    }

    override fun hideRefresh() {
        refresh.isRefreshing = false
        setRefresherState(false)
    }

    override fun showErrorSnackbar(message: String) {
        val snackbar = Snackbar.make(
            root_layout_tab, message,
            Snackbar.LENGTH_LONG
        )
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#FF575D"))
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16f
        snackbar.show()

    }

    override fun openMemeDetails(data: NetworkMeme) {
        navMemeDetailsFragment.startMemeDetailsScreen(data)
    }

    override fun onRefresh() {
        presenter.updateMemes()
    }

    override fun setRefresherState(refresherState: Boolean) {
        refresh.post { refresh.isRefreshing = refresherState }
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

}
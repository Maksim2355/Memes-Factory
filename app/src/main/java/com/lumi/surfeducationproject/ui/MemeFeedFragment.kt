package com.lumi.surfeducationproject.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.RefresherOwner
import com.lumi.surfeducationproject.data.model.Meme
import com.lumi.surfeducationproject.list_controllers.MemeController
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

    private lateinit var refresh: SwipeRefreshLayout

    private lateinit var memesRecyclerView: RecyclerView
    private val easyAdapter = EasyAdapter()

    private lateinit var errorView: TextView
    private lateinit var loadView: FrameLayout

    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meme_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh = view.findViewById(R.id.refresh_srl)
        refresh.setColorSchemeColors(Color.BLACK)
        refresh.setProgressBackgroundColorSchemeColor(resources.getColor(R.color.colorAccent))
        refresh.setOnRefreshListener(this)

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
        with(memesRecyclerView){
            adapter = easyAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun showMemes(memesList: List<Meme>) {
        val meme = memesList + memesList + memesList
        val itemList = ItemList.create().apply {
            addAll(meme, MemeController())
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

    override fun onRefresh() {
        presenter.updateMemes()
    }

    override fun setRefresherState(refresherState: Boolean) {
        refresh.post { refresh.isRefreshing = refresherState}
    }


}
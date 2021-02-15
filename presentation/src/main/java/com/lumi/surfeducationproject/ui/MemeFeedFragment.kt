package com.lumi.surfeducationproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.domain.model.Meme
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.databinding.FragmentMemeFeedBinding
import com.lumi.surfeducationproject.di.injection_extension.injectViewModel
import com.lumi.surfeducationproject.di.named.FRAGMENT_CONTENT_NAVIGATION
import com.lumi.surfeducationproject.navigation.NavigationDestination
import com.lumi.surfeducationproject.ui.custom_view.ToolbarSearchView
import com.lumi.surfeducationproject.ui.extension.activity_extension.setColorStatusBar
import com.lumi.surfeducationproject.vm.MemeDetailsViewModel
import dagger.android.support.DaggerFragment
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject
import javax.inject.Named


class MemeFeedFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    @Named(FRAGMENT_CONTENT_NAVIGATION)
    lateinit var navigationDestination: NavigationDestination

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var memeDetailsViewModel: MemeDetailsViewModel

    private lateinit var binding: FragmentMemeFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemeFeedBinding.inflate(inflater, container, false)
        memeDetailsViewModel = injectViewModel(viewModelFactory)
        requireActivity().setColorStatusBar(R.color.colorPrimaryContent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initRecyclerView()
    }

    private fun initToolbar() {
        meme_feed_Stoolbar.onChangeSearchMode =
            object : ToolbarSearchView.OnChangeSearchModeListener {
                override fun onStartSearch() {
                    presenter.startFilter()
                }

                override fun onStopSearch() {
                    presenter.stopFilter()
                    meme_feed_Stoolbar.clearSearchText()
                }
            }
        meme_feed_Stoolbar.onChangeSearchText = {
            presenter.updateSearchText(it)
        }

    }

    private fun initView() {
        with(refresh_container) {
            setColorSchemeColors(Color.BLACK)
            setProgressBackgroundColorSchemeColor(resources.getColor(R.color.colorAccent))
        }
        refresh_container.setOnRefreshListener(this)
    }

    private fun initRecyclerView() {
        with(meme_list_rv) {
            adapter = easyAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        memeController.memeDetailsClickListener = { presenter.openDetails(it) }

        memeController.shareClickListener = {
            presenter.shareMemeInSocialNetworks(it)
        }
    }

    override fun onStart() {
        super.onStart()
        inputModeManager.setAdjustPan()
    }

    override fun onStop() {
        super.onStop()
        inputModeManager.setAdjustResize()
    }

    override fun showMemes(memeList: List<Meme>) {
        val itemList = ItemList.create().apply {
            addAll(memeList, memeController)
        }
        easyAdapter.setItems(itemList)
        meme_list_rv.visibility = View.VISIBLE
        state_error_tv.visibility = View.GONE
    }

    override fun showErrorState() {
        meme_list_rv.visibility = View.GONE
        state_error_tv.text = getString(R.string.errorDownloadMemeState_message)
        state_error_tv.visibility = View.VISIBLE
    }

    override fun showEmptyFilterErrorState() {
        meme_list_rv.visibility = View.GONE
        state_error_tv.visibility = View.VISIBLE
        state_error_tv.text = getString(R.string.meme_feed_empty_filter_message)
    }

    override fun showLoadState() {
        state_progress_container.visibility = View.VISIBLE
    }

    override fun hideLoadState() {
        state_progress_container.visibility = View.GONE
    }

    override fun showRefresh() {
        setRefresherState(true)
    }

    override fun hideRefresh() {
        setRefresherState(false)
    }

    override fun enableSearchView() {
        meme_feed_Stoolbar.enableSearchMode()
    }

    override fun disableSearchView() {
        meme_feed_Stoolbar.disableSearchMode()
    }

    override fun showErrorSnackbar(message: String) {
        snackBarManager.showErrorMessage(message)
    }

    override fun openMemeDetails(data: Meme) {
        navMemeDetailsFragment.startMemeDetailsScreen(data)
    }

    override fun shareMeme(meme: Meme) {
        val shareMeme = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, meme.title)
            putExtra(Intent.EXTRA_STREAM, meme.photoUrl)
            type = "image/*"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }, null)
        startActivity(shareMeme)
    }

    override fun onRefresh() {
        presenter.updateMemes()
    }

    override fun setRefresherState(refresherState: Boolean) {
        refresh_container.post { refresh_container.isRefreshing = refresherState }
    }

    override fun getActionBar() = (activity as AppCompatActivity).supportActionBar


}
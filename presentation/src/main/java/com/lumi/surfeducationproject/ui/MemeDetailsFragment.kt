package com.lumi.surfeducationproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.domain.model.Meme
import com.example.domain.model.User
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.base_view.BaseFragment
import com.lumi.surfeducationproject.common.managers.BottomBarVisible
import com.lumi.surfeducationproject.navigation.NavigationBackPressed

import com.lumi.surfeducationproject.presenters.MemeDetailsPresenter
import com.lumi.surfeducationproject.ui.extension.activity_extension.setColorStatusBar
import com.lumi.surfeducationproject.utils.getPostCreateDate
import com.lumi.surfeducationproject.views.MemeDetailsView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_meme_details.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class MemeDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var navBack: NavigationBackPressed

    @Inject
    lateinit var bottomBarVisible: BottomBarVisible

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().setColorStatusBar(R.color.colorPrimaryContent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meme_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        val meme = args.meme
        presenter.meme = meme

        presenter.bindMeme()
        presenter.bindUserInfoToolbar()
    }

    private fun initToolbar() {
        meme_details_toolbar.navigationIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close) }
        (activity as AppCompatActivity).setSupportActionBar(meme_details_toolbar)
        getActionBar()?.title = null
        meme_details_toolbar.setNavigationOnClickListener { navBack.back() }
    }


    override fun onStart() {
        super.onStart()
        bottomBarVisible.hideBottomNavigationBar()

    }

    override fun onStop() {
        super.onStop()
        bottomBarVisible.showBottomNavigationBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar_details_meme, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {
            presenter.shareMeme()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showErrorStateUserInfoToolbar() {
        nickname_mini_tv.text = getString(R.string.memeDetails_errorToolbarUser_message)
    }

    override fun showMeme(data: Meme) {
        title_meme_tv.text = data.title
        Glide.with(this).load(data.photoUrl).into(img_meme_iv)
        created_date_tv.text = getPostCreateDate(data.createdDate)
        if (data.isFavorite) {
            favorite_details_chb.isChecked = true
        }
        text_meme_tv.text = data.description
    }

    override fun getActionBar() = (activity as AppCompatActivity).supportActionBar

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

    override fun showUserInfoToolbar(user: User) {
        Glide.with(this)
            .load("https://img.pngio.com/avatar-1-length-of-human-face-hd-png-download-6648260-free-human-face-png-840_640.png")
            .optionalCircleCrop()
            .into(avatars_mini_iv)
        nickname_mini_tv.text = user.firstName
    }
}
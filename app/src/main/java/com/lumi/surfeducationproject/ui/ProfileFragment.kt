package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.SnackBarManager
import com.lumi.surfeducationproject.common.StyleManager
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.model.User
import com.lumi.surfeducationproject.navigation.NavigationAuth
import com.lumi.surfeducationproject.presenters.ProfilePresenter
import com.lumi.surfeducationproject.views.ProfileView
import kotlinx.android.synthetic.main.fragment_profile.view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class ProfileFragment : MvpAppCompatFragment(), ProfileView {


    @Inject
    lateinit var presenterProvider: Provider<ProfilePresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    @Inject
    lateinit var styleManager: StyleManager

    @Inject
    lateinit var snackBarManager: SnackBarManager

    @Inject
    lateinit var navLogout: NavigationAuth

    private lateinit var toolbar: Toolbar
    private lateinit var avatarIv: ImageView
    private lateinit var nicknameTv: TextView
    private lateinit var descriptionTv: TextView
    private lateinit var recycler: RecyclerView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.startFragmentComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        styleManager.setColorStatusBar(R.color.colorPrimary)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.toolbar_profile
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = ""

        avatarIv = view.avatars_iv
        nicknameTv = view.nickname_tv
        descriptionTv = view.descriptionProfile_tv

        recycler = view.memeList_profile_rv

        presenter.loadProfile()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onStart() {
        super.onStart()
        presenter.loadMemes()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                presenter.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showMemes(memeList: List<Meme>) {
        TODO("Not yet implemented")
    }

    override fun exitAccount() {
        navLogout.startAuthScreen()
    }

    override fun showProfile(user: User) {
        Glide.with(this)
            .load("https://img.pngio.com/avatar-1-length-of-human-face-hd-png-download-6648260-free-human-face-png-840_640.png")
            .circleCrop()
            .into(avatarIv)
        nicknameTv.text = user.firstName
        descriptionTv.text = user.descriptionProfile
    }

    override fun showErrorSnackBarDownloadProfile(message: String) {
        snackBarManager.showErrorMessage(message)
    }


    override fun onDetach() {
        super.onDetach()
        App.instance.clearFragmentComponent()
    }

}
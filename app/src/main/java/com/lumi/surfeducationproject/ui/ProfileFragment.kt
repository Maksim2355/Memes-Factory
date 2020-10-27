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
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.data.dto.network.NetworkUserResponse
import com.lumi.surfeducationproject.navigation.NavigationAuth
import com.lumi.surfeducationproject.presenters.ProfilePresenter
import com.lumi.surfeducationproject.views.ProfileView
import kotlinx.android.synthetic.main.fragment_profile.view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    private lateinit var toolbar: Toolbar

    private val presenter by moxyPresenter { ProfilePresenter() }

    private lateinit var avatarIv: ImageView
    private lateinit var nicknameTv: TextView
    private lateinit var descriptionTv: TextView

    private lateinit var recycler: RecyclerView

    private lateinit var navLogout: NavigationAuth

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navLogout = context as NavigationAuth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
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

        presenter.initProfile()
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

    override fun showMemes(networkMemes: List<NetworkMeme>) {
        //Todo Создается при создании БД
    }

    override fun exitAccount() {
        navLogout.startAuthScreen()
    }

    override fun showProfile(networkUserResponse: NetworkUserResponse) {
        Glide.with(this)
            .load("https://img.pngio.com/avatar-1-length-of-human-face-hd-png-download-6648260-free-human-face-png-840_640.png")
            .circleCrop()
            .into(avatarIv)
        nicknameTv.text = networkUserResponse.firstName
        descriptionTv.text = networkUserResponse.userDescription
    }

}
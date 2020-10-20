package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.lumi.surfeducationproject.R
import kotlinx.android.synthetic.main.fragment_profile.view.*
import moxy.MvpAppCompatFragment


class ProfileFragment : MvpAppCompatFragment() {

    private lateinit var toolbar: Toolbar

    private lateinit var avatarIv: ImageView
    private lateinit var nicknameTv: TextView
    private lateinit var descriptionTv: TextView

    private lateinit var recycler: RecyclerView

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
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.Key_Details_Meme
import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceServiceImpl
import com.lumi.surfeducationproject.utils.getPostCreateDate
import moxy.MvpAppCompatFragment


class MemeDetailsFragment : MvpAppCompatFragment() {

    private lateinit var toolbar: Toolbar

    private lateinit var memeTitleTv: TextView
    private lateinit var memeImgIv: ImageView
    private lateinit var dateCreateTv: TextView
    private lateinit var favoriteCheckBox: CheckBox
    private lateinit var descriptionTv: TextView

    private lateinit var avatarsMiniIv: ImageView
    private lateinit var nicknameMiniTv: TextView

    private lateinit var navBack: NavigationBackPressed

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navBack = context as NavigationBackPressed
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_meme_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar_meme_details)
        toolbar.navigationIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close)}
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        getActionBar()?.title = null
        toolbar.setNavigationOnClickListener { navBack.back() }


        memeTitleTv = view.findViewById(R.id.title_meme_tv)
        memeImgIv = view.findViewById(R.id.img_meme_iv)
        dateCreateTv = view.findViewById(R.id.created_date_tv)
        favoriteCheckBox = view.findViewById(R.id.favorite_detatils_checkBox)
        descriptionTv = view.findViewById(R.id.text_meme_tv)

        avatarsMiniIv = view.findViewById(R.id.avatars_mini_iv)
        nicknameMiniTv = view.findViewById(R.id.nickname_mini_tv)

        val user = SharedPreferenceServiceImpl.readUser()
        user?.let {
            Glide.with(this).load("https://img.pngio.com/avatar-1-length-of-human-face-hd-png-download-6648260-free-human-face-png-840_640.png")
                .optionalCircleCrop()
                .into(avatarsMiniIv)
            nicknameMiniTv.text = user.firstName
        }

        val meme = arguments?.getSerializable(Key_Details_Meme) as NetworkMeme
        meme.let {
            memeTitleTv.text = meme.title
            Glide.with(this).load(meme.photoUrl).into(memeImgIv)
            //Todo сделать преобразования даты
            dateCreateTv.text = getPostCreateDate(meme.createdDate)
            if (meme.isFavorite){
                favoriteCheckBox.isChecked = true
            }
            descriptionTv.text = meme.description
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar_details_meme, menu)
    }


    private fun getActionBar() = (activity as AppCompatActivity).supportActionBar

}
package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.Key_Details_Meme
import com.lumi.surfeducationproject.data.model.Meme
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.utils.getPostCreateDate
import kotlinx.android.synthetic.main.fragment_meme_details.view.*
import moxy.MvpAppCompatFragment


class MemeDetailsFragment : MvpAppCompatFragment() {

    private lateinit var toolbar: Toolbar

    private lateinit var memeTitleTv: TextView
    private lateinit var memeImgIv: ImageView
    private lateinit var dateCreateTv: TextView
    private lateinit var favoriteCheckBox: CheckBox
    private lateinit var descriptionTv: TextView
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
        toolbar.setNavigationIcon(R.drawable.ic_close)
        toolbar.setNavigationOnClickListener { navBack.back() }
        (activity as AppCompatActivity).setSupportActionBar(toolbar)


        memeTitleTv = view.findViewById(R.id.title_meme_tv)
        memeImgIv = view.findViewById(R.id.img_meme_iv)
        dateCreateTv = view.findViewById(R.id.created_date_tv)
        favoriteCheckBox = view.findViewById(R.id.favorite_detatils_checkBox)
        descriptionTv = view.findViewById(R.id.text_meme_tv)

        val meme = arguments?.getSerializable(Key_Details_Meme) as Meme
        meme.let {
            getActionBar()?.title = meme.title
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
        inflater.inflate(R.menu.menu_toolbar_details_meme, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun getActionBar() = (activity as AppCompatActivity).supportActionBar

}
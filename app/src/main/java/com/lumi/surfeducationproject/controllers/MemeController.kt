package com.lumi.surfeducationproject.controllers

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.domain.model.Meme
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class MemeController: BindableItemController<Meme, MemeController.Holder>() {

    var memeDetailsClickListener: ((Meme) -> Unit) = {}
    var shareClickListener: ((Meme) -> Unit) = {}

    override fun createViewHolder(parent: ViewGroup?) = Holder(parent)

    override fun getItemId(data: Meme) = data.id.hashCode().toString()

    inner class Holder(parent: ViewGroup?): BindableViewHolder<Meme>(
        parent,
        R.layout.list_item_mem
    ) {
        private val photoMeme: ImageView = itemView.findViewById(R.id.photo_meme_iv)
        private val nameMeme: TextView = itemView.findViewById(R.id.meme_name_tv)
        private val favoriteBtn: CheckBox = itemView.findViewById(R.id.favorite_chb)
        private val shareBtn: CheckBox = itemView.findViewById(R.id.share_btn)

        override fun bind(data: Meme) {
            Glide.with(itemView).load(data.photoUrl).into(photoMeme)
            nameMeme.text = data.title
            if (data.isFavorite){
                favoriteBtn.isChecked = true
            }
            shareBtn.setOnClickListener { shareClickListener(data) }
            itemView.setOnClickListener { memeDetailsClickListener(data) }
        }
    }
}
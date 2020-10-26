package com.lumi.surfeducationproject.controllers

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.data.model.Meme
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class MemeController: BindableItemController<Meme, MemeController.Holder>() {

    var memeDetailsClickListener: ((Meme) -> Unit) = {}
    var shareClickListener: ((Meme) -> Unit) = {}

    override fun createViewHolder(parent: ViewGroup?) = Holder(parent)

    override fun getItemId(data: Meme) = data.id.hashCode().toString()

    inner class Holder(parent: ViewGroup?): BindableViewHolder<Meme>(
        parent,
        R.layout.item_mem
    ) {

        private val photoMeme: ImageView = itemView.findViewById(R.id.photoMeme_iv)
        private val nameMeme: TextView = itemView.findViewById(R.id.memeName_tv)
        private val favoriteBtn: CheckBox = itemView.findViewById(R.id.favorite_btn)
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
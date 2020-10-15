package com.lumi.surfeducationproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.views.MemeFeedView
import moxy.MvpAppCompatFragment


class MemeFeedFragment : MvpAppCompatFragment(), MemeFeedView {

    private lateinit var memesRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meme_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun showMemes() {
        TODO("Not yet implemented")
    }

    override fun showErrorState() {
        TODO("Not yet implemented")
    }

    override fun showRefresh() {
        TODO("Not yet implemented")
    }

    override fun hideRefresh() {
        TODO("Not yet implemented")
    }

    override fun showLoadState() {
        TODO("Not yet implemented")
    }

    override fun hideLoadState() {
        TODO("Not yet implemented")
    }

    override fun showErrorSnackbar() {
        TODO("Not yet implemented")
    }
}
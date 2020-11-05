package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.SnackBarManager
import com.lumi.surfeducationproject.common.StyleManager
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.presenters.AddMemePresenter
import com.lumi.surfeducationproject.views.AddMemeView
import kotlinx.android.synthetic.main.fragment_add_meme.view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class AddMemeFragment : MvpAppCompatFragment(), AddMemeView, View.OnClickListener {

    private lateinit var toolbar: Toolbar
    private lateinit var createMemeBtnToolbar: Button
    private lateinit var inputTitleMemeEt: TextInputEditText
    private lateinit var inputDescriptionMemeEt: TextInputEditText
    private lateinit var memeView: FrameLayout
    private lateinit var closeBtnIbtn: ImageButton
    private lateinit var imgMemeIv: ImageView
    private lateinit var addImgIbtn: ImageButton

    @Inject
    lateinit var presenterProvider: Provider<AddMemePresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    @Inject
    lateinit var styleManager: StyleManager

    @Inject
    lateinit var snackBarManager: SnackBarManager

    @Inject
    lateinit var navBack: NavigationBackPressed

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.startFragmentComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        styleManager.setColorStatusBar(R.color.colorPrimaryContent)
        return inflater.inflate(R.layout.fragment_add_meme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initToolbar(view)
    }

    private fun initToolbar(view: View) {
        toolbar = view.add_meme_toolbar
        createMemeBtnToolbar = view.create_meme_btn
        toolbar.navigationIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close) }
        toolbar.setNavigationOnClickListener { navBack.back() }
    }

    private fun initView(view: View) {
        inputTitleMemeEt = view.input_title_meme_et
        inputDescriptionMemeEt = view.input_description_meme_et
        memeView = view.img_meme_container

        imgMemeIv = view.img_add_meme_iv
        closeBtnIbtn = view.img_close_ibtn
        closeBtnIbtn.setOnClickListener {
            presenter.closeImg()
        }
        addImgIbtn = view.add_img_ibtn
        addImgIbtn.setOnClickListener {
            showAddImg()
        }
    }


    override fun onDetach() {
        super.onDetach()
        App.instance.clearFragmentComponent()
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                R.id.img_close_ibtn -> {
                    closeImg()
                }
                R.id.create_meme_btn -> {

                }
                R.id.add_img_ibtn -> {

                }
            }
        }
    }

    private fun closeImg() {
        TODO("Not yet implemented")
    }

    override fun showAddImg() {
        TODO("Not yet implemented")
    }

    override fun hideAddImg() {
        TODO("Not yet implemented")
    }

    override fun disableCreateMemeBtn() {
        TODO("Not yet implemented")
    }

    override fun enableCreateMemeBtn() {
        TODO("Not yet implemented")
    }

    override fun showAddImgDialog() {
        TODO("Not yet implemented")
    }

}
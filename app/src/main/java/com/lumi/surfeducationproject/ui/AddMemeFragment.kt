package com.lumi.surfeducationproject.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.*
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.presenters.AddMemePresenter
import com.lumi.surfeducationproject.ui.dialogs.AddImgDialog
import com.lumi.surfeducationproject.views.AddMemeView
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.fragment_add_meme.view.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class AddMemeFragment : BaseFragment(), AddMemeView, View.OnClickListener {

    companion object {
        private val REQUEST_DIALOG_WAY_GET_IMG = 100
        private val REQUEST_CODE_CAMERA = 101
        private val REQUST_CODE_GALLERY = 102
    }

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
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
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
        addImgIbtn = view.add_img_ibtn
    }

    override fun onClick(v: View?) {
        v?.let {
            when (v.id) {
                R.id.img_close_ibtn -> presenter.deleteImg()
                R.id.create_meme_btn -> presenter.createMeme()
                R.id.add_img_ibtn -> presenter.addMeme()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //Создаем потоки, содержащие измененей полей
        presenter.observableTitleMeme = Observable.create { subscriber ->
            inputTitleMemeEt.addTextChangedListener(
                object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun afterTextChanged(s: Editable?){}

                    override fun onTextChanged(s: CharSequence?,
                                               start: Int,
                                               before: Int,
                                               count: Int) {
                        subscriber.onNext(inputTitleMemeEt.text.toString())
                    }
                }
            )
        }
        presenter.observableDescriptionMeme = Observable.create { subscriber ->
            inputDescriptionMemeEt.addTextChangedListener(
                object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun afterTextChanged(s: Editable?){}

                    override fun onTextChanged(s: CharSequence?,
                                               start: Int,
                                               before: Int,
                                               count: Int) {
                        subscriber.onNext(inputDescriptionMemeEt.text.toString())
                    }
                }
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_DIALOG_WAY_GET_IMG -> {
                    //Получаем выбор нашего пользователя из диалога
                }
                REQUEST_CODE_CAMERA -> {

                }
                REQUST_CODE_GALLERY -> {

                }
            }
        }
    }

    private fun onClickCloseImg() {
        TODO("Not yet implemented")
    }

    override fun showImg() {
        TODO("Not yet implemented")
    }

    override fun hideImg() {
        TODO("Not yet implemented")
    }

    override fun disableCreateMemeBtn() {
        TODO("Not yet implemented")
    }

    override fun enableCreateMemeBtn() {
        TODO("Not yet implemented")
    }

    override fun showAddImgDialog() {
        val addImgDialog = AddImgDialog()
        addImgDialog.setTargetFragment(this, REQUEST_DIALOG_WAY_GET_IMG)
        fragmentManager?.let { addImgDialog.show(it, EXTRA_WAY_GET_IMG) }
    }

    override fun disposeControl(): ControlDispose = presenter

    override fun getActionBar(): ActionBar? = (activity as AppCompatActivity).supportActionBar
}

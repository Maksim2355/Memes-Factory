package com.lumi.surfeducationproject.ui.custom_view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lumi.surfeducationproject.R
import kotlinx.android.synthetic.main.view_serach_toolbar.view.*


class ToolbarSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : Toolbar(context, attrs, defStyle) {

    private lateinit var toolbarSearch: Toolbar
    private lateinit var titleContainer: ConstraintLayout
    private lateinit var titleTv: TextView
    private lateinit var searchIBtn: ImageButton
    private lateinit var searchContainer: ConstraintLayout
    private lateinit var closeSearchIbtn: ImageButton
    private lateinit var inputTitleMemeTil: TextInputLayout
    private lateinit var inputTitleMemeEt: TextInputEditText
    private lateinit var clearSearchIbtn: ImageButton

    var onChangeSearchMode: OnChangeSearchModeListener? = null

    var onChangeSearchText: ((String) -> Unit)? = null

    interface OnChangeSearchModeListener{

        fun onStartSearch()

        fun onStopSearch()

    }

    private var isSearchMode = false

    init {
        inflate(context, R.layout.view_serach_toolbar, this)
        initView()
        val attr = context.theme.obtainStyledAttributes(
            attrs, R.styleable.ToolbarSearchView, 0, 0
        )
        //Берем из атрибутов title
        title = attr.getString(R.styleable.ToolbarSearchView_titleToolbar)
        titleTv.text = title
        attr.recycle()

        initLogicToolbar()

        initChangeSearchTextListener()
    }

    private fun initChangeSearchTextListener() {
        inputTitleMemeEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(e: Editable?) {
                onChangeSearchText?.invoke(inputTitleMemeEt.text.toString())
            }
        })
    }

    private fun initView() {
        toolbarSearch = search_toolbar
        titleContainer = title_container
        titleTv = title_search_tv
        searchIBtn = search_Ibtn
        searchContainer = search_container
        closeSearchIbtn = close_search_Ibtn
        clearSearchIbtn = clear_text_Ibtn
        inputTitleMemeTil = input_title_meme_Til
        inputTitleMemeEt = input_title_meme_et
    }

    private fun initLogicToolbar(){
        searchIBtn.setOnClickListener {
            openSearch()
            inputTitleMemeEt.requestFocus()
            onChangeSearchMode?.onStartSearch()
        }
        closeSearchIbtn.setOnClickListener {
            closeSearch()
            onChangeSearchMode?.onStopSearch()
        }
        clearSearchIbtn.setOnClickListener {
            if (inputTitleMemeEt.text.toString().isEmpty()) {
                closeSearch()
                onChangeSearchMode?.onStopSearch()
            } else {
                inputTitleMemeEt.text?.clear()
            }
        }
    }


    //Переключаемся на режим title в нашей view, очищаем поле для ввода и отписываем всех
    private fun closeSearch() {
        searchContainer.visibility = View.GONE
        titleContainer.visibility = View.VISIBLE
        inputTitleMemeEt.text?.clear()

        isSearchMode = false
    }

    //Переключаемся на режим title
    private fun openSearch() {
        title_container.visibility = View.GONE
        search_container.visibility = View.VISIBLE
        isSearchMode = true
    }

    fun enableSearchMode() {
        if (!isSearchMode) {
            openSearch()
        }
    }

    fun disableSearchMode() {
        if (isSearchMode) {
            closeSearch()
        }
    }

}
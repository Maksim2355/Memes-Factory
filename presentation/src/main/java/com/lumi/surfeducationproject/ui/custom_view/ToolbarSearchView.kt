package com.lumi.surfeducationproject.ui.custom_view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.databinding.ViewSerachToolbarBinding
import com.lumi.surfeducationproject.ui.extension.view_extension.onTextChanged
import com.lumi.surfeducationproject.utils.KeyboardUtil


class ToolbarSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : Toolbar(context, attrs, defStyle) {

    var onChangeSearchMode: OnChangeSearchModeListener? = null

    var onChangeSearchText: ((String) -> Unit)? = null

    private var binding: ViewSerachToolbarBinding = ViewSerachToolbarBinding.inflate(LayoutInflater.from(context))

    interface OnChangeSearchModeListener {
        fun onStartSearch()
        fun onStopSearch()
    }

    init {
        val attr = context.theme.obtainStyledAttributes(
            attrs, R.styleable.ToolbarSearchView, 0, 0
        )
        title = attr.getString(R.styleable.ToolbarSearchView_titleToolbar)
        binding.titleSearchTv.text = title
        attr.recycle()
        initLogicToolbar()
        initChangeSearchTextListener()
    }



    private fun initLogicToolbar() {
        binding.searchIbtn.setOnClickListener {
            onChangeSearchMode?.onStartSearch()
        }
        binding.closeSearchIbtn.setOnClickListener {
            onChangeSearchMode?.onStopSearch()
        }
        binding.clearTextIbtn.setOnClickListener {
            if (binding.inputTitleMemeEt.text.toString().isEmpty()) {
                onChangeSearchMode?.onStopSearch()
            } else {
                binding.inputTitleMemeEt.text?.clear()
            }
        }
    }

    fun clearSearchText(){
        binding.inputTitleMemeEt.text?.clear()
    }


    fun enableSearchMode() {
        openSearch()
    }

    fun disableSearchMode() {
        closeSearch()
    }

    private fun openSearch() {
        binding.titleContainer.visibility = View.GONE
        binding.searchContainer.visibility = View.VISIBLE
        binding.inputTitleMemeEt.requestFocus()
        KeyboardUtil.showKeyboard(binding.inputTitleMemeEt)
    }

    private fun closeSearch() {
        binding.searchContainer.visibility = View.GONE
        binding.titleContainer.visibility = View.VISIBLE
        KeyboardUtil.hideSoftKeyboard(this)
    }

    private fun initChangeSearchTextListener() {
        binding.inputTitleMemeEt.onTextChanged { s, start, before, count ->
            onChangeSearchText?.invoke(binding.inputTitleMemeEt.text.toString())
        }
    }

}
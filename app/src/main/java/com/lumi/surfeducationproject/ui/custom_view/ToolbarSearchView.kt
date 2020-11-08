package com.lumi.surfeducationproject.ui.custom_view

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.lumi.surfeducationproject.R
import kotlinx.android.synthetic.main.view_serach_toolbar.view.*


class ToolbarSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : Toolbar(context, attrs, defStyle) {

    val searchText: String = ""

    private var title: String? = ""
    private var textWatcher: TextWatcher? = null

    init {
        inflate(context, R.layout.view_serach_toolbar, this)
        search_btn.setOnClickListener { openSearch() }
        close_search_btn.setOnClickListener { closeSearch() }
        val attr = context.theme.obtainStyledAttributes(
            attrs, R.styleable.ToolbarSearchView, 0, 0
        )
        title = attr.getString(R.styleable.ToolbarSearchView_titleToolbar)
        title_search_text.text = title
        attr.recycle()
    }

    fun setSearchTextChangedListener(watcher: TextWatcher){
        textWatcher = watcher
        input_title_meme_et.addTextChangedListener(textWatcher)
    }

    private fun closeSearch() {
        search_container.visibility = View.GONE
        title_container.visibility = View.VISIBLE
        search_toolbar.title = title
        textWatcher?.let {
            input_title_meme_et.removeTextChangedListener(textWatcher)
        }
    }

    private fun openSearch() {
        title_container.visibility = View.GONE
        search_container.visibility = View.VISIBLE
        search_toolbar.title = null
        textWatcher?.let {
            input_title_meme_et.addTextChangedListener(textWatcher)
        }
    }


}
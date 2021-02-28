package com.lumi.surfeducationproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.domain.model.Meme
import com.example.domain.model.User
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.managers.BottomBarVisible
import com.lumi.surfeducationproject.common.params.EXTRA_MEME_ID
import com.lumi.surfeducationproject.databinding.FragmentMemeDetailsBinding
import com.lumi.surfeducationproject.di.injection_extension.injectViewModel
import com.lumi.surfeducationproject.navigation.NavigationBackPressed

import com.lumi.surfeducationproject.ui.extension.activity_extension.setColorStatusBar
import com.lumi.surfeducationproject.ui.extension.fragment_extensions.loadAvatars
import com.lumi.surfeducationproject.ui.extension.fragment_extensions.setToolbar
import com.lumi.surfeducationproject.vm.MemeDetailsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MemeDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var memeDetailsViewModel: MemeDetailsViewModel

    private lateinit var binding: FragmentMemeDetailsBinding

    @Inject
    lateinit var navBack: NavigationBackPressed

    @Inject
    lateinit var bottomBarVisible: BottomBarVisible

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().setColorStatusBar(R.color.colorPrimaryContent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMemeDetailsBinding.inflate(inflater, container, false)
        memeDetailsViewModel = injectViewModel(viewModelFactory)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        arguments?.getInt(EXTRA_MEME_ID).let {
            if (it != null) {
                memeDetailsViewModel.bindMeme(it)
            }
        }
        observeViews()

    }

    private fun initToolbar() {
        with(binding.memeDetailsToolbar) {
            navigationIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close) }
            setToolbar(this)
            setNavigationOnClickListener { memeDetailsViewModel.navigateBack() }
        }
    }

    private fun observeViews() {
        with(memeDetailsViewModel) {
            meme.observe(viewLifecycleOwner) { meme->
                binding.titleMemeTv.text = meme.title
                loadAvatars(binding.imgMemeIv, meme.photoUrl)
                binding.createdDateTv.setText(meme.createdDate)
                binding.favoriteDetailsChb.isChecked = meme.isFavorite
                binding.textMemeTv.text = meme.description
            }
            user.observe(viewLifecycleOwner) {
                it?.let { user ->
                    loadAvatars(binding.avatarsMiniIv)
                    binding.nicknameMiniTv.text = user.firstName
                }
            }
            shareMemeEvent.observe(viewLifecycleOwner) { memeEvent ->
                memeEvent.getContentIfNotHandled()?.let { meme ->
                    val shareMeme = Intent.createChooser(Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, meme.title)
                        putExtra(Intent.EXTRA_STREAM, meme.photoUrl)
                        type = "image/*"
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }, null)
                    startActivity(shareMeme)
                }
            }
            navigateBackstack.observe(viewLifecycleOwner) {
                navBack.back()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar_details_meme, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {
            memeDetailsViewModel.shareMeme()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        bottomBarVisible.hideBottomNavigationBar()
    }

    override fun onStop() {
        super.onStop()
        bottomBarVisible.showBottomNavigationBar()
    }
}
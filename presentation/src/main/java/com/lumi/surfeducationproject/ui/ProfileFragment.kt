package com.lumi.surfeducationproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.domain.model.Meme
import com.example.domain.model.User
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.databinding.FragmentProfileBinding
import com.lumi.surfeducationproject.di.injection_extension.injectViewModel
import com.lumi.surfeducationproject.di.named.FRAGMENT_CONTENT_NAVIGATION
import com.lumi.surfeducationproject.navigation.NavigationDestination
import com.lumi.surfeducationproject.ui.extension.activity_extension.setColorStatusBar
import com.lumi.surfeducationproject.vm.ProfileViewModel
import dagger.android.support.DaggerFragment
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject
import javax.inject.Named


class ProfileFragment : DaggerFragment() {

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var profileViewModel: ProfileViewModel

    @Inject
    @Named(FRAGMENT_CONTENT_NAVIGATION)
    lateinit var navigationDestination: NavigationDestination

    private var dialogLogout: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = injectViewModel(viewModelFactory)
        requireActivity().setColorStatusBar(R.color.colorPrimary)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(profile_toolbar)
        getActionBar()?.title = ""
    }

    private fun initView() {
        memeController.memeDetailsClickListener = { presenter.openDetails(it) }
        memeController.shareClickListener = {
            presenter.shareMemeInSocialNetwork(it)
        }
        with(meme_list_profile_rv) {
            adapter = easyAdapter
            layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
                2,
                androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
            )
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.bindProfile()
        presenter.loadMemes()
    }


    override fun onDestroy() {
        super.onDestroy()
        dialogLogout?.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                presenter.startLogoutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showMemes(memeList: List<Meme>) {
        val itemList = ItemList.create().apply {
            addAll(memeList, memeController)
        }
        easyAdapter.setItems(itemList)
    }

    override fun exitAccount() {
        navLogout.startAuthScreen()
    }

    override fun showProfile(user: User) {
        Glide.with(this)
            .load("https://img.pngio.com/avatar-1-length-of-human-face-hd-png-download-6648260-free-human-face-png-840_640.png")
            .circleCrop()
            .into(avatars_iv)
        nickname_tv.text = user.firstName
        description_profile_tv.text = user.descriptionProfile
    }

    override fun showDialog() {
        context?.let {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            dialogLogout = builder.setTitle(R.string.dialogLogout_exit_btn)
                .setCancelable(false)
                .setMessage("")
                .setPositiveButton(
                    R.string.dialogLogout_exitAccount_btn
                ) { dialog, _ ->
                    dialog.dismiss()
                    presenter.logout()
                }
                .setNegativeButton(
                    R.string.dialogLogout_cancel_btn
                ) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
            dialogLogout?.show()
        }
    }

    override fun openMemeDetails(data: Meme) {
        navMemeDetailsFragment.startMemeDetailsScreen(data)
    }

    override fun shareMeme(meme: Meme) {
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

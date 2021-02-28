package com.lumi.surfeducationproject.navigation.navigation

import android.os.Bundle
import com.lumi.surfeducationproject.R



data class Route(val actionRoute: ActionRoute, val bundle: Bundle? = null)

enum class ActionRoute(val action: Int) {
    SPLASH_FRAGMENT_TO_TAB_FRAGMENT(R.id.action_splashFragment_to_tabFragment),
    SPLASH_FRAGMENT_TO_AUTH_FRAGMENT(R.id.action_splashFragment_to_authFragment),
    AUTH_FRAGMENT_TO_TAB_FRAGMENT(R.id.action_authFragment_to_tabFragment),
    TAB_FRAGMENT_TO_AUTH_FRAGMENT(R.id.action_tabFragment_to_authFragment),
    MEME_FEED_FRAGMENT_TO_MEME_DETAILS(R.id.action_memeFeedFragment_to_memeDetailsFragment),
    PROFILE_FRAGMENT_TO_MEME_DETAILS(R.id.action_profileFragment_to_memeDetailsFragment),
}

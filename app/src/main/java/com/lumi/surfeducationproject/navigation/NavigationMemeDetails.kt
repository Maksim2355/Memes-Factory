package com.lumi.surfeducationproject.navigation

import com.lumi.surfeducationproject.data.dto.network.NetworkMeme

interface NavigationMemeDetails {

    fun startMemeDetailsScreen(networkMeme: NetworkMeme)

}
package com.lumi.surfeducationproject.common

interface PermissionManager {

    fun requestPermissionGallery(): Boolean

    fun requestPermissionCamera(): Boolean

}
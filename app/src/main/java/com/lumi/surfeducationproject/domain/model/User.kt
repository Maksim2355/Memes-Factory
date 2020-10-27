package com.lumi.surfeducationproject.domain.model

import com.google.gson.annotations.SerializedName


data class User(val id: Int,
                var username: String,
                var firstName: String,
                var lastName: String,
                var descriptionProfile: String )
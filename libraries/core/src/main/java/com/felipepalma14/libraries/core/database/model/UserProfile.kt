package com.felipepalma14.libraries.core.database.model
import com.google.firebase.firestore.PropertyName

data class UserProfile(var uId: String?=null,var createdAt: Long?=null,
                       var updatedAt: Long?=null,
                       var image: String="", var userName: String="",
                       var about: String="",
                       var token :String="",
                       var mobile: ModelMobile?=null,
                       @get:PropertyName("device_details")
                       @set:PropertyName("device_details")
                       var deviceDetails: ModelDeviceDetails?=null)
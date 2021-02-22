package com.felipepalma14.libraries.core.database.model

data class ModelDeviceDetails(var device_id: String?=null,var device_model: String?=null,
                              var device_brand: String?=null,var device_country: String?=null,
                              var device_os_v: String?=null,var app_version: String?=null,
                              var package_name: String?=null,var device_type: String?=null)
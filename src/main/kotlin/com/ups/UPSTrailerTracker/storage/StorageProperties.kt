package com.ups.UPSTrailerTracker.storage

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties
class StorageProperties {
    //The directory in which files are uploaded to
    var location: String = "./uploadDir"
}
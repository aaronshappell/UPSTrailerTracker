package com.ups.UPSTrailerTracker.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class StorageProperties {
    // The directory in which files are uploaded to
    private String location = "uploadDir";

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }
}

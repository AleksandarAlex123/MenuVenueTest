package com.aleksandar.menutest.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class VenueListApiResponse {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("is_open")
    private boolean isOpen;

    @SerializedName("welcome_message")
    private String welcomeMessage;

    @SerializedName("thumbnail_medium")
    private String thumbnailMedium;

    @SerializedName("distance")
    private String distance;

    @SerializedName("address")
    private String address;

    @SerializedName("date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getThumbnailMedium() {
        return thumbnailMedium;
    }

    public void setThumbnailMedium(String thumbnailMedium) {
        this.thumbnailMedium = thumbnailMedium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}

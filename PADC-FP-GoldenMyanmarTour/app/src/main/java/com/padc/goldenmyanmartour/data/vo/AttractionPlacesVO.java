package com.padc.goldenmyanmartour.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class AttractionPlacesVO {
    @SerializedName("place-id")
    public long placeId;
    @SerializedName("title")
    public String title;
    @SerializedName("image")
    public String[] image;
    @SerializedName("description")
    public String description;
    @SerializedName("note-to-visitor")
    public String noteToVisitor;

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoteToVisitor() {
        return noteToVisitor;
    }

    public void setNoteToVisitor(String noteToVisitor) {
        this.noteToVisitor = noteToVisitor;
    }
}

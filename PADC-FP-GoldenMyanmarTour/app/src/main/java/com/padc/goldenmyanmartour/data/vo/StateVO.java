package com.padc.goldenmyanmartour.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class StateVO {
    @SerializedName("state-id")
    private long stateId;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }
}

package ru.etysoft.dira.requests;

import com.google.gson.annotations.SerializedName;

public enum RequestType {
    @SerializedName("0")
    KEEPALIVE,
    @SerializedName("1")
    SUBSCRIBE_REQUEST
}

package com.test.vaishnavi.testwz.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("photographer")
    @Expose
    public String photographer;
    @SerializedName("photographer_url")
    @Expose
    public String photographerUrl;
    @SerializedName("photographer_id")
    @Expose
    public Integer photographerId;
    @SerializedName("src")
    @Expose
    public Src src;

}

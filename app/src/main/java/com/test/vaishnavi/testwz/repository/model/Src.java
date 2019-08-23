package com.test.vaishnavi.testwz.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Src {
    @SerializedName("original")
    @Expose
    public String original;
    @SerializedName("large2x")
    @Expose
    public String large2x;
    @SerializedName("large")
    @Expose
    public String large;
    @SerializedName("medium")
    @Expose
    public String medium;
    @SerializedName("small")
    @Expose
    public String small;
    @SerializedName("portrait")
    @Expose
    public String portrait;
    @SerializedName("landscape")
    @Expose
    public String landscape;
    @SerializedName("tiny")
    @Expose
    public String tiny;
}

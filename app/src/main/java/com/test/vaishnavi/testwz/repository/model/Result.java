package com.test.vaishnavi.testwz.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.vaishnavi.testwz.repository.model.Photos;

import java.util.List;

public class Result {
        @SerializedName("total_results")
        @Expose
        public Integer totalResults;
        @SerializedName("page")
        @Expose
        public Integer page;
        @SerializedName("per_page")
        @Expose
        public Integer perPage;
        @SerializedName("photos")
        @Expose
        public List<Photos> photos = null;
}

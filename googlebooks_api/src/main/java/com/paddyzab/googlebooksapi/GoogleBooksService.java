package com.paddyzab.googlebooksapi;

import com.paddyzab.googlebooksapi.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksService {

    @GET("volumes")
    Call<ItemsResponse> listItems(@Query("q") String query);
}

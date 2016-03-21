package com.paddyzab.googlebooksapi;

import com.paddyzab.googlebooksapi.models.Book;
import com.paddyzab.googlebooksapi.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoogleBooksService {

    @GET("volumes")
    Call<ItemsResponse> listItems(@Query("q") String query,
                                  @Query("startIndex") int startIndex,
                                  @Query("maxResults") int maxResults);

    @GET("volumes/{volumeId}")
    Call<Book> singleItemById(@Path("volumeId") String volumeId);
}

package com.paddyzab.googlebooksapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleBooksApiProvider {

    private final GoogleBooksService mBooksService;

    public GoogleBooksApiProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mBooksService = retrofit.create(GoogleBooksService.class);
    }

    public GoogleBooksService getBooksService() {
        return mBooksService;
    }
}

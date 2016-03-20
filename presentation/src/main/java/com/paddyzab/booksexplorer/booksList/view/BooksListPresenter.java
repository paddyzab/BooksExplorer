package com.paddyzab.booksexplorer.booksList.view;

import android.util.Log;

import com.paddyzab.booksexplorer.common.Presenter;
import com.paddyzab.googlebooksapi.GoogleBooksService;
import com.paddyzab.googlebooksapi.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksListPresenter implements Presenter {

    private final BooksListView mBooksListView;
    private final GoogleBooksService mGoogleBooksService;

    public BooksListPresenter(final BooksListView booksListView,
                              final GoogleBooksService googleBooksService) {
        mBooksListView = booksListView;
        mGoogleBooksService = googleBooksService;
    }

    @Override
    public void resume() {
        fetchItems(0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void openDetails() {
//        mBooksListView.openBookDetails();
    }

    public void fetchItems(int index) {
        Call<ItemsResponse> science = mGoogleBooksService.listItems("science", index, 40);
        science.enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(final Call<ItemsResponse> call, final Response<ItemsResponse>
                    response) {
               mBooksListView.populateItems(response.body().items);
            }

            @Override
            public void onFailure(final Call<ItemsResponse> call, final Throwable t) {
                Log.d(BooksListActivity.class.getSimpleName(), t.toString());
            }
        });
    }
}

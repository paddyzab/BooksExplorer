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
    private Call<ItemsResponse> mResponseCall;

    public BooksListPresenter(final BooksListView booksListView,
                              final GoogleBooksService googleBooksService) {
        mBooksListView = booksListView;
        mGoogleBooksService = googleBooksService;
    }

    @Override
    public void resume() {
        // nop
    }

    @Override
    public void pause() {
        // nop
    }

    @Override
    public void destroy() {
        mResponseCall.cancel();
    }

    public void openDetails(final String itemId) {
        mBooksListView.openBookDetails(itemId);
    }

    public void fetchItems(int index) {
        mResponseCall = mGoogleBooksService.listItems("science",
                index, 40);
        mResponseCall.enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(final Call<ItemsResponse> call, final Response<ItemsResponse>
                    response) {
                mBooksListView.populateItems(response.body().items);
            }

            @Override
            public void onFailure(final Call<ItemsResponse> call, final Throwable t) {
                Log.d(BooksListPresenter.class.getSimpleName(), t.toString());
            }
        });
    }
}

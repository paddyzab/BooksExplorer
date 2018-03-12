package com.paddyzab.booksexplorer.booksList.view;

import android.support.annotation.NonNull;
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

        Log.d(BooksListPresenter.class.getSimpleName(), mGoogleBooksService.getClass().toString());
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
            public void onResponse(@NonNull final Call<ItemsResponse> call,
                                   @NonNull final Response<ItemsResponse>
                    response) {
                Log.d(BooksListPresenter.class.getSimpleName(), response.toString());
                mBooksListView.populateItems(response.body().items);
            }

            @Override
            public void onFailure(@NonNull final Call<ItemsResponse> call,
                                  @NonNull final Throwable t) {
                mBooksListView.showError(t.getMessage());
            }
        });
    }
}

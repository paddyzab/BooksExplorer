package com.paddyzab.booksexplorer.booksDetails.view;

import com.paddyzab.booksexplorer.common.Presenter;
import com.paddyzab.googlebooksapi.GoogleBooksService;
import com.paddyzab.googlebooksapi.models.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsPresenter implements Presenter {

    private final BookDetailsView mDetailsView;
    private final GoogleBooksService mGoogleBooksService;
    private Call<Book> mResponseCall;

    public BookDetailsPresenter(final BookDetailsView detailsView, final GoogleBooksService
            googleBooksService) {
        mDetailsView = detailsView;
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

    public void fetchItem(final String itemId) {
        mResponseCall = mGoogleBooksService.singleItemById(itemId);
        mResponseCall.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(final Call<Book> call, final Response<Book>
                    response) {
                mDetailsView.populateView(response.body());
            }

            @Override
            public void onFailure(final Call<Book> call, final Throwable t) {
                mDetailsView.showError(t.getMessage());
            }
        });
    }
}

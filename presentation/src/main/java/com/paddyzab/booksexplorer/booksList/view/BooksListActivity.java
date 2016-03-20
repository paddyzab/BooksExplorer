package com.paddyzab.booksexplorer.booksList.view;

import android.net.LinkAddress;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.booksexplorer.booksList.di.BooksListModule;
import com.paddyzab.booksexplorer.common.BookExplorerApplication;
import com.paddyzab.booksexplorer.common.InjectingActivity;
import com.paddyzab.booksexplorer.common.Intents;
import com.paddyzab.googlebooksapi.GoogleBooksService;
import com.paddyzab.googlebooksapi.models.ItemsResponse;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksListActivity extends InjectingActivity implements BooksListView {

    @Inject
    protected Intents mIntents;

    @Inject
    protected BooksListPresenter mBooksListPresenter;

    @Inject
    protected GoogleBooksService mGoogleBooksService;

    @Inject
    protected BooksAdapter mBooksAdapter;

    @Bind(R.id.recyclerViewBooks)
    protected RecyclerView mRecyclerViewBooks;

    @OnClick(R.id.buttonDetails)
    protected void openDetails() {
        mBooksListPresenter.openDetails();
        fetchItems(0);
    }

    private void fetchItems(int index) {
        Call<ItemsResponse> science = mGoogleBooksService.listItems("science", index, 40);
        science.enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(final Call<ItemsResponse> call, final Response<ItemsResponse>
                    response) {
                mBooksAdapter.setBookItems(response.body().items);
            }

            @Override
            public void onFailure(final Call<ItemsResponse> call, final Throwable t) {
                Log.d(BooksListActivity.class.getSimpleName(), t.toString());
            }
        });
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        ButterKnife.bind(this);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewBooks.setHasFixedSize(true);
        mRecyclerViewBooks.setLayoutManager(layoutManager);
        mRecyclerViewBooks.setAdapter(mBooksAdapter);
    }

    @Override
    public void openBookDetails() {
        mIntents.startBookDetailsActivity(this, "bookId");
    }

    @Override
    protected void setupActivityComponent() {
        BookExplorerApplication.get(this)
                .getAppComponent()
                .plus(new BooksListModule(this))
                .inject(this);
    }
}

package com.paddyzab.booksexplorer.booksList.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.booksexplorer.booksList.di.BooksListModule;
import com.paddyzab.booksexplorer.common.BookExplorerApplication;
import com.paddyzab.booksexplorer.common.InjectingActivity;
import com.paddyzab.booksexplorer.common.Intents;
import com.paddyzab.booksexplorer.common.views.EndlessRecyclerViewScrollListener;
import com.paddyzab.googlebooksapi.models.Book;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BooksListActivity extends InjectingActivity implements BooksListView {

    @Inject
    protected Intents mIntents;

    @Inject
    protected BooksListPresenter mBooksListPresenter;

    @Inject
    protected BooksAdapter mBooksAdapter;

    @Bind(R.id.recyclerViewBooks)
    protected RecyclerView mRecyclerViewBooks;

    @OnClick(R.id.buttonDetails)
    protected void openDetails() {
        mBooksListPresenter.openDetails();
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        ButterKnife.bind(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewBooks.setHasFixedSize(true);
        mRecyclerViewBooks.setLayoutManager(layoutManager);
        mRecyclerViewBooks.setAdapter(mBooksAdapter);
        mRecyclerViewBooks.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(final int page, final int totalItemsCount) {
                mBooksListPresenter.fetchItems(mBooksAdapter.getItemCount());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBooksListPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBooksListPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBooksListPresenter.destroy();
    }

    @Override
    public void populateItems(Book[] books) {
        mBooksAdapter.setBookItems(books);
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

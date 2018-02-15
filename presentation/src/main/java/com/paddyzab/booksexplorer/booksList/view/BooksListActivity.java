package com.paddyzab.booksexplorer.booksList.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.booksexplorer.booksList.di.BooksListModule;
import com.paddyzab.booksexplorer.common.BookExplorerApplication;
import com.paddyzab.booksexplorer.common.InjectingActivity;
import com.paddyzab.booksexplorer.common.Intents;
import com.paddyzab.booksexplorer.common.views.EndlessRecyclerViewScrollListener;
import com.paddyzab.googlebooksapi.models.Book;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BooksListActivity extends InjectingActivity implements BooksListView, BooksAdapter
        .OnCardClickedListener {

    @Inject
    protected Intents mIntents;

    @Inject
    protected BooksListPresenter mBooksListPresenter;

    @Inject
    protected BooksAdapter mBooksAdapter;

    @BindView(R.id.recyclerViewBooks)
    protected RecyclerView mRecyclerViewBooks;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        ButterKnife.bind(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewBooks.setHasFixedSize(true);
        mRecyclerViewBooks.setLayoutManager(layoutManager);
        mRecyclerViewBooks.setAdapter(mBooksAdapter);
        mRecyclerViewBooks.addOnScrollListener(new EndlessRecyclerViewScrollListener
                (layoutManager) {
            @Override
            public void onLoadMore(final int page, final int totalItemsCount) {
                mBooksListPresenter.fetchItems(mBooksAdapter.getItemCount());
            }
        });
        mBooksListPresenter.fetchItems(0);
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
    public void onCardClicked(String itemId) {
        mBooksListPresenter.openDetails(itemId);
    }

    @Override
    public void openBookDetails(final String itemId) {
        mIntents.startBookDetailsActivity(this, itemId);
    }

    @Override
    public void showError(final String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void setupActivityComponent() {
        BookExplorerApplication.get(this)
                .getAppComponent()
                .plus(new BooksListModule(this))
                .inject(this);
    }
}

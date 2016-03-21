package com.paddyzab.booksexplorer.booksDetails.view;

import android.os.Bundle;
import android.util.Log;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.booksexplorer.booksDetails.di.BookDetailsModule;
import com.paddyzab.booksexplorer.common.BookExplorerApplication;
import com.paddyzab.booksexplorer.common.InjectingActivity;
import com.paddyzab.booksexplorer.common.Intents;
import com.paddyzab.googlebooksapi.models.Book;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BookDetailsActivity extends InjectingActivity implements BookDetailsView {

    @Inject
    protected BookDetailsPresenter mBookDetailsPresenter;

    private String mBookId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_book_details);

        final Bundle bundleExtra = getIntent().getBundleExtra(Intents.BOOK_DETAILS_ARGS);
        mBookId = bundleExtra.getString(Intents.BOOK_ID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBookDetailsPresenter.fetchItem(mBookId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mBookDetailsPresenter.destroy();
    }

    @Override
    public void populateView(final Book item) {
        Log.d(BookDetailsActivity.class.getSimpleName(), item.toString());
    }

    @Override
    protected void setupActivityComponent() {
        BookExplorerApplication.get(this)
                .getAppComponent()
                .plus(new BookDetailsModule(this))
                .inject(this);
    }
}

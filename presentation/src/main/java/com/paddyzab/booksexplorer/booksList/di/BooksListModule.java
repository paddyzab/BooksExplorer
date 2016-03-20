package com.paddyzab.booksexplorer.booksList.di;

import com.paddyzab.booksexplorer.booksList.view.BooksAdapter;
import com.paddyzab.booksexplorer.booksList.view.BooksListActivity;
import com.paddyzab.booksexplorer.booksList.view.BooksListPresenter;
import com.paddyzab.googlebooksapi.GoogleBooksService;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BooksListModule {

    private final BooksListActivity mBooksListActivity;

    public BooksListModule(BooksListActivity booksListActivity) {
        mBooksListActivity = booksListActivity;
    }

    @Provides
    BooksListActivity provideBooksListActivity() {
        return mBooksListActivity;
    }

    @Provides
    BooksListPresenter provideBookListPresenter(final BooksListActivity booksListActivity,
                                                final GoogleBooksService googleBooksService) {
        return new BooksListPresenter(booksListActivity, googleBooksService);
    }

    @Provides
    BooksAdapter providesBooksAdapter(final BooksListActivity booksListActivity) {
        return new BooksAdapter(booksListActivity);
    }
}

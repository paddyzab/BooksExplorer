package com.paddyzab.booksexplorer.booksList.di;

import com.paddyzab.booksexplorer.booksList.view.BooksAdapter;
import com.paddyzab.booksexplorer.booksList.view.BooksListActivity;
import com.paddyzab.booksexplorer.booksList.view.BooksListPresenter;

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
    BooksListPresenter provideBookListPresenter() {
        return new BooksListPresenter(mBooksListActivity);
    }

    @Provides
    BooksAdapter providesBooksAdapter() {
        return new BooksAdapter();
    }
}

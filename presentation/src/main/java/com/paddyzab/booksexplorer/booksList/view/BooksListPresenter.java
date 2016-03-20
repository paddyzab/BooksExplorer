package com.paddyzab.booksexplorer.booksList.view;

import com.paddyzab.booksexplorer.common.Presenter;

public class BooksListPresenter implements Presenter {

    private final BooksListView mBooksListView;

    public BooksListPresenter(BooksListView booksListView) {
        mBooksListView = booksListView;
    }

    @Override
    public void resume() {

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
}

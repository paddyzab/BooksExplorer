package com.paddyzab.booksexplorer.booksList.view;

import android.os.Bundle;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.booksexplorer.common.InjectingActivity;

import butterknife.ButterKnife;

public class BooksListActivity extends InjectingActivity implements BooksListView {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_books_list);
    }

    @Override
    protected void setupActivityComponent() {

    }
}

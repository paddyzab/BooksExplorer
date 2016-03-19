package com.paddyzab.booksexplorer.booksDetails.view;

import android.os.Bundle;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.booksexplorer.common.InjectingActivity;

import butterknife.ButterKnife;

public class BookDetailsActivity extends InjectingActivity implements BookDetailsView {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_book_details);
    }

    @Override
    protected void setupActivityComponent() {

    }
}

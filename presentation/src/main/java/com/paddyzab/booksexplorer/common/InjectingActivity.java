package com.paddyzab.booksexplorer.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class InjectingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();
}

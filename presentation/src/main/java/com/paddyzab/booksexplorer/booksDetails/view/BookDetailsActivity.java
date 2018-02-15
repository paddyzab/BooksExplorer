package com.paddyzab.booksexplorer.booksDetails.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.booksexplorer.booksDetails.di.BookDetailsModule;
import com.paddyzab.booksexplorer.common.BookExplorerApplication;
import com.paddyzab.booksexplorer.common.InjectingActivity;
import com.paddyzab.booksexplorer.common.Intents;
import com.paddyzab.googlebooksapi.models.Book;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailsActivity extends InjectingActivity implements BookDetailsView {

    @Inject
    protected BookDetailsPresenter mBookDetailsPresenter;

    private String mBookId;

    @BindView(R.id.imageViewThumbnail)
    protected ImageView mImageViewThumbnail;

    @BindView(R.id.textViewTitle)
    protected TextView mTextViewTitle;

    @BindView(R.id.textViewAuthor)
    protected TextView mTextViewAuthor;

    @BindView(R.id.textViewPublisher)
    protected TextView mTextViewPublisher;

    @BindView(R.id.textViewPublishDate)
    protected TextView mTextViewPublisherDate;

    @BindView(R.id.textViewDescription)
    protected TextView mTextViewDescription;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        ButterKnife.bind(this);

        final Bundle bundleExtra = getIntent().getBundleExtra(Intents.BOOK_DETAILS_ARGS);
        mBookId = bundleExtra.getString(Intents.BOOK_ID);
        mBookDetailsPresenter.fetchItem(mBookId);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookDetailsPresenter.destroy();
    }

    @Override
    public void populateView(final Book item) {
        Picasso.with(this).load(item.volumeInfo.imageLinks.thumbnail).into(mImageViewThumbnail);
        populateAuthor(item, mTextViewAuthor);
        mTextViewTitle.setText(item.volumeInfo.title);
        mTextViewPublisher.setText(item.volumeInfo.publisher);
        mTextViewPublisherDate.setText(item.volumeInfo.publishedDate);
        mTextViewDescription.setText(item.volumeInfo.description);
    }

    @Override
    public void showError(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void setupActivityComponent() {
        BookExplorerApplication.get(this)
                .getAppComponent()
                .plus(new BookDetailsModule(this))
                .inject(this);
    }

    private void populateAuthor(final Book bookItem, final TextView textView) {
        if (bookItem.volumeInfo.authors != null) {
            textView.setText(bookItem.volumeInfo.authors[0]);
            textView.setTextColor(Color.GRAY);
        } else {
            textView.setText("No author data available.");
            textView.setTextColor(Color.RED);
        }
    }
}

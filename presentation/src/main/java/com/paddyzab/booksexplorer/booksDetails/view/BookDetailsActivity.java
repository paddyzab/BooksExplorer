package com.paddyzab.booksexplorer.booksDetails.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.booksexplorer.booksDetails.di.BookDetailsModule;
import com.paddyzab.booksexplorer.common.BookExplorerApplication;
import com.paddyzab.booksexplorer.common.InjectingActivity;
import com.paddyzab.booksexplorer.common.Intents;
import com.paddyzab.googlebooksapi.models.Book;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookDetailsActivity extends InjectingActivity implements BookDetailsView {

    @Inject
    protected BookDetailsPresenter mBookDetailsPresenter;

    private String mBookId;

    @Bind(R.id.imageViewThumbnail)
    protected ImageView mImageViewThumbnail;

    @Bind(R.id.textViewTitle)
    protected TextView mTextViewTitle;

    @Bind(R.id.textViewAuthor)
    protected TextView mTextViewAuthor;

    @Bind(R.id.textViewPublisher)
    protected TextView mTextViewPublisher;

    @Bind(R.id.textViewPublishDate)
    protected TextView mTextViewPublisherDate;

    @Bind(R.id.textViewDescription)
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
        mTextViewTitle.setText(item.volumeInfo.title);
        mTextViewAuthor.setText(item.volumeInfo.authors[0]);
        mTextViewPublisher.setText(item.volumeInfo.publisher);
        mTextViewPublisherDate.setText(item.volumeInfo.publishedDate);
        mTextViewDescription.setText(item.volumeInfo.description);
    }

    @Override
    protected void setupActivityComponent() {
        BookExplorerApplication.get(this)
                .getAppComponent()
                .plus(new BookDetailsModule(this))
                .inject(this);
    }
}

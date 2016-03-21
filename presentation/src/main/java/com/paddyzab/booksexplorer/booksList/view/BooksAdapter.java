package com.paddyzab.booksexplorer.booksList.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paddyzab.booksexplorer.R;
import com.paddyzab.googlebooksapi.models.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

    private List<Book> mBooks;
    private Context mContext;
    private OnCardClickedListener mCardClickedListener;

    public BooksAdapter(OnCardClickedListener cardClickedListener) {
        mBooks = new ArrayList<>();
        mCardClickedListener = cardClickedListener;
    }

    public void setBookItems(Book[] books) {
        mBooks.addAll(Arrays.asList(books));
        notifyDataSetChanged();

        for (Book book : books) {
            Log.d(BooksAdapter.class.getSimpleName(), book.toString());
        }
    }

    @Override
    public BooksViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        mContext = parent.getContext();

        final View adapterView = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_booklist, parent, false);

        return new BooksViewHolder(adapterView);
    }

    @Override
    public void onBindViewHolder(final BooksViewHolder holder, final int position) {
        final Book bookItem = mBooks.get(position);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mCardClickedListener.onCardClicked(bookItem.id);
            }
        });
        holder.title.setText(bookItem.volumeInfo.title);
        holder.publishDate.setText(bookItem.volumeInfo.publishedDate);
        holder.publisher.setText(bookItem.volumeInfo.publisher);
        populateAuthor(bookItem, holder.author);
        loadThumbnail(bookItem, holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    private void populateAuthor(final Book bookItem, final TextView author) {
        if (bookItem.volumeInfo.authors != null) {
            author.setText(bookItem.volumeInfo.authors[0]);
            author.setTextColor(Color.GRAY);
        } else {
            author.setText("No author data available.");
            author.setTextColor(Color.RED);
        }
    }

    private void loadThumbnail(final Book bookItem, final ImageView thumbnail) {
        if (bookItem.volumeInfo.imageLinks != null) {
            Picasso.with(mContext).load(bookItem.volumeInfo.imageLinks.thumbnail).into(thumbnail);
        } else {
            thumbnail.setImageBitmap(Bitmap.createBitmap(120, 120, Bitmap.Config.ARGB_8888));
        }
    }

    public static class BooksViewHolder extends RecyclerView.ViewHolder {

        final CardView card;
        final TextView title;
        final TextView author;
        final TextView publisher;
        final TextView publishDate;
        final ImageView thumbnail;

        public BooksViewHolder(View adapterView) {
            super(adapterView);

            card = (CardView) adapterView.findViewById(R.id.cardViewBook);
            title = (TextView) adapterView.findViewById(R.id.textViewTitle);
            author = (TextView) adapterView.findViewById(R.id.textViewAuthor);
            publisher = (TextView) adapterView.findViewById(R.id.textViewPublisher);
            publishDate = (TextView) adapterView.findViewById(R.id.textViewPublishDate);
            thumbnail = (ImageView) adapterView.findViewById(R.id.imageViewThumbnail);
        }
    }

    public interface OnCardClickedListener {
        void onCardClicked(String itemId);
    }
}

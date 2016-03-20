package com.paddyzab.googlebooksapi.models;

public class ImageLinks {

    public final String smallThumbnail;
    public final String thumbnail;

    public ImageLinks(final String smallThumbnail, final String thumbnail) {
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "ImageLinks{" +
                "smallThumbnail='" + smallThumbnail + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}

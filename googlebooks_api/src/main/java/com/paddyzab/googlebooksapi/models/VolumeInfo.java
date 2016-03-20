package com.paddyzab.googlebooksapi.models;

import java.util.Arrays;

public class VolumeInfo {

    public final String title;
    public final String subtitle;
    public final String[] authors;
    public final String description;
    public final ImageLinks imageLinks;
    public final String previewLink;
    public final String infoLink;
    public final String publisher;
    public final String publishedDate;

    public VolumeInfo(final String title, final String subtitle, final String[] authors,
                      final String description, final ImageLinks imageLinks,
                      final String previewLink, final String infoLink, final String publisher,
                      final String publishedDate) {
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.description = description;
        this.imageLinks = imageLinks;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", description='" + description + '\'' +
                ", imageLinks=" + imageLinks +
                ", previewLink='" + previewLink + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                '}';
    }
}

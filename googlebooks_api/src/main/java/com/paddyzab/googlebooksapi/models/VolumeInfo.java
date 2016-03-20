package com.paddyzab.googlebooksapi.models;

import java.util.Arrays;

public class VolumeInfo {

    public final String title;
    public final String subtitle;
    public final String[] authors;

    public VolumeInfo(final String title, final String subtitle, final String[] authors) {
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors=" + Arrays.toString(authors) +
                '}';
    }
}

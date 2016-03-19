package com.paddyzab.booksexplorer.booksDetails.di;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(
        modules = {
                BookDetailsModule.class
        }
)
public interface BookDetailsComponent {
}

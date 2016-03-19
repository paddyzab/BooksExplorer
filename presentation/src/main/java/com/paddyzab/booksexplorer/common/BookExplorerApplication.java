package com.paddyzab.booksexplorer.common;

import android.app.Application;
import android.content.Context;

public class BookExplorerApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initialiseMainComponent();
    }

    public static BookExplorerApplication get(Context context) {
        return (BookExplorerApplication) context.getApplicationContext();
    }

    private void initialiseMainComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}

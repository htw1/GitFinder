package com.htw.githubrepofinder;
import android.app.Application;
import com.htw.githubrepofinder.di.components.AppComponent;
import com.htw.githubrepofinder.di.components.DaggerAppComponent;
import com.htw.githubrepofinder.di.module.AppModule;

public class MainApplication extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

}

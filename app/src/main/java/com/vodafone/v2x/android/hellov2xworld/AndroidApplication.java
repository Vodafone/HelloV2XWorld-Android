package com.vodafone.v2x.android.hellov2xworld;


import android.app.Application;
import android.content.Context;
import timber.log.Timber;

/**
 This class is the Application class for the Android Application.
 It extends the Android {@link Application} class and provides a static method to get the {@link Context} of the application.
 It initializes the logging using Timber library and logs various lifecycle methods of the Application class.
 */
public class AndroidApplication extends Application {

    private static AndroidApplication instance;
    /**
     Static method to get the Context of the Application
     @return Context of the Application
     */
    public static Context getContext() {
        return instance.getApplicationContext();
    }


    /**
     This method is called when the application is created and it is used to
     initialize the logging library, Timber, and to set the instance of this class.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
        Timber.d("Lifecycle : onCreate()");
    }

    /**
     This method is called when the application is terminated.
     It sets the instance of this class to null.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
        Timber.d("Lifecycle : onTerminate()");
    }

    /**
     This method is called when the system is running low on memory.
     It logs a message about the memory level.
     @param level the level of memory in the system.
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Timber.d("Lifecycle : onTrimMemory()");
    }
}

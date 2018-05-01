package org.codefordenver.encorelink;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseAppOnStartConfiguration extends android.app.Application {


    /**
     * This class extends Application and is required in order to enable Firebase persistence
     * throughout the application. This allows a few things. First, if the user is logged in
     * they won't need to log in again, instead they will be brought to the appropriate activity.
     * Second, this has to exist here in order for the user to log out. Third, the user will
     * be able to still view information that has been cached on their device if there is any
     * disconnects from either data or wifi.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}

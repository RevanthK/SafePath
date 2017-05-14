package com.example.revanthkorrapolu.yournotmydad;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceTransitionsIntentService extends IntentService {
    public GeofenceTransitionsIntentService(){
        super("hjhgj");
    }
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {

            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            List triggeringGeofences = geofencingEvent.getTriggeringGeofences();
           for(int i=0;i<triggeringGeofences.size();i++){
               Geofence geofence=(Geofence)triggeringGeofences.get(i);

               PubNubClient.text("6096491171","Your son just entered a dangerous area. Please text him to make sure he is ok.");
               PubNubClient.text("7326628487","You just entered a dangerous area. Guide yourself to a safe location using the app.");

           }
            // Send notification and log the transition details.

        } else {
            // Log the error.
            Log.e("hello", "geofence_transition_invalid_type");
        }
    }

    /*private sendNotification(){
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(this).setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                .setContentText("bhjgghfh")
                .setContentText("hjgjg").build();

// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(mId, mBuilder.build());

    }*/
}
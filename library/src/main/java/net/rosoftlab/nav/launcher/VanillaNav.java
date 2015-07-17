package net.rosoftlab.nav.launcher;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import net.rosoftlab.nav.ui.navigation.IntentFactory;

/**
 * Created by Adi Pascu on 7/17/2015.
 * Email mail@adipascu.ro
 */
public class VanillaNav {

    public static int meaningOfLife() {
        return 42;
    }

    public static void navigate(@NonNull Activity activity, long venueId, long destinationId) {
        Intent intent = IntentFactory.destinationIntent(venueId, destinationId);
        activity.startActivity(intent);
    }
}

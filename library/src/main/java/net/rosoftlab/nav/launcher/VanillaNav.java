package net.rosoftlab.nav.launcher;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import net.rosoftlab.nav.ui.navigation.IntentFactory;

/**
 * Created by Adi Pascu on 7/17/2015.
 * Email mail@adipascu.ro
 */
public class VanillaNav {

    public static void navigate(@NonNull Context context, long venueId, long destinationId) {
        try {
            Intent intent = IntentFactory.destinationIntent(venueId, destinationId);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = IntentFactory.marketIntent();
            context.startActivity(intent);
        }

    }
}

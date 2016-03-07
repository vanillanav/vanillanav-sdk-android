package net.rosoftlab.nav.loader;

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

    /**
     * Show the navigation screen for a location or event
     *
     * @param context     used to call {@link Context#startActivity(Intent)}
     * @param venueId     from the <a href="http://www.vanillanav.com/admin">admin panel</a>
     * @param referenceId used to reference a location or event, this field is editable in the <a href="http://www.vanillanav.com/admin">admin panel</a>
     */
    public static boolean navigate(@NonNull Context context, long venueId, long referenceId) {
        return navigate(context, venueId, referenceId, false);
    }

    private static boolean navigate(@NonNull Context context, long venueId, long destinationId, boolean showDetails) {
        if (showDetails)
            throw new UnsupportedOperationException();
        try {
            Intent intent = IntentFactory.destinationIntent(venueId, destinationId);
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException | IllegalArgumentException e) {
            Intent intent = IntentFactory.marketIntent();
            context.startActivity(intent);
        } catch (SecurityException e) {
            //invalid VN Version
        }
        return false;
    }
}

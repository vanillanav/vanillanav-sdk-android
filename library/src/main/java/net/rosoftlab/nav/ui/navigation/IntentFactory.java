package net.rosoftlab.nav.ui.navigation;

import android.content.Intent;
import android.net.Uri;

import net.rosoftlab.nav.launcher.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adi Pascu on 7/17/2015.
 * Email mail@adipascu.ro
 */
public class IntentFactory {
    protected static final String VENUE_ID = "venueId";
    protected static final String EVENT_ID = "eventId";
    protected static final String DESTINATION_ID = "destinationId";
    private static final String NAV_ACTIVITY = "net.rosoftlab.nav.ui.navigation.NavigationActivity";
    private static final String APP_ID = "net.rosoftlab.nav";
    private static final Uri MARKET_URI;

    static {
        Uri baseUri = Uri.parse("market://details?id=" + APP_ID);
        JSONObject x = new JSONObject();
        try {
            x.put("appId", BuildConfig.APPLICATION_ID);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        MARKET_URI = baseUri.buildUpon().appendQueryParameter("referrer", x.toString()).build();
    }

    private static Intent baseIntent(long venueId) {
        Intent intent = new Intent();
        intent.setClassName(APP_ID, NAV_ACTIVITY);
        intent.putExtra(VENUE_ID, venueId);
        return intent;
    }

    public static Intent destinationIntent(long venueId, long destinationId) {
        Intent intent = baseIntent(venueId);
        intent.putExtra(DESTINATION_ID, destinationId);
        return intent;
    }

    public static Intent eventIntent(long venueId, long eventId) {
        Intent intent = baseIntent(venueId);
        intent.putExtra(EVENT_ID, eventId);
        return intent;
    }

    public static Intent marketIntent() {
        return new Intent(Intent.ACTION_VIEW, MARKET_URI);
    }
}

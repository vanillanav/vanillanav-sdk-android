package net.rosoftlab.nav.ui.navigation;

import android.content.Intent;

/**
 * Created by Adi Pascu on 7/17/2015.
 * Email mail@adipascu.ro
 */
public class IntentFactory {
    protected static final String VENUE_ID = "venueId";
    protected static final String EVENT_ID = "eventId";
    protected static final String DESTINATION_ID = "destinationId";
    private static final String NAV_ACTIVITY = "net.rosoftlab.nav.ui.navigation.NavigationActivity";

    private static Intent baseIntent(long venueId) {
        Intent intent = new Intent();
        intent.setClassName(Referrer.APP_ID, NAV_ACTIVITY);
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
        return new Intent(Intent.ACTION_VIEW, Referrer.MARKET_URI);
    }
}

package net.rosoftlab.nav.ui.navigation;

import android.net.Uri;

import net.rosoftlab.nav.loader.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adi Pascu on 7/20/2015.
 * Email mail@adipascu.ro
 */
public class Referrer {
    static final Uri MARKET_URI;
    static final String APP_ID = "net.rosoftlab.nav";

    static {
        Uri baseUri = Uri.parse("market://details?id=" + APP_ID);
        MARKET_URI = baseUri.buildUpon().appendQueryParameter("referrer", Referrer.getParameter()).build();
    }

    private static String getParameter() {
        return "extra_data=" + Uri.encode(getExtraData());
    }

    private static String getExtraData() {
        JSONObject jsonObs = new JSONObject();
        try {
            jsonObs.put("appId", BuildConfig.APPLICATION_ID);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObs.toString();
    }
}

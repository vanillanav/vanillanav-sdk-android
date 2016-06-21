package net.rosoftlab.nav.sdk.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import net.rosoftlab.nav.sdk.VanillaNav;

public class CustomActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_activity);
        findViewById(R.id.navigate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VanillaNav.navigate(CustomActivity.this, 3870, 8);
            }
        });
    }
}

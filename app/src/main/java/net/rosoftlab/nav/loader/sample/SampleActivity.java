package net.rosoftlab.nav.loader.sample;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by Adi Pascu on 7/17/2015.
 * Email mail@adipascu.ro
 */
public class SampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity);
        ButterKnife.bind(this);
    }

//    @OnClick(R.id.navigate)
//    void nav() {
//        VanillaNav.navigate(this, 3870, 8);
//    }
}

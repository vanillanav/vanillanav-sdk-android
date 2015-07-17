package net.rosoftlab.vanillanavlauncher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import net.rosoftlab.nav.launcher.SampleTest;
import net.rosoftlab.nav.launcher.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Adi Pascu on 7/17/2015.
 * Email mail@adipascu.ro
 */
public class SampleActivity extends Activity {
    @Bind(R.id.meaningOfLife)
    TextView meaningView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity);
        ButterKnife.bind(this);
        meaningView.setText(String.valueOf(SampleTest.meaningOfLife()));
    }
}

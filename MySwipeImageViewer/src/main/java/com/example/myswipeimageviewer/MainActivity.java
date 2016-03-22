package com.example.myswipeimageviewer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.LoginManager;
import net.hockeyapp.android.FeedbackManager;



public class MainActivity extends Activity {

    // The varible for the Button
    Button buttonStart,feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        feedback = (Button) findViewById(R.id.buttonFeedback);
        buttonStart=(Button)findViewById(R.id.button);

        // Capture butoon clicks
        buttonStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the new Activity to see the Dia Show
                Intent intent = new Intent(MainActivity.this, SlideActivity.class);
                startActivity(intent);
            }
        });

        checkForUpdates();
        LoginManager.register(this, "946be04f40c3f11e64373437d571c4e1", LoginManager.LOGIN_MODE_EMAIL_PASSWORD);
        LoginManager.verifyLogin(this, getIntent());
        FeedbackManager.register(this);


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackManager.showFeedbackActivity(MainActivity.this);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        // ... your own onResume implementation
        checkForCrashes();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterManagers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }
    private void checkForCrashes() {
        CrashManager.register(this);
    }
    private void checkForUpdates() {
        // Remove this for store builds!
        UpdateManager.register(this);
    }
    private void unregisterManagers() {
        UpdateManager.unregister();
    }


}

package com.mobile.luthkemp.luthkemp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mobile.luthkemp.luthkemp.helper.MobileNotification;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import me.pushy.sdk.Pushy;
import me.pushy.sdk.util.exceptions.PushyException;


public class MainActivity extends BaseEntry {

  private final String SETWELCOME = "welcome";
  private final int SETTODAYNEWS = 8;
  private final int SETPRAYER = 9;
  private final int SETNOTFY = 10;
  private FloatingActionButton fab;
  private Button mOpenNot;
  private CardView btnShare;
  private CardView btnPrayer;
  private CardView btnGive;
  private CardView btnNotification;
  private CardView btnWelcome;
  private CardView btnEvents;
  private static String pushyToken;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    // Restart the socket service, in case the user force-closed the app
    Pushy.listen(this);

    // Register device for push notifications (async)
    if (pushyToken == null || pushyToken.isEmpty()) {
      new RegisterForPushNotificationsAsync().execute();
    }
    initViews();
    checkForUpdates();
  }

  private void initViews() {

    fab = (FloatingActionButton) findViewById(R.id.fab);
    btnEvents = (CardView) findViewById(R.id.btnEvents);
    btnGive = (CardView) findViewById(R.id.btnGive);
    btnNotification = (CardView) findViewById(R.id.btnNotification);
    btnPrayer = (CardView) findViewById(R.id.btnPrayers);
    btnWelcome = (CardView) findViewById(R.id.btnWelcome);
    btnShare = (CardView) findViewById(R.id.btnShare);

    btnShare.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent notify = new Intent(MainActivity.this, Share.class);
        startActivity(notify);
      }
    });

    btnWelcome.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent notify = new Intent(MainActivity.this, Welcome.class);
        //startActivityForResult(notify,SETWELCOME);
        notify.putExtra("key", SETWELCOME);
        startActivity(notify);
      }
    });

    btnPrayer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent notify = new Intent(MainActivity.this, Prayer.class);
        startActivity(notify);
      }
    });

    btnNotification.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent notify = new Intent(MainActivity.this, Notification.class);
        startActivity(notify);
      }
    });

    btnGive.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    btnEvents.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent notify = new Intent(MainActivity.this, Event.class);
        startActivity(notify);
      }
    });
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();

      }
    });

   /* mOpenNot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent notify = new Intent(MainActivity.this, Notification.class);
        startActivity(notify);
      }
    });*/

  }

  @Override
  protected void onResume() {
    super.onResume();
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
    UpdateManager.register(MainActivity.this);
  }

  private void unregisterManagers() {
    UpdateManager.unregister();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void OnGetDataSucces(String responseBody) {

  }

  @Override
  public void OnGetDataFailed(String ResponseBody) {

  }

  private class RegisterForPushNotificationsAsync extends AsyncTask<String, Void, RegistrationResult> {
    ProgressDialog mLoading;

    public RegisterForPushNotificationsAsync() {
      // Create progress dialog and set it up
      mLoading = new ProgressDialog(MainActivity.this);
      // mLoading.setMessage(getString(R.string.registeringDevice));
      mLoading.setCancelable(false);

      // Show it
      mLoading.show();
    }

    @Override
    protected RegistrationResult doInBackground(String... params) {
      // Prepare registration result
      RegistrationResult result = new RegistrationResult();

      try {
        // Register device for push notifications
        result.deviceToken = Pushy.register(MainActivity.this);
      } catch (PushyException exc) {
        // Store registration error in result
        result.error = exc;
      }

      // Handle result in onPostExecute (UI thread)
      return result;
    }

    @Override
    protected void onPostExecute(RegistrationResult result) {
      // Activity died?
      if (isFinishing()) {
        return;
      }

      // Hide progress bar
      mLoading.dismiss();

      // Registration failed?
      if (result.error != null) {
        // Write error to logcat
        Log.e("Pushy", "Registration failed: " + result.error.getMessage());

        // Display registration failed in app UI
        //mInstructions.setText(R.string.restartApp);
        //mDeviceToken.setText(R.string.registrationFailed);

        // Display error dialog
        new AlertDialog.Builder(MainActivity.this).setTitle("error")
          .setMessage(result.error.getMessage())
          .setPositiveButton("Ok", null)
          .create()
          .show();
      } else {
        // Write device token to logcat
        Log.d("Pushy", "Device token: " + result.deviceToken);
        pushyToken = result.deviceToken;
        POST(new MobileNotification(pushyToken,"testDevice"),"mobileNotify/post");
        // Display device token and copy from logcat instructions
        //mInstructions.setText(R.string.copyLogcat);
        //mDeviceToken.setText(result.deviceToken);
      }
    }
  }
}

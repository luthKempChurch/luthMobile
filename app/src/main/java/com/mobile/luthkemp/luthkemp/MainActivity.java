package com.mobile.luthkemp.luthkemp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private FloatingActionButton fab;
  private Button mOpenNot;
  private CardView btnShare;
  private CardView btnPrayer;
  private CardView btnGive;
  private CardView btnNotification;
  private CardView btnWelcome;
  private CardView btnEvents;
  private static final String SETWELCOME = "welcome";
  private static final int SETTODAYNEWS = 8;
  private static final int SETPRAYER = 9;
  private static final int SETNOTFY =10;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    initViews();

  }

  private void initViews() {

    fab = (FloatingActionButton) findViewById(R.id.fab);
    btnEvents = (CardView) findViewById(R.id.btnEvents);
    btnGive = (CardView) findViewById(R.id.btnGive);
    btnNotification = (CardView)findViewById(R.id.btnNotification);
    btnPrayer = (CardView) findViewById(R.id.btnPrayers);
    btnWelcome = (CardView) findViewById(R.id.btnWelcome);
    btnShare = (CardView) findViewById(R.id.btnShare);

    btnShare.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    btnWelcome.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent notify = new Intent(MainActivity.this, Welcome.class);
        //startActivityForResult(notify,SETWELCOME);
        notify.putExtra("key",SETWELCOME);
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
}

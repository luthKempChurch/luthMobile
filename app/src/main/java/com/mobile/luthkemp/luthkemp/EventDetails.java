package com.mobile.luthkemp.luthkemp;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.luthkemp.luthkemp.helper.EventView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventDetails extends AppCompatActivity {
  private ImageView imgEvent;
  private TextView txtDateTimeEvent;
  private TextView txtLocationEvent;
  private TextView txtDescription;
  private TextView txtWebsiteUrl;
  private TextView txtPhone;
  private EventView eventId;
  private TextView txtEmail;
  private static final int action_SHOW_PROGRESS_DIALOG = 3;
  private static final int action_HIDE_PROGRESS_DIALOG = 4;
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.event_details);
    initViews();
    adapterTest();
    SetDataViews();
  }

  private void adapterTest() {
  }

  private void initViews() {
    imgEvent = (ImageView) findViewById(R.id.imgEvents);
    txtDateTimeEvent = (TextView) findViewById(R.id.txtDateTEvent);
    txtLocationEvent = (TextView) findViewById(R.id.txtLocationEvent);
    txtDescription = (TextView) findViewById(R.id.txtDescriptionEvent);
    txtWebsiteUrl = (TextView) findViewById(R.id.txtWebSEvent);
    txtPhone = (TextView) findViewById(R.id.txtPhoneEvent);
    txtEmail =(TextView) findViewById(R.id.txtEmailEvent);

    Bundle extras = getIntent().getExtras();
    if(extras != null){
      eventId = (EventView) extras.get("EventDetail");
    }
  }
  private void SetDataViews(){
    txtPhone.setText(eventId.ContactPerson);
    txtDescription.setText(eventId.Description);
    txtLocationEvent.setText(eventId.Address);
   // Calendar cal = Calendar.getInstance();
   // cal.setTime(eventId.Date);
    //txtDateTimeEvent.setText(new SimpleDateFormat("dd-MM-YYYY").toString());
    txtEmail.setText(eventId.Email);

  }

  private void doOnUIThread(final int action, final String txt) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        switch (action) {
          case action_SHOW_PROGRESS_DIALOG:
            progressDialog.setMessage(txt);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            break;
          case action_HIDE_PROGRESS_DIALOG:

            if ( progressDialog != null) {
              progressDialog.dismiss();
            }
            break;
          default:
            break;
        }
      }
    });
  }
  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
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


package com.mobile.luthkemp.luthkemp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.mobile.luthkemp.luthkemp.adapters.EventsAdapter;
import com.mobile.luthkemp.luthkemp.helper.EventView;

public class Event extends BaseEntry {
  private EventsAdapter adapter;
  private ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.event);
    initViews();
    showEvents();
    //getActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void initViews() {
    listView = (ListView) findViewById(R.id.listviewEvents);
  }

  private void showEvents() {
    doOnUIThread(action_SHOW_PROGRESS_DIALOG, "getting events");
    GET("event/getEvents");
  }

  private void display() {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        doOnUIThread(action_HIDE_PROGRESS_DIALOG, "");
      }
    });
  }

  private void doOnUIThread(final int action, final String txt) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        //ProgressDialog progressDialog = new ProgressDialog(Event.this);
        switch (action) {
          case action_SHOW_PROGRESS_DIALOG:
            progressDialog = new ProgressDialog(Event.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(txt);
            // Show it
            progressDialog.show();
            break;
          case action_HIDE_PROGRESS_DIALOG:
            if (progressDialog != null) {
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

  @Override
  public void OnGetDataSucces(String responseBody) {
    EventView[] events = new Gson().fromJson(responseBody, EventView[].class);
    adapter = new EventsAdapter(Event.this, events);
    display();

  }

  @Override
  public void OnGetDataFailed(String ResponseBody) {
    doOnUIThread(action_HIDE_PROGRESS_DIALOG, "");
  }
}

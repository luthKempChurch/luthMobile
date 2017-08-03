package com.mobile.luthkemp.luthkemp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.mobile.luthkemp.luthkemp.adapters.NotificationAdapter;
import com.mobile.luthkemp.luthkemp.helper.NotificationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Notification extends BaseEntry {

  private NotificationAdapter adapter;
  private ListView lstView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);
    initViews();
    initVals();
    GetNotifications();
    //adapterTest();
    //postData();
  }

  private void initViews() {
    lstView = (ListView) findViewById(R.id.notifications);
  }

  private void initVals() {
    db = new DbAccess();
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
  private void postData() {
    try {
      //String json = new Gson().toJson(new DbAccess());

      //String loginData = "grant_type=password&username=amFidWxhbmkrNDVAc3RvY2tzaG9wLmNvLnph&password=cGFzc3dvcmQ=";
      db.client.newCall(db.GET("notify/all_notifications"))
        .enqueue(new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {

          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
              String responseBody = response.body().string();
            } else {
              Log.e("Notification", response.message());
            }
          }
        });
    } catch (Exception e) {


    }
  }

  private void adapterTest(final NotificationView[] notifes) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        adapter = new NotificationAdapter(Notification.this, notifes);
        lstView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
      }
    });

  }

  private void GetNotifications() {
    try {
      doOnUIThread(action_SHOW_PROGRESS_DIALOG,"Getting notifications");
      GET("notify/all_notifications");
    } catch (Exception e) {
      doOnUIThread(action_HIDE_PROGRESS_DIALOG,"");
    }
  }
  private void doOnUIThread(final int action, final String txt) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        //ProgressDialog progressDialog = new ProgressDialog(Event.this);
        switch (action) {
          case action_SHOW_PROGRESS_DIALOG:
            progressDialog = new ProgressDialog(Notification.this);
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
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {

    }
  }

  @Override
  public void OnGetDataSucces(String responseBody) {
    NotificationView[] nt = (NotificationView[]) new Gson().fromJson(responseBody, NotificationView[].class);
    //ArrayAdapter<NotificationView> itemsNotfy = new ArrayAdapter<NotificationView>()
    adapterTest(nt);
    doOnUIThread(action_HIDE_PROGRESS_DIALOG,"");
  }

  @Override
  public void OnGetDataFailed(String ResponseBody) {
    doOnUIThread(action_HIDE_PROGRESS_DIALOG,"");
  }
}

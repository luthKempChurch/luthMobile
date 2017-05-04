package com.mobile.luthkemp.luthkemp;

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

public class Notification extends AppCompatActivity {

  private DbAccess db;
  //public OkHttpClient client;
  private NotificationAdapter adapter;
  private ListView lstView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);
    initViews();
    initVals();
    //GetNotifications();
    adapterTest();
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

  private void postData() {
    try {
      String json = new Gson().toJson(new DbAccess());

      String loginData = "grant_type=password&username=amFidWxhbmkrNDVAc3RvY2tzaG9wLmNvLnph&password=cGFzc3dvcmQ=";
      db.client.newCall(db.POST(loginData))
        .enqueue(new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {

          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
              String responseBody = response.body().toString();
            } else {
              Log.e("Notification", response.message());
            }
          }
        });
    } catch (Exception e) {


    }
  }

  private void adapterTest() {
    List<NotificationView> notifes = new ArrayList<>();
    notifes.add(new NotificationView("church starts at 9 am", 0, "Church meeting"));
    notifes.add(new NotificationView("camp on 12 september", 1, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 2, "Church meeting"));
    notifes.add(new NotificationView("camp on 14 september", 3, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 0, "Church meeting"));
    notifes.add(new NotificationView("camp on 12 september", 1, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 2, "Church meeting"));
    notifes.add(new NotificationView("camp on 14 september", 3, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 0, "Church meeting"));
    notifes.add(new NotificationView("camp on 12 september", 1, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 2, "Church meeting"));
    notifes.add(new NotificationView("camp on 14 september", 3, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 0, "Church meeting"));
    notifes.add(new NotificationView("camp on 12 september", 1, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 2, "Church meeting"));
    notifes.add(new NotificationView("camp on 14 september", 3, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 0, "Church meeting"));
    notifes.add(new NotificationView("camp on 12 september", 1, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 2, "Church meeting"));
    notifes.add(new NotificationView("camp on 14 september", 3, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 0, "Church meeting"));
    notifes.add(new NotificationView("camp on 12 september", 1, "Youth camp"));
    notifes.add(new NotificationView("church starts at 9 am", 2, "Church meeting"));
    notifes.add(new NotificationView("camp on 14 september", 3, "Youth camp"));
    adapter = new NotificationAdapter(Notification.this, notifes);
    lstView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
  }

  private void GetNotifications() {
    try {
      db.client.newCall(db.GET("api/notify/all_notifications"))
        .enqueue(new Callback() {
          @Override
          public void onFailure(okhttp3.Call call, IOException e) {

            Log.e("Erro", e.getMessage());
          }

          @Override
          public void onResponse(okhttp3.Call call, final Response response) throws IOException {
            if (response.isSuccessful()) {
              String responseBody = response.body().toString();
              List<NotificationView> nt = (List<NotificationView>) new Gson().fromJson(responseBody, NotificationView.class);
              //ArrayAdapter<NotificationView> itemsNotfy = new ArrayAdapter<NotificationView>()
              adapter = new NotificationAdapter(Notification.this, nt);
              lstView.setAdapter(adapter);
              adapter.notifyDataSetChanged();
            } else {
              Log.e("Notification", response.message());
            }

          }
        });

    } catch (Exception e) {

    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {

    }
  }
}

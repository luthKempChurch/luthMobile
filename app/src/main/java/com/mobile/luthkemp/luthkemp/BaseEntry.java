package com.mobile.luthkemp.luthkemp;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mobile.luthkemp.luthkemp.interfaces.NetworkEvnts;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by J.Madzivadondo on 2017/08/02.
 */

public abstract class BaseEntry extends AppCompatActivity implements NetworkEvnts {
  protected final int action_SHOW_PROGRESS_DIALOG = 1;
  protected final int action_HIDE_PROGRESS_DIALOG = 0;
  protected DbAccess db;
  protected ProgressDialog progressDialog;
  private Context context;

  public BaseEntry() {
    db = new DbAccess();
    context = this;
  }

  protected void GET(String url) {
    db.client.newCall(db.GET(url))
      .enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
          NetworkEvnts listener = (NetworkEvnts) context;
          listener.OnGetDataFailed(e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
          NetworkEvnts listener = (NetworkEvnts) context;
          if (response.isSuccessful()) {
            listener.OnGetDataSucces(response.body().string());
          } else {
            listener.OnGetDataFailed(response.body().string());
          }
        }
      });
  }

  protected void POST(Object Body, String url) {
    db.client.newCall(db.POST(new Gson().toJson(Body), url))
      .enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
          NetworkEvnts listener = (NetworkEvnts) context;
          listener.OnGetDataFailed(e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
          NetworkEvnts listener = (NetworkEvnts) context;
          if (response.isSuccessful()) {
            listener.OnGetDataSucces(response.body().string());
          } else {
            listener.OnGetDataFailed(response.body().string());
          }
        }
      });
  }
}

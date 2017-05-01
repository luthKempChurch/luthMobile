package com.mobile.luthkemp.luthkemp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by J.Madzivadondo on 2017/04/28.
 */

public class DbAccess {
  private static final String Url = "http://localhost:62168/";
  public OkHttpClient client;
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  public DbAccess() {
    client = new OkHttpClient();
  }

  public static Request GET(String path) {
    try {
      return new Request.Builder()
        .url(Url+path)
        .build();
    } catch (Exception e) {

      return null;
    }
  }

  public static Request POST(String json) {
    try {
      RequestBody body = RequestBody.create(JSON, json);
      return new Request.Builder()
        .url(Url)
        .post(body)
        .build();

    } catch (Exception e) {

      return null;
    }
  }
}

package com.mobile.luthkemp.luthkemp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by J.Madzivadondo on 2017/04/28.
 */

public class DbAccess {
  private final String Url = "https://09a99ca2.ngrok.io/api/";
  public OkHttpClient client;
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  public DbAccess() {
    client = new OkHttpClient();
  }

  public Request GET(String path) {
    try {
      return new Request.Builder()
        .url(Url+path)
        .build();
    } catch (Exception e) {

      return null;
    }
  }

  public Request POST(String json,String path) {
    try {
      RequestBody body = RequestBody.create(JSON, json);
      return new Request.Builder()
        .url(Url+path)
        .post(body)
        .build();

    } catch (Exception e) {

      return null;
    }
  }
}

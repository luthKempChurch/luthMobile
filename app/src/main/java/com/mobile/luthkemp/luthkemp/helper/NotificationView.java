package com.mobile.luthkemp.luthkemp.helper;

/**
 * Created by J.Madzivadondo on 2017/04/30.
 */

public class NotificationView {
  public int Id;
  public String Message;
  public String Title;

  public NotificationView(String message,int id,String title){
    this.Id = id;
    this.Message = message;
    this.Title = title;
  }
}

package com.mobile.luthkemp.luthkemp.helper;

/**
 * Created by J.Madzivadondo on 2017/08/03.
 */

public class MobileNotification {
  public int Id;
  public String Token;
  public String DeviceId;

  public MobileNotification(String token,String deviceId){
    this.Id = 0;
    this.DeviceId = deviceId;
    this.Token = token;
  }
}

package com.mobile.luthkemp.luthkemp.helper;

/**
 * Created by J.Madzivadondo on 2017/08/02.
 */

public class PrayerView {
  public int Id;
  public String FirstName;
  public String Surname;
  public String Email;
  public String PhoneNumber;
  public String Description;

  public PrayerView(String FirstName, String Surname, String Email, String PhoneNumber, String Description) {
    this.Id = 0;
    this.Description = Description;
    this.Email = Email;
    this.FirstName = FirstName;
    this.PhoneNumber = PhoneNumber;
    this.Surname = Surname;
  }
}

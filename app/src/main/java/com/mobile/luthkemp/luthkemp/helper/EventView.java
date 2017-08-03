package com.mobile.luthkemp.luthkemp.helper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by J.Madzivadondo on 2017/05/04.
 */

public class EventView implements Parcelable {
  public static final Creator<EventView> CREATOR = new Creator<EventView>() {
    @Override
    public EventView createFromParcel(Parcel in) {
      return new EventView(in);
    }

    @Override
    public EventView[] newArray(int size) {
      return new EventView[size];
    }
  };
  public int Id;
  public String Date;
  public String Title;
  public String Address;
  public String ContactPerson;
  public String Description;
  public String Email;
  public Boolean Show;

  protected EventView(Parcel in) {
    Id = in.readInt();
    Title = in.readString();
    Address = in.readString();
    ContactPerson = in.readString();
    Description = in.readString();
    Email = in.readString();
  }

  public EventView(int Id, String Date, String Title, String Address, String ContactPerson, String Description, String Email, Boolean Show) {
    this.Address = Address;
    this.ContactPerson = ContactPerson;
    this.Date = Date;
    this.Description = Description;
    this.Email = Email;
    this.Id = Id;
    this.Title = Title;
    this.Show = Show;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(Id);
    dest.writeString(Title);
    dest.writeString(Address);
    dest.writeString(ContactPerson);
    dest.writeString(Description);
    dest.writeString(Email);
  }
}

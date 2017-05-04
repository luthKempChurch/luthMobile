package com.mobile.luthkemp.luthkemp.helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by J.Madzivadondo on 2017/04/30.
 */

public class NotificationView implements Parcelable {
  public static final Creator<NotificationView> CREATOR = new Creator<NotificationView>() {
    @Override
    public NotificationView createFromParcel(Parcel in) {
      return new NotificationView(in);
    }

    @Override
    public NotificationView[] newArray(int size) {
      return new NotificationView[size];
    }
  };
  public int Id;
  public String Message;
  public String Title;

  public NotificationView(String message, int id, String title) {
    this.Id = id;
    this.Message = message;
    this.Title = title;
  }

  protected NotificationView(Parcel in) {
    Id = in.readInt();
    Message = in.readString();
    Title = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(Id);
    dest.writeString(Message);
    dest.writeString(Title);
  }
}

package com.mobile.luthkemp.luthkemp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.luthkemp.luthkemp.R;
import com.mobile.luthkemp.luthkemp.helper.NotificationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J.Madzivadondo on 2017/05/01.
 */

public class NotificationAdapter extends ArrayAdapter<NotificationView> {

  public NotificationAdapter(Context context, List<NotificationView> notifications) {
    super(context, R.layout.activity_notification, notifications);

  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // Get the data item for this position
    NotificationView user = getItem(position);
    // Check if an existing view is being reused, otherwise inflate the view
    ViewHolder viewHolder; // view lookup cache stored in tag
    if (convertView == null) {
      // If there's no view to re-use, inflate a brand new view for row
      viewHolder = new ViewHolder();
      LayoutInflater inflater = LayoutInflater.from(getContext());
      convertView = inflater.inflate(R.layout.list_details, parent, false);
      viewHolder.title = (TextView) convertView.findViewById(R.id.txtTitle);
      viewHolder.message = (TextView) convertView.findViewById(R.id.txtMsg);
      // Cache the viewHolder object inside the fresh view
      convertView.setTag(viewHolder);
    } else {
      // View is being recycled, retrieve the viewHolder object from tag
      viewHolder = (ViewHolder) convertView.getTag();
    }
    // Populate the data from the data object via the viewHolder object
    // into the template view.
    viewHolder.title.setText(user.Title);
    viewHolder.message.setText(user.Message);
    // Return the completed view to render on screen
    return convertView;
  }

  public static class ViewHolder {
    TextView title;
    TextView message;
  }

}

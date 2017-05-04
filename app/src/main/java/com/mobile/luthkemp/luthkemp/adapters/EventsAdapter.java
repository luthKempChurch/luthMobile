package com.mobile.luthkemp.luthkemp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.luthkemp.luthkemp.R;
import com.mobile.luthkemp.luthkemp.helper.EventView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by J.Madzivadondo on 2017/05/04.
 */

public class EventsAdapter extends ArrayAdapter<EventView> {
  public EventsAdapter(Context context, List<EventView> events) {
    super(context, R.layout.fragment_event, events);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // Get the data item for this position
    EventView user = getItem(position);
    // Check if an existing view is being reused, otherwise inflate the view
    ViewHolder viewHolder; // view lookup cache stored in tag
    if (convertView == null) {
      // If there's no view to re-use, inflate a brand new view for row
      viewHolder = new ViewHolder();
      LayoutInflater inflater = LayoutInflater.from(getContext());
      convertView = inflater.inflate(R.layout.list_events, parent, false);
      viewHolder.title = (TextView) convertView.findViewById(R.id.txtTitleEvent);
      viewHolder.description = (TextView) convertView.findViewById(R.id.txtDescripEvent);
      viewHolder.month = (TextView) convertView.findViewById(R.id.txtMonth);
      viewHolder.day = (TextView) convertView.findViewById(R.id.txtDay);
      // Cache the viewHolder object inside the fresh view
      convertView.setTag(viewHolder);
    } else {
      // View is being recycled, retrieve the viewHolder object from tag
      viewHolder = (ViewHolder) convertView.getTag();
    }
    // Populate the data from the data object via the viewHolder object
    // into the template view.
    Calendar cal = Calendar.getInstance();
    cal.setTime(user.Date);
    viewHolder.title.setText(user.Title);
    viewHolder.description.setText(user.Description);
    viewHolder.day.setText(new SimpleDateFormat("MMMM").format(cal.getTime()));
    viewHolder.month.setText(new SimpleDateFormat("dd").format(cal.getTime()));
    // Return the completed view to render on screen
    return convertView;
  }

  public static class ViewHolder {
    TextView title;
    TextView description;
    TextView day;
    TextView month;
  }

}

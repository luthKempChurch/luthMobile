package com.mobile.luthkemp.luthkemp;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by J.Madzivadondo on 2017/05/04.
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class PrayerFragment extends Fragment {
  public PrayerFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_prayer, container, false);
  }
}

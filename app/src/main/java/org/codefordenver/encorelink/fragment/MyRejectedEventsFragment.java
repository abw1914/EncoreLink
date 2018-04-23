package org.codefordenver.encorelink.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.codefordenver.encorelink.R;

public class MyRejectedEventsFragment extends Fragment {
  private static final String TAG = "MyRejectedEventsFragment";

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_my_rejected_events, container, false);
    return v;
  }
  
}

package com.mwhive.lastfmcharts.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.bluelinelabs.conductor.Controller;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.base.BaseActivity;
import com.mwhive.lastfmcharts.screens.charts.ChartScreenController;

public class MainActivity extends BaseActivity {

  @Override
  protected Controller initialScreen() {
    return new ChartScreenController();
  }

  @Override
  protected int layoutRes() {
    return R.layout.activity_main;
  }
}



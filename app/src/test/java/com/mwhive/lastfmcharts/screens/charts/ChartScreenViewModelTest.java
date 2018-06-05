package com.mwhive.lastfmcharts.screens.charts;

import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.models.charts.Charts;
import com.mwhive.lastfmcharts.testutils.TestUtils;
import io.reactivex.observers.TestObserver;
import java.io.IOException;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by MadWasp79 on 04-Jun-18.
 */

public class ChartScreenViewModelTest {

  private ChartScreenViewModel viewModel;

  @Before
  public void setUp() throws Exception {
    viewModel = new ChartScreenViewModel();
  }

  @Test
  public void loading() throws Exception {
    TestObserver<Boolean> loadingObserver = viewModel.loading().test();
    viewModel.loadingUpdated().accept(true);
    viewModel.loadingUpdated().accept(false);

    loadingObserver.assertValues(true, false);
  }

  @Test
  public void artistsChart() throws Exception {
    Charts response = TestUtils.loadJson("mock/mock_charts_response.json", Charts.class);
    viewModel.artistsUpdated().accept(response.topArtists().artist());

    viewModel.artists().test().assertValue(response.topArtists().artist());
  }

  @Test
  public void error() throws Exception {
    TestObserver<Integer> errorObserver = viewModel.error().test();
    viewModel.onError().accept(new IOException());
    viewModel.artistsUpdated().accept(Collections.emptyList());

    errorObserver.assertValues(R.string.api_error_chart, -1);
  }
}
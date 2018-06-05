package com.mwhive.lastfmcharts.screens.charts;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.mwhive.lastfmcharts.data.ChartRequester;
import com.mwhive.lastfmcharts.models.charts.Artist;
import com.mwhive.lastfmcharts.models.charts.Charts;
import com.mwhive.lastfmcharts.testutils.TestUtils;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * Created by MadWasp79 on 04-Jun-18.
 */

@SuppressWarnings("ThrowableNotThrown")
public class ChartScreenPresenterTest {

  @Mock ChartRequester chartRequester;
  @Mock ChartScreenViewModel viewModel;
  @Mock Consumer<Throwable> onErrorConsumer;
  @Mock Consumer<Boolean> loadingConsumer;
  @Mock Consumer<List<Artist>> onSuccessConsumer;

  private ChartScreenPresenter presenter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
    when(viewModel.onError()).thenReturn(onErrorConsumer);
    when(viewModel.artistsUpdated()).thenReturn(onSuccessConsumer);
  }

  @Test
  public void artistsLoaded() throws Exception {
    List<Artist> artists = setUpSuccess();
    initializePresenter();

    verify(chartRequester).getCharts("ukraine");
    verify(onSuccessConsumer).accept(artists);
    verifyZeroInteractions(onErrorConsumer);
  }

  @Test
  public void reposLoadedError() throws Exception {
    Throwable error = setUpError();
    initializePresenter();

    verify(onErrorConsumer).accept(error);
    verifyZeroInteractions(onSuccessConsumer);
  }

  @Test
  public void loadingSuccess() throws Exception {
    setUpSuccess();
    initializePresenter();

    InOrder inOrder = Mockito.inOrder(loadingConsumer);
    inOrder.verify(loadingConsumer).accept(true);
    inOrder.verify(loadingConsumer).accept(false);
  }

  @Test
  public void loadingError() throws Exception {
    setUpError();
    initializePresenter();

    InOrder inOrder = Mockito.inOrder(loadingConsumer);
    inOrder.verify(loadingConsumer).accept(true);
    inOrder.verify(loadingConsumer).accept(false);
  }


  @Test

  public void onArtistClicked() throws Exception {
    //todo
  }

  //helper method
  private List<Artist> setUpSuccess() {
    Charts response = TestUtils.loadJson("mock/mock_charts_response.json", Charts.class);
    List<Artist> artists = response.topArtists().artist();

    when(chartRequester.getCharts("ukraine")).thenReturn(Single.just(artists));

    return artists;
  }

  //helper method
  private Throwable setUpError() {
    Throwable error = new IOException();
    when(chartRequester.getCharts("ukraine")).thenReturn(Single.error(error));
    return error;
  }

  //helper method
  private void initializePresenter() {
    presenter = new ChartScreenPresenter(viewModel, chartRequester);
  }
}
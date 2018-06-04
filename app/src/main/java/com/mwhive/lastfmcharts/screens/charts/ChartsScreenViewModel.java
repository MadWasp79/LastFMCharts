package com.mwhive.lastfmcharts.screens.charts;


import com.jakewharton.rxrelay2.BehaviorRelay;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.di.ScreenScope;
import com.mwhive.lastfmcharts.models.charts.Artist;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by MadWasp79 on 03-Jun-18.
 * Self-explanatory. ViewModel with  Consumer => BehaviorRelays => Observable for Charts(first) screen.
 */

@ScreenScope
class ChartsScreenViewModel {

  private final BehaviorRelay<List<Artist>> artistsChartRelay = BehaviorRelay.create();
  private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
  private final BehaviorRelay<Boolean> loadingRelay= BehaviorRelay.create();

  @Inject
  ChartsScreenViewModel() {

  }

  Observable<Boolean> loading() {
    return loadingRelay;
  }

  Observable<List<Artist>> artistsChart() {
    return artistsChartRelay;
  }

  Observable<Integer> error() {
    return errorRelay;
  }

  Consumer<Boolean> loadingUpdated() {
    return loadingRelay;
  }

  Consumer<List<Artist>> artistsChartUpdated() {
    errorRelay.accept(-1);
    return artistsChartRelay;
  }

  Consumer<Throwable> onError() {
    return throwable -> {
      Timber.e(throwable,"Error loading Chart");
      errorRelay.accept(R.string.api_error_chart);
    };
  }


}

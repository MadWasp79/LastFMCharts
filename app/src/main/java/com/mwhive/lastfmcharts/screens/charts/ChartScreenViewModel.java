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
 * Helps us decouple View and Presenter, save View state on Screen orientation change.
 */

@ScreenScope
class ChartScreenViewModel {

  private final BehaviorRelay<List<Artist>> artistsChartRelay = BehaviorRelay.create();
  private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
  private final BehaviorRelay<Boolean> loadingRelay= BehaviorRelay.create();

  private final BehaviorRelay<Integer> countrySelectorRelay = BehaviorRelay.create();
  private final BehaviorRelay<Integer> countryImageRelay = BehaviorRelay.create();

  @Inject
  ChartScreenViewModel() {

  }

  Observable<Boolean> loading() {
    return loadingRelay;
  }

  Observable<List<Artist>> artists() {
    return artistsChartRelay;
  }

  Observable<Integer> error() {
    return errorRelay;
  }

  Observable<Integer> country() {return countrySelectorRelay;}

  Observable<Integer> selectorState() {return countryImageRelay;}



  Consumer<Boolean> loadingUpdated() {
    return loadingRelay;
  }

  Consumer<List<Artist>> artistsUpdated() {
    errorRelay.accept(-1);
    return artistsChartRelay;
  }

  Consumer<Throwable> onError() {
    return throwable -> {
      Timber.e(throwable,"Error loading Chart");
      errorRelay.accept(R.string.api_error_chart);
    };
  }

  Consumer<Integer> countrySelected(){
    return countrySelectorRelay;
  }

  Consumer<Integer> countrySelectorUpdated(){
    return countryImageRelay;
  }

}

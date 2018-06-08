package com.mwhive.lastfmcharts.screens.charts;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import butterknife.BindView;
import com.jakewharton.rxbinding2.view.RxView;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.base.BaseController;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */

public class ChartScreenController extends BaseController {

  @Inject ChartScreenPresenter presenter;
  @Inject ChartScreenViewModel viewModel;

  @BindView(R.id.top_artists_list) RecyclerView artistList;
  @BindView(R.id.loading_indicator) View loadingView;
  @BindView(R.id.tv_error) TextView errorText;
  @BindView(R.id.country_select_fab) FloatingActionButton countrySelectFab;
  @BindView(R.id.country_one_fab) FloatingActionButton selectCountryOne;
  @BindView(R.id.country_two_fab) FloatingActionButton selectCountryTwo;
  @BindView(R.id.country_three_fab) FloatingActionButton selectCountryThree;

  private Animation fab_open, fab_close, rotate_forward, rotate_backward;
  private boolean isFabOpen = false;

  @Override
  protected void onViewBound(View view) {
    artistList.setLayoutManager(new LinearLayoutManager(view.getContext()));
    artistList.setAdapter(new ArtistsListAdapter(presenter)); //since we set onClickListener directly to presenter
    setUpAnimation();

  }

  @Override
  protected Disposable[] subscriptions() {
    return new Disposable[]{
        viewModel.loading()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(loading -> {
          loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
          artistList.setVisibility(loading ? View.GONE : View.VISIBLE);
          errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
        }),
        viewModel.artists()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(((ArtistsListAdapter)artistList.getAdapter())::setData),
        viewModel.error()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(errorRes -> {
              if (errorRes == -1) {
                errorText.setText(null);
                errorText.setVisibility(View.GONE);
              } else {
                errorText.setVisibility(View.VISIBLE);
                artistList.setVisibility(View.GONE);
                errorText.setText(errorRes);
              }
        }),
        RxView.clicks(countrySelectFab).subscribe(__ -> animateFAB()),
        RxView.clicks(selectCountryOne).subscribe(__ -> {
            animateFAB();
            viewModel.countrySelected().accept(1);
        }),
        RxView.clicks(selectCountryTwo).subscribe(__ -> {
          animateFAB();
          viewModel.countrySelected().accept(2);
        }),
        RxView.clicks(selectCountryThree).subscribe(__ -> {
          animateFAB();
          viewModel.countrySelected().accept(3);
        }),
        viewModel.selectorState().subscribe(this::setFlagToFAB)

    };
  }

  @Override
  protected int layoutRes() {
    return R.layout.screen_charts;
  }

  public void setFlagToFAB(int countryCode) {
    Timber.d("Country code accepted: " + countryCode);
    switch (countryCode){
      case 1:
        countrySelectFab.setImageResource(R.drawable.ukraine);
        break;
      case 2:
        countrySelectFab.setImageResource(R.drawable.israel);
        break;
      case 3:
        countrySelectFab.setImageResource(R.drawable.united_kingdom);
        break;
    }
  }

  public void setUpAnimation(){
    fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
    fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
    rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
    rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
  }


  public void animateFAB(){

    if(isFabOpen){

      countrySelectFab.startAnimation(rotate_backward);
      selectCountryOne.startAnimation(fab_close);
      selectCountryTwo.startAnimation(fab_close);
      selectCountryThree.startAnimation(fab_close);
      selectCountryOne.setClickable(false);
      selectCountryTwo.setClickable(false);
      selectCountryThree.setClickable(false);
      selectCountryOne.setVisibility(View.INVISIBLE);
      selectCountryTwo.setVisibility(View.INVISIBLE);
      selectCountryThree.setVisibility(View.INVISIBLE);
      isFabOpen = false;
      Timber.d("FAB animation close, isFabOpen=" + isFabOpen);

    } else {

      countrySelectFab.startAnimation(rotate_forward);
      selectCountryOne.startAnimation(fab_open);
      selectCountryTwo.startAnimation(fab_open);
      selectCountryThree.startAnimation(fab_open);
      selectCountryOne.setClickable(true);
      selectCountryTwo.setClickable(true);
      selectCountryThree.setClickable(true);
      selectCountryOne.setVisibility(View.VISIBLE);
      selectCountryTwo.setVisibility(View.VISIBLE);
      selectCountryThree.setVisibility(View.VISIBLE);
      isFabOpen = true;
      Timber.d("FAB animation open, isFabOpen=" + isFabOpen);

    }
  }


}

package com.mwhive.lastfmcharts.screens.charts;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.base.BaseController;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * Created by MadWasp79 on 01-Jun-18.
 */

public class ChartScreenController extends BaseController {

  @Inject ChartScreenPresenter presenter;
  @Inject ChartsScreenViewModel viewModel;

  @BindView(R.id.top_artists_list) RecyclerView artistList;
  @BindView(R.id.loading_indicator) View loadingView;
  @BindView(R.id.tv_error) TextView errorText;

  //todo: add country selector and loading on country switch


  @Override
  protected void onViewBound(View view) {
    artistList.setLayoutManager(new LinearLayoutManager(view.getContext()));
    artistList.setAdapter(new ArtistsListAdapter(presenter)); //since we set onClickListener directly to presenter
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
        viewModel.artistsChart()
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
        })
    };
  }

  @Override
  protected int layoutRes() {
    return R.layout.screen_charts;
  }
}

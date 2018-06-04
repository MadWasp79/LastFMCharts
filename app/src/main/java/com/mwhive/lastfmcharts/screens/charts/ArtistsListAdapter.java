package com.mwhive.lastfmcharts.screens.charts;


import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.models.charts.Artist;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import timber.log.Timber;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */

public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsListAdapter.ChartsViewHolder>{

  private final ArtistClickListener listener;
  private final List<Artist> data = new ArrayList<>();

  public ArtistsListAdapter(ArtistClickListener listener) {
    this.listener = listener;
    //setHasStableIds(true);
  }

  @NonNull
  @Override
  public ChartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.artist_chart_list_item, parent, false);
    return new ChartsViewHolder(itemView, listener);
  }

  @Override
  public void onBindViewHolder(@NonNull ChartsViewHolder holder, int position) {
    holder.bind(data.get(position), position);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  void setData(List<Artist> artists) {
    if (artists != null) {
      DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ChartsDiffCallback(data, artists));
      data.clear();
      data.addAll(artists);
      diffResult.dispatchUpdatesTo(this);
    } else {
      data.clear();
      notifyDataSetChanged();
    }
  }


  static final class ChartsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_charts_artist) ImageView artistImage;
    @BindView(R.id.tv_charts_artist_name) TextView artistName;
    @BindView(R.id.tv_charts_artist_listeners) TextView listenersCount;

    private Artist artist;

    public ChartsViewHolder(View itemView, ArtistClickListener listener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(v -> {
        if(artist != null) {
          listener.onArtistClicked(artist);
        }
      });
    }

    void bind(Artist _artist, int position) {
      this.artist = _artist;

      String nameWithPosition = "#" + (position+1) + " : " + artist.artistName();
      String imageUrl = artist.artistImages().get(2).imageUrl();

      artistName.setText(nameWithPosition);
      listenersCount.setText(NumberFormat.getInstance().format(artist.listeners()));

      Glide.with(artistImage.getContext())
          .load(imageUrl)
          .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(10,6)))
          .into(artistImage);
    }
  }

  interface ArtistClickListener {

    void onArtistClicked(Artist artist);
  }
}

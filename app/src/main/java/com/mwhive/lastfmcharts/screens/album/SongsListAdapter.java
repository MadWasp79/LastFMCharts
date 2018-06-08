package com.mwhive.lastfmcharts.screens.album;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mwhive.lastfmcharts.R;
import com.mwhive.lastfmcharts.models.album.Track;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */

public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.SongsViewHolder>{

  private List<Track> data = new ArrayList<>();

  @NonNull
  @Override
  public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.songs_list_item, parent, false);
    return new SongsViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {
    holder.bind(data.get(position));
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  void setData(List<Track> trackList) {
    data.addAll(trackList);
    notifyDataSetChanged();
  }

  public class SongsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_song_title) TextView songTitle;
    @BindView(R.id.tv_duration) TextView songDuration;

    private Track track;

    public SongsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bind(Track track) {
      this.track = track;
      songTitle.setText(track.name());
      songDuration.setText(track.duration());
    }
  }

}

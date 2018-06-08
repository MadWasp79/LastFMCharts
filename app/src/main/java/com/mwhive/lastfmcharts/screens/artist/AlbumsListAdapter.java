package com.mwhive.lastfmcharts.screens.artist;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import com.mwhive.lastfmcharts.models.artist.Album;
import com.mwhive.lastfmcharts.screens.artist.ArtistScreenComponent.Builder;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import timber.log.Timber;

/**
 * Created by MadWasp79 on 04-Jun-18.
 */

public class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.AlbumViewHolder> {

  private final AlbumClickListener listener;
  private final List<Album> data = new ArrayList<>();

  public AlbumsListAdapter(AlbumClickListener listener) {
    this.listener = listener;
  }

  @NonNull
  @Override
  public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.albums_list_item, parent, false);
    return new AlbumViewHolder(itemView, listener);
  }

  @Override
  public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
    holder.bind(data.get(position));
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  void setData(List<Album> albums) {
    List<Album> cleanData = new ArrayList<>();
    for (Album album : albums) {
      if (album.albumName() != null) {
        cleanData.add(album);
      }
    }
    data.addAll(cleanData);
    notifyDataSetChanged();
  }

  public class AlbumViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.iv_album_list) ImageView albumImage;
    @BindView(R.id.tv_charts_album_name) TextView albumName;
    @BindView(R.id.tv_charts_album_listeners) TextView albumListeners;

    private Album album;

    public AlbumViewHolder(View itemView, AlbumClickListener listener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(v -> {
        if(album!=null){
          listener.onAlbumClicked(album);
        }
      });
    }

    void bind(Album album) {
      this.album = album;
      String imageUrl = album.albumImages().get(2).imageUrl();
      albumName.setText(album.albumName());
      albumListeners.setText(album.playcount()+"");
      Glide.with(albumImage.getContext())
          .load(imageUrl)
          .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(10, 6)))
          .into(albumImage);

    }
  }

  interface AlbumClickListener {

    void onAlbumClicked(Album album);
  }
}

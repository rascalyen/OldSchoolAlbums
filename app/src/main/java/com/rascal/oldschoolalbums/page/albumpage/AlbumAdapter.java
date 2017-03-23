package com.rascal.oldschoolalbums.page.albumpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.rascal.oldschoolalbums.R;
import com.rascal.oldschoolalbums.model.AlbumDetail;
import java.util.List;

/**
 * Created by yenhuang on 3/23/17.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder> {

    private List<AlbumDetail> albums;


    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_album, parent, false);
        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {

        final AlbumDetail album = albums.get(position);

        new ImageDownloadTask(holder.thumbnail).execute(album.getThumbnailUrl());
    }

    @Override
    public int getItemCount() {
        return albums == null ? 0 : albums.size();
    }

    public void addAll(List<AlbumDetail> albums) {
        if (!this.albums.isEmpty()) {
            this.albums.clear();
            this.albums.addAll(albums);
        }
        else
            this.albums.addAll(albums);

        notifyDataSetChanged();
    }

    public void setAlbums(List<AlbumDetail> albums) {
        this.albums = albums;
    }


    class AlbumHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;

        public AlbumHolder(final View itemView) {
            super(itemView);

            this.thumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
        }
    }

}

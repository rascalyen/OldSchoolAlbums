package com.rascal.oldschoolalbums.page.mainpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rascal.oldschoolalbums.R;
import com.rascal.oldschoolalbums.model.AlbumTitle;
import com.rascal.oldschoolalbums.navigation.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yenhuang on 3/22/17.
 */
public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleHolder> {

    private List<AlbumTitle> albumTitles;


    @Override
    public TitleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_album_title, parent, false);
        return new TitleHolder(view);
    }

    @Override
    public void onBindViewHolder(TitleHolder holder, int position) {

        final Context context = holder.itemView.getContext();
        final AlbumTitle title = albumTitles.get(position);

        holder.title.setText(title.getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Navigator().navigateToAlbumDetail(context, title.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumTitles == null ? 0 : albumTitles.size();
    }


    public void addAll(List<AlbumTitle> titles) {
        if (!this.albumTitles.isEmpty()) {
            this.albumTitles.clear();
            this.albumTitles.addAll(titles);
        }
        else
            this.albumTitles.addAll(titles);

        notifyDataSetChanged();
    }

    public void clearAll() {
        if (!this.albumTitles.isEmpty()) {
            this.albumTitles.clear();
            notifyDataSetChanged();
        }
    }

    public void setAlbumTitles(ArrayList<AlbumTitle> albumTitles) {
        this.albumTitles = albumTitles;
    }


    class TitleHolder extends RecyclerView.ViewHolder {

        TextView title;

        public TitleHolder(final View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}
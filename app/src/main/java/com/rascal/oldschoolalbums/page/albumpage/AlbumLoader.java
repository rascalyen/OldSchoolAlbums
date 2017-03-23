package com.rascal.oldschoolalbums.page.albumpage;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.rascal.oldschoolalbums.BuildConfig;
import com.rascal.oldschoolalbums.model.AlbumDetail;
import com.rascal.oldschoolalbums.network.NetworkUtil;
import com.rascal.oldschoolalbums.page.mainpage.TitleLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yenhuang on 3/23/17.
 */
public class AlbumLoader extends AsyncTaskLoader<List<AlbumDetail>> {

    private int albumId;

    public AlbumLoader(Context context, int albumId) {
        super(context);

        this.albumId = albumId;
    }

    @Override
    public List<AlbumDetail> loadInBackground() {
        JSONArray jsonArray = NetworkUtil.getJSONArray(BuildConfig.ALBUM_URL + albumId);

        List<AlbumDetail> list = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                AlbumDetail album = new AlbumDetail();
                album.setAlbumId(jsonObject.getInt("albumId"));
                album.setId(jsonObject.getInt("albumId"));
                album.setTitle(jsonObject.getString("title"));
                album.setUrl(jsonObject.getString("url"));
                album.setThumbnailUrl(jsonObject.getString("thumbnailUrl"));

                list.add(album);
            }

        } catch (JSONException e) {
            Log.e(TitleLoader.class.getSimpleName(), e.toString());
        }

        return list;
    }

}
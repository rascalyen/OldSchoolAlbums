package com.rascal.oldschoolalbums.page.mainpage;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.rascal.oldschoolalbums.BuildConfig;
import com.rascal.oldschoolalbums.network.NetworkUtil;
import com.rascal.oldschoolalbums.model.AlbumTitle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yenhuang on 3/22/17.
 */
public class TitleLoader extends AsyncTaskLoader<List<AlbumTitle>> {

    TitleLoader(Context context) {
        super(context);
    }


    @Override
    public List<AlbumTitle> loadInBackground() {

        JSONArray jsonArray = NetworkUtil.getJSONArray(BuildConfig.TITLE_URL);

        List<AlbumTitle> list = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                AlbumTitle title = new AlbumTitle();
                title.setId(jsonObject.getInt("id"));
                title.setUserId(jsonObject.getInt("userId"));
                title.setTitle(jsonObject.getString("title"));

                list.add(title);
            }

        } catch (JSONException e) {
            Log.e(TitleLoader.class.getSimpleName(), e.toString());
        }

        return list;
    }

}
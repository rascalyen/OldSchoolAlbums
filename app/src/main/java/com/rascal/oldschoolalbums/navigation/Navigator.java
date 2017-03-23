package com.rascal.oldschoolalbums.navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.rascal.oldschoolalbums.page.albumpage.AlbumpageActivity;

/**
 * Created by yenhuang on 3/22/17.
 */
public class Navigator {

    public Navigator() {}


    public void navigateToAlbumDetail(@NonNull Context context, int id) {
        context.startActivity(AlbumpageActivity.getCalled(context, id));
    }

}

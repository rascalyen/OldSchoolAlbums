package com.rascal.oldschoolalbums.page.albumpage;

import com.rascal.oldschoolalbums.model.AlbumDetail;
import com.rascal.oldschoolalbums.page.ProgressView;
import java.util.List;

/**
 * Created by yenhuang on 3/23/17.
 */
public interface AlbumView extends ProgressView {

    void viewAlbums(List<AlbumDetail> albumDetails);

}

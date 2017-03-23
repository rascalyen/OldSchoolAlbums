package com.rascal.oldschoolalbums.page.mainpage;

import com.rascal.oldschoolalbums.model.AlbumTitle;
import com.rascal.oldschoolalbums.page.ProgressView;
import java.util.List;

/**
 * Created by yenhuang on 3/22/17.
 */
public interface MainView extends ProgressView {

    void clearTitles();
    void viewTitles(List<AlbumTitle> albumTitles);

}
package com.rascal.oldschoolalbums.page.mainpage;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import com.rascal.oldschoolalbums.model.AlbumTitle;
import com.rascal.oldschoolalbums.page.Presenter;
import java.util.List;

/**
 * Created by yenhuang on 3/22/17.
 */
public class MainPresenter implements Presenter<MainView>, LoaderManager.LoaderCallbacks<List<AlbumTitle>> {

    private MainView mMainView;


    public void initialize() {
        mMainView.showProgress();

        if (mMainView instanceof MainpageActivity) {
            Loader loader = ((MainpageActivity) mMainView.getContext())
                    .getSupportLoaderManager().initLoader(0, null, this);
            loader.forceLoad();
        }
    }

    @Override
    public Loader<List<AlbumTitle>> onCreateLoader(int id, Bundle args) {
        return new TitleLoader(mMainView.getContext());
    }

    @Override
    public void onLoadFinished(Loader<List<AlbumTitle>> loader, List<AlbumTitle> data) {
        if (data != null) {
            mMainView.viewTitles(data);
            mMainView.hideProgress();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<AlbumTitle>> loader) {}


    @Override
    public void attachViewMVP(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void detachViewMVP() {
        this.mMainView = null;
    }

}
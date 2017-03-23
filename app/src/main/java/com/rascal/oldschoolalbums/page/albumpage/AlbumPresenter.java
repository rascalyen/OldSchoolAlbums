package com.rascal.oldschoolalbums.page.albumpage;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import com.rascal.oldschoolalbums.model.AlbumDetail;
import com.rascal.oldschoolalbums.page.Presenter;
import java.util.List;

/**
 * Created by yenhuang on 3/23/17.
 */
class AlbumPresenter implements Presenter<AlbumView>, LoaderManager.LoaderCallbacks<List<AlbumDetail>> {

    private AlbumView mAlbumView;
    private int albumId;


    void initialize(int id) {
        this.albumId = id;
        mAlbumView.showProgress();

        if (mAlbumView instanceof AlbumpageActivity) {
            Loader loader = ((AlbumpageActivity) mAlbumView.getContext())
                    .getSupportLoaderManager().initLoader(0, null, this);
            loader.forceLoad();
        }
    }

    @Override
    public Loader<List<AlbumDetail>> onCreateLoader(int id, Bundle args) {
        return new AlbumLoader(mAlbumView.getContext(), albumId);
    }

    @Override
    public void onLoadFinished(Loader<List<AlbumDetail>> loader, List<AlbumDetail> data) {
        if (data != null) {
            mAlbumView.viewAlbums(data);
            mAlbumView.hideProgress();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<AlbumDetail>> loader) {}

    @Override
    public void attachViewMVP(AlbumView albumView) {
        this.mAlbumView = albumView;
    }

    @Override
    public void detachViewMVP() {
        this.mAlbumView = null;
    }

}
package com.rascal.oldschoolalbums.page.albumpage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import com.rascal.oldschoolalbums.R;
import com.rascal.oldschoolalbums.model.AlbumDetail;
import java.util.ArrayList;
import java.util.List;

public class AlbumpageActivity extends AppCompatActivity implements AlbumView {

    RelativeLayout progressView;
    RecyclerView recyclerView;
    AlbumPresenter mAlbumPresenter;
    AlbumAdapter mAlbumAdapter;

    private static final String EXTRA_ALBUM_ID = "EXTRA_ALBUM_ID";
    private static final String INSTANCE_ALBUM_ID = "INSTANCE_ALBUM_ID";
    private static final String INSTANCE_ALBUM = "INSTANCE_ALBUM";
    private int albumId;
    private ArrayList<AlbumDetail> albumDetails;


    public static Intent getCalled(Context context, int id) {
        Intent intent = new Intent(context, AlbumpageActivity.class);
        intent.putExtra(EXTRA_ALBUM_ID, id);
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INSTANCE_ALBUM_ID, albumId);
        outState.putParcelableArrayList(INSTANCE_ALBUM, albumDetails);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            albumId = savedInstanceState.getInt(INSTANCE_ALBUM_ID);
            albumDetails = savedInstanceState.getParcelableArrayList(INSTANCE_ALBUM);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_recycler);

        bindViews();
        setRecyclerView();
        initialize();
    }

    private void bindViews() {
        progressView = (RelativeLayout) findViewById(R.id.rl_progress);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
    }


    private void setRecyclerView() {
        if (albumDetails == null)
            albumDetails = new ArrayList<>();

        mAlbumAdapter = new AlbumAdapter();
        mAlbumAdapter.setAlbums(albumDetails);
        recyclerView.setAdapter(mAlbumAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initialize() {

        if (mAlbumPresenter == null)
            mAlbumPresenter = new AlbumPresenter();

        mAlbumPresenter.attachViewMVP(this);

        if (albumDetails.isEmpty())
            mAlbumPresenter.initialize(this.getIntent().getIntExtra(EXTRA_ALBUM_ID, 0));
    }


    @Override
    protected void onDestroy() {
        if (mAlbumPresenter != null)
            mAlbumPresenter.detachViewMVP();

        super.onDestroy();
    }

    @Override
    public void viewAlbums(List<AlbumDetail> albumDetails) {
        if (mAlbumAdapter != null) {
            mAlbumAdapter.addAll(albumDetails);
            this.albumDetails = (ArrayList<AlbumDetail>) albumDetails;
        }
    }

    @Override
    public void showProgress() {
        if (progressView != null && progressView.getVisibility() != View.VISIBLE) {
            progressView.setVisibility(View.VISIBLE);
            this.setProgressBarIndeterminateVisibility(true);
        }
    }

    @Override
    public void hideProgress() {
        if (progressView != null && progressView.getVisibility() != View.GONE) {
            progressView.setVisibility(View.GONE);
            this.setProgressBarIndeterminateVisibility(false);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

}
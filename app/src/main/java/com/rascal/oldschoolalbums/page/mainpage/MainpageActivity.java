package com.rascal.oldschoolalbums.page.mainpage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import com.rascal.oldschoolalbums.R;
import com.rascal.oldschoolalbums.model.AlbumTitle;
import java.util.ArrayList;
import java.util.List;

public class MainpageActivity extends AppCompatActivity implements MainView {

    RelativeLayout progressView;
    RecyclerView recyclerView;
    MainPresenter mMainPresenter;
    TitleAdapter mTitleAdapter;

    private static final String INSTANCE_TITLE = "INSTANCE_TITLE";
    private ArrayList<AlbumTitle> albumTitles;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(INSTANCE_TITLE, albumTitles);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null)
            albumTitles = savedInstanceState.getParcelableArrayList(INSTANCE_TITLE);
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
        if (albumTitles == null)
            albumTitles = new ArrayList<>();

        mTitleAdapter = new TitleAdapter();
        mTitleAdapter.setAlbumTitles(albumTitles);
        recyclerView.setAdapter(mTitleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initialize() {

        if (mMainPresenter == null)
            mMainPresenter = new MainPresenter();

        mMainPresenter.attachViewMVP(this);

        if (albumTitles.isEmpty())
            mMainPresenter.initialize();
    }


    @Override
    protected void onDestroy() {
        if (mMainPresenter != null)
            mMainPresenter.detachViewMVP();

        super.onDestroy();
    }

    @Override
    public void clearTitles() {}

    @Override
    public void viewTitles(List<AlbumTitle> albumTitles) {
        if (mTitleAdapter != null) {
            mTitleAdapter.addAll(albumTitles);
            this.albumTitles = (ArrayList<AlbumTitle>) albumTitles;
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
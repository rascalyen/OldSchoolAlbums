package com.rascal.oldschoolalbums.page;

/**
 * Created by yenhuang on 3/22/17.
 */
public interface Presenter<T extends ProgressView> {

    void attachViewMVP(T t);

    void detachViewMVP();

}
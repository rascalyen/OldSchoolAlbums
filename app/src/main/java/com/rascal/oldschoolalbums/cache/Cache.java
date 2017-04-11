package com.rascal.oldschoolalbums.cache;

import android.graphics.Bitmap;
import android.util.LruCache;


public class Cache {

    private static Cache instance;
    private LruCache<String, Bitmap> lru;


    private Cache() {

        /*lru = new LruCache<String, Bitmap>(64);*/
        lru = new LruCache<String, Bitmap>(32 * 1024 *1024) {

            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

    }

    public static Cache getInstance() {
        if (instance == null)
            instance = new Cache();

        return instance;
    }

    public LruCache<String, Bitmap> getLru() {
        return lru;
    }

}
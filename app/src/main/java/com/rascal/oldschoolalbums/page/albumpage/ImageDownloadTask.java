package com.rascal.oldschoolalbums.page.albumpage;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.rascal.oldschoolalbums.cache.Cache;
import com.rascal.oldschoolalbums.network.NetworkUtil;
import java.lang.ref.WeakReference;

/**
 * Created by yenhuang on 3/23/17.
 */
class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;
    private final String imageUrl;


    ImageDownloadTask(ImageView imageView, String url) {
        imageViewReference = new WeakReference<>(imageView);
        imageUrl = url;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        if (Cache.getInstance().getLru().get(imageUrl) != null)
            return Cache.getInstance().getLru().get(imageUrl);

        return NetworkUtil.getBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) return;

        ImageView imageView = imageViewReference.get();
        if (imageView != null) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

        if (Cache.getInstance().getLru().get(imageUrl) == null)
            Cache.getInstance().getLru().put(imageUrl, bitmap);
    }

}
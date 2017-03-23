package com.rascal.oldschoolalbums.page.albumpage;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.rascal.oldschoolalbums.network.NetworkUtil;
import java.lang.ref.WeakReference;

/**
 * Created by yenhuang on 3/23/17.
 */
public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;


    public ImageDownloadTask(ImageView imageView) {
        imageViewReference = new WeakReference<>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = NetworkUtil.getBitmap(params[0]);
        return bitmap;
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
    }

}
package com.rascal.oldschoolalbums.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.rascal.oldschoolalbums.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by yenhuang on 3/22/17.
 */
public class NetworkUtil {

    static public JSONArray getJSONArray(String urlString) {

        HttpsURLConnection conn = null;
        JSONArray jsonArray = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(BuildConfig.TIMEOUT_MILLISECONDS);
            conn.setConnectTimeout(BuildConfig.TIMEOUT_MILLISECONDS);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer res = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    res.append(line);
                }
                reader.close();

                jsonArray = new JSONArray(res.toString());
            }

        } catch (IOException | JSONException e) {
            cancelConnection(conn);
            Log.e(NetworkUtil.class.getSimpleName(), "Error of getJSONArray on " + urlString);
        } finally {
            cancelConnection(conn);
        }

        return jsonArray;
    }

    static public Bitmap getBitmap(String urlString) {

        URL url;
        HttpURLConnection conn = null;
        boolean redirect = false;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "image/png");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
                redirect = true;
                cancelConnection(conn);
            }

            if (redirect)  {
                String newUrl = conn.getHeaderField("Location");
                url = new URL(newUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "image/png");
                conn.connect();

                responseCode = conn.getResponseCode();
            }

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                if (inputStream != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    return bitmap;
                }
            }

        } catch (IOException e) {
            cancelConnection(conn);
            Log.e(NetworkUtil.class.getSimpleName(), "Error of getBitmap on " + urlString);
        } finally {
            cancelConnection(conn);
        }

        return null;
    }

    static private void cancelConnection(HttpURLConnection connection) {
        if (connection != null)
            connection.disconnect();
    }

}
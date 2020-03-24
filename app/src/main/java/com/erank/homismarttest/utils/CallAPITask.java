package com.erank.homismarttest.utils;

import android.os.AsyncTask;

import com.erank.homismarttest.models.JsonUser;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CallAPITask extends AsyncTask<JsonUser, String, String> {

    private Callback callback;
    private String apiUrl;

    public CallAPITask(Callback callback, String urlStr) {
        this.callback = callback;
        apiUrl = urlStr;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(JsonUser... users) {

        String json = new Gson().toJson(users[0], JsonUser.class);
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(out, StandardCharsets.UTF_8));
            writer.write(json);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        callback.callDone();
    }

    public interface Callback {
        void callDone();
    }
}

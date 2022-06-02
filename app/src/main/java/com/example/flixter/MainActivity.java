package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String NOW_PLAYING = "https://api.themoviedb.org/3/movie/now_playing?api_key=9cf5af5129f4f863d5cf57a867a4084c";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get(NOW_PLAYING, params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.d("MainActivity", "success");
                        JSONObject jsonObject = json.jsonObject;
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            Log.i("MainActivity", "results: " + jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("MainActivity", "hit json exception", e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.d("MainActivity", "failure");

                    }
                }
        );
    }
}
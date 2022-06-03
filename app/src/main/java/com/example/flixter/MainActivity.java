package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;
import com.example.flixter.adapters.MovieAdapter;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String NOW_PLAYING = "https://api.themoviedb.org/3/movie/now_playing?api_key=9cf5af5129f4f863d5cf57a867a4084c";
    List<Movie> movieList;
    //private ActivitySimpleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movieList = new ArrayList<>();
        MovieAdapter movieAdapter = new MovieAdapter(this,movieList);

        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get(NOW_PLAYING, params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.d("MainActivity", "success");
                        JSONObject jsonObject = json.jsonObject;
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            movieList.addAll(Movie.fromJsonArray(jsonArray));
                            //movieList = Movie.fromJsonArray(jsonArray);
                            movieAdapter.notifyDataSetChanged();

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
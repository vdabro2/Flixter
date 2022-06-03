package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.parceler.Parcels;

public class MovieDetailsActivity extends YouTubeBaseActivity {
    Movie movie;

    TextView tvTitle2;
    TextView tvOverview2;
    RatingBar rbVoteAverage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //https://api.themoviedb.org/3/movie/338953/videos?api_key=9cf5af5129f4f863d5cf57a867a4084c&language=en-US
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        tvOverview2 = (TextView) findViewById(R.id.tvOverview2);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);


        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        tvTitle2.setText(movie.getTitle());
        tvOverview2.setText(movie.getOverview());

        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);

        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize("YOUR API KEY",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo("5xVh-7ywKpE");
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
}
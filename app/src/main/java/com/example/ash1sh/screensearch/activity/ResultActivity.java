package com.example.ash1sh.screensearch.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ash1sh.screensearch.MainActivity;
import com.example.ash1sh.screensearch.R;
import com.example.ash1sh.screensearch.adapter.MoviesAdapter;
import com.example.ash1sh.screensearch.model.Movie;
import com.example.ash1sh.screensearch.model.MoviesResponse;
import com.example.ash1sh.screensearch.rest.ApiClient;
import com.example.ash1sh.screensearch.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends AppCompatActivity {


    // API fields
    private static final String TAG = ResultActivity.class.getSimpleName();
    private final static String API_KEY = "e6b16025cc8301efd9259466db475f34";

    // String data to be sent through

    private String yeardata, sortbydata, genredata, methodcode;

    // movie object for manipulation
    public List<Movie> movies;

    // Declared variables for Recycler Views
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

       // CHECKPOINT
        // Data is being sent and getting received at the result activity with proper arguements needed for the query

       Intent datarec = getIntent();
        yeardata = datarec.getStringExtra("yeardata");
        genredata = datarec.getStringExtra("genredata");
        sortbydata = datarec.getStringExtra("sortbydata");
        methodcode = datarec.getStringExtra("methodcode");


        // adding on method for retrieving data from the methods
        getDataFromApi();



        // collapsing toolbar and its method

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initCollapsingToolbar();
        // completed with collapsing toolbar and its method



        // Recycler View and its analysis
        adapter = new MoviesAdapter(this, movies);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        // backdrop for collapsing toolbar image is added
        // TODO -- Add code here while designing the application


    }





    // method functionality checked and works perfectly fine.

    public void getDataFromApi()
    {

        if(methodcode.equals("popular"))
        {
            ApiInterface apiServicePopular =
                    ApiClient.getClient().create(ApiInterface.class);
            // second response needed
            Call<MoviesResponse> callPopular = apiServicePopular.getPopularMovies(API_KEY,"");
            callPopular.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Toast.makeText(getApplicationContext(),"Popular Movies Received",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<MoviesResponse>call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getApplicationContext(),"Error in Receiving",Toast.LENGTH_LONG).show();

                }
            });

        }
        else if(methodcode.equals("nowplaying"))
        {
            ApiInterface apiServiceNowPlay =
                    ApiClient.getClient().create(ApiInterface.class);
            // second response needed
            Call<MoviesResponse> callNowPlay = apiServiceNowPlay.getNowPlayingMovies(API_KEY,"");
            callNowPlay.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Toast.makeText(getApplicationContext(),"Now Playing Movies Received",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<MoviesResponse>call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getApplicationContext(),"Error in Receiving",Toast.LENGTH_LONG).show();

                }
            });

        }
        else if(methodcode.equals("toprated"))
        {
            ApiInterface apiServiceTopRated =
                    ApiClient.getClient().create(ApiInterface.class);
            // second response needed
            Call<MoviesResponse> callTopRated = apiServiceTopRated.getTopRatedMovies(API_KEY,"");
            callTopRated.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Toast.makeText(getApplicationContext(),"TopRated Movies Received",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<MoviesResponse>call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getApplicationContext(),"Error in Receiving",Toast.LENGTH_LONG).show();

                }
            });

        }
        else if(methodcode.equals("upcoming"))
        {
            ApiInterface apiServiceUpcoming =
                    ApiClient.getClient().create(ApiInterface.class);
            // second response needed
            Call<MoviesResponse> callUpcoming = apiServiceUpcoming.getPopularMovies(API_KEY,"");
            callUpcoming.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Toast.makeText(getApplicationContext(),"Upcoming Movies Received",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<MoviesResponse>call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getApplicationContext(),"Error in Receiving",Toast.LENGTH_LONG).show();

                }
            });
        }
        else if(methodcode.equals("discover"))
        {
            ApiInterface apiServiceDiscover =
                    ApiClient.getClient().create(ApiInterface.class);
            // second response needed
            Call<MoviesResponse> callDiscover = apiServiceDiscover.getDiscoveredMovies(API_KEY,sortbydata,genredata,yeardata,"");
            callDiscover.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Toast.makeText(getApplicationContext(),"Searched Movies Received",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<MoviesResponse>call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getApplicationContext(),"Error in Receiving",Toast.LENGTH_LONG).show();

                }
            });

        }

    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}




package com.example.ash1sh.screensearch.adapter;


import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ash1sh.screensearch.R;
import com.example.ash1sh.screensearch.model.Movie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;



public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movieList;

    // default value regarding movieList





    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleview, genreview, ratingsview;
        public ImageView thumbnailview, overflowview;


        public MyViewHolder(View view) {
            super(view);

            // TextViews
            titleview = (TextView) view.findViewById(R.id.titleview);
            genreview = (TextView) view.findViewById(R.id.genreview);
            ratingsview = (TextView) view.findViewById(R.id.ratingview);

            // ImageViews
            thumbnailview = (ImageView) view.findViewById(R.id.thumbnailview);
            overflowview = (ImageView) view.findViewById(R.id.overflowview);


        }
    }

    public MoviesAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MoviesAdapter.MyViewHolder holder, int position) {

        Movie movie = movieList.get(position);
        holder.titleview.setText(movie.getOriginalTitle());

        // declaring hashmap for transferring genre id's into a concatenated string

        LinkedHashMap<Integer,String> genremaprev = new LinkedHashMap<>();
        genremaprev.put(28, "Action");
        genremaprev.put(12,"Adventure");
        genremaprev.put(16,"Animation");
        genremaprev.put(35,"Comedy");
        genremaprev.put(80,"Crime");
        genremaprev.put(99,"Documentary");
        genremaprev.put(18,"Drama");
        genremaprev.put(10751,"Family");
        genremaprev.put(14,"Fantasy");
        genremaprev.put(36,"History");
        genremaprev.put(27,"Music");
        genremaprev.put(9648,"Mystery");
        genremaprev.put(10749,"Romance");
        genremaprev.put(878,"Science Fiction");
        genremaprev.put(10770,"TV Movie");
        genremaprev.put(53,"Thriller");
        genremaprev.put(10752,"War");
        genremaprev.put(37,"Western");

        // end of HashMap

        ArrayList<String> genrestrings = new ArrayList<>();
        ArrayList<Integer> genreints = (ArrayList<Integer>) movie.getGenreIds();
        String genretext = "";
        for(Integer val : genreints)
        {
                genrestrings.add(genremaprev.get(val));

        }

        for(String stringval : genrestrings) {

            if(genrestrings.indexOf(stringval) != genrestrings.size()-1)
                genretext += genrestrings.get(genrestrings.indexOf(stringval))+", ";    //simply genretext = stringval + ", "
            else
                genretext += genrestrings.get(genrestrings.indexOf(stringval))+". ";    //simply genretext = stringval + ". "
        }

        holder.genreview.setText(genretext);

        // end genre text setting work

        holder.ratingsview.setText(movie.getVoteAverage().toString());

        // Glide poster addition from the database using glide library


        String baseimgurl = "https://image.tmdb.org/t/p/w300";

        Glide.with(mContext).load(baseimgurl+movie.getBackdropPath()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.thumbnailview);




    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menumovie, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.more_info:
                    // TODO -- Need to insert code for triggering new activity
                    Toast.makeText(mContext, "More Info", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.add_watch:
                    // TODO -- Need to insert code for adding movies to watchlist
                    Toast.makeText(mContext, "Added To WatchList", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {

        return movieList.size();
    }
}

package com.example.ash1sh.screensearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ash1sh.screensearch.R;
import com.example.ash1sh.screensearch.activity.AboutUs;
import com.example.ash1sh.screensearch.activity.ResultActivity;
import com.example.ash1sh.screensearch.model.Movie;
import com.example.ash1sh.screensearch.model.MoviesResponse;
import com.example.ash1sh.screensearch.rest.ApiClient;
import com.example.ash1sh.screensearch.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private Spinner spinner,spinner2, spinner3;
    // Maps for spinner
    private static LinkedHashMap<String, String> genremap, sortbymap;

    // String data to be sent through
    private String yeardata, sortbydata, genredata;
    private static ArrayList<String> genre,year,sortby;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        // arraylist works fine for this year spinner data

        year = new ArrayList<>();

        year.add("None");

        for(int i=1900;i<=2017;i++)
             year.add(Integer.toString(i));




        genre = new ArrayList<>();
        genre.add("None");
        genre.add("Action");
        genre.add("Adventure");
        genre.add("Animation");
        genre.add("Comedy");
        genre.add("Crime");
        genre.add("Documentary");
        genre.add("Drama");
        genre.add("Family");
        genre.add("Fantasy");
        genre.add("History");
        genre.add("Music");
        genre.add("Mystery");
        genre.add("Romance");
        genre.add("Science Fiction");
        genre.add("TV Movie");
        genre.add("Thriller");
        genre.add("War");
        genre.add("Western");


        sortby = new ArrayList<>();
        sortby.add("None");
        sortby.add("Popularity Ascending");
        sortby.add("Popularity Descending");
        sortby.add("Rating Ascending");
        sortby.add("Rating Descending");
        sortby.add("Release Date Ascending");
        sortby.add("Release Date Descending");
        sortby.add("Revenue Ascending");
        sortby.add("Revenue Descending");
        sortby.add("Title (A-Z)");
        sortby.add("Title (Z-A)");




        // spinner content

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year);
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter1);



        // spinner1 content


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genre);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);



        // spinner2 content


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sortby);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner3.setAdapter(dataAdapter3);



        // Need of hashmap for spinner of genre and sort by

        genremap = new LinkedHashMap<>();
        genremap.put("None","");
        genremap.put("Action","28");
        genremap.put("Adventure","12");
        genremap.put("Animation","16");
        genremap.put("Comedy","35");
        genremap.put("Crime","80");
        genremap.put("Documentary","99");
        genremap.put("Drama","18");
        genremap.put("Family","10751");
        genremap.put("Fantasy","14");
        genremap.put("History","36");
        genremap.put("Music","27");
        genremap.put("Mystery","9648");
        genremap.put("Romance","10749");
        genremap.put("Science Fiction","878");
        genremap.put("TV Movie","10770");
        genremap.put("Thriller","53");
        genremap.put("War","10752");
        genremap.put("Western","37");


        sortbymap = new LinkedHashMap<>();
        sortbymap.put("None","");
        sortbymap.put("Popularity Ascending","popularity.asc");
        sortbymap.put("Popularity Descending","popularity.desc");
        sortbymap.put("Rating Ascending","vote_average.asc");
        sortbymap.put("Rating Descending","vote_average.desc");
        sortbymap.put("Release Date Ascending","release_date.asc");
        sortbymap.put("Release Date Descending","release_date.desc");
        sortbymap.put("Revenue Ascending","revenue.asc");
        sortbymap.put("Revenue Descending","revenue.desc");
        sortbymap.put("Title (A-Z)","original_title.asc");
        sortbymap.put("Title (Z-A)","original_title.desc");



        // implementing listeners for each of the spinners individually


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
        @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                yeardata = spinner.getSelectedItem().toString();
                if(yeardata==null || yeardata=="None")
                    yeardata = "";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                genredata= spinner2.getSelectedItem().toString();
                if(genredata==null)
                    genredata = "None";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                sortbydata = spinner3.getSelectedItem().toString();
                if(sortbydata==null)
                    sortbydata = "None";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menumain, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {

            case R.id.Popular:
                Intent pintent = new Intent(this, ResultActivity.class);
                pintent.putExtra("methodcode","popular");
                startActivity(pintent);
                return true;


            case R.id.NowPlaying:
                Intent npintent = new Intent(this, ResultActivity.class);
                npintent.putExtra("methodcode", "nowplaying");
                startActivity(npintent);
                return true;


            case R.id.TopRated:
                Intent tpintent = new Intent(this, ResultActivity.class);
                tpintent.putExtra("methodcode", "toprated");
                startActivity(tpintent);
                return true;


            case R.id.Upcoming:
                Intent uintent = new Intent(this, ResultActivity.class);
                uintent.putExtra("methodcode","upcoming");
                startActivity(uintent);
                return true;

            // division in menu items as no connection is needed here.

            case R.id.AboutUs:

                // TODO --  Insert Valid Activity Intent Code In It.
                Intent i = new Intent(this, AboutUs.class);
                startActivity(i);
                return true;

            case R.id.WatchList:

                // TODO --  Insert Valid Activity Intent Code In It.
                // currently no dealing with databases

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }




    public void onSendData(View view)
    {
        Intent datasent = new Intent(this, ResultActivity.class);


            datasent.putExtra("yeardata",yeardata);
            datasent.putExtra("genredata",genremap.get(genredata));
            datasent.putExtra("sortbydata",sortbymap.get(sortbydata));
            datasent.putExtra("methodcode","discover");
            startActivity(datasent);

    }


}



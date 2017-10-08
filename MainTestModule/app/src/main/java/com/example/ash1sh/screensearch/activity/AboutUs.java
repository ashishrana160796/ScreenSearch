package com.example.ash1sh.screensearch.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ash1sh.screensearch.R;

public class AboutUs extends AppCompatActivity {

    String url = "https://github.com/ashishrana160796/PixelPlay";
    String mailid = "arana_be15@thapar.edu";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

// For Starting Git in Browser

    public void onGitClick(View view){


        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

// For opening gmail to work upon

    public void onGmailClick(View view){

    /*

    // The code below only open gmail to perform the mailing task.

    Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
    startActivity(intent);
*/

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", mailid, null));
        // scheme, ssp , fragment
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hi Ashish, ");
        startActivity(Intent.createChooser(emailIntent, null));

    }

}

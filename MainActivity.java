package com.logicmaster.a99nameofallah;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    ArrayList<String> geteng;
    private AdView mAdView;
    InterstitialAd mInterstitialAd;
    private static final int SELECTED_PICTURE=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
               /*SCREEN WILL ON TILL TO LAST TOAST TEXT SHOW*/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                    /*MAKING ACTIVITY FULL SCREEN*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //  gridView = (ListView) findViewById(R.id.lst);

        dbHelper = new DatabaseHelper(this);
        dbHelper.createDatabse();
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {


            }
        });

        geteng = dbHelper.listAlphabet();
     /*   final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               android.R.layout.activity_list_item, geteng);
        ListView gridView=(ListView)findViewById(R.id.g1);

        gridView.setAdapter(adapter);*/
        ArrayAdapter<String> st=new ArrayAdapter<String>(this,R.layout.lst,geteng);
        ListView aa=(ListView)findViewById(R.id.lst);
        aa.setAdapter(st);

        aa.setOverscrollFooter(new ColorDrawable(Color.TRANSPARENT));
        aa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
               // Toast.makeText(MainActivity.this, "it will go to next level", Toast.LENGTH_SHORT).show();



//
//                 Toast.makeText(getApplicationContext(),
//                ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, detail.class );
                i.putExtra("key", position);
                //Toast.makeText(getApplicationContext(), "vlaue is "+position, Toast.LENGTH_LONG).show();
                startActivity(i);
                //Intent i = new Intent(MainActivity.this, more_info.class );
                //i.putExtra("key", position);
                // Toast.makeText(getApplicationContext(), "vlaue is "+position, Toast.LENGTH_LONG).show();
                //startActivity(i);
            }
        });


    }
    private static final int TIME_INTERVAL = 4000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    public void onBackPressed() {

       showInterstitial();

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button  to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}

package com.logicmaster.a99nameofallah;

import android.speech.tts.TextToSpeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Locale;
import java.util.ArrayList;

public class detail extends AppCompatActivity  {
    ArrayList<String> geteng,getword,getwrd;
    DatabaseHelper dbHelper;
    TextView t1,t2,t9;
    Button b1;
    TextToSpeech t7;
    ImageView img1;
    private AdView mAdView;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
               /*SCREEN WILL ON TILL TO LAST TOAST TEXT SHOW*/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                    /*MAKING ACTIVITY FULL SCREEN*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        //String stringData= intent.getStringExtra("key");
        int intValue = intent.getIntExtra("key", 0);
        //String data=stringData;
        int id = intValue+1;
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
        //v = Integer.parseInt(stringData)+1;
        // int result = Integer.parseInt(stringData);
        //int result = Integer.parseInt( data);

        // Toast.makeText(getApplicationContext(), ""+v, Toast.LENGTH_SHORT).show();
//        t7=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if(status != TextToSpeech.ERROR) {
//                    t7.setLanguage(Locale.UK);
//                }
//            }
//        });
        t7=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t7.setLanguage(Locale.UK);
                }
            }
        });

        dbHelper = new DatabaseHelper(this);
        dbHelper.createDatabse();
        geteng = dbHelper.getAlphabet(id);
        getword=dbHelper.getword(id);
        getwrd=dbHelper.benifit(id);
        // Toast.makeText(this, ""+geteng, Toast.LENGTH_SHORT).show();
        t1=(TextView)findViewById(R.id.alphabet);
        String s = ""+geteng;
        String regex = "\\[|\\]";
        s = s.replaceAll(regex, "");
        t1.setText(""+s);



        t9=(TextView)findViewById(R.id.t9);

        String e = ""+getwrd;
        String rgex = "\\[|\\]";
        e = e.replaceAll(rgex, "");
        t9.setText(""+e);
        t2=(TextView)findViewById(R.id.textView2);
        String d = ""+getword;
        String rex = "\\[|\\]";
        d = d.replaceAll(rex, "");
        t2.setText(""+d);
        //t2.setText(""+getword);

        img1=(ImageView)findViewById(R.id.image);
//        img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String toSpeak = t2.getText().toString();
//                //  Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
//                // t7.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
//                t7.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
//            }
//        });

        b1=(Button)findViewById(R.id.talk);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak = t9.getText().toString();
                //  Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                // t7.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                t7.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void onPause(){
        if(t7 !=null){
            t7.stop();
            t7.shutdown();
        }
        super.onPause();
    }
    public void onBackPressed() {
        showInterstitial();
        super.onBackPressed();

        Intent i=new Intent(detail.this,MainActivity.class);
        startActivity(i);

    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}


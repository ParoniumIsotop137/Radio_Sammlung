package com.ferenc.radio_sammlung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ferenc.radio_sammlung.data.AssetReader;
import com.ferenc.radio_sammlung.radio.Radio;
import com.ferenc.radio_sammlung.web.RadioHomePage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar menuBar;
    private AssetReader reader;
    private List<Radio> radios;

    private ImageButton imBtnPlay,imBtnStop;

    private Button btnHomePage;

    private TextView txtRadioName, txtRunning;

    private String webPage;
    private static MediaPlayer player = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(getIntent());
        }
        menuBar=findViewById(R.id.main_menu);
        menuBar.setTitleTextColor(Color.parseColor("#009688"));
        setSupportActionBar(menuBar);

        radios = new ArrayList<Radio>();
        reader = new AssetReader();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        ReadList();

        imBtnPlay = findViewById(R.id.imBtnPlay);
        imBtnPlay.setEnabled(false);
        imBtnStop = findViewById(R.id.imBtnStop);

        btnHomePage = findViewById(R.id.btnHomePage);

        txtRadioName = findViewById(R.id.txtRadioName);
        txtRunning = findViewById(R.id.txtRunning);

        txtRadioName.setVisibility(View.INVISIBLE);
        txtRunning.setVisibility(View.INVISIBLE);

        webPage = "";

    }

    private void ReadList() {

        try {

            InputStreamReader isr = new InputStreamReader(getAssets().open("sendungen.csv"));

            radios = reader.ReadRadioList(isr, ";");

        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       //switch-case nem működik itt / switch-case funktioniert hier nicht / switch-case does not working there

        int radioId = 0;

        if(item.getItemId() == R.id.radio_salzburg){

            radioId = 1;
            LoadRadioStream(radioId);

        } else if (item.getItemId() == R.id.rock_antenne_deutsch) {

            radioId = 2;
            LoadRadioStream(radioId);

        }else if (item.getItemId() == R.id.rock_antenne_live_rock) {

            radioId = 3;
            LoadRadioStream(radioId);

        }else if (item.getItemId() == R.id.rock_antenne_70) {

            radioId = 4;
            LoadRadioStream(radioId);

        }else if (item.getItemId() == R.id.rock_antenne_80) {

            radioId = 5;
            LoadRadioStream(radioId);

        }else if (item.getItemId() == R.id.rock_antenne_90) {

            radioId = 6;
            LoadRadioStream(radioId);

        }else if (item.getItemId() == R.id.rock_antenne_modern_rock) {

            radioId = 7;
            LoadRadioStream(radioId);

        }else if (item.getItemId() == R.id.rock_antenne_munich_city) {

            radioId = 8;
            LoadRadioStream(radioId);

        }else if (item.getItemId() == R.id.rock_antenne_symphonic) {

            radioId = 9;
            LoadRadioStream(radioId);

        }else if (item.getItemId() == R.id.antenne_vorarlberg_live) {

            radioId = 10;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.antenne_vorarlberg_plus80_90) {

            radioId = 11;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.antenne_vorarlberg_classic_rock) {

            radioId = 12;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.antenne_vorarlberg_schlager) {

            radioId = 13;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.antenne_vorarlberg_rock) {

            radioId = 14;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.u_1_tirol) {

            radioId = 15;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_kaernten) {

            radioId = 16;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_noe) {

            radioId = 17;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_ooe) {

            radioId = 18;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_stm) {

            radioId = 19;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_wien) {

            radioId = 20;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_886_on_air) {

            radioId = 21;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_886_hard_rock) {

            radioId = 22;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_886_classic_rock) {

            radioId = 23;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_886_new_rock) {

            radioId = 24;
            LoadRadioStream(radioId);
        }
        else if (item.getItemId() == R.id.radio_886_rotweiss_rock) {

            radioId = 25;
            LoadRadioStream(radioId);
        }


        return super.onOptionsItemSelected(item);
    }


    private void LoadRadioStream(int radioId) {

        txtRunning.setVisibility(View.VISIBLE);

        if(radioId != 0){
            StopRadio();
            try {
                for (Radio radio : radios) {
                    if (radio.getRadioId() == radioId) {
                        player.setDataSource(radio.getStreamUrl());
                        txtRadioName.setText(radio.getName());
                        webPage = radio.getHomePageUrl();
                        imBtnPlay.setEnabled(true);
                        Toast.makeText(this, radio.getName()+" ist startbereit!", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.imBtnPlay){
            PlayRadio();
        } else if (view.getId() == R.id.imBtnStop) {
            StopRadio();
        } else if (view.getId() == R.id.btnHomePage) {
            StartHomePageView(webPage);
        }

    }

    private void StartHomePageView(String webPage) {

        if(webPage != null && !webPage.isEmpty()){
            Intent intent = new Intent(this, RadioHomePage.class);
            intent.putExtra("link", webPage);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Es ist noch kein Radio gestartet worden!", Toast.LENGTH_LONG).show();
        }



    }

    private void StopRadio() {

        if(player != null && player.isPlaying()){
            player.stop();
            player.reset();
            txtRadioName.setVisibility(View.INVISIBLE);

        }


    }

    private void PlayRadio() {

        if(player != null){
            try {
                player.prepare();
                player.start();
                txtRadioName.setVisibility(View.VISIBLE);
                btnHomePage.setVisibility(View.VISIBLE);
                imBtnPlay.setEnabled(false);
                imBtnStop.setEnabled(true);
            } catch (IOException e) {
                Toast.makeText(this, "Die Sendung konnte nicht geladen werden! "+e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
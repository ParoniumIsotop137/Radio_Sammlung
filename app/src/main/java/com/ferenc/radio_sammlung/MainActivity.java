package com.ferenc.radio_sammlung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ferenc.radio_sammlung.data.AssetReader;
import com.ferenc.radio_sammlung.radio.Radio;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar menuBar;
    private AssetReader reader;
    private List<Radio> radios;

    private ImageButton imBtnPlay,imBtnStop;

    private static MediaPlayer player = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuBar=findViewById(R.id.main_menu);
        setSupportActionBar(menuBar);

        radios = new ArrayList<Radio>();
        reader = new AssetReader();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        ReadList();

        imBtnPlay = findViewById(R.id.imBtnPlay);
        imBtnPlay.setEnabled(false);
        imBtnStop = findViewById(R.id.imBtnStop);

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

        }else if (item.getItemId() == R.id.radio_osttirol) {

            radioId = 10;
            LoadRadioStream(radioId);
        }


        return super.onOptionsItemSelected(item);
    }


    private void LoadRadioStream(int radioId) {

        if(radioId != 0){
            try {
                for (Radio radio : radios) {
                    if (radio.getRadioId() == radioId) {
                        player.setDataSource(radio.getStreamUrl());
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
        }

    }

    private void StopRadio() {

        if(player != null && player.isPlaying()){
            player.stop();
            player.reset();

        }


    }

    private void PlayRadio() {

        if(player != null){
            try {
                player.prepare();
                player.start();
                imBtnPlay.setEnabled(false);
                imBtnStop.setEnabled(true);
            } catch (IOException e) {
                Toast.makeText(this, "Die Sendung konnte nicht geladen werden! "+e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
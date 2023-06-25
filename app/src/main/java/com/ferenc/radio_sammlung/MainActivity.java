package com.ferenc.radio_sammlung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Toolbar menuBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuBar=findViewById(R.id.main_menu);
        setSupportActionBar(menuBar);
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

        }

        return super.onOptionsItemSelected(item);
    }

    private void LoadRadioStream(int radioId) {

        Toast.makeText(this,"Sikeres teszt!", Toast.LENGTH_LONG).show();

    }
}
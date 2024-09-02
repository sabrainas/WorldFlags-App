package com.example.worldflags;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.shadow.ShadowRenderer;

public class GameOver extends AppCompatActivity {

    private TextView txtPoints, txtPersonalBest;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_over);

        int points = getIntent().getIntExtra("pontos", 0);

        txtPoints = findViewById(R.id.txtPoints);
        txtPersonalBest = findViewById(R.id.txtPersonalBest);

        txtPoints.setText(String.valueOf(points));
        sharedPreferences = getSharedPreferences("pref", 0);

        int pointsSP = sharedPreferences.getInt("pointsSP", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(points > pointsSP){
            pointsSP = points;
            editor.putInt("pointsSP", pointsSP);
            editor.apply();
        }

        txtPersonalBest.setText(String.valueOf(pointsSP));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void restart(View view){
        Intent intent = new Intent(GameOver.this, StartGame.class);
        startActivity(intent);

        finish();
    }

    public void telaPrincipal(View view) {
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        finish();
    }
}
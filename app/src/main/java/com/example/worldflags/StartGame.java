package com.example.worldflags;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartGame extends AppCompatActivity {

    private TextView txtResult;
    private ImageView imgShowImage;
    private HashMap<String, Integer> listaPaises = new HashMap<>();
    private ArrayList<String> listaDescricao = new ArrayList<>();
    private int index = 0;  // Inicializa o índice com 0
    private RadioButton rd1, rd2, rd3, rd4;
    private TextView txtPoints;
    private int points;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start_game);

        txtResult = findViewById(R.id.txtResult);
        imgShowImage = findViewById(R.id.imgShowImage);
        rd1 = findViewById(R.id.rd1);
        rd2 = findViewById(R.id.rd2);
        rd3 = findViewById(R.id.rd3);
        rd4 = findViewById(R.id.rd4);
        txtPoints = findViewById(R.id.txtPoints);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setEnabled(false);

        // Adiciona os países com suas respectivas imagens ao HashMap
        listaPaises.put("Brasil", R.drawable.brasil);
        listaPaises.put("Bulgaria", R.drawable.bulgaria);
        listaPaises.put("Canadá", R.drawable.canada);
        listaPaises.put("China", R.drawable.china);
        listaPaises.put("Coreia do Sul", R.drawable.coreia_sul);
        listaPaises.put("Cuba", R.drawable.cuba);
        listaPaises.put("Guatemala", R.drawable.guatemala);
        listaPaises.put("Hungria", R.drawable.hungria);
        listaPaises.put("Itália", R.drawable.italia);
        listaPaises.put("Estados Unidos", R.drawable.usa);

        // Preenche a lista de descrições
        listaDescricao.addAll(listaPaises.keySet());
        Collections.shuffle(listaDescricao);
        points = 0;
        startGame();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void generateQuestions(int index) {
        String respostaCorreta = listaDescricao.get(index);
        ArrayList<String> novaLista = new ArrayList<>(listaDescricao);
        novaLista.remove(respostaCorreta);
        Collections.shuffle(novaLista);

        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add(respostaCorreta);
        opcoes.add(novaLista.get(0));
        opcoes.add(novaLista.get(1));
        opcoes.add(novaLista.get(2));
        Collections.shuffle(opcoes);

        rd1.setChecked(false);
        rd2.setChecked(false);
        rd3.setChecked(false);
        rd4.setChecked(false);

        rd1.setText(opcoes.get(0));
        rd2.setText(opcoes.get(1));
        rd3.setText(opcoes.get(2));
        rd4.setText(opcoes.get(3));

        imgShowImage.setImageResource(listaPaises.get(respostaCorreta));

        btnNext.setEnabled(false);

    }

    public void answerSelected(View view) {
        String resposta = ((RadioButton) view).getText().toString().trim();
        String respostaCorreta = listaDescricao.get(index);

        if (resposta.equals(respostaCorreta)) {
            points++;
        }

        txtPoints.setText(points + " / " + listaDescricao.size());
        btnNext.setEnabled(true);
    }


    public void nextQuestion(View view) {
        index++;
        if (index > listaDescricao.size() - 1) {
            imgShowImage.setVisibility(View.GONE);
            rd1.setVisibility(View.GONE);
            rd2.setVisibility(View.GONE);
            rd3.setVisibility(View.GONE);
            rd4.setVisibility(View.GONE);

            Intent intent = new Intent(StartGame.this, GameOver.class);
            intent.putExtra("pontos", points);

            startActivity(intent);
            finish();
        } else {
            startGame();
        }
    }

    private void startGame() {
        txtPoints.setText(points + " / " + listaDescricao.size());
        generateQuestions(index);
    }
}

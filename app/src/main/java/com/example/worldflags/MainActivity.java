package com.example.worldflags;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtRa;
    private EditText edtTxtName;
    private Button btnInit;
    private Button btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtName = findViewById(R.id.txtName);
        txtRa = findViewById(R.id.txtRa);
        edtTxtName = findViewById(R.id.edtTxtName);
        btnInit = findViewById(R.id.btnInit);
        btnOut = findViewById(R.id.btnOut);

        // Inicialmente desabilita o botão de iniciar
        btnInit.setEnabled(false);

        edtTxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Atualiza o TextView com o nome
                txtName.setText(s.toString());

                // Habilita ou desabilita o botão dependendo se o texto está vazio ou não
                btnInit.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnOut.setOnClickListener(this::out);
    }

    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this, StartGame.class);
        String nome = edtTxtName.getText().toString();
        intent.putExtra("Nome", nome);
        startActivity(intent);
        finish();
    }

    public void out(View view) {
        finish();
    }

}
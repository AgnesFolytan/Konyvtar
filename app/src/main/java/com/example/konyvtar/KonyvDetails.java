package com.example.konyvtar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class KonyvDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konyv_detail);

        Button backButton = findViewById(R.id.backButton);
        TextView cimTextView = findViewById(R.id.cimTextView);
        TextView szerzoTextView = findViewById(R.id.szerzoTextView);
        TextView oldalszamTextView = findViewById(R.id.oldalszamTextView);
        TextView evTextView = findViewById(R.id.randomEvTextView);

        String cim = getIntent().getStringExtra("cim");
        String szerzo = getIntent().getStringExtra("szerzo");
        int oldalszam = getIntent().getIntExtra("oldalszam", 50);

        cimTextView.setText(cim);
        szerzoTextView.setText(szerzo);
        oldalszamTextView.setText(String.valueOf(oldalszam));
        Random rnd = new Random();
        evTextView.setText(String.valueOf(rnd.nextInt(2024-1800)+1800));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KonyvDetails.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

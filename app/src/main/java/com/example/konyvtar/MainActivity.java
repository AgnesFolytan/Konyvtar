package com.example.konyvtar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addBookButton;
    private static final ArrayList<Konyvtar> konyvtarArrayList = new ArrayList<>();

    private TextInputLayout konyvCim;
    private TextInputLayout konyvSzerzo;
    private TextInputLayout konyvOldalszam;

    private KonyvtarAdapter konyvtarAdapter;
    private ListView listView;
//    private Button deleteButtom;


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
        Init();
        konyvtarAdapter = new KonyvtarAdapter(konyvtarArrayList, this);
        listView.setAdapter(konyvtarAdapter);

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBookToList();
            }
        });


    }

    public void Init(){
        addBookButton = findViewById(R.id.addBookButton);
        konyvCim = findViewById(R.id.konyvCim);
        konyvSzerzo = findViewById(R.id.konyvSzerzo);
        konyvOldalszam = findViewById(R.id.konyvOldalszam);
        listView = findViewById(R.id.listView);
    }

    private void addBookToList() {
        TextInputEditText konyvCimET = (TextInputEditText) konyvCim.getEditText();
        TextInputEditText konyvSzerzoET = (TextInputEditText) konyvSzerzo.getEditText();
        TextInputEditText konyvOldalszamaEt = (TextInputEditText) konyvOldalszam.getEditText();

        if (konyvCimET != null && konyvSzerzoET != null && konyvOldalszamaEt != null) {
            String cim = konyvCimET.getText().toString().trim();
            String szerzo = konyvSzerzoET.getText().toString().trim();
            String oldalszam = konyvOldalszamaEt.getText().toString().trim();

            if (!cim.isEmpty() && !szerzo.isEmpty() && !oldalszam.isEmpty()){
                try {
                    int oldalszamInt = Integer.parseInt(oldalszam);
                    if (oldalszamInt > 49){
                        Konyvtar konyvtar = new Konyvtar(cim, szerzo, oldalszamInt);
                        konyvtarArrayList.add(konyvtar);
                        konyvtarAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Az oldalszám nem lehet 50nél kevesebb", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(this, "Kérem számot adjon meg oldalszámnak!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Kérem töltsön ki minden mezőt", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
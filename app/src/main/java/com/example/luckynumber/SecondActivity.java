package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewNumber;
    Button buttonShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewNumber = findViewById(R.id.textViewNumber);
        textViewTitle = findViewById(R.id.textViewTitle);
        buttonShare = findViewById(R.id.buttonShare);
        Intent i = getIntent();
        String userName = i.getStringExtra("name");
        int num_random = genratingRandomNumber();
        textViewNumber.setText("" + num_random);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareNumber(userName,num_random);
            }
        });

    }

    private void shareNumber(String userName, int numRandom) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,userName + " Get Lucky Today! ");
        intent.putExtra(Intent.EXTRA_TEXT, "His Lucky Number Today Is : " + numRandom);
        startActivity(Intent.createChooser(intent,"choose a platform"));
    }
    public int genratingRandomNumber(){
        Random random = new Random();
        int upper_limit = 1000;
        int numberGenerated = random.nextInt(upper_limit);
        return numberGenerated;
    }
}
package ru.job4j.okhttpexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EntryActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        start = findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            Intent intent = new Intent(EntryActivity.this, ResultActivity.class);
            intent.putExtra("username", username.getText().toString());
            intent.putExtra("password", password.getText().toString());
            startActivity(intent);
        }
    }
}


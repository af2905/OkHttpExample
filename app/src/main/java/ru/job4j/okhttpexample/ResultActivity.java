package ru.job4j.okhttpexample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class ResultActivity extends AppCompatActivity {
    private TextView result;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        result = findViewById(R.id.result);
        loadInfo();
    }

    private void loadInfo() {
        OkHttpClient client = new OkHttpClient.Builder().authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credentials = Credentials.basic(
                        username, password);
                return response
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", credentials)
                        .build();
            }
        }).build();
        String url = "https://api.github.com/users/af2905";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String strResponse = response.body().string();
                    ResultActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            result.setText(strResponse);
                        }
                    });
                }
            }
        });
    }
}


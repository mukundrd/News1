package com.example.news1.news1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.news1.news1.R.id.action_settings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHit = (Button) findViewById(R.id.btnHit);
        final TextView tvData = (TextView) findViewById(R.id.tvJsonItem);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=" + R.string.API_KEY);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = " ";
                    while ((line = reader.readLine()) != null) {

                        buffer.append(line);
                    }

                    tvData.setText(buffer.toString());


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                break;
            case R.id.action_app:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}

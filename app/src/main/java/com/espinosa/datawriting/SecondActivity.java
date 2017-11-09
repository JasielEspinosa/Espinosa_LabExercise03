package com.espinosa.datawriting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;

public class SecondActivity extends AppCompatActivity {

    Button btnPrevious, btnIntCache, btnExtCache, btnExtStorage, btnExtPubStorage;
    EditText inputFilename;
    TextView displayData;
    SharedPreferences preferences;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        inputFilename = (EditText) findViewById(R.id.inputGetFilename);
        displayData = (TextView) findViewById(R.id.displayData);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void callMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadSharedPref(View view) {
        String txtData = preferences.getString("dataInput", "NULL");
        String txtFilename = preferences.getString("filenameInput", "");
        displayData.setText(txtData);
        inputFilename.setText(txtFilename);
    }

    public void loadInternalStorage(View view) {
        StringBuilder buffer = new StringBuilder();
        String getFilename = inputFilename.getText().toString();
        int read;
        try {
            fis = openFileInput(getFilename);
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayData.setText(buffer.toString());
    }


    public void loadInternalCache(View view) {
        StringBuilder buffer = new StringBuilder();
        String getFilename = inputFilename.getText().toString();
        int read;
        File folder = getCacheDir();
        File file = new File(folder, getFilename);
        try {
            fis = new FileInputStream(file);
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayData.setText(buffer.toString());
    }

    public void loadExternalCache(View view) {
        StringBuilder buffer = new StringBuilder();
        String getFilename = inputFilename.getText().toString();
        int read;
        File folder = getExternalCacheDir();
        File file = new File(folder, getFilename);
        try {
            fis = new FileInputStream(file);
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayData.setText(buffer.toString());
    }

    public void loadExternalStorage(View view) {
        StringBuilder buffer = new StringBuilder();
        String getFilename = inputFilename.getText().toString();
        int read;
        File folder = getExternalFilesDir("");
        File file = new File(folder, getFilename);
        try {
            fis = new FileInputStream(file);
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayData.setText(buffer.toString());
    }

    public void loadExternalPublicStorage(View view) {
        StringBuilder buffer = new StringBuilder();
        String getFilename = inputFilename.getText().toString();
        int read;
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, getFilename);
        try {
            fis = new FileInputStream(file);
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayData.setText(buffer.toString());
    }


}

package com.espinosa.datawriting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnNext, btnIntCache, btnExtCache, btnExtStorage, btnExtPubStorage;
    SharedPreferences preferences;
    EditText inputData, inputFilename;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputData = (EditText) findViewById(R.id.inputData);
        inputFilename = (EditText) findViewById(R.id.inputFilename);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void callSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void saveSharedPref(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("dataInput", inputData.getText().toString());
        editor.putString("filenameInput", inputFilename.getText().toString());
        editor.apply();
        Toast.makeText(this, "Success! Saved to Shared Preferences", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalStorage(View view) {
        String txtData = inputData.getText().toString();
        String txtFilename = inputFilename.getText().toString();
        try {
            fos = openFileOutput(txtFilename, Context.MODE_PRIVATE);
            fos.write(txtData.getBytes());
            fos.write(txtFilename.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        Toast.makeText(this, "Success! Saved to Internal Storage", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalCache(View view) {
        String message = inputData.getText().toString();
        String txtFilename = inputFilename.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder, txtFilename);
        writeData(file, message);
        Toast.makeText(this, "Success! Saved to Internal Cache", Toast.LENGTH_SHORT).show();
    }

    public void saveExternalCache(View view) {
        String message = inputData.getText().toString();
        String txtFilename = inputFilename.getText().toString();
        File folder = getExternalCacheDir();
        File file = new File(folder, txtFilename);
        writeData(file, message);
        Toast.makeText(this, "Success! Saved to External Cache", Toast.LENGTH_SHORT).show();
    }

    public void saveExternalStorage(View view) {
        String message = inputData.getText().toString();
        String txtFilename = inputFilename.getText().toString();
        File folder = getExternalFilesDir("");
        File file = new File(folder, txtFilename);
        writeData(file, message);
        Toast.makeText(this, "Success! Saved to External Storage", Toast.LENGTH_SHORT).show();
    }

    public void saveExternalPublic(View view) {
        String message = inputData.getText().toString();
        String txtFilename = inputFilename.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, txtFilename);
        writeData(file, message);
        Toast.makeText(this, "Success! Saved to External Public Storage", Toast.LENGTH_SHORT).show();
    }

    public void writeData(File file, String message) {
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

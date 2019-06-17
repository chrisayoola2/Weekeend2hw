package com.example.weekeend2hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadAndWriteActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    EditText etTextEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_and_write);
        etTextEntry = findViewById(R.id.etTextEntry);
    }

    public void onClick(View view) throws FileNotFoundException {
        switch (view.getId()) {
            case R.id.btnSave:
                String etText = etTextEntry.getText().toString();
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(etText.getBytes());
                    etTextEntry.getText().clear();
                    Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "ERROR SAVING File Not Found", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "ERROR SAVING", Toast.LENGTH_LONG).show();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "ERROR Closing File", Toast.LENGTH_LONG).show();
                        }

                    }

                }

            case R.id.btnLoad:
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while ((text = br.readLine()) != null ){
                        sb.append(text).append("\n");
                    }
                    etTextEntry.setText(sb.toString());

        } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();

                }finally {
                    if(fis != null){
                        try {
                            fis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
        }
}
}

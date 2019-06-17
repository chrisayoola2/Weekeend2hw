package com.example.weekeend2hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.StringReader;

public class AddCelebrityActivity extends AppCompatActivity {
    public static final String EXTRA_CELEBNAME = "com.example.weekeend2hw.EXTRA_CELEBNAME";
    public static final String EXTRA_CELEBDESCRIPTION = "com.example.weekeend2hw.EXTRA_CELEBDESCRIPTION";
    public static final String EXTRA_CELEBFAMELEVEL = "com.example.weekeend2hw.EXTRA_FAMELEVEL";

    private EditText etEditCelebrity;
    private EditText etCelebrityDescription;
    private Spinner spnFameLevel;
    private NumberPicker npFameLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_celebrity);

        etEditCelebrity = findViewById(R.id.etEditCelebrity);
        etCelebrityDescription = findViewById(R.id.etCelebrityDescription);
        spnFameLevel = findViewById(R.id.spnFameLevel);
        npFameLevel = findViewById(R.id.npFameLevel);

        npFameLevel.setMinValue(1);
        npFameLevel.setMaxValue(5);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Celebrity");

    }

    private void saveCelebrity() { //this is called when save celebrity clicked
        String celebrityName  = etEditCelebrity.getText().toString(); //gets title in text view
        String description = etCelebrityDescription.getText().toString(); //gets description in textview
        int fameLevel = npFameLevel.getValue(); //gets value from number picker

        if(celebrityName.trim().isEmpty() || description.trim().isEmpty()){         //checks for empty edit texts view, displays toast if true
            Toast.makeText(this,"One or More Fields are Empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_CELEBNAME, celebrityName);
        intent.putExtra(EXTRA_CELEBDESCRIPTION, description);
        intent.putExtra(EXTRA_CELEBFAMELEVEL, fameLevel);

        setResult(RESULT_OK ,intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_celebrity_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // when menu item is seleced
        switch (item.getItemId()) {
            case R.id.save_celebrity:
                saveCelebrity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

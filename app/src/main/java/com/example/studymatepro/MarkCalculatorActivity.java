package com.example.studymatepro;


import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MarkCalculatorActivity extends AppCompatActivity {

    EditText markCat1, markCat2, fatEditText, internalText, labText;

    TextView markOutput;
    Button markCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_calculator);


        markCat1 = findViewById(R.id.cat1);
        markCat2 = findViewById(R.id.cat2);
        fatEditText = findViewById(R.id.fat);
        internalText = findViewById(R.id.internal);
        labText = findViewById(R.id.lab);
        markCal = findViewById(R.id.calculator);
        markOutput = findViewById(R.id.markOutput);

        markCal.setOnClickListener((v) -> calculator());


    }

    @SuppressLint("SetTextI18n")
    void calculator() {
        String cat1String = markCat1.getText().toString();
        String cat2String = markCat2.getText().toString();
        String fatString = fatEditText.getText().toString();
        String internalString = internalText.getText().toString();
        String labString = labText.getText().toString();

        int test;

        if (cat1String.isEmpty() || cat2String.isEmpty() || fatString.isEmpty() || internalString.isEmpty()) {
            if (cat1String.isEmpty()) {
                markCat1.setError("Required");
            }
            if (cat2String.isEmpty()) {
                markCat2.setError("Required");
            }
            if (fatString.isEmpty()) {
                fatEditText.setError("Required");
            }
            if (internalString.isEmpty()) {
                internalText.setError("Required");
            }
            return;
        } else {
            test = 1;
        }


        float cat1 = Integer.parseInt(cat1String);
        float cat2 = Integer.parseInt(cat2String);
        float fat = Integer.parseInt(fatString);
        float internal = Integer.parseInt(internalString);
        float lab = labString.isEmpty() ? 0 : Integer.parseInt(labString);
        float total;

        if (cat1 > 50) {
            markCat1.setError("Invalid");
            return;
        }
        if (cat2 > 50) {
            markCat2.setError("Invalid");
            return;
        }
        if (fat > 100) {
            fatEditText.setError("Invalid");
            return;
        }
        if (internal > 30) {
            internalText.setError("Invalid");
            return;
        }
        if (lab > 100) {
            labText.setError("Invalid");
            return;
        }

        total = cat1 * 0.3f + cat2 * 0.3f + internal + fat * 0.40f;

        if(lab>0){
        total = total*0.75f + lab * 0.25f;
        }

        if(test==1)
        {
            markOutput.setVisibility(View.VISIBLE);
            markOutput.setText("Your Marks: "+Math.round(total));
        }


    }
}
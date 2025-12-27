package com.example.studymatepro;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GPA_calculatorActivity extends AppCompatActivity {

    private int layoutCounter = 2;
    private double totalCredits = 0.0;
    private double totalPoints = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calculator);

        findViewById(R.id.add_gpa_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementLayout();
            }
        });

        findViewById(R.id.remove2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLayout(R.id.gpaIp2);
            }
        });

        findViewById(R.id.remove3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLayout(R.id.gpaIp3);
            }
        });
        findViewById(R.id.remove4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLayout(R.id.gpaIp4);
            }
        });
        findViewById(R.id.remove5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLayout(R.id.gpaIp5);
            }
        });
        findViewById(R.id.remove6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLayout(R.id.gpaIp6);
            }
        });
        findViewById(R.id.remove7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLayout(R.id.gpaIp7);
            }
        });
        findViewById(R.id.remove8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLayout(R.id.gpaIp8);
            }
        });

        // Implement remove buttons for other layouts in a similar way

        findViewById(R.id.gpa_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGPA();
            }
        });
    }

    private void incrementLayout() {
        if (layoutCounter <= 8) {
            @SuppressLint("DiscouragedApi") int layoutId = getResources().getIdentifier("gpaIp" + layoutCounter, "id", getPackageName());
            findViewById(layoutId).setVisibility(View.VISIBLE);
            layoutCounter++;
        }
    }

    private void removeLayout(int layoutId) {
        findViewById(layoutId).setVisibility(View.GONE);
        layoutCounter--;
    }

    private void calculateGPA() {
        totalCredits = 0.0;
        totalPoints = 0.0;

        for (int i = 1; i < layoutCounter; i++) {
            int gradeId = getResources().getIdentifier("grade" + i, "id", getPackageName());
            int creditId = getResources().getIdentifier("credit" + i, "id", getPackageName());

            EditText gradeText = findViewById(gradeId);
            EditText creditText = findViewById(creditId);

            String gradeStr = gradeText.getText().toString().trim();
            String creditStr = creditText.getText().toString().trim();

            double gradeValue = convertGradeToValue(gradeStr);

            // Validate credit input
            double creditValue;
            try {
                creditValue = Double.parseDouble(creditStr);
                if (creditValue <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid input for Credits in Course " + i, Toast.LENGTH_SHORT).show();
                return; // Stop calculation for this course
            }

            if (gradeValue >= 0) {
                totalCredits += creditValue;
                totalPoints += (gradeValue * creditValue);
            } else {
                Toast.makeText(this, "Invalid input for Grade in Course " + i, Toast.LENGTH_SHORT).show();
            }
        }

        if (totalCredits > 0) {
            double GPA = totalPoints / totalCredits;

            TextView gpaOutput = findViewById(R.id.gpaOutput);
            gpaOutput.setText(String.format("GPA: %.2f", GPA));
            gpaOutput.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "No valid Credits entered for GPA calculation", Toast.LENGTH_SHORT).show();
        }
    }

    private double convertGradeToValue(String grade) {
        switch (grade) {
            case "S":
                return 10;
            case "A":
                return 9;
            case "B":
                return 8;
            case "C":
                return 7;
            case "D":
                return 6;
            case "E":
                return 5;
            case "F":
            case "N":
                return 0;
            default:
                return -1.0;
        }
    }
}

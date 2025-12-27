package com.example.studymatepro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class CGPA_calculator extends AppCompatActivity {

    EditText creditsCompleted, currentCGPA, creditsThisSemester, gpaThisSemester;
    Button calculateBtn;
    TextView cgpaOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa_calculator);

        creditsCompleted = findViewById(R.id.credits);
        currentCGPA = findViewById(R.id.cgpa);
        creditsThisSemester = findViewById(R.id.creditTSem);
        gpaThisSemester = findViewById(R.id.gpa);
        calculateBtn = findViewById(R.id.calculate);
        cgpaOutput = findViewById(R.id.cgpaOutput);

        calculateBtn.setOnClickListener(v -> calculateCGPA());
    }

    private void calculateCGPA() {
        String creditsCompletedStr = creditsCompleted.getText().toString();
        String currentCGPAStr = currentCGPA.getText().toString();
        String creditsThisSemesterStr = creditsThisSemester.getText().toString();
        String gpaThisSemesterStr = gpaThisSemester.getText().toString();


        if (creditsThisSemesterStr.isEmpty() || creditsCompletedStr.isEmpty() || currentCGPAStr.isEmpty() || gpaThisSemesterStr.isEmpty()) {

        if (creditsCompletedStr.isEmpty())
            creditsCompleted.setError("Required");
        if (currentCGPAStr.isEmpty())
            currentCGPA.setError("Required");
        if (creditsThisSemesterStr.isEmpty())
            creditsThisSemester.setError("Required");
        if (gpaThisSemesterStr.isEmpty())
            gpaThisSemester.setError("Required");

        return;
        }

        double completedCredits = Double.parseDouble(creditsCompletedStr);
        double prevCGPA = Double.parseDouble(currentCGPAStr);
        double thisSemesterCredits = Double.parseDouble(creditsThisSemesterStr);
        double thisSemesterGPA = Double.parseDouble(gpaThisSemesterStr);

        if (completedCredits > 180 || prevCGPA > 10 || thisSemesterCredits > 27 || thisSemesterGPA > 10) {
            if (completedCredits > 180) {
                creditsCompleted.setError("Invalid input");
                return;
            }

            if (prevCGPA > 10) {
                currentCGPA.setError("Invalid input");
                return;
            }

            if (thisSemesterCredits > 27) {
                creditsThisSemester.setError("Invalid input");
                return;
            }

            if (thisSemesterGPA > 10) {
                gpaThisSemester.setError("Invalid input");
                return;
            }
        }


            double totalPrevCredits = completedCredits * prevCGPA;
            double newCredits = completedCredits + thisSemesterCredits;
            double total = totalPrevCredits + (thisSemesterGPA * thisSemesterCredits);
            double newCGPA = total / newCredits;

            // Update the TextView with the calculated CGPA
            cgpaOutput.setText("Your CGPA: " + newCGPA);
            cgpaOutput.setVisibility(View.VISIBLE);

    }
}
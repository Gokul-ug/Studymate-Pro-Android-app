package com.example.studymatepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button notesBtn,markBtn,gpaBtn,cgpaBtn;

    ImageButton menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesBtn=findViewById(R.id.notes_btn);
        markBtn=findViewById(R.id.mark_btn);
        gpaBtn=findViewById(R.id.gpa_btn);
        cgpaBtn=findViewById(R.id.cgpa_btn);
        menuBtn=findViewById(R.id.menu_btn);

        notesBtn.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this, NotesActivity.class)));
        markBtn.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this, MarkCalculatorActivity.class)));
        cgpaBtn.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this, CGPA_calculator.class)));
        gpaBtn.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this, GPA_calculatorActivity.class)));
        menuBtn.setOnClickListener((v)->showMenu());


    }

    void showMenu(){

        //ToDo Display log_out_btn

        PopupMenu popupMenu  = new PopupMenu(MainActivity.this,menuBtn);
        popupMenu.getMenu().add("Logout");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getTitle()=="Logout"){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

    }


}
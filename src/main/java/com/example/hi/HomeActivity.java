package com.example.SmartDada;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView tv_no,tv_try;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_no =  findViewById(R.id.tv_no);
        tv_try =  findViewById(R.id.tv_try);

//        move to login activity
        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

//        move to signin activity
        tv_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });


    }


}

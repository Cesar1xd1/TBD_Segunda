package com.example.app1_clase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText usu,contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usu = findViewById(R.id.id_caja_User);
        contra = findViewById(R.id.id_caja_Contra);
    }
    public void abirmenu(View v){
        if(usu.getText().toString().equals("admin")&&contra.getText().toString().equals("admin")){
            Intent i=new Intent(this,Activity_menu.class);
            startActivity(i);
        }else{
            Toast.makeText(getBaseContext(),"Incorrecto",Toast.LENGTH_LONG).show();
        }

    }
}
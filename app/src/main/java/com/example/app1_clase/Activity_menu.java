package com.example.app1_clase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity_menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void abrir_Activitis(View v){
        Intent i=null;
        switch (v.getId()){
            case R.id.btn_mnAgregar:
                i=new Intent(this,Activity_Altas.class);
                break;
            case R.id.btn_mnEliminar:
                i=new Intent(this,Activity_Bajas.class);
                break;
            case R.id.btn_mnModificar:
                i=new Intent(this,Activity_Cambios.class);
                break;
            case R.id.btn_mnConsultar:
                i=new Intent(this,Activity_Consultas.class);
                break;
        }
        startActivity(i);
    }

}

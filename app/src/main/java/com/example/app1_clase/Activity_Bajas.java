package com.example.app1_clase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import BaseDatos.Employees;
//import Entidades.Empleado;

public class Activity_Bajas extends Activity{
    EditText emp_no;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);
        emp_no=findViewById(R.id.txt_bjEmp_no);
    }
    /*
    public void busquedaBajas(View v){
        if(emp_no.getText().toString().isEmpty()==false) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Empleado emp=null;
                    Employees conexionBD=Employees.gettAppDatabase(getBaseContext());
                    emp = conexionBD.empleadoDAO().optenerUno(emp_no.getText().toString());
                    if(emp!=null){
                        System.out.println(emp.getFirtst_name().toString());
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(),"El registro NO existe",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }
    */
    public void borrar(View v){
        if(!emp_no.getText().toString().isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Employees conexionBD=Employees.gettAppDatabase(getBaseContext());
                        conexionBD.empleadoDAO().eliminaEmpleado(emp_no.getText().toString());
                        Toast.makeText(getBaseContext(),"Registro eliminado",Toast.LENGTH_LONG).show();
                    }catch(Exception ex){
                        System.out.println("error");
                    }

                }
            }).start();
        }
    }
}

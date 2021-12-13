package com.example.app1_clase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import BaseDatos.Employees;
import Entidades.Empleado;


public class Activity_Altas extends AppCompatActivity {
    EditText emp_no;
    EditText birth_date,first_name,last_name,hire_date;
    Spinner gender;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);
        emp_no=findViewById(R.id.txt_alEmp_no);
        birth_date=findViewById(R.id.txt_alBirthDate);
        first_name=findViewById(R.id.txt_alFirstName);
        last_name=findViewById(R.id.txt_alLastName);
        hire_date=findViewById(R.id.txt_alHireDate);
        gender=findViewById(R.id.spn_alGender);
    }
    public void agregarEmpleado(View v){
        Employees conexionBD=Employees.gettAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                String cadenaEmp_no = emp_no.getText().toString();
                int empNo = Integer.parseInt(cadenaEmp_no);
                cadenaEmp_no="";
                String gen = (String) gender.getSelectedItem();
                String genero;
                if(gen.equals("Masculino")){
                    genero = "M";
                }else{
                    genero = "F";
                }
                Empleado emp = new Empleado(empNo,birth_date.getText().toString(),first_name.getText().toString(),last_name.getText().toString(),genero,hire_date.getText().toString());
                conexionBD.empleadoDAO().insertarEmpleado(emp);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(),"Agregado con exito",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }
}

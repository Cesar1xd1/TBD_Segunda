package com.example.app1_clase;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import BaseDatos.Employees;
import Entidades.Empleado;


public class Activity_Cambios extends Activity{
    EditText emp_no;
    EditText birth_date,first_name,last_name,hire_date;
    Spinner gender;
    RecyclerView recicler;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Empleado> e;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);
        emp_no=findViewById(R.id.txt_cbEmpNo);
        birth_date=findViewById(R.id.txt_cdBirthDate);
        first_name=findViewById(R.id.txt_cdFirstName);
        last_name=findViewById(R.id.txt_cdLastName);
        hire_date=findViewById(R.id.txt_cdHireDate);
        gender=findViewById(R.id.sp_cdGender);

        String[] datos = {""};
        recicler=findViewById(R.id.rv_cb);

        recicler.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this);
        recicler.setLayoutManager(layoutManager);
        int[] c = new int[1];

        emp_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                datos[0]="";
                if(!emp_no.getText().toString().equals("")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Employees conexion=Employees.gettAppDatabase(getBaseContext());
                            e=null;
                            e=conexion.empleadoDAO().optenerFiltrando(emp_no.getText().toString()+"%");
                            c[0] =e.size();
                            if(e.size()!=0) {
                                for (int i = 0; i < c[0]; i++) {
                                    datos[0] = datos[0] + e.get(i).toString() + "/";
                                }
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter =new Adaptador(datos[0].split("/"));
                                    recicler.setAdapter(adapter);
                                }
                            });
                        }
                    }).start();

                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Employees conexion=Employees.gettAppDatabase(getBaseContext());
                            e= conexion.empleadoDAO().optenerTodos();
                            c[0] =e.size();
                            if(e.size()!=0) {
                                for (int i = 0; i < c[0]; i++) {
                                    datos[0] = datos[0] + e.get(i).toString() + "/";
                                }
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter =new Adaptador(datos[0].split("/"));
                                    recicler.setAdapter(adapter);
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }
   /* public void buscar_cambios(View v){
        if(!emp_no.getText().toString().isEmpty()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Empleado emp=null;
                    Employees conexionBD=Employees.gettAppDatabase(getBaseContext());
                    emp = conexionBD.empleadoDAO().optenerUno(emp_no.getText().toString());
                    if(emp!=null){
                        emp_no.setText(emp.getFirtst_name());
                    }
                }
            }).start();
        }
    }
    */
    public void cambiar_empleado(View v){
        if(!emp_no.getText().toString().isEmpty()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Employees conexionBD=Employees.gettAppDatabase(getBaseContext());
                    e=conexionBD.empleadoDAO().optenerFiltrando(emp_no.getText().toString());
                    if(e!=null){
                        String cadenaEmp_no = emp_no.getText().toString();
                        int empNo = Integer.parseInt(cadenaEmp_no);
                        String gen = (String) gender.getSelectedItem();
                        String genero;
                        if(gen.equals("Masculino")){
                            genero = "M";
                        }else{
                            genero = "F";
                        }
                        conexionBD.empleadoDAO().actualizarProNoControl(empNo,birth_date.getText().toString(),first_name.getText().toString(),last_name.getText().toString(),genero,hire_date.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(),"El registro se actualizo ",Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                }
            }).start();
        }
    }
}


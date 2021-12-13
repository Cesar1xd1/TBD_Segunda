package com.example.app1_clase;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import BaseDatos.Employees;
import Entidades.Empleado;

public class Activity_Consultas extends Activity {
    RecyclerView recicler;
    RecyclerView.LayoutManager layoutManager;
    List<Empleado> e;
    EditText emp_no;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        emp_no=  findViewById(R.id.txt_csNoEmpleado);
        String[] datos = {""};
        recicler=findViewById(R.id.rc_cs);

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
                            e=conexion.empleadoDAO().optenerFiltrando(emp_no.getText().toString()+"%");
                            c[0] =e.size();
                            if(e.size()!=0) {
                                for (int i = 0; i < c[0]; i++) {
                                    datos[0] = datos[0] + e.get(i) + "/";
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
}
class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder>{
    private final String[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder(TextView t) {
            super(t);
            textView = t;
        }
    }

    public Adaptador(String [] mDataset){
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public Adaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutrview,
                parent, false);
        return  new MyViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}

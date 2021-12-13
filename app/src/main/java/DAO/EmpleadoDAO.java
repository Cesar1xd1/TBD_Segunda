package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Entidades.Empleado;

@Dao
public interface EmpleadoDAO {

    //ALTA
    @Insert
    public void insertarEmpleado(Empleado em);

    //BAJA
    @Query("DELETE FROM Empleado WHERE emp_no=:emno")
    public void eliminaEmpleado(String emno);

    @Query("UPDATE Empleado SET birth_date=:x,first_name=:y,last_name=:z,gender=:e,hire_date=:s WHERE emp_no=:n")
    public void actualizarProNoControl(int n,String x,String y,String z,String e,String s);

    //Consultas
    @Query("SELECT * FROM Empleado")
    public List<Empleado> optenerTodos();

    @Query("SELECT * FROM Empleado WHERE first_name LIKE :n")
    public Empleado buscarPorNombre(String n);

    @Query("SELECT * FROM Empleado WHERE emp_no LIKE :c")
    public List<Empleado> optenerFiltrando(String c);

    @Query("SELECT * FROM Empleado WHERE emp_no =:c")
    public Empleado optenerUno(String c);




}

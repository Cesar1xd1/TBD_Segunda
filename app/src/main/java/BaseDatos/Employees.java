package BaseDatos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import DAO.EmpleadoDAO;
import Entidades.Empleado;


@Database(entities = {Empleado.class},version = 1,exportSchema = false)

public abstract class Employees extends RoomDatabase {

    public abstract EmpleadoDAO empleadoDAO();

    private static  Employees INSTANCE;

    public static Employees gettAppDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    Employees.class,"@employees").build();
        }
        return  INSTANCE;
        }

        public static void destroyInstance(){
        INSTANCE=null;
        }


}



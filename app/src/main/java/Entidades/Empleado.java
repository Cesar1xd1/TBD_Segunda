package Entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Empleado {
    @NonNull
    @PrimaryKey
    private int emp_no;

    @NonNull
    @ColumnInfo(name="birth_date")
    private String birth_date;

    @NonNull
    @ColumnInfo(name="first_name")
    private String firtst_name;

    @NonNull
    @ColumnInfo(name="last_name")
    private String last_name;

    @NonNull
    @ColumnInfo(name="gender")
    private String gender;

    @NonNull
    @ColumnInfo(name="hire_date")
    private String hire_date;

    public Empleado(int emp_no, @NonNull String birth_date, @NonNull String firtst_name, @NonNull String last_name, @NonNull String gender, @NonNull String hire_date) {
        this.emp_no = emp_no;
        this.birth_date = birth_date;
        this.firtst_name = firtst_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    @NonNull
    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(@NonNull String birth_date) {
        this.birth_date = birth_date;
    }

    @NonNull
    public String getFirtst_name() {
        return firtst_name;
    }

    public void setFirtst_name(@NonNull String firtst_name) {
        this.firtst_name = firtst_name;
    }

    @NonNull
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(@NonNull String last_name) {
        this.last_name = last_name;
    }

    @NonNull
    public String getGender() {
        return gender;
    }

    public void setGender(@NonNull String gender) {
        this.gender = gender;
    }

    @NonNull
    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(@NonNull String hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "emp_no=" + emp_no +
                ", birth_date='" + birth_date + '\'' +
                ", firtst_name='" + firtst_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", hire_date='" + hire_date + '\'' +
                '}';
    }
}

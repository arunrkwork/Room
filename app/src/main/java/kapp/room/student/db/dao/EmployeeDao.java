package kapp.room.student.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kapp.room.student.db.Const;
import kapp.room.student.db.entity.Employee;

/**
 * Created by Arunraj on 3/1/2018.
 */

@Dao
public interface EmployeeDao {

    @Insert
    long insert(Employee employee);

    @Update
    int update(Employee employee);

    @Query("select * from " + Const.TABLE_EMP)
    LiveData<List<Employee>> getAllEmployees();

}

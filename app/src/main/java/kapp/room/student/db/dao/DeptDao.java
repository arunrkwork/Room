package kapp.room.student.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.content.Context;

import java.util.List;

import kapp.room.student.db.Const;
import kapp.room.student.db.entity.Dept;

/**
 * Created by Arunraj on 3/1/2018.
 */

@Dao
public interface DeptDao {

    @Insert
    long insert(Dept dept);

    @Update
    int update(Dept dept);

    @Query("select * from " + Const.TABLE_DEPT)
    LiveData<List<Dept>> getAllDept();

    @Query("delete from " + Const.TABLE_DEPT)
    void deleteAll();

}

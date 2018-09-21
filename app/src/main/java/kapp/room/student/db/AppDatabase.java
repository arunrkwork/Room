package kapp.room.student.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import kapp.room.student.db.dao.DeptDao;
import kapp.room.student.db.dao.EmployeeDao;
import kapp.room.student.db.entity.Dept;
import kapp.room.student.db.entity.Employee;

/**
 * Created by Arunraj on 3/1/2018.
 */

@Database(entities = {Employee.class, Dept.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "office.db";
    private static volatile AppDatabase instance;

    public abstract EmployeeDao getEmpDAO();

    public abstract DeptDao getDeptDAO();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                instance = create(context);
            }
        }
        return instance;
    }

    private static AppDatabase create(Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME)
               // .fallbackToDestructiveMigration()
                //.addCallback(mAppDatabaseCallback)
                .build();
    }

   /* private static Callback mAppDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(instance);
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DeptDao mDeptDao;

        public PopulateDbAsync(AppDatabase db) {
            this.mDeptDao = db.getDeptDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDeptDao.deleteAll();
            Dept dept = new Dept();
            dept.setDeptName("Android");
            mDeptDao.insert(dept);
            dept = new Dept();
            dept.setDeptName("iOs");
            mDeptDao.insert(dept);
            return null;
        }
    }*/

}

package kapp.room.student.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import kapp.room.student.db.AppDatabase;
import kapp.room.student.db.dao.DeptDao;
import kapp.room.student.db.dao.EmployeeDao;
import kapp.room.student.db.entity.Dept;
import kapp.room.student.db.entity.Employee;

/**
 * Created by Arunraj on 3/2/2018.
 */

public class DatabaseRepository {

    private DeptDao mDeptDao;
    private LiveData<List<Dept>> mAllDepts;

    private EmployeeDao mEmployeeDao;
    private LiveData<List<Employee>> mAllEmployees;

    public DatabaseRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);

        // from dept
        mDeptDao = db.getDeptDAO();
        mAllDepts = mDeptDao.getAllDept();

        // from employee
        mEmployeeDao = db.getEmpDAO();
        mAllEmployees = mEmployeeDao.getAllEmployees();

    }

    public LiveData<List<Dept>> getAllDept(){
        return mAllDepts;
    }

    public void insertDept(Dept dept){
        new insertDeptAsyncTask(mDeptDao).execute(dept);
    }

    private static class insertDeptAsyncTask extends AsyncTask<Dept, Void, Void>{

        private DeptDao mDeptDao;

        public insertDeptAsyncTask(DeptDao mDeptDao) {
            this.mDeptDao = mDeptDao;
        }

        @Override
        protected Void doInBackground(Dept... params) {
            mDeptDao.insert(params[0]);
            return null;
        }
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return mAllEmployees;
    }

    public void insertEmployee(Employee employee) {
        new insertEmployeeAsyncTask(mEmployeeDao).execute(employee);
    }

    private static class insertEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao mEmployeeDao;

        public insertEmployeeAsyncTask(EmployeeDao mEmployeeDao) {
            this.mEmployeeDao = mEmployeeDao;
        }

        @Override
        protected Void doInBackground(Employee... params) {
            mEmployeeDao.insert(params[0]);
            return null;
        }
    }

}

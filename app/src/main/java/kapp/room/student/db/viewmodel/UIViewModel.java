package kapp.room.student.db.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import kapp.room.student.db.entity.Dept;
import kapp.room.student.db.entity.Employee;
import kapp.room.student.db.repository.DatabaseRepository;

/**
 * Created by Arunraj on 3/2/2018.
 */

public class UIViewModel extends AndroidViewModel {

    private DatabaseRepository mDatabaseRepository;
    private LiveData<List<Dept>> mAllDepts;
    private LiveData<List<Employee>> mAllEmployees;

    public UIViewModel(Application application) {
        super(application);
        mDatabaseRepository = new DatabaseRepository(application);
        mAllDepts = mDatabaseRepository.getAllDept();
        mAllEmployees = mDatabaseRepository.getAllEmployees();
    }

    public LiveData<List<Dept>> getAllDept(){
        return mAllDepts;
    }

    public void insertDept(Dept dept){
        mDatabaseRepository.insertDept(dept);
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return mAllEmployees;
    }

    public void insertEmployee(Employee employee){
        mDatabaseRepository.insertEmployee(employee);
    }
}

package kapp.room.student.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import kapp.room.student.R;
import kapp.room.student.fragments.DeptFragment;
import kapp.room.student.fragments.EmployeeFragment;

public class MainActivity extends AppCompatActivity implements DeptFragment.OnDeptFragmentInteractionListener, EmployeeFragment.OnEmployeeFragmentInteractionListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(DeptFragment.newInstance("", ""));
        Log.d(TAG, "onCreate: ");
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment, fragment.getClass().getSimpleName()).commit();
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getSimpleName()).addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    @Override
    public void onDeptFragmentInteraction() {
        setFragment(EmployeeFragment.newInstance("", ""));
    }

    @Override
    public void onEmployeeFragmentInteraction() {
        startActivity(new Intent(this, AddEmployeeActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}

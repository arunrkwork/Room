package kapp.room.student.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kapp.room.student.R;
import kapp.room.student.adapters.DeptAdapter;
import kapp.room.student.db.entity.Dept;
import kapp.room.student.db.viewmodel.UIViewModel;

public class DeptFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnDeptFragmentInteractionListener mListener;

    EditText edDeptName;
    Button btnDeptAdd, btnEmployee;
    RecyclerView mRecyclerView;
    List<Dept> list;
    LinearLayoutManager mLayoutManager;
    DeptAdapter mAdapter;
    private UIViewModel mUIViewModel;


    public DeptFragment() {
        // Required empty public constructor
    }

    public static DeptFragment newInstance(String param1, String param2) {
        DeptFragment fragment = new DeptFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dept, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edDeptName = view.findViewById(R.id.edDeptName);
        btnDeptAdd = view.findViewById(R.id.btnDeptAdd);
        btnEmployee = view.findViewById(R.id.btnEmployee);
        btnDeptAdd.setOnClickListener(this);
        btnEmployee.setOnClickListener(this);
        mRecyclerView = view.findViewById(R.id.mRecyclerView);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUIViewModel = ViewModelProviders.of(this).get(UIViewModel.class);
        list = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new DeptAdapter(list);
        mRecyclerView.setAdapter(mAdapter);


        mUIViewModel.getAllDept().observe(this, new Observer<List<Dept>>() {
            @Override
            public void onChanged(@Nullable List<Dept> list) {
                mAdapter.addItems(list);
            }
        });
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onDeptFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDeptFragmentInteractionListener) {
            mListener = (OnDeptFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeptFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        if (v == btnDeptAdd) addDept();
        else if (v == btnEmployee) onButtonPressed();
    }


    private void addDept() {
        String deptName = edDeptName.getText().toString();
        if (TextUtils.isEmpty(deptName)) {
            Toast.makeText(getActivity(), "Please Enter the Dept Name", Toast.LENGTH_SHORT).show();
        } else {
            Dept dept = new Dept();
            dept.setDeptName(deptName);
            mUIViewModel.insertDept(dept);
        }
    }


    public interface OnDeptFragmentInteractionListener {
        void onDeptFragmentInteraction();
    }
}

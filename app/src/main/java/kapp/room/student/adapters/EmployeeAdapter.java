package kapp.room.student.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kapp.room.student.R;
import kapp.room.student.db.entity.Employee;

/**
 * Created by Arunraj on 3/2/2018.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> {

    List<Employee> list;

    public EmployeeAdapter(List<Employee> list) {
        this.list = list;
    }

    @Override
    public EmployeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EmployeeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item_list, null));
    }

    @Override
    public void onBindViewHolder(EmployeeHolder holder, int position) {
        holder.txtName.setText(list.get(position).getEmpFirstName() + " " + list.get(position).getEmpLastName());
        holder.txtAatharNumber.setText(list.get(position).getAadharNumber());
        holder.txtQualification.setText(list.get(position).getQualification());
        holder.txtDept.setText(list.get(position).getDeptId()+"");
        holder.txtDate.setText(list.get(position).getEmpBirthday().toString());
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void addItems(List<Employee> employees) {
        list = employees;
        notifyDataSetChanged();
    }

    public static class EmployeeHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtAatharNumber, txtDate, txtQualification, txtDept, txtAddress;

        public EmployeeHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAatharNumber = itemView.findViewById(R.id.txtAatharNumber);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtQualification = itemView.findViewById(R.id.txtQualification);
            txtDept = itemView.findViewById(R.id.txtDept);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }
    }

}

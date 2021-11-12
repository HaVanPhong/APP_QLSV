package com.example.app_qlsv.HomeController;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_qlsv.Model.Lop;
import com.example.app_qlsv.Model.Student;
import com.example.app_qlsv.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    List<Student> list;
    Context context;
    IOnClickStd iOnClickStd;

    public IOnClickStd getiOnClickStd() {
        return iOnClickStd;
    }

    public void setiOnClickStd(IOnClickStd iOnClickStd) {
        this.iOnClickStd = iOnClickStd;
    }

    public StudentAdapter(List<Student> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_std_layout_home, parent, false);
        StudentAdapter.ViewHolder viewHolder = new StudentAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        Student std= list.get(position);
        holder.tvTenSvNoiBat.setText(std.getHoTen()+"");
        holder.lnSvNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickStd.iOnClickStdHome(std);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSvNoiBat;
        LinearLayout lnSvNB;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lnSvNB= itemView.findViewById(R.id.lnSvNB);
            tvTenSvNoiBat= itemView.findViewById(R.id.tvTenSvNoiBat);
        }
    }
}

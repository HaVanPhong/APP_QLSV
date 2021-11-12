package com.example.app_qlsv.ClassController;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_qlsv.Model.Lop;
import com.example.app_qlsv.Model.Student;
import com.example.app_qlsv.R;

import java.util.List;

public class StdAdapter extends RecyclerView.Adapter<StdAdapter.ViewHolder>{
    List<Student> list;
    Context context;

    public StdAdapter(List<Student> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_std_inclass, parent, false);
        StdAdapter.ViewHolder viewHolders = new StdAdapter.ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull StdAdapter.ViewHolder holder, int position) {
        Student student= list.get(position);
        holder.tvDiem.setText(student.getDiemTichLuy()+"");
        holder.tvTen.setText("SV: "+student.getHoTen()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvDiem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen= itemView.findViewById(R.id.tvName_item_in_class);
            tvDiem= itemView.findViewById(R.id.tvDiemTLIitem_in_class);
        }
    }
}
package com.example.app_qlsv.StudentController;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_qlsv.HomeController.IOnClickStd;
import com.example.app_qlsv.HomeController.StudentAdapter;
import com.example.app_qlsv.Model.Student;
import com.example.app_qlsv.R;

import java.util.List;

public class StudentDetailsAdapter extends RecyclerView.Adapter<StudentDetailsAdapter.ViewHolder> {
    List<Student> list;
    IOnClickUDStd iOnClickUDStd;

    public IOnClickUDStd getiOnClickUDStd() {
        return iOnClickUDStd;
    }

    public void setiOnClickUDStd(IOnClickUDStd iOnClickUDStd) {
        this.iOnClickUDStd = iOnClickUDStd;
    }

    public StudentDetailsAdapter(List<Student> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StudentDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_std_in_std_details, parent, false);
        StudentDetailsAdapter.ViewHolder viewHolder = new StudentDetailsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentDetailsAdapter.ViewHolder holder, int position) {
        Student std= list.get(position);
        holder.tvTenSV.setText(std.getHoTen());
        holder.tvMaSV.setText(std.getMaSV());
        holder.tvLop.setText(std.getLop());
        holder.tvQueQuan.setText("Quê Quán: "+std.getQueQuan());
        holder.tvNamSinh.setText(std.getNamSinh());
        holder.tvSDT.setText("SĐT:         " + std.getSoDT());
        holder.tvDiemTl.setText(std.getDiemTichLuy()+"");
        holder.tvGioiTinh.setText(std.getGioiTinh());
        holder.tvXepHang.setText(std.getXepHang());

        holder.tvXoaSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickUDStd.XoaSV(std, position);
            }
        });

        holder.tvSuaSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickUDStd.SuaSV(std, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSV, tvMaSV, tvQueQuan, tvNamSinh, tvDiemTl, tvSDT, tvGioiTinh, tvXepHang, tvLop, tvSuaSV, tvXoaSV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSV= itemView.findViewById(R.id.tvTenSV);
            tvMaSV= itemView.findViewById(R.id.tvMaSV);
            tvLop= itemView.findViewById(R.id.tvLop);
            tvQueQuan= itemView.findViewById(R.id.tvQueQuan);
            tvNamSinh= itemView.findViewById(R.id.tvNamSinh);
            tvSDT= itemView.findViewById(R.id.tvSDT);
            tvDiemTl= itemView.findViewById(R.id.tvDiemTL);
            tvGioiTinh= itemView.findViewById(R.id.tvGoiTinh);
            tvXepHang= itemView.findViewById(R.id.tvXepHang);
            tvSuaSV= itemView.findViewById(R.id.tvSua);
            tvXoaSV= itemView.findViewById(R.id.tvXoa);

        }
    }

    public void updateList(List<Student> l){
        list=l;
        notifyDataSetChanged();
    }
}

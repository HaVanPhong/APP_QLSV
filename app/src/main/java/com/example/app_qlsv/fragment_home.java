package com.example.app_qlsv;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_qlsv.HomeController.IOnClickLop;
import com.example.app_qlsv.HomeController.IOnClickStd;
import com.example.app_qlsv.HomeController.LopAdapter;
import com.example.app_qlsv.HomeController.StudentAdapter;
import com.example.app_qlsv.Model.Lop;
import com.example.app_qlsv.Model.Student;
import com.example.app_qlsv.SQL.SQLHelper;
import com.example.app_qlsv.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class fragment_home extends Fragment {
    ActivityHomeBinding binding;
    LopAdapter adapterLop;
    StudentAdapter adapterStudent;
    SQLHelper sqlHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_home, container, false);
        sqlHelper= new SQLHelper(getContext());

        //view lớp
        List<Lop> listLopUuTu= sqlHelper.getAllLop();
        if (listLopUuTu.size()==0){
            binding.tvChuaCoLop.setVisibility(View.VISIBLE);
        }
        //sắp xếp theo số sinh viên
        Collections.sort(listLopUuTu, new Comparator<Lop>() {
            @Override
            public int compare(Lop o1, Lop o2) {
                return o1.getSoLuongSV()-o2.getSoLuongSV();
            }
        });

        adapterLop= new LopAdapter(listLopUuTu, getContext());
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.revLop.setLayoutManager(layoutManager);
        binding.revLop.setAdapter(adapterLop);

        //view student outstanding
        List<Student> listSvUuTu= sqlHelper.getAllStudent();
        if (listSvUuTu.size()==0){
            binding.tvChuaCoSV.setVisibility(View.VISIBLE);
        }
        //sắp xếp học sinh theo điểm tích lũy
        Collections.sort(listSvUuTu, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o1.getDiemTichLuy()-o2.getDiemTichLuy());
            }
        });

        adapterStudent= new StudentAdapter(listSvUuTu, getContext());
        RecyclerView.LayoutManager layoutManager1= new  LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.revSv.setLayoutManager(layoutManager1);
        binding.revSv.setAdapter(adapterStudent);


        adapterStudent.setiOnClickStd(new IOnClickStd() {
            @Override
            public void iOnClickStdHome(Student std) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_sv);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtributes = window.getAttributes();
                windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                window.setAttributes(windowAtributes);

                dialog.setCancelable(true);


                TextView tvTenSV = dialog.findViewById(R.id.tvTenSV_dialog);
                TextView tvMaSV= dialog.findViewById(R.id.tvMaSV_dialog);
                TextView tvLop = dialog.findViewById(R.id.tvLop_dialog);
                TextView tvDiaChi= dialog.findViewById(R.id.tvQueQuan_dialog);
                TextView tvNS = dialog.findViewById(R.id.tvNamSinh_dialog);
                TextView tvGT = dialog.findViewById(R.id.tvGioiTinh_dialog);
                TextView tvSDT = dialog.findViewById(R.id.tvSDT_dialog);
                TextView tvXepHang = dialog.findViewById(R.id.tvXepHang_dialog);
                TextView tvDiemTL = dialog.findViewById(R.id.tvDiemTL_dialog);

                tvTenSV.setText(std.getHoTen()+"");
                tvMaSV.setText(std.getMaSV()+"");
                tvLop.setText(std.getLop()+"");
                tvDiaChi.setText(std.getQueQuan()+"");
                tvNS.setText(std.getNamSinh()+"");
                tvGT.setText(std.getGioiTinh()+"");
                tvSDT.setText(std.getSoDT()+"");
                tvXepHang.setText(std.getXepHang()+"");
                tvDiemTL.setText(std.getDiemTichLuy()+"");

                dialog.show();
            }
        });

        adapterLop.setiOnClickLop(new IOnClickLop() {
            @Override
            public void iOnClickLop(Lop lop) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_lop);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtributes = window.getAttributes();
                windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                window.setAttributes(windowAtributes);

                dialog.setCancelable(true);


                TextView tvMaLop = dialog.findViewById(R.id.tvMaLop_dialog);
                TextView tvTenLop= dialog.findViewById(R.id.tvTenLop_dialog);
                TextView tvGVCN = dialog.findViewById(R.id.tvGVCN_dialog);
                TextView tvSiSo = dialog.findViewById(R.id.tvSiso_dialog);

                tvMaLop.setText(lop.getMaLop()+"");
                tvTenLop.setText(lop.getTenLop()+"");
                tvGVCN.setText(lop.getgVCN()+"");
                tvSiSo.setText(lop.getSoLuongSV()+"");

                dialog.show();
            }
        });



        return binding.getRoot();
    }
}

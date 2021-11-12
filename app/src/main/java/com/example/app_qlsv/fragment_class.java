package com.example.app_qlsv;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_qlsv.ClassController.ClassAdapter;
import com.example.app_qlsv.ClassController.IOnClick;
import com.example.app_qlsv.ClassController.StdAdapter;
import com.example.app_qlsv.Model.Lop;
import com.example.app_qlsv.Model.Student;
import com.example.app_qlsv.SQL.SQLHelper;
import com.example.app_qlsv.databinding.ActivityClassBinding;

import java.util.ArrayList;
import java.util.List;

public class fragment_class extends Fragment {
    ActivityClassBinding binding;
    SQLHelper sqlHelper;
    ClassAdapter classAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Lop> listLop;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_class, container, false);
        sqlHelper= new SQLHelper(getContext());

        listLop= sqlHelper.getAllLop();

        classAdapter= new ClassAdapter(listLop, getContext());
        layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        binding.revDSLop.setAdapter(classAdapter);
        binding.revDSLop.setLayoutManager(layoutManager);
        classAdapter.setiOnClick(new IOnClick() {
            @Override
            public void iOnClickUpdate(Lop lop, int i) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_lop_update);

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

                TextView tvMaLop = dialog.findViewById(R.id.tvMaLop_dialog_up);
                EditText tvTenLop= dialog.findViewById(R.id.tvTenLop_dialog_up);
                EditText tvGVCN = dialog.findViewById(R.id.tvGVCN_dialog_up);
                EditText tvSiSo = dialog.findViewById(R.id.tvSiso_dialog_up);

                Button btnUpdate= dialog.findViewById(R.id.btnUpdateNow);

                tvMaLop.setText(lop.getMaLop()+"");
                tvTenLop.setText(lop.getTenLop()+"");
                tvGVCN.setText(lop.getgVCN()+"");
                tvSiSo.setText(lop.getSoLuongSV()+"");

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lop lop = new Lop(tvMaLop.getText()+"", tvTenLop.getText().toString().trim(), Integer.parseInt(tvSiSo.getText().toString().trim()), tvGVCN.getText().toString().trim());
                        sqlHelper.upDateLop(lop);
                        listLop.remove(i);
                        listLop.add(i, lop);
                        classAdapter.updateList(listLop);
                        dialog.cancel();
                    }
                });

                dialog.show();
            }

            @Override
            public void iOnClickDelete(Lop lop, int i) {
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("XÓA LỚP")
                        .setMessage("Xác nhận xóa lớp: " + lop.getTenLop())
                        .setPositiveButton("ĐỒNG Ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sqlHelper.deleteLop(lop.getMaLop());
                                listLop.remove(i);
                                classAdapter.updateList(listLop);
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create();
                dialog.show();
            }

            @Override
            public void iOnClickAddStd(Lop lop, int i) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_add_std);

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

                EditText edtTen= dialog.findViewById(R.id.edtTenSV_dialog_cr);
                EditText edtMaSV= dialog.findViewById(R.id.edtMaSV_dialog_cr);
                EditText edtQueQuan= dialog.findViewById(R.id.edtQueQuan_dialog_cr);
                EditText edtNamSinh= dialog.findViewById(R.id.edtNamSinh_dialog_cr);
                EditText edtGioiTinh= dialog.findViewById(R.id.edtGioiTinh_dialog_cr);
                EditText edtSDT= dialog.findViewById(R.id.edtSDT_dialog_cr);
                EditText edtDiemTl= dialog.findViewById(R.id.edtDiemTL_dialog_cr);
                EditText edtXH= dialog.findViewById(R.id.edtXepHang_dialog_cr);

                Button btnAdd = dialog.findViewById(R.id.btnAddDialog_cr);

                String tenLop= lop.getTenLop();
                int soSV= lop.getSoLuongSV();
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Student student= new Student(edtMaSV.getText()+"", edtTen.getText()+"", tenLop, edtQueQuan.getText()+"", edtNamSinh.getText()+"", edtSDT.getText()+"", Float.parseFloat(edtDiemTl.getText()+""), edtXH.getText()+"", edtGioiTinh.getText()+"");
                            sqlHelper.insertStudent(student);
                            sqlHelper.upDateSLSVLop(tenLop, soSV);
                            listLop.get(i).setSoLuongSV(listLop.get(i).getSoLuongSV()+1);
                            classAdapter.updateList(listLop);
                            dialog.cancel();
                    }
                });
                dialog.show();
            }

            @Override
            public void iOnClickItemLop(Lop lop) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_all_student_of_class);

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

                List<Student> l= sqlHelper.GetStudentOfLop(lop.getTenLop());
                if (l.size()==0){
                    Toast.makeText(getContext(), "Lớp học này chưa có sinh viên nào", Toast.LENGTH_SHORT).show();
                }else{
                    RecyclerView recyclerView= dialog.findViewById(R.id.revAllSTDOFCLASS);
                    StdAdapter adapter= new StdAdapter(l);
                    RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
                dialog.show();
            }
        });

        binding.btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_lop_create);

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

                EditText tvMaLop = dialog.findViewById(R.id.tvMaLop_dialog_cr);
                EditText tvTenLop= dialog.findViewById(R.id.tvTenLop_dialog_cr);
                EditText tvGVCN = dialog.findViewById(R.id.tvGVCN_dialog_cr);
                EditText tvSiSo = dialog.findViewById(R.id.tvSiso_dialog_cr);

                Button btnCreate= dialog.findViewById(R.id.btnCreateNow);

                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lop lop = new Lop(tvMaLop.getText()+"", tvTenLop.getText()+"", Integer.parseInt(tvSiSo.getText()+""), tvGVCN.getText()+"");
                        long s=sqlHelper.insertLop(lop);
                        listLop.add(lop);
                        classAdapter.updateList(listLop);
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });


        binding.revDSLop.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    binding.btnAddClass.hide();
                }else {
                    binding.btnAddClass.show();
                }
            }
        });

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str= s.toString();
                listLop= sqlHelper.getAllLop();
                List<Lop> list= new ArrayList<>();
                int size= listLop.size();
                for (int i=0; i<size; i++){
                    if (listLop.get(i).getTenLop().contains(str)){
                        list.add(listLop.get(i));
                    }
                }
                classAdapter.updateList(list);
            }
        });
        return binding.getRoot();
    }
}

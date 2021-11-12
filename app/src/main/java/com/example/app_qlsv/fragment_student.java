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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_qlsv.Model.Student;
import com.example.app_qlsv.SQL.SQLHelper;
import com.example.app_qlsv.StudentController.IOnClickUDStd;
import com.example.app_qlsv.StudentController.StudentDetailsAdapter;
import com.example.app_qlsv.databinding.ActivityStudentBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class fragment_student extends Fragment {
    ActivityStudentBinding binding;
    SQLHelper sqlHelper;
    List<Student> students;
    StudentDetailsAdapter studentDetailsAdapter ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_student, container, false);
        sqlHelper= new SQLHelper(getContext());
        students= sqlHelper.getAllStudent();
        if (students.size()==0){
            binding.tvChuaCoSVDetails.setVisibility(View.VISIBLE);
        }else {
            studentDetailsAdapter = new StudentDetailsAdapter(students);
            RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            binding.revStdDetails.setAdapter(studentDetailsAdapter);
            binding.revStdDetails.setLayoutManager(layoutManager);

            studentDetailsAdapter.setiOnClickUDStd(new IOnClickUDStd() {
                @Override
                public void XoaSV(Student student, int i) {
                    AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setTitle("XÓA SINH VIÊN")
                            .setMessage("Xác nhận xóa: " + student.getHoTen())
                            .setPositiveButton("ĐỒNG Ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sqlHelper.deleteStudent(student.getMaSV());
                                    students.remove(i);
                                    studentDetailsAdapter.updateList(students);
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
                public void SuaSV(Student student, int i) {
                    final Dialog dialog = new Dialog(getContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.layout_dialog_update_std);

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

                    EditText edtTen= dialog.findViewById(R.id.edtTenSV_dialog_up);
                    EditText edtQueQuan= dialog.findViewById(R.id.edtQueQuan_dialog_up);
                    EditText edtNamSinh= dialog.findViewById(R.id.edtNamSinh_dialog_up);
                    EditText edtGioiTinh= dialog.findViewById(R.id.edtGioiTinh_dialog_up);
                    EditText edtSDT= dialog.findViewById(R.id.edtSDT_dialog_up);
                    EditText edtDiemTl= dialog.findViewById(R.id.edtDiemTL_dialog_up);
                    EditText edtXH= dialog.findViewById(R.id.edtXepHang_dialog_up);

                    Button btnUpdate = dialog.findViewById(R.id.btnUpdateDialog_up);

                    edtTen.setText(student.getHoTen());
                    edtQueQuan.setText(student.getQueQuan());
                    edtNamSinh.setText(student.getNamSinh());
                    edtGioiTinh.setText(student.getGioiTinh());
                    edtSDT.setText(student.getSoDT());
                    edtDiemTl.setText(student.getDiemTichLuy()+"");
                    edtXH.setText(student.getXepHang());

                    String maSVUp= student.getMaSV();
                    String lop= student.getLop();
                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Student student1= new Student(maSVUp, edtTen.getText()+"", lop, edtQueQuan.getText()+"", edtNamSinh.getText()+"", edtSDT.getText()+"", Float.parseFloat(edtDiemTl.getText()+""), edtXH.getText()+"", edtGioiTinh.getText()+"");
                            sqlHelper.upDateStudent(student1);
                            students.remove(i);
                            students.add(i, student1);
                            studentDetailsAdapter.updateList(students);
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });


            binding.edtSearchStd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String str= s.toString();
                    students= sqlHelper.getAllStudent();
                    List<Student> list= new ArrayList<>();
                    int size= students.size();
                    for (int i=0; i<size; i++){
                        if (students.get(i).getHoTen().contains(str)){
                            list.add(students.get(i));
                        }
                    }
                    studentDetailsAdapter.updateList(list);
                }
            });

        }





        return binding.getRoot();
    }
}

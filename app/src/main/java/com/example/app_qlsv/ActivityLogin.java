package com.example.app_qlsv;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_qlsv.Model.User;
import com.example.app_qlsv.SQL.SQLHelper;

public class ActivityLogin extends AppCompatActivity {
    static  final  String SHARE_PRE_NAME="User";
    TextView btnRegister, btnForgetPassword, tvError;
    Button btnLogin;
    CheckBox btnSavePassword;
    EditText edtUsername, edtPassword;
    SQLHelper sqlHelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        sqlHelper= new SQLHelper(ActivityLogin.this);

        //hiện sẵn tên đăng nhập và mật khẩu
        sharedPreferences = getSharedPreferences(SHARE_PRE_NAME,MODE_PRIVATE);
        String username =sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        edtUsername.setText(username);
        edtPassword.setText(password);

        btnSavePassword.setChecked(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= edtUsername.getText().toString().trim();
                String password= edtPassword.getText().toString().trim();
                User user= new User(username, password);
                if (sqlHelper.checkLogin(user)){
                    if(btnSavePassword.isChecked()){
                        savePassword(username, password);
                    }else {
                        savePassword("", "");
                    }

                    Intent intent= new Intent(ActivityLogin.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    tvError.setText("Tên tài khoản hoặc mật khẩu chưa đúng");
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ActivityLogin.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_login);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtributes = window.getAttributes();
                windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                window.setAttributes(windowAtributes);

                dialog.setCancelable(true);

                final EditText edtUser = dialog.findViewById(R.id.edtUser);
                final EditText edtPass = dialog.findViewById(R.id.edtPass);
                final EditText edtRePass = dialog.findViewById(R.id.edtRePass);
                Button btnDk = dialog.findViewById(R.id.btnDk);
                TextView tvDn = (TextView)dialog.findViewById(R.id.tvDn);
                final TextView tvErrDK = dialog.findViewById(R.id.tvErrDK);

                btnDk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //check password
                        String username= edtUser.getText().toString().trim();
                        String password= edtPass.getText().toString().trim();
                        String rePassword= edtRePass.getText().toString().trim();
                        if (password.compareTo(rePassword)!=0){
                            tvErrDK.setText("Hãy nhập lại mật khẩu trùng khớp!!!");
                            edtRePass.setText("");
                        }else
                        if (username.length()<5 || password.length()<3){
                            tvErrDK.setText("Tên đăng nhập hoặc mật khẩu chưa đủ dài!!!");
                        }else
                        if (sqlHelper.checkExistsUser(username)){
                            tvErrDK.setText("Tài khoản đã tồn tại");
                        }else {
                            // lưa vào db
                            User user= new User(username, password);
                            sqlHelper.insertUser(user);
                            dialog.cancel();
                            Toast.makeText(ActivityLogin.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                tvDn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


    }

    private void savePassword(String username, String password) {
        sharedPreferences = getSharedPreferences(SHARE_PRE_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    private void AnhXa() {
        btnRegister= findViewById(R.id.btnRegister);
        btnForgetPassword= findViewById(R.id.btnForgetPassword);
        tvError= findViewById(R.id.tvError);
        btnLogin= findViewById(R.id.btnLogin);
        btnSavePassword= findViewById(R.id.btnSavepassword);
        edtUsername= findViewById(R.id.edtUsername);
        edtPassword= findViewById(R.id.edtPassword);
    }
}
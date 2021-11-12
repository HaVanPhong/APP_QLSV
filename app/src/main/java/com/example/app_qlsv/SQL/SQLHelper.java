package com.example.app_qlsv.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.app_qlsv.Model.Lop;
import com.example.app_qlsv.Model.Student;
import com.example.app_qlsv.Model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "AccountDATA";
    static final int DB_VERSION = 8;
    static final String DB_TABLE_STUDENTS = "Students";
    static final String DB_TABLE_LOP = "LOP";
    static final String DB_TABLE_USERS = "USERS";
    static final String MALOP = "malop";
    static final String TENLOP = "tenlop";
    static final String SLSV = "soluongsv";
    static final String GVCN = "gvcn";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DB_TABLE_STUDENTS + "(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "masv TEXT, hoten TEXT, lop TEXT, quequan TEXT, namsinh TEXT, sdt TEXT, diemtl NUMBER, xephang TEXT, gioitinh Text)";

        String query2 = "CREATE TABLE " + DB_TABLE_LOP + "(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                MALOP + " TEXT," + TENLOP + " TEXT, " + SLSV + " NUMBER, " + GVCN + " TEXT)";

        String query3 = "CREATE TABLE " + DB_TABLE_USERS + "(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, password TEXT)";


        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_STUDENTS);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_LOP);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_USERS);
            onCreate(db);
        }
    }

    public void insertStudent(Student student) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("masv", student.getMaSV());
        contentValues.put("hoten", student.getHoTen());
        contentValues.put("lop", student.getLop());
        contentValues.put("quequan", student.getQueQuan());
        contentValues.put("namsinh", student.getNamSinh());
        contentValues.put("sdt", student.getSoDT());
        contentValues.put("diemtl", student.getDiemTichLuy());
        contentValues.put("xephang", student.getXepHang());
        contentValues.put("gioitinh", student.getGioiTinh());
        sqLiteDatabase.insert(DB_TABLE_STUDENTS, null, contentValues);
        sqLiteDatabase.close();
    }

    public long insertLop(Lop lop) {
        System.out.println("insert lơp nè:   " + lop);
        sqLiteDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("malop", lop.getMaLop());
        contentValues.put("tenlop", lop.getTenLop());
        contentValues.put("soluongsv", lop.getSoLuongSV());
        contentValues.put("gvcn", lop.getgVCN());
        long rs = sqLiteDatabase.insert(DB_TABLE_LOP, null, contentValues);
        sqLiteDatabase.close();
        Log.d("SQL", "insertLop: " + rs);
        return rs;
    }

    public void insertUser(User user) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        sqLiteDatabase.insert(DB_TABLE_USERS, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<Student> GetStudentOfLop(String tenlop) {
        List<Student> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_STUDENTS + " WHERE lop=?", new String[]{tenlop + ""});
        while (cursor.moveToNext()) {
            String masv = cursor.getString(cursor.getColumnIndex("masv"));
            String hoten = cursor.getString(cursor.getColumnIndex("hoten"));
            String lop = cursor.getString(cursor.getColumnIndex("lop"));
            String quequan = cursor.getString(cursor.getColumnIndex("quequan"));
            String namsinh = cursor.getString(cursor.getColumnIndex("namsinh"));
            String sdt = cursor.getString(cursor.getColumnIndex("sdt"));
            String diemtl = cursor.getString(cursor.getColumnIndex("diemtl"));
            String xephang = cursor.getString(cursor.getColumnIndex("xephang"));
            String gioitinh = cursor.getString(cursor.getColumnIndex("gioitinh"));
            list.add(new Student(masv, hoten, lop, quequan, namsinh, sdt, Float.parseFloat(diemtl + ""), xephang, gioitinh));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<Student> getAllStudent() {
        List<Student> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DB_TABLE_STUDENTS, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String masv = cursor.getString(cursor.getColumnIndex("masv"));
            String hoten = cursor.getString(cursor.getColumnIndex("hoten"));
            String lop = cursor.getString(cursor.getColumnIndex("lop"));
            String quequan = cursor.getString(cursor.getColumnIndex("quequan"));
            String namsinh = cursor.getString(cursor.getColumnIndex("namsinh"));
            String sdt = cursor.getString(cursor.getColumnIndex("sdt"));
            String diemtl = cursor.getString(cursor.getColumnIndex("diemtl"));
            String xephang = cursor.getString(cursor.getColumnIndex("xephang"));
            String gioitinh = cursor.getString(cursor.getColumnIndex("gioitinh"));
            list.add(new Student(masv, hoten, lop, quequan, namsinh, sdt, Float.parseFloat(diemtl + ""), xephang, gioitinh));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<Lop> getAllLop() {
        List<Lop> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
//        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+DB_TABLE_STUDENTS, new String[]{});
        Cursor cursor = sqLiteDatabase.query(DB_TABLE_LOP, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String malop = cursor.getString(cursor.getColumnIndex("malop"));
            String tenlop = cursor.getString(cursor.getColumnIndex("tenlop"));
            String soluongsv = cursor.getString(cursor.getColumnIndex("soluongsv"));
            String gvcn = cursor.getString(cursor.getColumnIndex("gvcn"));
            list.add(new Lop(malop, tenlop, Integer.parseInt(soluongsv), gvcn));
        }
        sqLiteDatabase.close();
        return list;
    }

    public void deleteStudent(String masv) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_STUDENTS, "masv=?", new String[]{masv + ""});
    }

    public void deleteAllAcount() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_USERS, null, null);
    }

    public void deleteLop(String malop) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_LOP, "malop=?", new String[]{malop + ""});
        sqLiteDatabase.close();
    }

    public void upDateLop(Lop lop) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        if (lop.getMaLop() != null)
            contentValues.put("malop", lop.getMaLop());
        if (lop.getTenLop() != null)
            contentValues.put("tenlop", lop.getTenLop());
        if (lop.getSoLuongSV() != 0)
            contentValues.put("soluongsv", lop.getSoLuongSV());
        if (lop.getgVCN() != null)
            contentValues.put("gvcn", lop.getgVCN());
        sqLiteDatabase.update(DB_TABLE_LOP, contentValues, "malop=?", new String[]{lop.getMaLop() + ""});
        sqLiteDatabase.close();
    }

    public void upDateStudent(Student student) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        if (student.getHoTen() != null)
            contentValues.put("hoten", student.getHoTen());
        if (student.getLop() != null)
            contentValues.put("lop", student.getLop());
        if (student.getQueQuan() != null)
            contentValues.put("quequan", student.getQueQuan());
        if (student.getNamSinh() != null)
            contentValues.put("namsinh", student.getNamSinh());
        if (student.getSoDT() != null)
            contentValues.put("sdt", student.getSoDT());
        if (student.getDiemTichLuy() != 0)
            contentValues.put("diemtl", student.getDiemTichLuy());
        if (student.getXepHang() != null)
            contentValues.put("xephang", student.getXepHang());
        if (student.getGioiTinh() != null)
            contentValues.put("gioitinh", student.getGioiTinh());

        sqLiteDatabase.update(DB_TABLE_STUDENTS, contentValues, "masv=?", new String[]{student.getMaSV() + ""});
        sqLiteDatabase.close();
    }

    public void upDateSLSVLop(String tenlop, int soSV) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("soluongsv", soSV + 1);
        sqLiteDatabase.update(DB_TABLE_LOP, contentValues, "tenlop=?", new String[]{tenlop + ""});
        sqLiteDatabase.close();
    }

    public boolean checkExistsStd(String masv) {
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_STUDENTS + " WHERE masv=?", new String[]{masv + ""});
        if (cursor.getCount() == 1) {
            sqLiteDatabase.close();
            return true;
        }
        sqLiteDatabase.close();
        return false;
    }

    public boolean checkExistsLop(String tenlop) {
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_LOP + " WHERE tenlop=?", new String[]{tenlop + ""});
        if (cursor.getCount() == 1) {
            sqLiteDatabase.close();
            return true;
        }
        sqLiteDatabase.close();
        return false;
    }

    public boolean checkExistsUser(String username) {
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_USERS + " WHERE username=?", new String[]{username + ""});
        if (cursor.getCount() == 1) {
            sqLiteDatabase.close();
            return true;
        }
        sqLiteDatabase.close();
        return false;
    }


    public boolean checkLogin(User user) {
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_USERS + " WHERE username=? and password=?", new String[]{user.getUsername() + "", user.getPassword() + ""});
        if (cursor.getCount() == 1) {
            sqLiteDatabase.close();
            return true;
        }
        sqLiteDatabase.close();
        return false;
    }

}

package com.example.app_qlsv.Model;

public class Student {
    private String maSV;
    private String hoTen;
    private String lop;
    private String queQuan;
    private String namSinh;
    private String soDT;
    private float diemTichLuy;
    private String xepHang;
    private String gioiTinh;


    public Student(String maSV, String hoTen, String lop, String queQuan, String namSinh, String soDT, float diemTichLuy, String xepHang, String gioiTinh) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.lop = lop;
        this.queQuan = queQuan;
        this.namSinh = namSinh;
        this.soDT = soDT;
        this.diemTichLuy = diemTichLuy;
        this.xepHang = xepHang;
        this.gioiTinh= gioiTinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public float getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(float diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public String getXepHang() {
        return xepHang;
    }

    public void setXepHang(String xepHang) {
        this.xepHang = xepHang;
    }

    public Student() {
    }
}

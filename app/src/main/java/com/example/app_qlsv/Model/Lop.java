package com.example.app_qlsv.Model;

public class Lop {
    private String maLop;
    private String tenLop;
    private int soLuongSV;
    private String gVCN;

    public Lop(String maLop, String tenLop, int soLuongSV, String gVCN) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.soLuongSV = soLuongSV;
        this.gVCN = gVCN;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getSoLuongSV() {
        return soLuongSV;
    }

    public void setSoLuongSV(int soLuongSV) {
        this.soLuongSV = soLuongSV;
    }

    public String getgVCN() {
        return gVCN;
    }

    public void setgVCN(String gVCN) {
        this.gVCN = gVCN;
    }

    public Lop() {
    }

    @Override
    public String toString() {
        return "Lop{" +
                "maLop='" + maLop + '\'' +
                ", tenLop='" + tenLop + '\'' +
                ", soLuongSV=" + soLuongSV +
                ", gVCN='" + gVCN + '\'' +
                '}';
    }
}

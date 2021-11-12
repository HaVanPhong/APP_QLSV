package com.example.app_qlsv.ClassController;

import com.example.app_qlsv.Model.Lop;

public interface IOnClick {
    void iOnClickUpdate(Lop lop, int i);
    void iOnClickDelete(Lop lop, int i);
    void iOnClickAddStd(Lop lop, int i);
    void iOnClickItemLop(Lop lop);
}

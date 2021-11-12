package com.example.app_qlsv.HomeController;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_qlsv.ActivityLogin;
import com.example.app_qlsv.Model.Lop;
import com.example.app_qlsv.R;

import java.util.List;

public class LopAdapter extends RecyclerView.Adapter<LopAdapter.ViewHolder> {
    List<Lop> list;
    Context context;
    IOnClickLop iOnClickLop;

    public IOnClickLop getiOnClickLop() {
        return iOnClickLop;
    }

    public void setiOnClickLop(IOnClickLop iOnClickLop) {
        this.iOnClickLop = iOnClickLop;
    }

    public LopAdapter(List<Lop> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_lop_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LopAdapter.ViewHolder holder, int position) {
        Lop lop= list.get(position);
        holder.tvTenLop.setText(lop.getTenLop()+"");
        holder.lnLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickLop.iOnClickLop(lop);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLop;
        LinearLayout lnLop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lnLop= itemView.findViewById(R.id.lnLop);
            tvTenLop= itemView.findViewById(R.id.tvTl);
        }
    }
}

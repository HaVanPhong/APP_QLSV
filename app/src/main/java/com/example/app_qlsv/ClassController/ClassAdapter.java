package com.example.app_qlsv.ClassController;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_qlsv.HomeController.LopAdapter;
import com.example.app_qlsv.Model.Lop;
import com.example.app_qlsv.R;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    List<Lop> list= new ArrayList<>();
    Context context;

    IOnClick iOnClick;


    public IOnClick getiOnClick() {
        return iOnClick;
    }

    public void setiOnClick(IOnClick iOnClick) {
        this.iOnClick = iOnClick;
    }

    public ClassAdapter(List<Lop> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_lop_layout_crete, parent, false);
        ClassAdapter.ViewHolder viewHolders = new ClassAdapter.ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ViewHolder holder, int position) {
        Lop lop= list.get(position);
        holder.tvTenLop.setText(lop.getTenLop()+"");
        holder.tvGVCN.setText("CN: "+lop.getgVCN()+"");
        holder.tvMaLop.setText(lop.getMaLop()+"");
        holder.tvSoSV.setText(lop.getSoLuongSV()+"");

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClick.iOnClickDelete(lop, position);
                Log.d("dl", "onClick delete: "+ lop);
            }
        });
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pre", "onClick update 1: "+ lop);
                iOnClick.iOnClickUpdate(lop, position);
            }
        });

        holder.imgAddStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pre", "onClick1: "+ lop);
                iOnClick.iOnClickAddStd(lop, position);
            }
        });

        holder.lnLop_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClick.iOnClickItemLop(lop);
                Log.d("dl", "iOnClickItemLop  trong adapter: "+  lop);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGVCN, tvTenLop, tvMaLop, tvSoSV;
        ImageView imgUpdate, imgDelete, imgAddStd;
        LinearLayout lnLop_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lnLop_item= itemView.findViewById(R.id.lnLop_item_class);
            imgAddStd= itemView.findViewById(R.id.imgAddStd);
            tvTenLop= itemView.findViewById(R.id.tvTenLop_item);
            tvMaLop= itemView.findViewById(R.id.tvMaLop_item);
            tvSoSV= itemView.findViewById(R.id.tvSoSV_item);
            tvGVCN= itemView.findViewById(R.id.tvGVCN_item);

            imgDelete= itemView.findViewById(R.id.imgDelete);
            imgUpdate= itemView.findViewById(R.id.imgUpdate);
        }
    }

    public void updateList(List<Lop> lops){
        list= lops;
        notifyDataSetChanged();
    }
}

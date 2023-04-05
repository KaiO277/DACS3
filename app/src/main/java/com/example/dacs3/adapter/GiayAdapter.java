package com.example.dacs3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dacs3.R;
import com.example.dacs3.model.SapPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.MyViewHolder> {
    Context context;
    List<SapPhamMoi> array;

    public GiayAdapter(Context context, List<SapPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giay, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SapPhamMoi sanPham = array.get(position);
        holder.tensp.setText(sanPham.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: "+decimalFormat.format(Double.parseDouble(sanPham.getPrice()))+"Đ");
        holder.mota.setText(sanPham.getDescription());
        Glide.with(context).load(sanPham.getThumb()).into(holder.hinhanh);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView tensp, giasp, mota;
        ImageView hinhanh;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp   = itemView.findViewById(R.id.itemgiay_ten);
            giasp   = itemView.findViewById(R.id.itemgiay_gia);
            mota    = itemView.findViewById(R.id.itemgiay_mota);
            hinhanh = itemView.findViewById(R.id.itemgiay_image);
        }
    }
}

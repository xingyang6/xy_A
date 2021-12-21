package com.example.a12_3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.R;
import com.example.a12_3.bean.hourse_bean;
import com.example.a12_3.hours.house_init;
import com.example.a12_3.util.NetWork;

import java.util.List;

public class hourse_Adapter extends RecyclerView.Adapter<hourse_Adapter.LearViewHolder> {
    private Context context;
    private int type;
private List<hourse_bean> hourse;
    public hourse_Adapter(Context context,List<hourse_bean> hourse){
        this.context=context;
        this.hourse=hourse;
    }
    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.hourse_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        NetWork.doImager(context,hourse.get(position).getPic(),holder.imageView29);
        holder.hourseAddress.setText(hourse.get(position).getAddress());
        holder.hoursePrice.setText(hourse.get(position).getPrice());
        holder.hourseAreaSize.setText(hourse.get(position).getAreaSize()+"m*m");
        holder.hourseDescription.setText(hourse.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                house_init.hourse_id=hourse.get(position).getId();
                house_init.phone=hourse.get(position).getTel();
                context.startActivity(new Intent(context,house_init.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return hourse.size();
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView29;
        private TextView hourseAddress;
        private TextView hoursePrice;
        private TextView hourseAreaSize;
        private TextView hourseDescription;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView29 = itemView.findViewById(R.id.imageView29);
            hourseAddress = itemView.findViewById(R.id.hourse_address);
            hoursePrice = itemView.findViewById(R.id.hourse_price);
            hourseAreaSize = itemView.findViewById(R.id.hourse_areaSize);
            hourseDescription = itemView.findViewById(R.id.hourse_description);
        }
    }
}

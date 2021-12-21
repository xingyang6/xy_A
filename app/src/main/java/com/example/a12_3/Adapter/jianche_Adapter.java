package com.example.a12_3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.R;
import com.example.a12_3.jianche.Adapter;

public class jianche_Adapter extends RecyclerView.Adapter<jianche_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    public jianche_Adapter(Context context,int a){
        this.context=context;
        this.type=a;
    }
    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder=null;
        switch (type){
            case 0:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.jianche_4_item,parent,false));
                break;
            case 1:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.jianche_3_item,parent,false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
                switch (type){
                    case 0:
                        holder.chepai.setText("车牌号："+Adapter.chepaihao.get(position));
                        holder.chejia.setText("车架号："+Adapter.chejiahao.get(position));
                        holder.cheliangtype.setText("车辆类型：："+Adapter.cheliangtype.get(position));
                        holder.km.setText("公里数："+Adapter.km.get(position));
                        holder.phone.setText("手机号："+Adapter.phone.get(position));
                        break;
                    case 1:
                        holder.jianche3Chepaiaho.setText(Adapter.chengg_chepaihao.get(position));
                        holder.jianche3Data.setText(Adapter.chengg_date.get(position));
                        holder.jianche3Adress.setText(Adapter.chengg_adress.get(position));
                        break;
                }
    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=Adapter.chejiahao.size();
                break;
            case 1:
                a=Adapter.chengg_date.size();
                break;
        }
        return a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private TextView chepai;
        private TextView chejia;
        private TextView cheliangtype;
        private TextView km;
        private TextView phone;

        private TextView jianche3Chepaiaho;
        private TextView jianche3Data;
        private TextView jianche3Adress;

        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    chepai = itemView.findViewById(R.id.chepai);
                    chejia = itemView.findViewById(R.id.chejia);
                    cheliangtype = itemView.findViewById(R.id.cheliangtype);
                    km = itemView.findViewById(R.id.km);
                    phone = itemView.findViewById(R.id.phone);
                    break;
                case 1:
                    jianche3Chepaiaho = itemView.findViewById(R.id.jianche_3_chepaiaho);
                    jianche3Data = itemView.findViewById(R.id.jianche_3_data);
                    jianche3Adress = itemView.findViewById(R.id.jianche_3_adress);
                    break;
            }
        }
    }
}

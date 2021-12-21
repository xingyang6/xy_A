package com.example.a12_3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.R;
import com.example.a12_3.bean.jiuehzenrne_bean;
import com.example.a12_3.bean.menzhen_keshi_bean;
import com.example.a12_3.bean.yiyuan_list_bean;
import com.example.a12_3.jianche.Adapter;
import com.example.a12_3.menzhen.menzhen_init;
import com.example.a12_3.menzhen.menzhen_jiuzherne_init;
import com.example.a12_3.menzhen.menzhen_keshi_type_1;
import com.example.a12_3.menzhen.menzhen_ok;
import com.example.a12_3.menzhen.menzhne_keshi_type;
import com.example.a12_3.util.NetWork;

import java.util.List;

public class menzhen_Adapter extends RecyclerView.Adapter<menzhen_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    private List<yiyuan_list_bean> lsit;
    private List<jiuehzenrne_bean> jzr;
    private List<menzhen_keshi_bean> keshi;

    public menzhen_Adapter(Context context,List<yiyuan_list_bean> lsit, List<jiuehzenrne_bean> jzr,List<menzhen_keshi_bean> keshi,int a) {
        this.context = context;
        this.type = a;
        this.lsit=lsit;
        this.jzr=jzr;
        this.keshi=keshi;
    }

    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder = null;
        switch (type) {
            case 0:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.menzhen_home_list_item, parent, false));
                break;
            case 1:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.jzr_item, parent, false));
                break;
            case 2:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.keshi_item, parent, false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        switch (type) {
            case 0:
                NetWork.doImager(context,lsit.get(position).getImgUrl(),holder.menzhenHomeImg);
                holder.menzhenHomeName.setText(lsit.get(position).getHospitalName());
                holder.menzhenHomeXx.setRating(lsit.get(position).getLevel());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        menzhen_ok.keshi_name=lsit.get(position).getHospitalName();
                        menzhen_ok.keshi_id=lsit.get(position).getId();
                        menzhen_init.Hospital_id=lsit.get(position).getId();
                        context.startActivity(new Intent(context,menzhen_init.class));
                    }
                });
                break;
            case 1:
                    holder.jzrIdcard.setText(jzr.get(position).getCardId());
                    holder.jzrName.setText(jzr.get(position).getName());
                    holder.jzrPhone.setText(jzr.get(position).getTel());
                    holder.jzrJianit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            menzhen_ok.user_name=jzr.get(position).getName();
                            context.startActivity(new Intent(context, menzhne_keshi_type.class));
                        }
                    });
                    holder.jzrSet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            menzhen_jiuzherne_init.id=jzr.get(position).getId();
                            menzhen_jiuzherne_init.pos=position;
                            menzhen_jiuzherne_init.type=0;
                            context.startActivity(new Intent(context,menzhen_jiuzherne_init.class));
                        }
                    });
                break;
            case 2:
                    holder.textView31.setText(keshi.get(position).getCategoryName());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            menzhen_ok.money=keshi.get(position).getMoney();
                            menzhen_keshi_type_1.keshi_naem=keshi.get(position).getCategoryName();
                            context.startActivity(new Intent(context,menzhen_keshi_type_1.class));
                        }
                    });
                break;
        }
    }

    @Override
    public int getItemCount() {
        int a = 0;
        switch (type) {
            case 0:
                a=lsit.size();
                break;
            case 1:
                a=jzr.size();
                break;
            case 2:
                a=keshi.size();
                break;
        }
        return a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private ImageView menzhenHomeImg;
        private TextView menzhenHomeName;
        private RatingBar menzhenHomeXx;

        private LinearLayout addJiuzhenrne;
        private TextView jzrName;
        private ImageView jzrSet;
        private ImageView jzrJianit;
        private TextView jzrIdcard;
        private TextView jzrPhone;

        private TextView textView31;
        private ImageView imageView10;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type) {
                case 0:
                    menzhenHomeImg = itemView.findViewById(R.id.menzhen_home_img);
                    menzhenHomeName = itemView.findViewById(R.id.menzhen_home_name);
                    menzhenHomeXx = itemView.findViewById(R.id.menzhen_home_xx);
                    break;
                case 1:
                    jzrName = itemView.findViewById(R.id.jzr_name);
                    jzrSet = itemView.findViewById(R.id.jzr_set);
                    jzrJianit = itemView.findViewById(R.id.jzr_jiant);
                    jzrIdcard = itemView.findViewById(R.id.jzr_idcard);
                    jzrPhone = itemView.findViewById(R.id.jzr_phone);
                    break;
                case 2:
                    textView31 = itemView.findViewById(R.id.textView31);
                    imageView10 = itemView.findViewById(R.id.imageView10);
                    break;
            }
        }
    }
}

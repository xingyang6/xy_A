package com.example.a12_3.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.R;
import com.example.a12_3.bean.all_service_bean;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class All_Adapter extends RecyclerView.Adapter<All_Adapter.LearViewHolder> {
    private String[] service={"车主服务","生活服务","便民服务"};
    private Context context;
    private int type;
    private List<all_service_bean> list;
    public All_Adapter(Context context,List<all_service_bean> list,int a){
        this.context=context;
        this.list=list;
        this.type=a;
    }


    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder=null;
        switch (type){
            case 0:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.laa_service_item,parent,false));
                break;
            case 1:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.nei_item,parent,false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        switch (type){
            case 0:
                holder.serviceName.setText(service[position]);
                NetWork.doGet("/prod-api/api/service/list?serviceType=" + service[position], new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            List<all_service_bean> sear=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<all_service_bean>>(){
                            }.getType());
                            holder.allServiceNeiRecy.setLayoutManager(new GridLayoutManager(context,3));
                            holder.allServiceNeiRecy.setAdapter(new All_Adapter(context,sear,1));
                        }else{
                            Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case 1:
                    NetWork.doImager(context,list.get(position).getImgUrl(),holder.neiImg);
                    holder.neiText.setText(list.get(position).getServiceName());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "点击"+list.get(position).getServiceName(), Toast.LENGTH_SHORT).show();
                        }
                    });
                break;
        }

    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=service.length;
                break;
            case 1:
                a=list.size();
                break;
        }
        return a;
    }
    public class LearViewHolder extends RecyclerView.ViewHolder {
        private TextView serviceName;
        private RecyclerView allServiceNeiRecy;

        private ImageView neiImg;
        private TextView neiText;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    serviceName = itemView.findViewById(R.id.service_name);
                    allServiceNeiRecy = itemView.findViewById(R.id.all_service_nei_recy);
                    break;
                case 1:
                    neiImg = itemView.findViewById(R.id.nei_img);
                    neiText = itemView.findViewById(R.id.nei_text);
                    break;
            }
        }
    }
}

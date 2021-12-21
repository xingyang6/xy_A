package com.example.a12_3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.R;
import com.example.a12_3.bean.home_remen_bean;
import com.example.a12_3.bean.home_tuijian_bean;
import com.example.a12_3.bean.news_pingl_bean;
import com.example.a12_3.home.nes_init;
import com.example.a12_3.hours.hours_home;
import com.example.a12_3.jianche.jianche;
import com.example.a12_3.menzhen.menzhen_home;
import com.example.a12_3.util.NetWork;

import java.util.List;

public class home_Adapter extends RecyclerView.Adapter<home_Adapter.LearViewHolder> {
    private Context context;
    private List<home_tuijian_bean> tuijain;
    private List<home_remen_bean> remen;
    private List<news_pingl_bean> pl;
    private int type;
    private ImageView imageView4;
    private TextView title;
    private TextView newsCount;
    private TextView pinglCount;
    private TextView data;

    public home_Adapter(Context context, List<home_tuijian_bean> tuijain, List<home_remen_bean> remen,List<news_pingl_bean> pl, int a) {
        this.context = context;
        this.tuijain = tuijain;
        this.remen = remen;
        this.pl=pl;
        this.type = a;
    }

    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder = null;
        switch (type) {
            case 0:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.home_tuijain_item, parent, false));
                break;
            case 1:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.hom_remen_item, parent, false));
                break;
            case 2:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false));
                break;
            case 3:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false));
                break;
            case 4:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news_pingl_item, parent, false));
                break;
            case 5:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.search_item, parent, false));
                break;
            case 6:
                learViewHolder = new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news_tuijian, parent, false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        switch (type) {
            case 0:
                switch (position) {
                    case 3:
                        NetWork.doImager(context, tuijain.get(position).getImgUrl(), holder.tuijainImg);
                        holder.tuijainName.setText(tuijain.get(position).getServiceName());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, hours_home.class));
                            }
                        });
                        break;
                    case 7:
                        holder.tuijainImg.setImageResource(R.drawable.menzhen);
                        holder.tuijainName.setText("门诊预约");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, menzhen_home.class));
                            }
                        });
                        break;
                    case 8:
                        holder.tuijainImg.setImageResource(R.drawable.jc);
                        holder.tuijainName.setText("预约检车");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context,jianche.class));
                            }
                        });
                        break;
                    case 9:
                        holder.tuijainImg.setImageResource(R.drawable.ic_dashboard_black_24dp);
                        holder.tuijainName.setText("更多服务");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(v).navigate(R.id.navigation_dashboard);
                            }
                        });
                        break;
                    default:
                        NetWork.doImager(context, tuijain.get(position).getImgUrl(), holder.tuijainImg);
                        holder.tuijainName.setText(tuijain.get(position).getServiceName());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, "点击了" + tuijain.get(position).getServiceName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
                break;
            case 1:
                NetWork.doImager(context, remen.get(position).getCover(), holder.imageView3);
                holder.title.setText(Html.fromHtml(remen.get(position).getContent()));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nes_init.news_id=remen.get(position).getId();
                        context.startActivity(new Intent(context,nes_init.class));
                    }
                });
                break;
            case 2:
                NetWork.doImager(context, remen.get(position).getCover(), holder.imageView4);
                holder.newstitle.setText(remen.get(position).getTitle());
                holder.newsCount.setText(Html.fromHtml(remen.get(position).getContent()));
                holder.pinglCount.setText(remen.get(position).getCommentNum()+"");
                holder.data.setText(remen.get(position).getPublishDate());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nes_init.news_id=remen.get(position).getId();
                        context.startActivity(new Intent(context,nes_init.class));
                    }
                });
                break;
            case 3:
                if (remen.get(position).getId()!= nes_init.news_id){
                    NetWork.doImager(context, remen.get(position).getCover(), holder.imageView4);
                    holder.newstitle.setText(remen.get(position).getTitle());
                    holder.newsCount.setText(Html.fromHtml(remen.get(position).getContent()));
                    holder.pinglCount.setText(remen.get(position).getCommentNum()+"");
                    holder.data.setText(remen.get(position).getPublishDate());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nes_init.news_id=remen.get(position).getId();
                            context.startActivity(new Intent(context,nes_init.class));
                        }
                    });
                }else{
                    NetWork.doImager(context, remen.get(position+10).getCover(), holder.imageView4);
                    holder.newstitle.setText(remen.get(position+10).getTitle());
                    holder.newsCount.setText(Html.fromHtml(remen.get(position+10).getContent()));
                    holder.pinglCount.setText(remen.get(position+10).getCommentNum()+"");
                    holder.data.setText(remen.get(position+10).getPublishDate());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nes_init.news_id=remen.get(position+10).getId();
                            context.startActivity(new Intent(context,nes_init.class));
                        }
                    });
                }
                break;
            case 4:
                holder.username.setText(pl.get(position).getUserName());
                holder.plData.setText(pl.get(position).getCommentDate());
                holder.plCount.setText(Html.fromHtml(pl.get(position).getContent()));
                break;
            case 5:
                holder.textView6.setText(remen.get(position).getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nes_init.news_id=remen.get(position).getId();
                        context.startActivity(new Intent(context,nes_init.class));
                    }
                });
                break;
            case 6:
                NetWork.doImager(context, remen.get(position).getCover(), holder.mimageView4);
                holder.newsTitle.setText(remen.get(position).getTitle());
                holder.mnewsCount.setText(Html.fromHtml(remen.get(position).getContent()));
                holder.personalCount.setText(remen.get(position).getReadNum()+"");
                holder.dianzanCount.setText(remen.get(position).getLikeNum()+"");
                break;

        }

    }

    @Override
    public int getItemCount() {
        int a = 0;
        switch (type) {
            case 0:
                a = 10;
                break;
            case 1:
                a = remen.size();
                break;
            case 2:
                a = remen.size();
                break;
            case 3:
                a = 3;
                break;
            case 4:
                a = pl.size();
                break;
            case 5:
                a = remen.size();
                break;
            case 6:
                a = remen.size();
                break;
        }
        return a;
    }

    private void initView(View itemView) {
        imageView4 = itemView.findViewById(R.id.imageView4);
        title = itemView.findViewById(R.id.title);
        newsCount = itemView.findViewById(R.id.news_count);
        pinglCount = itemView.findViewById(R.id.pingl_count);
        data = itemView.findViewById(R.id.data);
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private ImageView tuijainImg;
        private TextView tuijainName;

        private ImageView imageView3;
        private TextView title;

        private ImageView imageView4;
        private TextView newstitle;
        private TextView newsCount;
        private TextView pinglCount;
        private TextView data;
        private LinearLayout linearLayout;

        private TextView username;
        private TextView plData;
        private TextView plCount;

        private TextView textView6;

        private ImageView mimageView4;
        private TextView newsTitle;
        private TextView mnewsCount;
        private ImageView imageView6;
        private TextView personalCount;
        private ImageView imageView9;
        private TextView dianzanCount;

        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type) {
                case 0:
                    tuijainImg = itemView.findViewById(R.id.tuijain_img);
                    tuijainName = itemView.findViewById(R.id.tuijain_name);

                    break;
                case 1:
                    imageView3 = itemView.findViewById(R.id.imageView3);
                    title = itemView.findViewById(R.id.title);
                    break;
                case 2:
                    imageView4 = itemView.findViewById(R.id.imageView4);
                    newstitle = itemView.findViewById(R.id.news_title);
                    newsCount = itemView.findViewById(R.id.news_count);
                    pinglCount = itemView.findViewById(R.id.pingl_count);
                    data = itemView.findViewById(R.id.data);
                    break;
                case 3:
                    linearLayout=itemView.findViewById(R.id.line);
                    imageView4 = itemView.findViewById(R.id.imageView4);
                    newstitle = itemView.findViewById(R.id.news_title);
                    newsCount = itemView.findViewById(R.id.news_count);
                    pinglCount = itemView.findViewById(R.id.pingl_count);
                    data = itemView.findViewById(R.id.data);
                    break;
                case 4:
                    username = itemView.findViewById(R.id.username);
                    plData = itemView.findViewById(R.id.pl_data);
                    plCount = itemView.findViewById(R.id.pl_count);
                    break;
                case 5:
                    textView6 = itemView.findViewById(R.id.textView6);
                    break;
                case 6:
                    mimageView4 = itemView.findViewById(R.id.imageView4);
                    newsTitle = itemView.findViewById(R.id.news_title);
                    mnewsCount = itemView.findViewById(R.id.news_count);
                    imageView6 = itemView.findViewById(R.id.imageView6);
                    personalCount = itemView.findViewById(R.id.personal_count);
                    imageView9 = itemView.findViewById(R.id.imageView9);
                    dianzanCount = itemView.findViewById(R.id.dianzan_count);

                    break;
            }
        }
    }
}

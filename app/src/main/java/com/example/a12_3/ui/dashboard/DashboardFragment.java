package com.example.a12_3.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.All_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.all_service_bean;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.example.a12_3.util.gongge;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TextView sear1;
    private TextView sear2;
    private TextView sear3;
    private RecyclerView allServiceRecy;
    private SearchView searchSear;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        intdata();

    }

    private void intttank( String s) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.pop_up_item,null);
        builder.setView(view);
        NetWork.doGet("/prod-api/api/service/list?serviceName=" + s, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    TextView textView=view.findViewById(R.id.pop_up_text);
                    RecyclerView recyclerView=view.findViewById(R.id.pop_up_recy);
                    List<all_service_bean> sear=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<all_service_bean>>(){
                    }.getType());
                    if (sear.size()==0){
                        textView.setVisibility(View.VISIBLE);
                    }else{
                        textView.setVisibility(View.GONE);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                        recyclerView.setAdapter(new All_Adapter(getActivity(),sear,1));
                    }
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }

    private void intdata() {
        sear1.setBackgroundColor(Color.rgb(255, 255, 255));
        sear2.setBackgroundColor(Color.rgb(239, 234, 234));
        sear3.setBackgroundColor(Color.rgb(239, 234, 234));
        sear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sear1.setBackgroundColor(Color.rgb(255, 255, 255));
                sear2.setBackgroundColor(Color.rgb(239, 234, 234));
                sear3.setBackgroundColor(Color.rgb(239, 234, 234));
            }
        });
        sear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sear2.setBackgroundColor(Color.rgb(255, 255, 255));
                sear1.setBackgroundColor(Color.rgb(239, 234, 234));
                sear3.setBackgroundColor(Color.rgb(239, 234, 234));
            }
        });
        sear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sear3.setBackgroundColor(Color.rgb(255, 255, 255));
                sear2.setBackgroundColor(Color.rgb(239, 234, 234));
                sear1.setBackgroundColor(Color.rgb(239, 234, 234));
            }
        });
        List<all_service_bean> list = new ArrayList<>();
        allServiceRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        allServiceRecy.setAdapter(new All_Adapter(getActivity(), list, 0));

        //搜索
        searchSear.setSubmitButtonEnabled(true);
        searchSear.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        searchSear.setIconified(true);
        searchSear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (gongge.isFirst()){
                    return false;
                }else{
                    intttank(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    private void initView(View view) {
        sear1 = view.findViewById(R.id.sear_1);
        sear2 = view.findViewById(R.id.sear_2);
        sear3 = view.findViewById(R.id.sear_3);
        allServiceRecy = view.findViewById(R.id.all_service_recy);
        searchSear = view.findViewById(R.id.search_sear);
    }
}
package com.example.a12_3.zhihui1dnagjain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;

public class tuijain_4 extends BaseActivity {

    private ImageView imageView37;
    private EditText editTextTextPersonName4;
    private Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijain_4);
        setTitle("随手拍");
        initView();
        initdata();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0 && resultCode==RESULT_OK){
            Bundle ex =data.getExtras();
            Bitmap bitmap=(Bitmap) ex.get("data");
            imageView37.setImageBitmap(bitmap);
        }
    }

    private void initdata() {
        imageView37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent take=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (take.resolveActivity(getPackageManager())!= null){
                    startActivityForResult(take,0);
                }
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextPersonName4.getText().toString().trim().isEmpty()){
                    Toast.makeText(tuijain_4.this, "请输入", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(tuijain_4.this, "已提交", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initView() {
        imageView37 = findViewById(R.id.imageView37);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        button9 = findViewById(R.id.button9);
    }
}
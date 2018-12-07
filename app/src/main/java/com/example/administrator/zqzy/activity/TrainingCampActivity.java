package com.example.administrator.zqzy.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.zqzy.AppConfig;
import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.fragment.MyFragment;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrainingCampActivity extends AppCompatActivity {

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.one)
    ImageView one;
    @BindView(R.id.two)
    ImageView two;
    @BindView(R.id.three)
    ImageView three;
    @BindView(R.id.five)
    ImageView five;
    @BindView(R.id.six)
    ImageView six;
    @BindView(R.id.seven)
    ImageView seven;
    @BindView(R.id.eight)
    ImageView eight;
    @BindView(R.id.nine)
    ImageView nine;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.scan)
    ImageView scan;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_camp);
        ButterKnife.bind(this);
        title.setText("会计训练营");
        context = this;
        setImg();
        longClick();
    }

    //    放置图片
    private void setImg() {
        Glide.with(this).load(R.mipmap.camp1).into(one);
        Glide.with(this).load(R.mipmap.camp2).into(two);
        Glide.with(this).load(R.mipmap.camp3).into(three);
        Glide.with(this).load(R.mipmap.camp5).into(five);
        Glide.with(this).load(R.mipmap.camp6).into(six);
        Glide.with(this).load(R.mipmap.camp7).into(seven);
        Glide.with(this).load(R.mipmap.camp8).into(eight);
        Glide.with(this).load(R.mipmap.scan).into(scan);
    }

    //打电话
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:18511996678");
        intent.setData(data);
        startActivity(intent);
    }

    //点击事件
    @OnClick({R.id.back, R.id.tel, R.id.ok, R.id.scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tel:
                callPhone();
                break;
            case R.id.ok:
                Intent intent = new Intent(this, AskActivity.class);
                startActivity(intent);
                break;
            case R.id.scan:
                break;
        }
    }

    //长按
    private void longClick() {
        scan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ReadAndWrite();
                return false;
            }
        });
    }

    //保存图片
    private void savePhoto() throws IOException {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.scan, null);
        File file = new File(AppConfig.appDownload);
        if (!file.exists())
            file.mkdir();
        String fileName = System.currentTimeMillis() + ".png";
        file = new File(AppConfig.appDownload + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        boolean is = bitmap.compress(Bitmap.CompressFormat.PNG, 60, fos);
        fos.flush();
        fos.close();
        Uri uri = Uri.fromFile(file);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        Toast.makeText(context, "图片已保存到" + AppConfig.appDownload + fileName, Toast.LENGTH_LONG).show();
    }

    //读写权限
    public void ReadAndWrite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int white = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (white == PackageManager.PERMISSION_GRANTED && PackageManager.PERMISSION_GRANTED == read) {
                Log.e("可以", "可以");
                TrainingCampActivity.this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                Log.e("禁止", "禁止");
                TrainingCampActivity.this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } else {
            Log.e("版本太低静态申请", "版本太低静态申请");

        }
    }

    //    读写权限允许后返回值
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            int one = grantResults[0];
            int two = grantResults[1];
            Log.e("返回值成功", "返回值成功");
            if (one == PackageManager.PERMISSION_GRANTED && two == PackageManager.PERMISSION_GRANTED) {
                Log.e("允许权限", "允许权限");
                try {
                    savePhoto();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                boolean isWrite = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0]);
                boolean isRead = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[1]);
                if (!isWrite || !isRead) {
                    Toast.makeText(this, "您以禁止读写权限", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}

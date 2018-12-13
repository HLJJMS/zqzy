package com.example.administrator.zqzy.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private LoginPresenter presenter;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.weixin)
    Button weixin;
    @BindView(R.id.editpsw)
    TextView editpsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
    }


    @OnClick({R.id.ok, R.id.weixin, R.id.editpsw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ok:
                login();
                break;
            case R.id.weixin:
                break;
            case R.id.editpsw:
                break;
        }
    }

    private void login() {
        if(!number.getText().equals("")&&!password.getText().equals("")){
            presenter.login(number.getText().toString(),password.getText().toString());
        }else{
            showToast("请输入用户名密码");
        }
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void success() {
    finish();
    Intent intent = new Intent(this,MainActivity.class);
    startActivity(intent);
    }
}

package com.example.administrator.zqzy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zqzy.R;
import com.example.administrator.zqzy.base.DropDownViewNoDefault;
import com.example.administrator.zqzy.base.bean.DropDownItem;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*会计集训营在线咨询*/
public class AskActivity extends AppCompatActivity {

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.drop_down_type)
    DropDownViewNoDefault dropDownType;
    @BindView(R.id.ask_detail)
    EditText askDetail;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.ok)
    TextView ok;
    private LinkedList<DropDownItem> typeDown=new LinkedList<DropDownItem>();
    private String type,phone,detail,preson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        ButterKnife.bind(this);
        setDataForDownView();
        title.setText("我要咨询");
    }
//    下拉列表死数据装载
    private void setDataForDownView() {
        typeDown.add(new DropDownItem("外资企业财务", "外资企业财务"));
        typeDown.add(new DropDownItem("内资企业财务", "内资企业财务"));
        typeDown.add(new DropDownItem("最新政策解读", "最新政策解读"));
        typeDown.add(new DropDownItem("企业税务业务", "企业税务业务"));
        typeDown.add(new DropDownItem("用友软件操作", "用友软件操作"));
        typeDown.add(new DropDownItem("用友软件实施", "用友软件实施"));
        dropDownType.setmData(typeDown);
    }

    @OnClick({R.id.back, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ok:
                submit();
                break;
        }
    }
//    提交验证
    private void submit() {
        type=dropDownType.getValue();
        preson = name.getText().toString();
        phone = tel.getText().toString();
        detail = askDetail.getText().toString();
        if(preson==""){
            showToast("请留下您的姓名，以便于我们后期联系您");
        }else if (phone==""){
            showToast("请留下您的联系方式，以便于我们后期联系您");
        }else if (detail==""){
            showToast("请输入您要咨询的内容");
        }else{

        }
    }
    private void showToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();

    }

}

package app.com.auto_fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn_add;
    private Button btn_delete;
    private Button btn_modify;
     MyFragment1 mf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 进行动态布局中对Fragment的动态切换 添加响应的事务管理器和事务
         * */
        mf1=new MyFragment1();
//        mf1.setOnBtnClickListener(new MyFragment1.OnBtnClickListener() {
//            @Override
//            public void onBtnClick() {
//                TextView textView=(TextView) findViewById(R.id.tv_in_activity);
//                textView.setText("fragment->activity(接口反馈)");
//            }
//        });
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_modify=(Button)findViewById(R.id.motify_from_activity_to_fragment);
        btn_add.setOnClickListener(new BtnPressedUtil());
        btn_delete.setOnClickListener(new BtnPressedUtil());
        btn_modify.setOnClickListener(new BtnPressedUtil());
    }

    class BtnPressedUtil implements View.OnClickListener {

        FragmentManager fm;
        FragmentTransaction ft;
        /**
         * 每次进行fragment的更改需要进行管理和事务的更新
         * */
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_add:
                    /**
                     * 1.容器id
                     * 2.创建fragment实体
                     * */
                    fm = getSupportFragmentManager();//添加事务器
                    ft = fm.beginTransaction();//添加事务
                    mf1 = new MyFragment1();//创建实体
                    ft.add(R.id.my_fragmentlayout1, mf1);
                    //事务提交
                    ft.commit();
                    break;
                case R.id.btn_delete:
                    fm = getSupportFragmentManager();//添加事务器
                    ft = fm.beginTransaction();//添加事务
                    ft.remove(mf1);
                    ft.commit();//事务处理后需要提交
                    break;
                case R.id.motify_from_activity_to_fragment:
                    /**
                     * 通过按按钮实现调整activity中fragment中的ui内容
                     * */
                    mf1.modify();
                    //通过接口进行回调
                    break;
                default:
                    break;
            }
        }
    }
    //从fragment中进行方法的调用，实现在activity中的方法更改
    public void modify(){
        TextView textView=(TextView) findViewById(R.id.tv_in_activity);
        textView.setText("fragment->activity");
    }
}

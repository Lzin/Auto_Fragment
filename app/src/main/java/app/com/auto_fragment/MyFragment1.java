package app.com.auto_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment1 extends Fragment {

    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //动态更改布局
        View view = inflater.inflate(R.layout.my_fragment1, null);
        //实现从fragment到activity的通信
        view.findViewById(R.id.motify_from_fragment_to_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.modify();//进行主界面上的修改

//                //使用接口进行回调
//                if(onBtnClickListener!=null){
//                    onBtnClickListener.onBtnClick();
//                }
            }
        });

        return view;
    }

    //activity->motify
    //从activity中进行方法的调用，实现在Fragment中ui的改变
    public void modify() {
        TextView textView = (TextView) getView().findViewById(R.id.tv_in_fragment);//获取fragment中的tv
        textView.setText("activity->fragment");
    }
}

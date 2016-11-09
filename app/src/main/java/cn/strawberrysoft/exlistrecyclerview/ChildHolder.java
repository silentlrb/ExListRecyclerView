package cn.strawberrysoft.exlistrecyclerview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * 版权  ：水晶草莓软件工作室版权所有
 * 作者  ：李瑞豹
 * Created by LRB on 2016/11/1.18:42.
 */
public class ChildHolder extends BaseHoleder {
    private TextView textView;
    public ChildHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void initView() {
        textView = (TextView) getItemView().findViewById(R.id.child);

    }

    @Override
    public void refreshView(Object o) {
        textView.setText(((Child)o).getName());
    }
}

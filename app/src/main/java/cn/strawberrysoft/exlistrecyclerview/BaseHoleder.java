package cn.strawberrysoft.exlistrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 版权  ：水晶草莓软件工作室版权所有
 * 作者  ：李瑞豹
 * Created by LRB on 2016/10/28.18:48.
 */
public abstract class BaseHoleder<Data> extends RecyclerView.ViewHolder {
    private Context context;
    private View itemView;
    private Data data;

    private BaseHoleder(View itemView) {
        super(itemView);
    }
    public BaseHoleder(Context context, View itemView){
        super(itemView);
        this.itemView = itemView;
        this.context = context;

        initView();


    }

    public abstract void initView();
    public void setDate(Data data){
        this.data = data;
        refreshView(data);
    }

    public View getItemView() {
        return itemView;
    }

    public abstract void refreshView(Data data);

    public Context getContext() {
        return context;

    }
}

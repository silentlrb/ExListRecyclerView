package cn.strawberrysoft.exlistrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权  ：水晶草莓软件工作室版权所有
 * 作者  ：李瑞豹
 * Created by LRB on 2016/11/1.16:56.
 */
public class ExListRecycleViewAdapter<GROUP, CHILD> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_GROUP = 1;
    private static final int TYPE_CHILD = 2;


    private Context context;
    private ItemClickListener itemClickListener;
    private ExlistRecycleViewStateListener exlistRecycleViewStateListener;

    private List<GROUP> groups;
    private List<Boolean> isCheckeds;

    private List<List<CHILD>> childs;
    private int position = 0;

    public ExListRecycleViewAdapter(Context context, ItemClickListener itemClickListener, ExlistRecycleViewStateListener exlistRecycleViewStateListener, List<GROUP> groups, List<List<CHILD>> childs) {

        this.context = context;
        this.itemClickListener = itemClickListener;
        this.exlistRecycleViewStateListener = exlistRecycleViewStateListener;
        this.groups = groups;

        this.childs = childs;

        isCheckeds = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            isCheckeds.add(false);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_GROUP:
                return new GroupHolder(context, View.inflate(context, R.layout.item_group, null));
            case TYPE_CHILD:
                if (isCheckeds.get(getGropdPosition(position))){
                    return null;
                }else {
                    return new ChildHolder(context, View.inflate(context, R.layout.item_child, null));
                }
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof GroupHolder) {

            ((GroupHolder) holder).setDate(groups.get(getGropdPosition(position)));
            ((GroupHolder) holder).getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.itemClicked(groups.get(getGropdPosition(position)));
                    if (!isCheckeds.get(getGropdPosition(position))) {
                       isCheckeds.set(getGropdPosition(position), true);
                    } else {
                        isCheckeds.set(getGropdPosition(position), false);
                    }
                    exlistRecycleViewStateListener.onSectionStateChanged(getGropdPosition(position),isCheckeds.get(getGropdPosition(position)),isCheckeds);
                }
            });


        } else if (holder instanceof ChildHolder) {


            ((ChildHolder) holder).setDate(childs.get(getGropdPosition(position)).get(getChildPosition(position)));

            ((ChildHolder) holder).getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.itemClicked(childs.get(getGropdPosition(position)).get(getChildPosition(position)));
                }
            });


        }
        this.position = position;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (int i = 0; i < childs.size(); i++) {
            count += childs.get(i).size();
        }
        return count + groups.size();
    }

    @Override
    public int getItemViewType(int position) {//获取条目类型
        int sum = 0;

        for (int i = 0; i < groups.size(); i++) {
            if (sum == position) {
                return TYPE_GROUP;
            }

            for (int j = 0; j < childs.get(i).size(); j++) {
                sum++;
                if (sum == position) {
                    return TYPE_CHILD;
                }
            }
            sum++;
            continue;
        }
        return super.getItemViewType(position);
    }


    public int getGropdPosition(int position) {//获取父条目所在的位置

        int sum = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (sum == position) {
                return i;
            }
            sum++;

            for (int j = 0; j < childs.get(i).size(); j++) {
                if (sum == position) {
                    return i;
                }
                sum++;
            }
        }
        return 0;
    }

    public int getChildPosition(int position) {//获取子条目所在的位置
        int sum = 0;

        for (int i = 0; i < groups.size(); i++) {
            if (sum == position) {

            }

            for (int j = 0; j < childs.get(i).size(); j++) {
                sum++;
                if (sum == position) {
                    return j;
                }
            }
            sum++;
            continue;
        }
        return 0;

    }


}

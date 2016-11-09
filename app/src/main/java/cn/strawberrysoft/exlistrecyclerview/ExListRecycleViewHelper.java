package cn.strawberrysoft.exlistrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 版权  ：水晶草莓软件工作室版权所有
 * 作者  ：李瑞豹
 * Created by LRB on 2016/11/1.16:25.
 */
public class ExListRecycleViewHelper<GROUP, CHILD> implements ExlistRecycleViewStateListener {
    private Context context;
    private RecyclerView recyclerView;
    private ItemClickListener itemClickListener;
    private List<GROUP> groups;
    private List<GROUP> groupsTemp = new ArrayList<>();
    private List<List<CHILD>> childs;
    private List<List<CHILD>> childsTemp = new ArrayList<>();
    private ExListRecycleViewAdapter exListRecycleViewAdapter;

    public ExListRecycleViewHelper(Context context, RecyclerView recyclerView, ItemClickListener itemClickListener, List<GROUP> groups, List<List<CHILD>> childs) {

        this.context = context;
        this.recyclerView = recyclerView;
        this.itemClickListener = itemClickListener;
        this.groups = groups;
        this.groupsTemp.addAll(groups);
        this.childs = childs;
        this.childsTemp.addAll(childs);
        exListRecycleViewAdapter = new ExListRecycleViewAdapter(context, itemClickListener, this, groups, childs);
        recyclerView.setAdapter(exListRecycleViewAdapter);

    }

    public List<List<CHILD>> getChilds() {
        return childs;
    }

    public void setChilds(List<List<CHILD>> childs) {
        this.childs = childs;
    }

    public List<GROUP> getGroups() {
        return groups;
    }

    public void setGroups(List<GROUP> groups) {
        this.groups = groups;
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onSectionStateChanged(int gropdPosition, Boolean isCheck, List<Boolean> isCheckeds) {

        /**
         * 这里纠结了好多次，出现的情况是全部是展开的时候点击最后一项
         * 用notifyItemRemoved（）不出现bug但是notifyDataSetChanged（）出现bug，如果有其他的条目折叠
         * 则没有问题，如果都用用notifyItemRemoved（），怎第二项折叠时，第三项第一项没有折叠，点击第一项
         * 出现第三项的名字改成了第二项，最终利用判断状态的方法解决
         */
        //折叠
        if (isCheck) {
            int sum = 0;
            int num = 0;
            num = childs.get(gropdPosition).size();
            for (int i = 0; i < gropdPosition; i++) {
                sum += childs.get(i).size() + 1;
            }
            List<CHILD> childList = new ArrayList<>();
            childs.set(gropdPosition, childList);

            for (int i = 0; i < groups.size(); i++) {
                if (i != gropdPosition && isCheckeds.get(i)){
                    exListRecycleViewAdapter.notifyDataSetChanged();
                    return;
                }
            }
            for (int i = 0; i < num; i++) {
                exListRecycleViewAdapter.notifyItemRemoved(sum + i);
            }
        } else {//展开
            childs.set(gropdPosition, childsTemp.get(gropdPosition));
            exListRecycleViewAdapter.notifyDataSetChanged();

        }

    }
}

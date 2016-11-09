package cn.strawberrysoft.exlistrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        List<Group> groups = new ArrayList<>();
        List<List<Child>> childs = new ArrayList<>();
        //添加组
        Group group1 = new Group(1,"group1");
        Group group2 = new Group(2,"group2");
        Group group3 = new Group(2,"group3");
        Group group4 = new Group(3,"group4");
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);

        //添加子条目
        List<Child> childTemp = new ArrayList<>();
        Child child1 = new Child(1,"g1-child1");
        Child child2 = new Child(2,"g1-child2");
        Child child3 = new Child(3,"g1-child3");
        Child child4 = new Child(4,"g1-child4");
        childTemp.add(child1);
        childTemp.add(child2);
        childTemp.add(child3);
        childTemp.add(child4);
        childs.add(childTemp);

        childTemp = new ArrayList<>();
        child1 = new Child(1,"g2-child1");
        child2 = new Child(2,"g2-child2");
        child3 = new Child(3,"g2-child3");
        child4 = new Child(4,"g2-child4");
        childTemp.add(child1);
        childTemp.add(child2);
        childTemp.add(child3);
        childTemp.add(child4);
        childs.add(childTemp);

        childTemp = new ArrayList<>();
        child1 = new Child(1,"g3-child1");
        child2 = new Child(2,"g3-child2");
        child3 = new Child(3,"g3-child3");
        child4 = new Child(4,"g3-child4");
        childTemp.add(child1);
        childTemp.add(child2);
        childTemp.add(child3);
        childTemp.add(child4);
        childs.add(childTemp);

        childTemp = new ArrayList<>();
        child1 = new Child(1,"g4-child1");
        child2 = new Child(2,"g4-child2");
        child3 = new Child(3,"g4-child3");

        childTemp.add(child1);
        childTemp.add(child2);
        childTemp.add(child3);

        childs.add(childTemp);


        ExListRecycleViewHelper exListRecycleViewHelper = new ExListRecycleViewHelper<Group,Child>(this,recyclerView,this,groups,childs);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_home);
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void itemClicked(Object o) {
//        if (o instanceof Group){
//            Toast.makeText(this,((Group)o).getName(),Toast.LENGTH_LONG).show();
//        }else if (o instanceof Child){
//            Toast.makeText(this,((Child)o).getName(),Toast.LENGTH_LONG).show();
//        }

    }
}

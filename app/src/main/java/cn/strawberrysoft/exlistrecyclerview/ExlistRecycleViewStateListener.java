package cn.strawberrysoft.exlistrecyclerview;

import java.util.List;

/**
 * 版权  ：水晶草莓软件工作室版权所有
 * 作者  ：李瑞豹
 * Created by LRB on 2016/11/1.16:26.
 */
public interface ExlistRecycleViewStateListener {
    void onSectionStateChanged(int gropdPosition, Boolean aBoolean, List<Boolean> isCheckeds);
}

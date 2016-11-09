package cn.strawberrysoft.exlistrecyclerview;

/**
 * 版权  ：水晶草莓软件工作室版权所有
 * 作者  ：李瑞豹
 * Created by LRB on 2016/11/1.16:34.
 */
public interface ItemClickListener<GROUP> {
    /**
     * 点击事件
     * @param group
     */
    public void itemClicked(GROUP group);
}

package cn.edu.heuet.quickshop.page;

import android.content.Context;

import cn.edu.heuet.quickshop.page.impl.SortPageImpl;


/**
 * Create by SunnyDay on 2019/03/16
 */
public class SortPageFactory implements Provider {

    Context context;

    public SortPageFactory(Context context) {
        this.context = context;
    }

    @Override
    public BasePage produce() {
        return new SortPageImpl(context);
    }
}

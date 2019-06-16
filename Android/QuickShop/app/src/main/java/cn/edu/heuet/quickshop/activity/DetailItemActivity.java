package cn.edu.heuet.quickshop.activity;


import android.view.Gravity;
import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.joanzapata.iconify.widget.IconTextView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.edu.heuet.quickshop.R;
import cn.edu.heuet.quickshop.activity.customview.StatusBarUtils;
import cn.edu.heuet.quickshop.activity.customview.ToastUtils;
import cn.edu.heuet.quickshop.events.GoodInfoMsg;
import cn.edu.heuet.quickshop.loader.ModelImageLoader;

/**
 * 商品的具体内容
 * <p>
 * 用户在主页或者其他页面看商品则跳转此页面
 */

public class DetailItemActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_select_addr)
    RelativeLayout rlSelectAddr;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;

    @BindView(R.id.select_size_color)
    TextView selectSizeColor;
    @BindView(R.id.post_to)
    TextView PostTo;
    @BindView(R.id.goods_desc)
    TextView goodsDescrible;

    @BindView(R.id.pay_attation)
    LottieAnimationView payAttation;

    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.img_3)
    ImageView img3;

    @BindView(R.id.img_banner)
    Banner banner;
    @BindView(R.id.tv_share)
    IconTextView tvShare;
    @BindView(R.id.tv_back)
    IconTextView tvBack;
    @BindView(R.id.custom_service)
    IconTextView customService;
    @BindView(R.id.look_shop)
    IconTextView lookShop;
    @BindView(R.id.add_cart)
    TextView addCart;
    @BindView(R.id.buy_rightnow)
    TextView buyRightNow;
    List<Integer> mBigImgList;


    @Override
    public Object offerLayout() {
        return R.layout.activity_detail_item;
    }

    @Override
    public void onBindView() {
//        hideActionBar();
        StatusBarUtils.setWindowStatusBarColor(this, R.color.red);
        initImageData();


        tvBack.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        customService.setOnClickListener(this);
        lookShop.setOnClickListener(this);
        addCart.setOnClickListener(this);
        buyRightNow.setOnClickListener(this);
        payAttation.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        rlSelect.setOnClickListener(this);
        rlSelectAddr.setOnClickListener(this);
    }

    /**
     * Banner 图片 与 banner 下面的小图
     */
    private void initImageData() {
        mBigImgList = new ArrayList<>();
        mBigImgList.add(R.drawable.big1);
        mBigImgList.add(R.drawable.big2);
        mBigImgList.add(R.drawable.big3);

        banner.setImageLoader(new ModelImageLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR)//指示器样式
                .setIndicatorGravity(BannerConfig.LEFT)// 指示器位置
                .setBannerAnimation(Transformer.DepthPage)//动画效果
                .setImages(mBigImgList)
                .isAutoPlay(false)
                .start();

        img1.setImageResource(R.drawable.small1);
        img2.setImageResource(R.drawable.small2);
        img3.setImageResource(R.drawable.small3);

    }


    @Override
    public void destory() {
    }

    /**
     * 点击事件的同一处理
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                share();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.custom_service:
                ToastUtils.showToast(this, "程序员小哥正在请客服过来！", ToastUtils.LENGTH_LONG);
                break;
            case R.id.look_shop:
                ToastUtils.showToast(this, "程序员小哥正在努力赶来！", ToastUtils.LENGTH_LONG);
                break;
            case R.id.add_cart:
                ToastUtils.showToast(this, "商品已经添加到购物车！", ToastUtils.LENGTH_LONG);
                // 发送消息   添加了1个商品
                EventBus.getDefault().post(new GoodInfoMsg(1));
                finish();
                break;
            case R.id.buy_rightnow:
                ToastUtils.showToast(this, "model支付平台还未开张！", ToastUtils.LENGTH_LONG);
                break;
            case R.id.pay_attation:
                ToastUtils.showToast(this, "亲，您关注成功。", ToastUtils.LENGTH_LONG);
                payAttation.playAnimation();
                break;
            case R.id.img_1:
                ToastUtils.showToast(this, "待续", ToastUtils.LENGTH_LONG);
                break;
            case R.id.img_2:
                ToastUtils.showToast(this, "待续", ToastUtils.LENGTH_LONG);
                break;
            case R.id.img_3:
                ToastUtils.showToast(this, "待续", ToastUtils.LENGTH_LONG);
                break;
            case R.id.rl_select:
                showSelectDialog();
                break;
            case R.id.rl_select_addr:
                showAddrDialog();
                break;
        }
    }

    private void share() {
    }

    /**
     * 收货地址对话框
     * */
    private void showAddrDialog() {
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.select_addr_dialog))
                .setCancelable(true)
                .setGravity(Gravity.BOTTOM)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        switch (view.getId()) {
                            case R.id.other_addr:
                                ToastUtils.showToast(DetailItemActivity.this, "待续，程序员小哥生病了", ToastUtils.LENGTH_LONG);
                                break;
                        }

                    }

                })
                .create();
        dialog.show();
    }

    /**
     * 简单的模拟 数据写死 不是真正的来源网络
     * */
    private void showSelectDialog() {
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.select_clothes_dialog))
                .setCancelable(true)
                .setGravity(Gravity.BOTTOM)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        switch (view.getId()) {
                            case R.id.text_color1:
                                ToastUtils.showToast(DetailItemActivity.this, "测试1", ToastUtils.LENGTH_LONG);
                                break;
                            case R.id.text_color2:
                                ToastUtils.showToast(DetailItemActivity.this, "测试2", ToastUtils.LENGTH_LONG);
                                break;
                            case R.id.text_color3:
                                ToastUtils.showToast(DetailItemActivity.this, "测试3", ToastUtils.LENGTH_LONG);
                                break;
                            case R.id.question:
                                ToastUtils.showToast(DetailItemActivity.this, "待续", ToastUtils.LENGTH_LONG);
                                break;
                            case R.id.ll_easy_buy:
                                ToastUtils.showToast(DetailItemActivity.this, "model支付平台还没开张！", ToastUtils.LENGTH_LONG);
                                break;
                            case R.id.tv_add_cart:
                                ToastUtils.showToast(DetailItemActivity.this, "添加成功", ToastUtils.LENGTH_LONG);
                                dialog.dismiss();
                                break;
                        }

                    }

                })
                .create();
        dialog.show();
    }

}

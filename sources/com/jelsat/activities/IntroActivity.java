package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.viewpagerindicator.InkPageIndicator;
import com.jelsat.fragments.IntroFragment;

public class IntroActivity extends BaseAppCompactActivity implements OnPageChangeListener {
    @BindView(2131362005)
    InkPageIndicator circlePageIndicator;
    @BindView(2131362159)
    LinearLayout fancyButtonsLayout;
    @BindView(2131362276)
    LinearLayout lastlayout;
    @BindView(2131362828)
    ViewPager viewPager;

    public int getActivityLayout() {
        return R.layout.activity_intro;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    @OnClick({2131361942})
    public void skipButton() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @OnClick({2131362202})
    public void SignIn() {
        PrefUtils.getInstance().setClickSkip(true);
        goToActivity(DashBoardActivity.class);
        finish();
    }

    @OnClick({2131362267})
    public void joinNow() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(StringConstants.IS_JOIN_NOW, true);
        goToActivity(HomeActivity.class, bundle);
        finish();
    }

    @OnClick({2131361936})
    public void btnNext() {
        goToSlide(this.viewPager.getCurrentItem() + 1);
    }

    private void goToSlide(int i) {
        this.viewPager.setCurrentItem(i);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setupViewPager(this.viewPager);
    }

    protected void onStart() {
        super.onStart();
        this.viewPager.addOnPageChangeListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(IntroFragment.newInstance(R.drawable.ic_slide1_img, getString(R.string.intro_title_1), getString(R.string.intro_slide_1_description)), "");
        viewPagerAdapter.addFragment(IntroFragment.newInstance(R.drawable.ic_slide2_img, getString(R.string.intro_title_2), getString(R.string.intro_slide_2_description)), "");
        viewPagerAdapter.addFragment(IntroFragment.newInstance(R.drawable.ic_slide_03_updated, getString(R.string.intro_title_3), getString(R.string.intro_slide_3_description)), "");
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        this.circlePageIndicator.setViewPager(viewPager);
    }

    public void onPageSelected(int i) {
        if (i == 2) {
            this.fancyButtonsLayout.setVisibility(4);
            this.lastlayout.setVisibility(0);
            return;
        }
        this.fancyButtonsLayout.setVisibility(0);
        this.lastlayout.setVisibility(4);
    }

    protected void onStop() {
        this.viewPager.removeOnPageChangeListener(this);
        super.onStop();
    }

    public void onBackPressed() {
        if (this.viewPager.getCurrentItem() > 0) {
            goToSlide(this.viewPager.getCurrentItem() - 1);
        } else {
            super.onBackPressed();
        }
    }
}

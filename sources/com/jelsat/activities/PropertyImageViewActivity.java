package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.jelsat.R;
import com.jelsat.adapters.PropertyDetailImageAdapter;
import com.jelsat.adapters.PropertyDetailImageAdapter$ImageClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import java.util.List;

public class PropertyImageViewActivity extends BaseAppCompactActivity implements PropertyDetailImageAdapter$ImageClickListener {
    private List<String> image;
    private PropertyDetailImageAdapter imageAdapter;
    private List<String> imageTitle;
    @BindView(2131362368)
    TextView noOfPropertyImagesCount;
    private int poperty_image_position;
    @BindView(2131362239)
    TextView propertyImageName;
    @BindView(2131362469)
    RecyclerView propertyImageRecyclerView;

    public void clickOnImage(List<String> list, List<String> list2, int i) {
    }

    public int getActivityLayout() {
        return R.layout.activity_prooerty_imageview_layout;
    }

    @OnClick({2131362017})
    public void clickOnClose() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            this.image = getIntent().getStringArrayListExtra(StringConstants.PROPERTY_IMAGE);
            this.imageTitle = getIntent().getStringArrayListExtra(StringConstants.PREOPERTY_IMAGE_TITLE);
            this.poperty_image_position = getIntent().getIntExtra(StringConstants.PROPERTY_IMAGE_POSITION, 0);
        }
        this.noOfPropertyImagesCount.setText(String.format("%s/%s", new Object[]{Integer.valueOf(this.poperty_image_position + 1), Integer.valueOf(this.image.size())}));
        this.propertyImageName.setText(String.format("%s", new Object[]{this.imageTitle.get(this.poperty_image_position)}));
        initPropertyImagesRecyclerView();
    }

    private void initPropertyImagesRecyclerView() {
        final LayoutManager anonymousClass1 = new LinearLayoutManager(this, 0, false) {
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.propertyImageRecyclerView.setLayoutManager(anonymousClass1);
        this.propertyImageRecyclerView.setNestedScrollingEnabled(false);
        this.imageAdapter = new PropertyDetailImageAdapter(this, this.image, this.imageTitle, this);
        this.propertyImageRecyclerView.setAdapter(this.imageAdapter);
        this.propertyImageRecyclerView.scrollToPosition(this.poperty_image_position);
        new PagerSnapHelper().attachToRecyclerView(this.propertyImageRecyclerView);
        this.propertyImageRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i != 1 && i == 0) {
                    Log.e("123", String.valueOf(anonymousClass1.findFirstVisibleItemPosition()));
                    if (PropertyImageViewActivity.this.noOfPropertyImagesCount != null && PropertyImageViewActivity.this.propertyImageName != null) {
                        PropertyImageViewActivity.this.noOfPropertyImagesCount.setText(String.format("%s/%s", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(PropertyImageViewActivity.this.image.size())}));
                        PropertyImageViewActivity.this.propertyImageName.setText(String.format("%s", new Object[]{PropertyImageViewActivity.this.imageTitle.get(i)}));
                    }
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
    }
}

package com.maalgaadi.admin.cordinatorlayoutexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by admin on 7/4/2017.
 */

public class AvatarImageBehaviorActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_OF_SHOW_TITLE_AT_TOOLBAR = 0.9F;
    private static final float PERCENTAGE_OF_HIDE_TITLE_AT_TOOLBAR = 0.3F;
    private static final int ALPHA_ANIMATION_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_image_behavior);
        bindActivity();

        mAppBarLayout.addOnOffsetChangedListener(this);
        mToolbar.inflateMenu(R.menu.menu_main);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

    }

    private void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE) ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;


    }

    private void bindActivity() {

        mTitleContainer = (LinearLayout) findViewById(R.id.ll_title);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_OF_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATION_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            } else {
                if (mIsTheTitleVisible) {

                    startAlphaAnimation(mTitle, ALPHA_ANIMATION_DURATION, View.INVISIBLE);
                    mIsTheTitleVisible = false;
                }

            }


        }


    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_OF_HIDE_TITLE_AT_TOOLBAR) {
            if(mIsTheTitleContainerVisible){
                startAlphaAnimation(mTitle, ALPHA_ANIMATION_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;

            }else{
                if(!mIsTheTitleContainerVisible){

                    startAlphaAnimation(mTitle, ALPHA_ANIMATION_DURATION, View.VISIBLE);
                    mIsTheTitleVisible = true;
                }
            }


        }

    }
}

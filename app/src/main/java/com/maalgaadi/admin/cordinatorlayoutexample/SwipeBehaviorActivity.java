package com.maalgaadi.admin.cordinatorlayoutexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by admin on 7/4/2017.
 */

public class SwipeBehaviorActivity extends AppCompatActivity {
    private FloatingActionButton button;
    private CardView mCardView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_behavior);

        button=(FloatingActionButton)findViewById(R.id.fb);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SwipeBehaviorActivity.this,AvatarImageBehaviorActivity.class);
                startActivity(intent);
            }
        });
        mCardView=(CardView)findViewById(R.id.cardView);
        final SwipeDismissBehavior<CardView> swipe= new SwipeDismissBehavior<>();
        swipe.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                Toast.makeText(SwipeBehaviorActivity.this,
                        "Card swiped !!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDragStateChanged(int state) {

            }
        });
        CoordinatorLayout.LayoutParams coordinatorParams =
                (CoordinatorLayout.LayoutParams) mCardView.getLayoutParams();

        coordinatorParams.setBehavior(swipe);
    }


}

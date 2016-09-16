package com.padc.goldenmyanmartour.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by WT on 9/5/2016.
 */
public class DynamicImageView extends ImageView {

    private float mAspectRatio=1.4f;
    public DynamicImageView (Context context){super(context);}
    public DynamicImageView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }
    public void setmAspectRatio(float aspectRatio){
        mAspectRatio=aspectRatio;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth=getMeasuredWidth();
        setMeasuredDimension(measureWidth,(int)(measureWidth/mAspectRatio));;
    }
}

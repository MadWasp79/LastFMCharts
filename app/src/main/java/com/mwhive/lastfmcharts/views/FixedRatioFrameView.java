package com.mwhive.lastfmcharts.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.mwhive.lastfmcharts.R;

/**
 * Created by MadWasp79 on 05-Jun-18.
 */

public class FixedRatioFrameView extends FrameLayout {

  private int aspectRatioWidth;
  private int aspectRatioHeight;

  public FixedRatioFrameView(@NonNull Context context) {
    super(context);
  }

  public FixedRatioFrameView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public FixedRatioFrameView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  public FixedRatioFrameView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }


  private void init(Context context, AttributeSet attributeSet) {
    TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.FixedRatioFrameView);

    aspectRatioWidth = ta.getInt(R.styleable.FixedRatioFrameView_aspect_ratio_width, 1);
    aspectRatioHeight = ta.getInt(R.styleable.FixedRatioFrameView_aspect_ratio_height, 1);

    ta.recycle();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int originalWidth = MeasureSpec.getSize(widthMeasureSpec);
    int originalHeight = MeasureSpec.getSize(heightMeasureSpec);
    int calculateHeight = originalWidth * aspectRatioHeight / aspectRatioWidth;
    int finalWidth, finalHeight;

    if(calculateHeight>originalHeight) {
      finalWidth = originalHeight * aspectRatioWidth / aspectRatioHeight;
      finalHeight = originalHeight;
    } else {
      finalWidth = originalWidth;
      finalHeight = calculateHeight;
    }
    super.onMeasure(
        MeasureSpec.makeMeasureSpec(finalWidth, MeasureSpec.EXACTLY),
        MeasureSpec.makeMeasureSpec(finalHeight,MeasureSpec.EXACTLY));
  }
}

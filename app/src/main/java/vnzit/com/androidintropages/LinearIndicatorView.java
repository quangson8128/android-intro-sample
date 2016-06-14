package vnzit.com.androidintropages;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by sh on 6/14/16.
 */
public class LinearIndicatorView extends LinearLayout {
    public LinearIndicatorView(Context context) {
        super(context);
        init();
    }

    public LinearIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LinearIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setNumOfIndicator(3, 1);
    }

    public void setNumOfIndicator(int numOfIndicator, int selectedIndex) {
        removeAllViews();
        for (int i = 0; i < numOfIndicator; ++i) {
            ImageView img = new ImageView(getContext());
            final LinearLayout.LayoutParams param = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.setMargins(10, 0, 10, 0);
            img.setLayoutParams(param);
            if (selectedIndex == i) {
                img.setImageResource(R.drawable.circle_selected);
            } else {
                img.setImageResource(R.drawable.circle_normal);
            }

            addView(img);
        }
    }
    public void setNumOfIndicator(int numOfIndicator) {
        setNumOfIndicator(numOfIndicator, -1);
    }

    public void setSelectedIndicator(int selectedIndex) {
        final int n = getChildCount();
        if (selectedIndex < 0 || selectedIndex >= n) return;
        for (int i = 0; i < n; ++i) {
            final View v = getChildAt(i);
            final ImageView img;
            if (v instanceof ImageView) {
                img = (ImageView) v;
            } else {
                throw new IllegalStateException("Do not add any view to this view");
            }
            if (selectedIndex == i) {
                img.setImageResource(R.drawable.circle_selected);
            } else {
                img.setImageResource(R.drawable.circle_normal);
            }
        }
    }

}

package vnzit.com.androidintropages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class IntroActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public interface Callback {
        void onDone();
    }

    private final Callback callback = new Callback() {
        @Override
        public void onDone() {
            final Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            ActivityCompat.finishAffinity(IntroActivity.this);
        }
    };

    ViewPager vpContent;
    ImageButton btnPrev;
    ImageButton btnNext;
    LinearIndicatorView indicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        vpContent = (ViewPager) findViewById(R.id.vpContent);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        indicators = (LinearIndicatorView) findViewById(R.id.indicators);


        final PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpContent.setAdapter(adapter);
        indicators.setNumOfIndicator(adapter.getCount(), 0);

        vpContent.addOnPageChangeListener(this);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int index = vpContent.getCurrentItem();
                if (index <= 0) return;
                vpContent.setCurrentItem(index - 1, true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int index = vpContent.getCurrentItem();
                if (index < vpContent.getAdapter().getCount() - 1) {
                    vpContent.setCurrentItem(index + 1, true);
                } else {
                    callback.onDone();
                }

            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        btnNext.setVisibility(View.VISIBLE);
        btnPrev.setVisibility(View.VISIBLE);
        btnNext.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
        indicators.setSelectedIndicator(position);
        if (position == 0) {
            btnPrev.setVisibility(View.INVISIBLE);
        } else if (position == vpContent.getAdapter().getCount() - 1) {
            btnNext.setImageResource(R.drawable.ic_done_white_24dp);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final String[] INTROS = {
                "Intro for page 1",
                "Intro for page 2",
                "Intro for page 3",
        };
        private final int[] IMGS = {
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3,
        };

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return IntroFragment.newInstance(IMGS[position], INTROS[position]);
        }

        @Override
        public int getCount() {
            return INTROS.length;
        }
    }
}

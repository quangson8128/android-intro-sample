package vnzit.com.androidintropages;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sh on 6/14/16.
 */
public class IntroFragment extends Fragment {
    private static final String EXTRA_IMAGE = "extra_image";
    private static final String EXTRA_INTRO = "extra_intro";

    public static IntroFragment newInstance(@DrawableRes int imageRes, String intro) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_IMAGE, imageRes);
        args.putString(EXTRA_INTRO, intro);
        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.frg_intro, container, false);
        final ImageView iv = (ImageView) root.findViewById(R.id.ivBackground);
        final TextView tvIntro = (TextView) root.findViewById(R.id.tvIntro);

        iv.setImageResource(getArguments().getInt(EXTRA_IMAGE, R.drawable.img1));
        tvIntro.setText(getArguments().getString(EXTRA_INTRO));
        return root;
    }
}

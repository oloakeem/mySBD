package come.example.mysbd;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class WeekOneFragment extends Fragment {
    List<Model> models;
    ViewPager viewPager;
    DayAdapter adapter;
    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.day_pager, container, false);
        TextView textView = view.findViewById(R.id.thisWeek);
        textView.setText("Week 1");
        models = new ArrayList<>();
        models.add(new Model("Day One","Day 1",R.drawable.yellow_background));
        models.add(new Model("Day Two","Day 2", R.drawable.purple_background));
        models.add(new Model("Day Three","Day 3",R.drawable.red_background));
        models.add(new Model("Day Four", "Day 4",R.drawable.blue_background));
        adapter = new DayAdapter(models,getContext(), new WeekOneDays(),1);
        tabLayout = view.findViewById(R.id.DayTracker);

        viewPager = view.findViewById(R.id.myNewViewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100, 0, 100, 0);
        tabLayout.setupWithViewPager(viewPager);


        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setAlpha(normalizedposition);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() - 1) && position < (3 - 1)) {


                } else {
                    if(position<=2){
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        return view;
    }


}






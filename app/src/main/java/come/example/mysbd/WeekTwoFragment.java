package come.example.mysbd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WeekTwoFragment extends Fragment {
    List<Model> models;
    ViewPager viewPager;
    DayAdapter adapter;
    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.day_pager, container, false);
        TextView textView = view.findViewById(R.id.thisWeek);
        textView.setText("Week 2");
        models = new ArrayList<>();
        models.add(new Model("Day Five","Day 5",R.drawable.yellow_background));
        models.add(new Model("Day Six","Day 6", R.drawable.purple_background));
        models.add(new Model("Day Seven","Day 7",R.drawable.red_background));
        models.add(new Model("Day Eight", "Day 8",R.drawable.blue_background));
        adapter = new DayAdapter(models,getContext(), new WeekTwoDays(),2);
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
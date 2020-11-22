package come.example.mysbd;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class DayAdapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    Bundle whatDay = new Bundle();
    Fragment myFragment;
    int myDay;
    UsersNumbers usersNumbers = new UsersNumbers();
    Attendance attendance = new Attendance();

    public DayAdapter(List<Model> models, Context context,Fragment fragment,int i) {
        this.models = models;
        this.myFragment = fragment;
        this.myDay = i;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return models.get(position).getDesc();
       // return "Day " + (position + 1);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.day_item, container, false);


        ImageView imageView;
        TextView title;
        ProgressBar progressBar;

        imageView = view.findViewById(R.id.Day_Image);
        title = view.findViewById(R.id.Day_Text);
        progressBar = view.findViewById(R.id.Day_Image2);
        ProgressBarAnimation anim = new ProgressBarAnimation(progressBar, 0, 100);
        anim.setDuration(1000);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());

        if (position > 1) {
            imageView.setVisibility(View.VISIBLE);
        }
            if (attendance.peekDay(myDay, position)) {
                progressBar.startAnimation(anim);
        }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragmentselected =  myFragment;
                    switch(position){
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            whatDay.putString("bundleKey", models.get(position).getDesc());
                            fragmentselected.setArguments(whatDay);
                            break;
                    }
                        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fragmentselected);
                        transaction.addToBackStack(null);
                        transaction.commit();

                }
                });

            container.addView(view, 0);

            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }

}
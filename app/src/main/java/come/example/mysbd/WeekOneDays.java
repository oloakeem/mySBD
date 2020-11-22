package come.example.mysbd;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WeekOneDays extends Fragment {
    WorkoutAdapter workoutAdapter;
    String myWeek;
    Attendance attendance = new Attendance();
    ToggleButton toggleButton;
    AccesoryAdapter accesoryAdapter;
    UsersNumbers usersNumbers = new UsersNumbers();
    ListView simpleList;
    ListView listView2;
    TextView Day, Week;
    int[] push = {R.drawable.isolation_shoulders,R.drawable.isolation_reardelts,R.drawable.isolation_triceps};
    int[] pull = {R.drawable.isolation_back,R.drawable.isolation_traps,R.drawable.isolation_bicep};
    int[] legs = {R.drawable.isolation_quads,R.drawable.isolation_hamstrings,R.drawable.isolation_calves};

    int myDay = 0;
    String[] sample = new  String[3];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workout_program, container, false);
        sample = usersNumbers.loadValue(getContext());

        simpleList = view.findViewById(R.id.workout_Items);
        toggleButton = view.findViewById(R.id.isComplete);
        listView2 = view.findViewById(R.id.accesory_Layout);
        Day = view.findViewById(R.id.volume_DAY);

        Week = view.findViewById(R.id.volume_WEEK);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myWeek = bundle.getString("bundleKey");
        }

        selectWeek(myWeek);
        buttonPosition();
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    attendance.checkDay(1,myDay);
                    Toast.makeText(getContext(), sample[1], Toast.LENGTH_SHORT).show();
                }else{
                    attendance.uncheckDay(1,myDay);

                }
                attendance.save(getContext());
            }
        });
        return view;
    }

    public void buttonPosition(){
        if(attendance.peekDay(1,myDay)) toggleButton.setChecked(true);

        else toggleButton.setChecked(false);
    }


    public void selectWeek(String selectedWeek){

        switch (selectedWeek){
            case "Day 1":
                myDay = 0;
                OneOfFour();
                break;
            case "Day 2":
                myDay = 1;
                TwoOfFour();
                break;
            case "Day 3":
                myDay =2;
                ThreeOfFour();
                break;
            case "Day 4":
                myDay = 3;
                FourOfFour();

                break;
            default:
                break;
        }
        Day.setText(selectedWeek);
        Week.setText("Week 1");
    }

    public void  OneOfFour() {
        String[] testing =
                /*Main exercises */{"Dips", "Cable Rope Tricep Pushdown", "Diamond Push-Ups"};
        String[] pushMain =
                /*Main exercises */{"Bench", "Overhead press", "Incline bench"};
        String pushAcc1[] =
                /*accessory/sub1 exercises */{"Sets-4 Reps-8", "Sets-3 Reps-8", "Sets-3 Reps-9"};
        String pushAcc2[] =
                /*accessory/sub2 exercises */ {"RPE @ 9", "RPE @ 7", "RPE @ 6"};
        accesoryAdapter = new AccesoryAdapter(getContext(),push,testing,pushAcc1);
        workoutAdapter = new WorkoutAdapter(getContext(), pushMain, pushAcc1);
        listView2.setAdapter(accesoryAdapter);
        simpleList.setAdapter(workoutAdapter);

    }

    public void TwoOfFour() {
        String[] testing =
                /*Main exercises */{"PullUps or Assisted-PullUps", "Lat Pulldown", "Seated Row"};
        String[] pushMain =
                /*Main exercises */{"PullUps or Assisted-PullUps", "Lat Pulldown", "Seated Row"};
        String pushAcc1[] =
                /*accessory/sub1 exercises */{"Sets-3 Reps-8", "Sets-3 Reps-9", "Sets-4 Reps-9"};
        String pushAcc2[] =
                /*accessory/sub2 exercises */ {"RPE @ 8", "RPE @ 7", "RPE @ 6"};
        int week = 1;
        int day = 2;
        accesoryAdapter = new AccesoryAdapter(getContext(),pull,testing,pushAcc1);
        workoutAdapter = new WorkoutAdapter(getContext(), pushMain, pushAcc1);
        listView2.setAdapter(accesoryAdapter);
        simpleList.setAdapter(workoutAdapter);


    }

    public void ThreeOfFour() {
        String[] testing =
                /*Main exercises */{"Dips", "Cable Rope Tricep Pushdown", "Diamond Push-Ups"};
        String[] pushMain =
                /*Main exercises */{"Squat", "Conventional Deadlift", "Goblet Squat"};
        String pushAcc1[] =
                /*accessory/sub1 exercises */{"Sets-3 Reps-8", "Sets-3 Reps-8", "Sets-2 Reps-10"};
        String pushAcc2[] =
                /*accessory/sub2 exercises */ {"RPE @ 8", "RPE @ 7", "RPE @ 6"};
        int week = 1;
        int day = 3;

        accesoryAdapter = new AccesoryAdapter(getContext(),legs,testing,pushAcc1);
        workoutAdapter = new WorkoutAdapter(getContext(), pushMain, pushAcc1);
        listView2.setAdapter(accesoryAdapter);
        simpleList.setAdapter(workoutAdapter);

    }

    public void FourOfFour() {
        String[] pushMain =
                /*Main exercises */{"Bench", "DB Shoulder Press", "Chest-Fly"};
        String pushAcc1[] =
                /*accessory/sub1 exercises */{"Sets-3 Reps-8", "Sets-3 Reps-9", "Sets-4 Reps-9"};
        String pushAcc2[] =
                /*accessory/sub2 exercises */ {"RPE @ 8", "RPE @ 7", "RPE @ 6"};
        int week = 1;
        int day = 4;
        String[] testing =
                /*Main exercises */{"Dips", "Cable Rope Tricep Pushdown", "Diamond Push-Ups"};
        accesoryAdapter = new AccesoryAdapter(getContext(),push,testing,pushAcc1);
        workoutAdapter = new WorkoutAdapter(getContext(), pushMain, pushAcc1);
        listView2.setAdapter(accesoryAdapter);
        simpleList.setAdapter(workoutAdapter);

    }
}
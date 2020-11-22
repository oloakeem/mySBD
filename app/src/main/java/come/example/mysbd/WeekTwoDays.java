package come.example.mysbd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

public class WeekTwoDays extends Fragment {
    WorkoutAdapter workoutAdapter;
    String myWeek;
    Attendance attendance = new Attendance();
    ToggleButton toggleButton;
    AccesoryAdapter accesoryAdapter;
    ListView simpleList;
    UsersNumbers usersNumbers = new UsersNumbers();
    ListView listView2;
    int[] push = {R.drawable.isolation_shoulders,R.drawable.isolation_reardelts,R.drawable.isolation_triceps};
    int[] pull = {R.drawable.isolation_back,R.drawable.isolation_traps,R.drawable.isolation_bicep};
    int[] legs = {R.drawable.isolation_quads,R.drawable.isolation_hamstrings,R.drawable.isolation_calves};
    TextView Day, Week;
    int myDay = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workout_program, container, false);
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
                    attendance.checkDay(2,myDay);
                }else{
                    attendance.uncheckDay(2,myDay);

                }
                attendance.save(getContext());
            }
        });
        return view;
    }

    public void buttonPosition(){
        if(attendance.peekDay(2,myDay)) toggleButton.setChecked(true);
        else toggleButton.setChecked(false);
    }


    public void selectWeek(String selectedWeek){

        switch (selectedWeek){
            case "Day 5":
                myDay = 0;
                OneOfFour();
                break;
            case "Day 6":
                myDay = 1;
                TwoOfFour();
                break;
            case "Day 7":
                myDay =2;
                ThreeOfFour();
                break;
            case "Day 8":
                myDay = 3;
                FourOfFour();

                break;
            default:
                break;
        }
        Day.setText(selectedWeek);
        Week.setText("Week 2");

    }

    public void  OneOfFour() {

        String[] pushMain =
                /*Main exercises */{"PullUps or Assisted PullUps", "Barbel Row", "Inverted Row"};
        String pushAcc1[] =
                /*accessory/sub1 exercises */{"Sets-3 Reps-9", "Sets-3 Reps-8", "Sets-3 Reps-8"};
        String pushAcc2[] =
                /*accessory/sub2 exercises */ {"RPE @ 9", "RPE @ 7", "RPE @ 6"};

        String[] testing =
                /*Main exercises */{"Dips", "Cable Rope Tricep Pushdown", "Diamond Push-Ups"};
        accesoryAdapter = new AccesoryAdapter(getContext(),pull,testing,pushAcc1);
        workoutAdapter = new WorkoutAdapter(getContext(), pushMain, pushAcc1);
        listView2.setAdapter(accesoryAdapter);
        simpleList.setAdapter(workoutAdapter);
    }

    public void TwoOfFour() {
        String[] pushMain =
                /*Main exercises */{"Squat", "Romanian Deadlift", "Lunges"};
        String pushAcc1[] =
                /*accessory/sub1 exercises */{"Sets-3 Reps-9", "Sets-4 Reps-8", "Sets-3 Reps-8"};
        String pushAcc2[] =
                /*accessory/sub2 exercises */ {"RPE @ 8", "RPE @ 7", "RPE @ 6"};
        int week = 1;
        int day = 2;

        String[] testing =
                /*Main exercises */{"Dips", "Cable Rope Tricep Pushdown", "Diamond Push-Ups"};
        accesoryAdapter = new AccesoryAdapter(getContext(),legs,testing,pushAcc1);
        workoutAdapter = new WorkoutAdapter(getContext(), pushMain, pushAcc1);
        listView2.setAdapter(accesoryAdapter);
        simpleList.setAdapter(workoutAdapter);

    }

    public void ThreeOfFour() {

        String[] pushMain =
                /*Main exercises */{"Bench", "Barbell Overhead Press", "Incline Bench"};
        String pushAcc1[] =
                /*accessory/sub1 exercises */{"Sets-3 Reps-9", "Sets-3 Reps-9", "Sets-3 Reps-8"};
        String pushAcc2[] =
                /*accessory/sub2 exercises */ {"RPE @ 8", "RPE @ 7", "RPE @ 6"};
        int week = 1;
        int day = 3;
        String[] testing =
                /*Main exercises */{"Dips", "Cable Rope Tricep Pushdown", "Diamond Push-Ups"};
        accesoryAdapter = new AccesoryAdapter(getContext(),push,pushAcc1,pushAcc1);
        workoutAdapter = new WorkoutAdapter(getContext(), pushMain, pushAcc1);
        listView2.setAdapter(accesoryAdapter);
        simpleList.setAdapter(workoutAdapter);
    }

    public void FourOfFour() {
        String[] pushMain =
                /*Main exercises */{"Lat Pulldown", "Barbell Row", "Seated Row"};
        String pushAcc1[] =
                /*accessory/sub1 exercises */{"Sets-3 Reps-8", "Sets-2 Reps-10", "Sets-2 Reps-10"};
        String pushAcc2[] =
                /*accessory/sub2 exercises */ {"RPE @ 8", "RPE @ 7", "RPE @ 6"};
        int week = 1;
        int day = 4;

        String[] testing =
                /*Main exercises */{"Dips", "Cable Rope Tricep Pushdown", "Diamond Push-Ups"};
        accesoryAdapter = new AccesoryAdapter(getContext(),pull,testing,pushAcc1);
        workoutAdapter = new WorkoutAdapter(getContext(), pushMain, pushAcc1);
        listView2.setAdapter(accesoryAdapter);
        simpleList.setAdapter(workoutAdapter);
    }
}
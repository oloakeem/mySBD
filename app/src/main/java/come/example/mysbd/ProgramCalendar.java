package come.example.mysbd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramCalendar extends Fragment {
    ListView simpleList;
    Toolbar toolbar;
    Attendance attendance = new Attendance();
    Load load = new Load();
    Bundle result = new Bundle();
    String weeks[] = {"Week-1", "Week-2", "Week-3", "Week-4", "Week-5", "Week-6"};
    String weekType[] = {"Volume-Cycle 1", "Volume-Cycle 2", "Volume-Cycle 3", "Volume-Cycle 4", "Intensity-Cycle 1", "Intensity-Cycle 2"};
    UsersNumbers usersNumbers = new UsersNumbers();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_program_calendar, container, false);
        attendance.setCW(attendance.loadData(getContext()));
        load.setListHashMap(load.getLoadData());
        usersNumbers.loadValue(getContext());
        toolbar = (Toolbar) view.findViewById(R.id.main_Toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("8-Week Program");
        setHasOptionsMenu(true);

        simpleList = view.findViewById(R.id.pc_LV);
        ProgramCalendarAdapter programCalendarAdapter = new ProgramCalendarAdapter(getContext(), weeks, weekType);
        simpleList.setAdapter(programCalendarAdapter);
//        attendance.setCW(attendance.loadData(getContext()));


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Fragment fragmentselected = null;

                switch (position) {
                    // For volume Fragment pass on their values from each week
                    case 0:
                        fragmentselected = new WeekOneFragment();
                        //pass week in as a String

                        result.putString("bundleKey", "Week-1");
                        fragmentselected.setArguments(result);

                        break;
                    case 1:
                        fragmentselected = new WeekTwoFragment();
                        //pass week in as a String
                        result.putString("bundleKey", "Week-2");
                        fragmentselected.setArguments(result);

                        break;
                    case 2:
                        fragmentselected = new WeekThreeFragment();
                        //pass week in as a String
                        result.putString("bundleKey", "Week-3");
                        fragmentselected.setArguments(result);

                        break;
                    case 3:
                        fragmentselected = new WeekFourFragment();
                        //pass week in as a String
                        result.putString("bundleKey", "Week-4");
                        fragmentselected.setArguments(result);

                        break;
                    case 4:
                        fragmentselected = new WeekFiveFragment();
                        //pass week in as a String
                        result.putString("bundleKey", "Week-5");
                        fragmentselected.setArguments(result);
                        break;
                    case 5:
                        fragmentselected = new WeekSixFragment();
                        //pass week in as a String
                        result.putString("bundleKey", "Week-6");
                        fragmentselected.setArguments(result);
                        break;

                }
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentselected);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            alertDialog();
            alertDialog().show();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public AlertDialog alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.alert_message)
                .setPositiveButton(R.string.alert_Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent homeIntent = new Intent(getContext(), MainActivity.class);
                        startActivity(homeIntent);
                    }
                })
                .setNegativeButton(R.string.alert_No, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
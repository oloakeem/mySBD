package come.example.mysbd;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


public class ShowStats extends Fragment {
     private static final String TAG = "ShowStats";
     DatabaseHelper mDatabaseHelper;
     UsersNumbers usersNumbers = new UsersNumbers();
     private TextView textView,textView2,textView3,textView4;
     Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_showstats, container, false);
        textView2 = view.findViewById(R.id.cardSquat);
        textView3 = view.findViewById(R.id.cardBench);
        textView4 = view.findViewById(R.id.cardDead);
        button = view.findViewById(R.id.newButton);
        textView = view.findViewById(R.id.statslist);
        mDatabaseHelper = new DatabaseHelper((getContext()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new ProgramCalendar());
                  transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        showUserCard();
    return view;
    }

    private void showUserCard(){

        Cursor data = mDatabaseHelper.getData();
        while(data.moveToNext()){
            // get the value from the database in column 1
            // then add it to the ArrayList
            String name = (data.getString(1));
            String bench = (data.getString(2));
            String squat = (data.getString(3));
            String dead = (data.getString(4));

            textView.setText(name);
            textView2.setText(squat);
            textView3.setText(bench);
            textView4.setText(dead);

        }
    }
}

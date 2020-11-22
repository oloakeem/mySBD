package come.example.mysbd;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.io.Serializable;

public class WorkoutAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    String[] MainEx1;
    String[] MainExSnR1;
    Attendance attendance;
    //============================
    SharedPreferences sharedPreferences ;
    int day;
    int week;
    // First exercise
    int [][] myCW = new int[4][7];
    public WorkoutAdapter(Context context, String[] x, String[] y) {
        this.context = context;
        this.MainEx1 = x;
        this.MainExSnR1 = y;
        inflater = (LayoutInflater.from(context));

    }
    @Override
    public int getCount() {
        return MainEx1.length;


    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {
        // Declare Variables
        ToggleButton toggleButton;
        TextView MainEx1_txt;
        TextView MainExSnR1_txt;
        EditText editText;
        attendance = new Attendance();
        itemView = inflater.inflate(R.layout.mainworkout_item, null);

        // Locate the TextViews in viewpager_item.xml
        MainEx1_txt = itemView.findViewById(R.id.vex_2);
        //================================================
        MainExSnR1_txt = itemView.findViewById(R.id.vex_2_snr);
        //================================================
        editText = itemView.findViewById(R.id.myLoad);
        // Capture position and set to the TextViews
        MainEx1_txt.setText(MainEx1[i]);
        //================================================
        MainExSnR1_txt.setText(MainExSnR1[i]);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return itemView;
    }


}
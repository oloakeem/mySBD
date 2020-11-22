package come.example.mysbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class CreateStats extends AppCompatActivity {
    private static final String TAG = "CreateStats";
    ImageView relativeLayout;
    DatabaseHelper mDatabaseHelper;
    UsersNumbers usersNumbers = new UsersNumbers();
    RadioGroup radioGroup;
    RadioButton KtoL, LtoK;
    String bench = null;
    String squat = null;
    String dead = null;
    String newEntry1 = null;
    boolean LSB;
    private Button btnAdd;
    private EditText editTextName, editTextBench, editTextSquat, editTextDead;
    private TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_stats);
        editTextName = findViewById(R.id.name_ETXT);
        editTextBench = findViewById(R.id.bench_ETXT);
        editTextSquat = findViewById(R.id.squat_ETXT);
        editTextDead = findViewById(R.id.deadlift_ETXT);
        radioGroup = findViewById(R.id.input_switch);
        textView1 = findViewById(R.id.textViewSquat);
        textView2 = findViewById(R.id.textViewBench);
        textView3 = findViewById(R.id.textViewDead);
        editTextBench.setFilters(new InputFilter[]{new InputFilterMinMax("0", "999")});
        editTextSquat.setFilters(new InputFilter[]{new InputFilterMinMax("0", "999")});
        editTextDead.setFilters(new InputFilter[]{new InputFilterMinMax("0", "999")});

        LtoK = findViewById(R.id.input_LtoK);
        KtoL = findViewById(R.id.input_KtoL);

        btnAdd = findViewById(R.id.btnAdd);
        mDatabaseHelper = new DatabaseHelper(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntry = editTextName.getText().toString().trim();
                String newEntry2 = editTextBench.getText().toString().trim();
                String newEntry3 = editTextSquat.getText().toString().trim();
                String newEntry4 = editTextDead.getText().toString().trim();

                if (editTextName.length() != 0) {
                    AddData(newEntry, newEntry2, newEntry3, newEntry4,newEntry1);
                    editTextName.setText(" ");
                    Toast.makeText(CreateStats.this, "Your stats have been updated", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateStats.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateStats.this, "Stat not complete", Toast.LENGTH_SHORT).show();

                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch (checkedId) {
                    case R.id.input_LtoK:
                        newEntry1 = "lbs";
                        textView1.setText("lbs");
                        textView2.setText("lbs");
                        textView3.setText("lbs");

                        //  LbsToKgs();
                        break;
                    case R.id.input_KtoL:
                        newEntry1 = "kg";
                        textView1.setText("kg");
                        textView2.setText("kg");
                        textView3.setText("kg");

                        //KgstoLbs();
                        break;
                }

            }
        });
    }

    public void LbsToKgs() {

    }

    public void KgstoLbs() {

    }

    public void AddData(String newEntry, String newEntry2, String newEntry3, String newEntry4,String myEntry1) {
        boolean insertData = mDatabaseHelper.addData(newEntry, newEntry2, newEntry3, newEntry4,myEntry1);
        if (insertData) {
            setNumbers();
            Toast.makeText(getApplicationContext(),
                    "Yes", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(getApplicationContext(),
                    "No", Toast.LENGTH_SHORT);
        }
    }

    public void setNumbers() {


        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
             bench = (data.getString(2));
             squat = (data.getString(3));
             dead = (data.getString(4));
        }
        usersNumbers.setUserDEADS(dead);
        usersNumbers.setUserSQUATS(squat);
        usersNumbers.setUserBENCHS(bench);
        usersNumbers.saveValues(getApplicationContext());

    }
}

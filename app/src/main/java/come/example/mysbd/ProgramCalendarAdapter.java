package come.example.mysbd;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ProgramCalendarAdapter extends BaseAdapter {
    Context context;
    String week[];
    String weekType[];
    LayoutInflater inflter;
    SharedPreferences sharedPreferences;
    int currentPosition = -1;
    String highScore = "notEmpty";
    String[] apple;
    Attendance attendance = new Attendance();
    public ProgramCalendarAdapter(Context apcontext,String[] week,String[] weekType) {
        this.context = apcontext;
        this.week = week;
        this.weekType = weekType;
        inflter = (LayoutInflater.from(apcontext));

    }

    @Override
    public int getCount() {

        return week.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    static class ViewHolder {
        private  TextView pc_week;
        private TextView pc_week_type;
        private TextView toggleButton;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflter.inflate(R.layout.pc_item, null);
            holder = new ViewHolder();
            holder.pc_week = (TextView) view.findViewById(R.id.week_TXT);
            holder.pc_week_type = (TextView) view.findViewById(R.id.week_TYPE);
            holder.toggleButton = view.findViewById(R.id.checkImage);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }


        holder.pc_week.setText(week[i]);
        holder.pc_week_type.setText(weekType[i]);
        //holder.personImageView.setImageBitmap(person.getImage());

        sharedPreferences = context.getSharedPreferences("isComplete", Context.MODE_PRIVATE);
        highScore = sharedPreferences.getString((context.getString(R.string.saved_high_score_key)), "empty");
        attendance = new Attendance();
        Resources res = context.getResources();
        TypedArray imgs = res.obtainTypedArray(R.array.your_colors);
        RelativeLayout relativeLayout;
        relativeLayout = view.findViewById(R.id.myLayout);
/*
        ImageView toggleButton;
        toggleButton = view.findViewById(R.id.checkImage);
        TextView pc_week = (TextView) view.findViewById(R.id.week_TXT);
        TextView pc_week_type = (TextView) view.findViewById(R.id.week_TYPE);
        pc_week_type.setText(weekType[i]);
        pc_week.setText(week[i]);
*/
        if (i < week.length) {
            relativeLayout.setBackgroundColor(imgs.getColor(i,1));
            i++;
        }
        /* Checks which weeks have been completed.  */
            if (attendance.checkWeek(i))
               holder.toggleButton.setBackgroundResource(R.drawable.is_complete_background);
            else
            {
                holder.toggleButton.setBackgroundResource(R.drawable.not_finished);

            }

       // relativeLayout.setBackgroundResource(imgs.getResourceId(i, 1));


        return view;

    }
}

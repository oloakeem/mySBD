package come.example.mysbd;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class AccesoryAdapter extends BaseAdapter {
    Context context;
    int bodyParts[];
    String[] workOuts;
    String[] setNrep;
    LayoutInflater inflater;

    public AccesoryAdapter(Context applicationContext, int[] bodyParts, String[] workOuts, String[] setNrep) {
        this.context = applicationContext;
        this.bodyParts = bodyParts;
        this.workOuts = workOuts;
        this.setNrep = setNrep;
        inflater = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return workOuts.length;
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
        private ImageView imageView;
        private Spinner spinner;
        private TextView textView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AccesoryAdapter.ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.acc_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.accesory_Image);
            holder.spinner = (Spinner) view.findViewById(R.id.accesory_Item1);
            holder.textView = view.findViewById(R.id.accesory_Item2);
            holder.imageView.setTag(1);

            view.setTag(holder);
        }
        else {
            holder = (AccesoryAdapter.ViewHolder) view.getTag();
        }


        holder.imageView.setImageResource(bodyParts[i]);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.spinner_item,workOuts);
        holder.spinner.setAdapter(arrayAdapter);
        holder.textView.setText(setNrep[i]);
        return view;
    }
}

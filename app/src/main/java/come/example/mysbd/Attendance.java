package come.example.mysbd;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class Attendance {

    public static int [][]  CW = new int[7][4];
            /*= {
            {0, 0, 0, 0}, // Doesnt count.
            {0, 0, 0, 0}, //week One
            {0, 0, 0, 0}, //week Two
            {0, 0, 0, 0}, //week Three
            {0, 0, 0, 0}, //week Four
            {0, 0, 0, 0}, //week Five
            {0, 0, 0, 0}, //week Six
            };
*/


    public void save(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(getCW());
        editor.putString("list", json);
        editor.apply();
    }
    public int[][] loadData(Context context) {
        int[][] newArray = new int[7][4];
        Gson x = new Gson();

        SharedPreferences sharedPreferences = context.getSharedPreferences("shared", MODE_PRIVATE);
           String xy = sharedPreferences.getString("list", null);
           if(xy != null) {

               newArray = x.fromJson(xy, int[][].class);
       }
   return newArray; }

    public void setCW(int[][] CW) {
        this.CW = CW;
    }


    public boolean peekDay(int week , int day ) {
            if (CW[week][day] != 1)
                return false;
            else
                return true;

    }

    public void checkDay(int week, int day ) {
        if (CW != null) {
            CW[week][day] = 1;
        }
  }
    public void uncheckDay(int week, int day) {
        if(CW != null) {
            CW[week][day] = 0;
        }
    }
    public int[][] getCW() {
        return CW;
    }

    public Boolean checkWeek(int week ){
        if(CW != null) {
            for (int i = 0; i < 4; i++) {
                if (CW[week][i] != 1) return false;
            }
        }
   return  true;
    }
}

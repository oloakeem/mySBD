package come.example.mysbd;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class UsersNumbers {
    static int userBENCH;
    static int userDEAD;
    static int userSQUAT;
    static String userBENCHS;
    static String userDEADS;
    static String userSQUATS;

    public String getUserBENCHS() {
        return userBENCHS;
    }

    public String getUserDEADS() {
        return userDEADS;
    }

    public String getUserSQUATS() {
        return userSQUATS;
    }

    public void setUserBENCHS(String userBENCHS) {
        this.userBENCHS = userBENCHS;
    }

    public void setUserDEADS(String userDEADS) {
        this.userDEADS = userDEADS;
    }

    public void setUserSQUATS(String userSQUATS) {
        this.userSQUATS = userSQUATS;
    }

    public void saveValues(Context context){
        SharedPreferences preferences = context.getSharedPreferences("sharedx", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("savedBench",getUserBENCHS());
        editor.putString("savedSquat",getUserSQUATS());
        editor.putString("savedDead",getUserDEADS());
        editor.apply();
    }
    public String [] loadValue(Context context) {
        String [] newArray = new String[3];
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedx", MODE_PRIVATE);
        String x = sharedPreferences.getString("savedBench", null);
        String z= sharedPreferences.getString("savedSquat", null);
        String y = sharedPreferences.getString("savedDead", null);
        if(x != null && y != null && z!= null){
            newArray[0] = x;
            newArray[1] = z;
            newArray[2] = y;

        }
        return newArray; }
    public int convertString(String string){
        int newValue = 0;
        newValue = Integer.parseInt(string);

    return   newValue;
    }
}

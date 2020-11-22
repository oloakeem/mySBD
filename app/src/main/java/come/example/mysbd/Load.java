package come.example.mysbd;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Load {
    HashMap<String, List<String>> listHashMap= new HashMap<String, List<String>>();

    public HashMap<String, List<String>> getListHashMap() {
        return listHashMap;
    }

    public void setListHashMap(HashMap<String, List<String>> listHashMap) {
        this.listHashMap = listHashMap;
    }

    // find the day and add the load to the hash map
    public void putLoad(String string, List<String> list){
        getListHashMap().put(string,list);
    }

    public List<String> getLoad(String day){
        return getListHashMap().get(day);

    }
    public HashMap<String, List<String>> getLoadData() {
        HashMap<String, List<String>> read = new HashMap<String, List<String>>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data"));
            if(ois != null) {
                try {
                    read = (HashMap<String, List<String>>) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }
    public void saveLoad(Context context){

        File file = new File(context.getDir("data", MODE_PRIVATE), "map");
        try {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(getListHashMap());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

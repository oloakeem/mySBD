package come.example.mysbd;

public class  Model {

    private int image;
    private String title;
    private String desc;

    public Model( String title,String Day, int image) {
        this.image = image;
        this.title = title;
        this.desc = Day;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
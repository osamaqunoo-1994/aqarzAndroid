package sa.aqarz.Modules;

public class imagemodules {


    String image_url;
    String type;


    public imagemodules(String image_url, String type) {
        this.image_url = image_url;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public String getImage_url() {
        return image_url;
    }
}

package com.j4f.wallpaper.Model;

/**
 * Created by pham on 7/27/2015.
 */
public class Album extends BaseModel {
    private String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Album(String Id, String Name, String Url, String Type) {
        super(Id, Name, Url);
        this.Type = Type;
    }
}

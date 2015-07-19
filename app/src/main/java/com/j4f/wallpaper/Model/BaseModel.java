package com.j4f.wallpaper.Model;

/**
 * Created by pham on 7/3/2015.
 */
public class BaseModel {
    public String Id;
    public String Name;
    public String Url;

    public BaseModel() {

    }

    public BaseModel(String Id, String Name, String Url) {
        this.Id=Id;
        this.Name=Name;
        this.Url=Url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}

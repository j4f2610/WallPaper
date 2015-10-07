package com.j4f.wallpaper.Model;

import java.io.Serializable;

/**
 * Created by pham on 7/31/2015.
 */
public class WallPaper implements Serializable {

    public String urlImage;
    public String urlImageMini;

    public WallPaper() {
    }

    public WallPaper(String urlImage, String urlImageMini) {
        this.urlImage = urlImage;
        this.urlImageMini = urlImageMini;

    }


    public String getUrlImageMini() {
        return urlImageMini;
    }

    public void setUrlImageMini(String urlImageMini) {
        this.urlImageMini = urlImageMini;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}

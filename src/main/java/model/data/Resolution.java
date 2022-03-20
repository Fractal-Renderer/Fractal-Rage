package model.data;

public record Resolution (int width, int height){

    public Resolution(int ratioW, int ratioH, int scale) {
        this(ratioW * scale, ratioH * scale);
    }

}

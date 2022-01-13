package model;

import lombok.Getter;

public class Resolution {
    @Getter
    private final int width, height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private Resolution(int ratioW, int ratioH, int scale) {
        this(ratioW * scale, ratioH * scale);
    }
}

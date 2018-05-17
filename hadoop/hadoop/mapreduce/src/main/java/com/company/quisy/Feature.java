package com.company.quisy;

public class Feature {
    private Features2d features2D;
    private String side;
    private int series;
    private int sample;

    public Features2d getFeatures2D() {
        return features2D;
    }

    public void setFeatures2D(Features2d features2D) {
        this.features2D = features2D;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getSample() {
        return sample;
    }

    public void setSample(int sample) {
        this.sample = sample;
    }
}

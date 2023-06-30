package com.ferenc.radio_sammlung.radio;

public class Radio {

    private int radioId;
    private String name;
    private String streamUrl;
    private String homePageUrl;

    public Radio(int radioId, String name, String streamUrl, String homePageUrl) {
        this.radioId = radioId;
        this.name = name;
        this.streamUrl = streamUrl;
        this.homePageUrl = homePageUrl;
    }

    public int getRadioId() {
        return radioId;
    }

    public String getName() {
        return name;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    @Override
    public String toString() {
        return "Radio{" +
                "radioId=" + radioId +
                ", name='" + name + '\'' +
                ", streamUrl='" + streamUrl + '\'' +
                ", homePageUrl='" + homePageUrl + '\'' +
                '}';
    }
}

package com.example.calackids;


public class MenuCard {
    private String card_text;
    private int image_id;
    public Class<?> activity;


    public MenuCard(String textId, int imageId, Class<?> activity){
        card_text = textId;
        image_id = imageId;
        this.activity = activity;
    }

    public String getCard_text() {
        return card_text;
    }

    public void setCard_text(String card_text) {
        this.card_text = card_text;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity_id(Class<?> activity) {
        this.activity = activity;
    }

}

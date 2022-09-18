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
    //    private void setNewCard(){
//        newCard.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
////        newCard.setMinimumWidth(180);
////        newCard.setMinimumHeight(180);
//        newCard.setElevation(10);
//        newCard.setRadius(20);
//        newCard.setPreventCornerOverlap(true);
//        newCard.setUseCompatPadding(true);
//        newCard.setMaxCardElevation(12);

        //        newCard.setCardBackgroundColor();
//    }
//    private void setImage(){
//        image.setMaxHeight(50);
//        image.setMaxWidth(50);
//        image.setImageResource(image_id);
//    }
//    private void setText(){
//        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        text.setText(card_text);
//        text.setGravity(Gravity.CENTER_HORIZONTAL);
//        text.setTextSize(20);
//    }
//    public CardView createC(Context context){
//        newCard = new CardView(context);
//        setNewCard();
//        image = new ImageView(context);
//        setImage();
//        text = new TextView(context);
//        setText();
//
//        return newCard;
//    }

}

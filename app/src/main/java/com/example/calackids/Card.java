package com.example.calackids;


public class Card {
    private String card_text;
    private int image_id;
    private int activity_id;

    public Card(String textId, int imageId, int activity){
        card_text = textId;
        image_id = imageId;
        activity_id = activity;
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

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
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

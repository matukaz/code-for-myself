package com.teddydeveloper.stardewvalleywiki;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by Matu on 2.03.2016.
 */
public class Data implements Serializable {

    String itemType;
    int photoId;

    private String title;
    private String image;
    private String description;
    private String price;
    private String effect;
    private String sellingPrice;
    private String buyingPrice;
    private String itemLocation;
    private String ingredients;
    private LinkedHashMap<String, String> details;

    //Dummy constructor to solve Jackson JsonMappingException Error.
    public Data() {
    }

    public Data(String itemType,String title, int photoId) {
        this.itemType=itemType;
        this.title = title;
        this.photoId = photoId;
    }

    public Data(String itemType,String title, int photoId, String description) {
        this.itemType=itemType;
        this.title = title;
        this.photoId = photoId;
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public String getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }


    public LinkedHashMap<String, String> getDetails() {
        return details;
    }
    public void setDetails(LinkedHashMap<String, String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Data{" +
                "itemType='" + itemType + '\'' +
                ", photoId=" + photoId +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", effect='" + effect + '\'' +
                ", sellingPrice='" + sellingPrice + '\'' +
                ", buyingPrice='" + buyingPrice + '\'' +
                ", itemLocation='" + itemLocation + '\'' +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }
}

package com.teddydeveloper.stardewvalleywiki;

/**
 * Created by Matu on 2.03.2016.
 */
public class SearchData {

    String itemType;
    String title;
    int photoId;

    public String getDetailedText() {
        return detailedText;
    }

    public void setDetailedText(String detailedText) {
        this.detailedText = detailedText;
    }

    String detailedText;

    public SearchData(String itemType, String title, int photoId, String s) {
        this.itemType=itemType;
        this.title = title;
        this.photoId = photoId;
        this.detailedText = s;
    }

    @Override
    public String toString() {
        return "Data{" +
                "itemType='" + itemType + '\'' +
                ", title='" + title + '\'' +
                ", photoId=" + photoId +
                '}';
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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public SearchData(String itemType, String title, int photoId) {
        this.itemType=itemType;
        this.title = title;
        this.photoId = photoId;
    }


}

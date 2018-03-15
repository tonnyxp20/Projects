package com.developer.tonny.myroutine.Adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by Tonny on 7/13/2017.
 */

public class Category {

    private String title;
    private String categoryId;
    private String description;
    private int imagen;

    public Category() {
        super();
    }

    public Category(String categoryId, String title, String description, int imagen) {
        super();
        this.title = title;
        this.description = description;
        this.imagen = imagen;
        this.categoryId = categoryId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getCategoryId(){return categoryId;}

    public void setCategoryId(String categoryId){this.categoryId = categoryId;}
}

package com.example.lacuisine;

public class Recette {
    private int id;
    private String title;
    private int readyIn;
    private int servings;
    private String image;
    private final static String baseUri= "https://spoonacular.com/recipeImages/";

    public Recette(int id, String title, int readyIn, int servings, String image) {
        this.id = id;
        this.title = title;
        this.readyIn = readyIn;
        this.servings = servings;
        this.image = baseUri + image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyIn() {
        return readyIn;
    }

    public void setReadyIn(int readyIn) {
        this.readyIn = readyIn;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static String getBaseUri() {
        return baseUri;
    }

    @Override
    public String toString() {
        return "Recette{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", readyIn=" + readyIn +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                '}';
    }
}

package android.zoo;

public class AnimalItem {

    private String animal;
    private int imageUrl;
    private String description;

    public AnimalItem(String animal, int imageUrl, String description) {

        this.animal = animal;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getAnimal() {
        return animal;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }


}



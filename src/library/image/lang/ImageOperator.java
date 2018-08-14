package library.image.lang;

public abstract class ImageOperator {
    private Image image;

    public ImageOperator(Image image){
        this.image = image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Image operate(){
        Image computed = new Image(image);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                computed.set(x, y, compute(image.get(x, y)));
            }
        }

        return computed;
    }

    public abstract Color compute(Color originalColor);
}

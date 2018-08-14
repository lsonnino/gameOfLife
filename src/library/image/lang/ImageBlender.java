package library.image.lang;

public abstract class ImageBlender {
    private Image referenceImage, blendedImage, texture;

    public ImageBlender(Image referenceImage, Image blendedImage){
        this(referenceImage, blendedImage, null);
    }
    public ImageBlender(Image referenceImage, Image blendedImage, Image texture){
        this.referenceImage = referenceImage;
        this.blendedImage = blendedImage;
        this.texture = texture;
    }

    public void setReferenceImage(Image referenceImage) {
        this.referenceImage = referenceImage;
    }
    public Image getReferenceImage() {
        return referenceImage;
    }

    public void setBlendedImage(Image blendedImage) {
        this.blendedImage = blendedImage;
    }
    public Image getBlendedImage() {
        return blendedImage;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }
    public Image getTexture() {
        return texture;
    }

    public Image blend(){
        return blend(0, 0, false, 0, 0);
    }
    public Image blend(int offsetX, int offsetY){
        return blend(offsetX, offsetY, false, 0, 0);
    }
    public Image blend(boolean repeat){
        return blend(0, 0, false, 0, 0);
    }
    public Image blend(int offsetX, int offsetY, boolean repeat){
        return blend(offsetX, offsetY, repeat, 0, 0);
    }
    public Image blend(int offsetX, int offsetY, boolean repeat, int textureOffsetX, int textureOffsetY){
        boolean tex = texture != null;

        Image blended = new Image(referenceImage);

        int bX = offsetX;
        int texX = textureOffsetX;

        for (int x = 0; x < referenceImage.getWidth(); x++) {
            int bY = offsetY;
            int texY = textureOffsetY;

            for (int y = 0; y < referenceImage.getHeight(); y++) {

                Color referenceColor = referenceImage.get(x, y);
                Color blendColor = blendedImage.get(bX, bY);

                blended.set(x, y, blendMode(referenceColor, blendColor));
                if(tex){
                    blended.get(x, y).mix(referenceColor, texture.get(texX, texY).getBrightness());
                }


                bY++;
                if(bY >= blendedImage.getHeight()){
                    if(repeat){
                        bY = 0;
                    }
                    else {
                        break;
                    }
                }

                if(tex) {
                    texY++;
                    if (texY >= texture.getHeight()) {
                        texY = 0;
                    }
                }
            }
            bX++;
            if(bX >= blendedImage.getWidth()){
                if(repeat){
                    bX = 0;
                }
                else {
                    return blended;
                }
            }

            texX++;
            if(tex){
                if (texX >= texture.getWidth()) {
                    texX = 0;
                }
            }
        }

        return blended;
    }

    public abstract Color blendMode(Color referenceColor, Color blendColor);
}

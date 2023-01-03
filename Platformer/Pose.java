public enum Pose {
    RIGHT("Assets/link-right.png", "Assets/link-right2.png"),
    LEFT("Assets/link-left.png", "Assets/link-left2.png");

    private final Animation animation;

    private Pose(String... images){
        this.animation = new Animation(images);
    }

    public String getImage(){
        return this.animation.getImage();
    }
}

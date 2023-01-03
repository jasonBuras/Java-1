public class Animation {
    long startTime;
    int frameIndex;
    String[] sequence;

    public Animation(String... images){//https://stackoverflow.com/questions/3158730/what-do-3-dots-next-to-a-parameter-type-mean-in-java
        this.startTime = System.currentTimeMillis();
        this.frameIndex=0;
        this.sequence=images;
    }

    public String getImage(){
        int index = this.frameIndex;

        long now = System.currentTimeMillis();
        if(now-this.startTime > 75){
            this.frameIndex = (index+1) % this.sequence.length;
            this.startTime = now;
        }
        return this.sequence[index];
    }
}

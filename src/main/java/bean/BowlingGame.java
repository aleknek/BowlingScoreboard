package bean;

import constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BowlingGame implements Serializable{

    private static BowlingGame instance;
    private List<Frame> frames = new ArrayList<Frame>();
    private Frame currentFrame;

    private BowlingGame() {

        for (int i = 1; i <= 9; i++) {
            frames.add(new Frame(i, 1, Constants.EMPTY_VIEW_FRAME));
        }
        frames.add(new FrameTenth(10, 1, Constants.EMPTY_VIEW_FRAME_TENTH));

        currentFrame = frames.get(0);

    }

    public static BowlingGame getInstance() {

        if (instance == null) {
            instance = new BowlingGame();
        }
        return instance;
    }

    public static void setInstance(BowlingGame instance) {
        BowlingGame.instance = instance;
    }

    public Frame getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(Frame currentFrame) {
        this.currentFrame = currentFrame;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frame) {
        this.frames = frame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BowlingGame)) return false;

        BowlingGame that = (BowlingGame) o;

        if (getFrames() != null ? !getFrames().equals(that.getFrames()) : that.getFrames() != null) return false;
        return getCurrentFrame() != null ? getCurrentFrame().equals(that.getCurrentFrame()) : that.getCurrentFrame() == null;

    }

    @Override
    public int hashCode() {
        int result = getFrames() != null ? getFrames().hashCode() : 0;
        result = 31 * result + (getCurrentFrame() != null ? getCurrentFrame().hashCode() : 0);
        return result;
    }
}

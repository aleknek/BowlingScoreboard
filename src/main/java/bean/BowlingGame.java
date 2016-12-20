package bean;

import constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private static BowlingGame instance;
    private List<Frame> frames = new ArrayList<Frame>();
    private Frame currentFrame;

    private BowlingGame() {

        for (int i = 1; i <= 9; i++) {
            frames.add(new Frame(1, Constants.EMPTY_VIEW_FRAME));
        }
        frames.add(new FrameTenth(1, Constants.EMPTY_VIEW_FRAME_TENTH));

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

}

package dao;

import bean.BowlingGame;
import bean.Frame;

public class BowlingGameDAO {

    BowlingGame bowlingGame;
    FrameDAO frameDAO = new FrameDAO();

    public BowlingGameDAO() {
        this.bowlingGame = BowlingGame.getInstance();
    }

    public int getIndexFrame(Frame frame) {
        return bowlingGame.getFrames().indexOf(frame);
    }

    public Frame getNextFrame(Frame frame) {

        int indexOfCurrentFrame = getIndexFrame(frame);

        if (indexOfCurrentFrame == bowlingGame.getFrames().size() - 1) {
            return frame;
        }
        return bowlingGame.getFrames().get(indexOfCurrentFrame + 1);
    }

    public void updateCurrentFrame() {

        Frame currentFrame = bowlingGame.getCurrentFrame();

        if (frameDAO.isStrike(currentFrame) || frameDAO.isSpare(currentFrame) || currentFrame.getNumberOfThrow() == 0) {
            bowlingGame.setCurrentFrame(getNextFrame(currentFrame));
        }
    }
}

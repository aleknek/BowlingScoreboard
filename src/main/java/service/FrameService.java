package service;

import bean.BowlingGame;
import bean.Frame;
import constants.Constants;

public class FrameService {

    public void setScore(Frame currentFrame, int countOfPinsKnockedDown) {

        if (currentFrame.getNumberOfThrow() == 1) {
            currentFrame.setScoreOfFirstThrow(countOfPinsKnockedDown);
            currentFrame.setView(isStrike(currentFrame) ? " ,X" : parseValue(countOfPinsKnockedDown) + ", ");
            currentFrame.setNumberOfThrow(2);
        } else {
            currentFrame.setScoreOfSecondThrow(countOfPinsKnockedDown);
            currentFrame.setView(isSpare(currentFrame) ? parseValue(currentFrame.getScoreOfFirstThrow()) + ", /"
                    : currentFrame.getView().replace(", ", "") + "," + parseValue(currentFrame.getScoreOfSecondThrow()));
            currentFrame.setNumberOfThrow(0);
        }
    }

    public int getScoreOfFrame(Frame frame) {

        if (isStrike(frame)) {
            return getSumThrows(frame) + addRewardPointsStrike(frame);
        } else if (isSpare(frame)) {
            return getSumThrows(frame) + addRewardPointsSpare(frame);
        } else {
            return getSumThrows(frame);
        }
    }

    public boolean isSpare(Frame frame) {
        return frame.getScoreOfFirstThrow() + frame.getScoreOfSecondThrow() == 10;
    }

    public boolean isStrike(Frame frame) {
        return frame.getScoreOfFirstThrow() == 10;
    }

    public int addRewardPointsStrike(Frame frame) {
        Frame nextFrame = getNextFrame(frame);
        if (isStrike(nextFrame)) {
            return getSumPointsStrike(nextFrame);
        } else {
            return getSumThrows(nextFrame);
        }
    }

    public int addRewardPointsSpare(Frame frame) {
        return getNextFrame(frame).getScoreOfFirstThrow();
    }

    public int getSumPointsStrike(Frame frame) {
        return frame.getScoreOfFirstThrow() + getNextFrame(frame).getScoreOfFirstThrow();
    }

    public int getSumThrows(Frame frame) {
        return frame.getScoreOfFirstThrow() + frame.getScoreOfSecondThrow();
    }

    public boolean isNotActiveFrame(Frame frame) {
        return frame.getView().equals(Constants.EMPTY_VIEW_FRAME) || frame.getView().equals(Constants.EMPTY_VIEW_FRAME_TENTH);
    }

    public String parseValue(int value) {
        return String.valueOf(value).replace("0", "-");
    }

    public int getNumberOfRemainingPins(Frame frame) {
        return 10 - frame.getScoreOfFirstThrow();
    }

    public Frame getNextFrame(Frame frame) {

        int indexOfCurrentFrame = getIndexFrame(frame);

        if (indexOfCurrentFrame == BowlingGame.getInstance().getFrames().size() - 1) {
            return frame;
        }
        return BowlingGame.getInstance().getFrames().get(indexOfCurrentFrame + 1);
    }

    public int getIndexFrame(Frame frame) {
        return BowlingGame.getInstance().getFrames().indexOf(frame);
    }

    public void updateCurrentFrame() {

        Frame currentFrame = BowlingGame.getInstance().getCurrentFrame();

        if (isStrike(currentFrame) || isSpare(currentFrame) || currentFrame.getNumberOfThrow() == 0) {
            BowlingGame.getInstance().setCurrentFrame(getNextFrame(currentFrame));
        }
    }
}

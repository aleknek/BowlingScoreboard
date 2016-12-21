package service;

import bean.FrameTenth;

public class FrameTenthService extends FrameService {

    public void setScore(FrameTenth currentFrame, int countOfPinsKnockedDown) {

        if (currentFrame.getNumberOfThrow() == 1) {
            currentFrame.setScoreOfFirstThrow(countOfPinsKnockedDown);
            currentFrame.setView(parseValue(countOfPinsKnockedDown) + ", , ");
            currentFrame.setNumberOfThrow(2);
        } else if (currentFrame.getNumberOfThrow() == 2) {
            currentFrame.setScoreOfSecondThrow(countOfPinsKnockedDown);
            currentFrame.setView(currentFrame.getView().replace(", , ", "") + "," + parseValue(countOfPinsKnockedDown) + ", ");
            currentFrame.setNumberOfThrow(3);
        } else {
            currentFrame.setScoreOfThirdThrow(countOfPinsKnockedDown);
            currentFrame.setView(currentFrame.getView() + parseValue(countOfPinsKnockedDown));
            currentFrame.setNumberOfThrow(-1);
        }
    }

    public int getScoreOfFrame(FrameTenth frame) {
        return frame.getScoreOfFirstThrow() + frame.getScoreOfSecondThrow() + frame.getScoreOfThirdThrow();
    }

    public String parseValue(int value) {
        return String.valueOf(value).replace("10", "X").replace("0", "-");
    }

    public int getNumberOfRemainingPins(FrameTenth frame) {
        if (frame.getScoreOfFirstThrow() == 10 || frame.getScoreOfSecondThrow() == 10) {
            return 10;
        } else {
            return 10 - frame.getScoreOfFirstThrow() - frame.getScoreOfSecondThrow();
        }
    }
}

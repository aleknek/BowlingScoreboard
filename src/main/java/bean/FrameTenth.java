package bean;

import dao.FrameTenthDAO;

public class FrameTenth extends Frame {

    private int scoreOfThirdThrow;

    public FrameTenth() {
    }

    public FrameTenth(int throwNumber, String view) {
        super(throwNumber, view);
    }

    public int getScoreOfThirdThrow() {
        return scoreOfThirdThrow;
    }

    public void setScoreOfThirdThrow(int scoreOfThirdThrow) {
        this.scoreOfThirdThrow = scoreOfThirdThrow;
    }

    public int getScore() {
        return getScoreOfFirstThrow() + getScoreOfSecondThrow() + scoreOfThirdThrow;
    }

    public void setScore(int score) {
        new FrameTenthDAO().setScore(this, score);
    }

    public int getNumberOfRemainingPins() {
        return new FrameTenthDAO().getNumberOfRemainingPins(this);
    }
}

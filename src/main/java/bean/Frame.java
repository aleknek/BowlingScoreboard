package bean;

import dao.FrameDAO;

public class Frame {

    private int scoreOfFirstThrow;
    private int scoreOfSecondThrow;
    private int numberOfThrow;
    private String view;

    public Frame() {
    }

    public Frame(int throwNumber, String view) {
        this.numberOfThrow = throwNumber;
        this.view = view;
    }

    public int getScoreOfFirstThrow() {
        return scoreOfFirstThrow;
    }

    public void setScoreOfFirstThrow(int scoreOfFirstThrow) {
        this.scoreOfFirstThrow = scoreOfFirstThrow;
    }

    public int getScoreOfSecondThrow() {
        return scoreOfSecondThrow;
    }

    public void setScoreOfSecondThrow(int scoreOfSecondThrow) {
        this.scoreOfSecondThrow = scoreOfSecondThrow;
    }

    public int getNumberOfThrow() {
        return numberOfThrow;
    }

    public void setNumberOfThrow(int numberOfThrow) {
        this.numberOfThrow = numberOfThrow;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setScore(int score) {
        new FrameDAO().setScore(this, score);
    }

    public int getNumberOfRemainingPins() {
        return new FrameDAO().getNumberOfRemainingPins(this);
    }

    public int getScore() {
        return new FrameDAO().getScoreOfFrame(this);
    }
}





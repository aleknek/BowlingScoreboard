package bean;

import service.FrameService;

import java.io.Serializable;

public class Frame implements Serializable {

    private int id;
    private int scoreOfFirstThrow;
    private int scoreOfSecondThrow;
    private int numberOfThrow;
    private String view;

    public Frame() {
    }

    public Frame(int id, int throwNumber, String view) {
        this.id = id;
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
        new FrameService().setScore(this, score);
    }

    public int getNumberOfRemainingPins() {
        return new FrameService().getNumberOfRemainingPins(this);
    }

    public int getScore() {
        return new FrameService().getScoreOfFrame(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frame)) return false;

        Frame frame = (Frame) o;

        if (id != frame.id) return false;
        if (getScoreOfFirstThrow() != frame.getScoreOfFirstThrow()) return false;
        if (getScoreOfSecondThrow() != frame.getScoreOfSecondThrow()) return false;
        if (getNumberOfThrow() != frame.getNumberOfThrow()) return false;
        return getView() != null ? getView().equals(frame.getView()) : frame.getView() == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + getScoreOfFirstThrow();
        result = 31 * result + getScoreOfSecondThrow();
        result = 31 * result + getNumberOfThrow();
        result = 31 * result + (getView() != null ? getView().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "id=" + id +
                ", scoreOfFirstThrow=" + scoreOfFirstThrow +
                ", scoreOfSecondThrow=" + scoreOfSecondThrow +
                ", numberOfThrow=" + numberOfThrow +
                ", view='" + view + '\'' +
                '}';
    }
}





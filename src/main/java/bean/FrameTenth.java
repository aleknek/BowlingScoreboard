package bean;

import service.FrameTenthService;

import java.io.Serializable;

public class FrameTenth extends Frame implements Serializable {

    private int scoreOfThirdThrow;

    public FrameTenth() {
    }

    public FrameTenth(int id, int throwNumber, String view) {
        super(id, throwNumber, view);
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
        new FrameTenthService().setScore(this, score);
    }

    public int getNumberOfRemainingPins() {
        return new FrameTenthService().getNumberOfRemainingPins(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrameTenth)) return false;

        FrameTenth that = (FrameTenth) o;

        return getScoreOfThirdThrow() == that.getScoreOfThirdThrow();

    }

    @Override
    public int hashCode() {
        return getScoreOfThirdThrow();
    }

    @Override
    public String toString() {
        return "FrameTenth{" +
                "scoreOfThirdThrow=" + scoreOfThirdThrow +
                '}';
    }
}

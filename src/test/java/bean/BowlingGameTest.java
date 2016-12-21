package bean;

import service.FrameService;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @org.junit.Before
    public void setUp() throws Exception {
        bowlingGame = BowlingGame.getInstance();
    }

    @Test
    public void testZero() {

        fillValuesFrames(0, 0, 21);
        assertEquals(0, getScoreTotal());

        clearValuesOfFrames();
    }

    @Test
    public void testPerfectGame() {

        fillValuesFrames(10, 0, 12);
        assertEquals(300, getScoreTotal());

        clearValuesOfFrames();
    }

    @Test
    public void testDifferentValues() {

        fillValuesFrames(10, 0, 2);
        fillValuesFrames(2, 3, 6);
        fillValuesFrames(5, 6, 10);
        fillValuesFrames(1, 10, 12);
        fillValuesFrames(0, 12, 21);
        assertEquals(69, getScoreTotal());

        clearValuesOfFrames();
    }

    public void fillValuesFrames(int value, int countFrom, int countTo) {

        for (int i = countFrom; i < countTo; i++) {
            bowlingGame.getCurrentFrame().setScore(value);
            new FrameService().updateCurrentFrame();
        }
    }

    public int getScoreTotal() {

        int totalScore = 0;

        List<Frame> frames = bowlingGame.getFrames();

        Iterator<Frame> iterator = frames.iterator();
        while (iterator.hasNext()) {
            Frame frame = iterator.next();
            totalScore = totalScore + frame.getScore();
        }
        return totalScore;
    }

    private void clearValuesOfFrames() {
        List<Frame> frames = bowlingGame.getFrames();
        Iterator<Frame> iterator = frames.iterator();
        while (iterator.hasNext()) {
            Frame frame = iterator.next();
            frame.setScoreOfFirstThrow(0);
            frame.setScoreOfSecondThrow(0);
            frame.setNumberOfThrow(1);
        }
        bowlingGame.setCurrentFrame(bowlingGame.getFrames().get(0));
    }
}
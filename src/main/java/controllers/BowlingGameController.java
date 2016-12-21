package controllers;

import bean.BowlingGame;
import bean.Frame;
import service.FrameService;
import service.FrameTenthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BowlingGameController", urlPatterns = {"/bowlingGame"})
public class BowlingGameController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("newGame", false);
        req.getRequestDispatcher("/WEB-INF/JSP/bowlingGame.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String countOfPinsKnockedDown = req.getParameter("chooser");
        String newGame = req.getParameter("newGame");

        req.setAttribute("newGame", true);
        req.setAttribute("endNewGame", false);
        req.setAttribute("maxCountPins", 10);

        if (Boolean.parseBoolean(newGame)) {
            setParametersOfNewGame(req, resp, BowlingGame.getInstance());
        } else {
            setParametersOfCurrentGame(req, resp, countOfPinsKnockedDown);
        }
    }

    public void setParametersOfNewGame(HttpServletRequest req, HttpServletResponse resp, BowlingGame bowlingGame) throws ServletException, IOException {

        bowlingGame.setCurrentFrame(null);
        bowlingGame.setFrames(null);
        BowlingGame.setInstance(null);
        getRequestDispatcher(req, resp);
    }

    public void setParametersOfCurrentGame(HttpServletRequest req, HttpServletResponse resp, String countOfPinsKnockedDown) throws ServletException, IOException {

        BowlingGame bowlingGame = BowlingGame.getInstance();

        FrameService frameService = new FrameTenthService();
        bowlingGame.getCurrentFrame().setScore(Integer.valueOf(countOfPinsKnockedDown));
        frameService.updateCurrentFrame();

        Frame currentFrame = bowlingGame.getCurrentFrame();

        req.setAttribute("maxCountPins", currentFrame.getNumberOfRemainingPins());
        req.setAttribute("frames", bowlingGame.getFrames());
        req.setAttribute("frameService", frameService);
        req.setAttribute("endNewGame", currentFrame.getNumberOfThrow() == -1);
        getRequestDispatcher(req, resp);
    }

    public void getRequestDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/bowlingGame.jsp").forward(req, resp);
    }
}

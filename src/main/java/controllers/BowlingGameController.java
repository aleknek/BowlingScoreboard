package controllers;

import bean.BowlingGame;
import bean.Frame;
import dao.BowlingGameDAO;
import dao.FrameDAO;
import dao.FrameTenthDAO;

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
        BowlingGame bowlingGame = BowlingGame.getInstance();

        if (Boolean.parseBoolean(newGame)) {

            bowlingGame.setCurrentFrame(null);
            bowlingGame.setFrames(null);
            BowlingGame.setInstance(null);

            req.setAttribute("bowlingGame", BowlingGame.getInstance());
            req.getRequestDispatcher("/WEB-INF/JSP/bowlingGame.jsp").forward(req, resp);

        } else {

            FrameDAO frameDAO = new FrameTenthDAO();
            bowlingGame.getCurrentFrame().setScore(Integer.valueOf(countOfPinsKnockedDown));
            new BowlingGameDAO().updateCurrentFrame();

            Frame currentFrame = bowlingGame.getCurrentFrame();

            req.setAttribute("maxCountPins", currentFrame.getNumberOfRemainingPins());
            req.setAttribute("bowlingGame", bowlingGame);
            req.setAttribute("frames", bowlingGame.getFrames());
            req.setAttribute("frameDAO", frameDAO);
            req.setAttribute("endNewGame", currentFrame.getNumberOfThrow() == -1);

            req.getRequestDispatcher("/WEB-INF/JSP/bowlingGame.jsp").forward(req, resp);
        }
    }
}

package net.psv73.websait.controller;

import net.psv73.websait.dao.ArticleDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/mcon/deleteArticle"})
public class DeleteArticle extends HttpServlet {

    public DeleteArticle() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String error;
        String id = req.getParameter("id");

        error = ArticleDAO.deleteArticle(id);

        if (error != null) {
            req.setAttribute("error", error);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/mcon/articlesList.jsp");

            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/mcon");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

package net.psv73.websait.controller;

import net.psv73.websait.Languages;
import net.psv73.websait.dao.ArticleDAO;
import net.psv73.websait.model.Article;
import net.psv73.websait.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/mcon","/mcon/articlesList","/mcon/"})
public class articlesListServlet extends HttpServlet {

    public articlesListServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean requeredData = true;
        SimpleDateFormat sdf = new SimpleDateFormat(Utils.DATEFORMAT);
//        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

        if (req.getSession().getAttribute("dateStart") == null) {
            Date dateStart = Utils.getDefaultDateStart();
//            req.getSession().setAttribute("dateStart", sdf.format(dateStart));
            req.getSession().setAttribute("dateStart", dateStart);
            requeredData = false;
        }

        if (req.getSession().getAttribute("dateEnd") == null) {
            Date dateEnd = new Date();
//            req.getSession().setAttribute("dateEnd", sdf.format(dateEnd));
            req.getSession().setAttribute("dateEnd", dateEnd);
            requeredData = false;
        }

        if (req.getSession().getAttribute("lang") == null) {
            Languages lang  = Languages.SK;
            req.getSession().setAttribute("lang", lang);
            requeredData = false;
        }

        if (requeredData) {
            List<Article> articles = ArticleDAO.getArticles(req.getSession());
            req.getSession().setAttribute("articles", articles);
        }

        String aaa = Utils.getDatePattern();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/mcon/articlesList.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String dateStartStr = req.getParameter("dateStart");
        String dateEndStr = req.getParameter("dateEnd");
        String lang = req.getParameter("lang");

        Date dateStart = Utils.getDefaultDateStart();
        Date dateEnd = new Date();
        Languages language = Languages.SK;

        SimpleDateFormat sdf = new SimpleDateFormat(Utils.DATEFORMAT);

        try {
            dateEnd = sdf.parse(dateEndStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dateStart = sdf.parse(dateStartStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            language = Languages.valueOf(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        req.getSession().setAttribute("dateStart", sdf.format(dateStart));
//        req.getSession().setAttribute("dateEnd", sdf.format(dateEnd));
        req.getSession().setAttribute("dateStart", dateStart);
        req.getSession().setAttribute("dateEnd", dateEnd);
        req.getSession().setAttribute("lang", language);

        List<Article> articles = ArticleDAO.getArticles(req.getSession());
        req.getSession().setAttribute("articles", articles);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/mcon/articlesList.jsp");

        dispatcher.forward(req,resp);
    }
}
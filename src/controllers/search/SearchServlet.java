package controllers.search;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import models.Search;
import models.validators.SearchValidator;
import utils.DBUtil;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         EntityManager em = DBUtil.createEntityManager();

         Search s = new Search();
         s.setSearch(request.getParameter("search"));

         String re = request.getParameter("search");

         int page;
         try{
             page = Integer.parseInt(request.getParameter("page"));
         } catch(Exception e) {
             page = 1;
         }

         List<String> errors = SearchValidator.validate(s);
         if(errors.size() > 0) {
             em.close();

             request.setAttribute("_token", request.getSession().getId());
             request.setAttribute("errors", errors);

             RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/search/search.jsp");
             rd.forward(request, response);

         }else {


             List<Report> reports = em.createQuery("SELECT r FROM Report r WHERE r.employee in (SELECT e.id FROM Employee e WHERE e.name LIKE :keyword) or content like :content or title like :title").setParameter("keyword", "%" + re + "%").setParameter("content", "%" + re + "%").setParameter("title","%" + re + "%").getResultList();

             long reports_count = reports.size();

             request.setAttribute("reports",  reports);
             request.setAttribute("reports_count",  reports_count);
             request.setAttribute("page",  page);


             request.getSession().setAttribute("flush", "検索が完了しました。");
             RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/search/result.jsp");
             rd.forward(request, response);
         }

    }



}

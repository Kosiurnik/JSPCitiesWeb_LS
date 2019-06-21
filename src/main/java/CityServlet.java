//TODO porządnie podrasować paginację (przejście po podaniu numeru strony) + testy

import entity.City;
import org.apache.tomcat.util.buf.StringUtils;
import utils.Paginator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="CityServlet", urlPatterns = {"/city/one","/city/all","/city/find"})
public class CityServlet extends HttpServlet {

    private List<City> cities;
    private Paginator paginator;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        cities = Cities.loadCities(context.getResourceAsStream(Cities.getPathFile()));
        paginator = new Paginator(cities.size(),(int)Cities.MAX_LIMIT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.endsWith("one")){
            String idStr = req.getParameter("id");
            if(idStr!=null){
                findByID(req,resp);
            }else{
                req.setAttribute("cities",cities.subList(0,(int)Cities.MAX_LIMIT));
                req.getRequestDispatcher("/id_form.jsp").forward(req,resp);
            }
        }
        if(uri.endsWith("find")){
            String name = req.getParameter("name");
            if(name!=null){
                findListByName(req,resp);
            }else{
                req.setAttribute("cities",cities.subList(0,(int)Cities.MAX_LIMIT));
                req.getRequestDispatcher("/name_form.jsp").forward(req,resp);
            }
        }
        if(uri.endsWith("all")){
            String page = req.getParameter("page");
            if(isNumeric(page)){
                paginator.setCurrentPage(Integer.parseInt(page));
            }else if(page!=null){
                switch(page){
                    case "prev":
                        paginator.previousPage();
                        break;
                    case "next":
                        paginator.nextPage();
                        break;
                    case "first":
                        paginator.setCurrentPage(0);
                        break;
                    case "last":
                        paginator.setCurrentPage(paginator.getPagesCount()-1);
                        break;
                }
            }
            List<City> output = cities
                    .stream()
                    .skip(paginator.getStartIndex())
                    .limit(100).collect(Collectors.toList());
            req.setAttribute("cities",output);
            req.setAttribute("currentPage",paginator.getCurrentPage());
            req.setAttribute("lastPage",paginator.getPagesCount()-1);
            req.getRequestDispatcher("/cities.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.endsWith("one")){
            findByID(req,resp);
        }
        if(uri.endsWith("all")){
            findListByName(req,resp);
        }
    }

    private void findListByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        List<City> output = cities.stream()
                .filter(c -> c.getAsciiName().toLowerCase().contains(name.toLowerCase()))
                .limit(Cities.MAX_LIMIT)
                .collect(Collectors.toList());
        req.setAttribute("cities",output);
        req.getRequestDispatcher("/cities.jsp").forward(req, resp);

    }

    private void findByID(HttpServletRequest req, HttpServletResponse resp) {
        String idStr = req.getParameter("id");
        long id = Long.parseLong(idStr);
        cities.stream()
                .filter(c -> c.getId()==id)
                .limit(Cities.MAX_LIMIT)
                .findAny()
                .ifPresent(c -> {
                    req.setAttribute("city",c);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/city.jsp");
                    try {
                        dispatcher.forward(req, resp);
                    } catch (ServletException | IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

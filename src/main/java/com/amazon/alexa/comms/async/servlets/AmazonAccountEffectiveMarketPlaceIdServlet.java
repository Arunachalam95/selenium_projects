package com.amazon.alexa.comms.async.servlets;

import com.amazon.alexa.comms.async.scripts.AmazonAccountEffectiveMarketPlaceId;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.IOException;


/**
 * Servlet implementation class AmazonAccountEffectiveMarketPlaceIdServlet
 */
@Log
public class AmazonAccountEffectiveMarketPlaceIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmazonAccountEffectiveMarketPlaceIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.info(String.format("Parameters {email - %1$s and password - %2$s,} from the JSP - AmazonAccountEffectiveMarketPlaceId", email, password));

        AmazonAccountEffectiveMarketPlaceId amazonAccountEffectiveMarketPlaceId = new AmazonAccountEffectiveMarketPlaceId();
        String result = amazonAccountEffectiveMarketPlaceId.getPreferredMarketplace(email, password);
        log.info(String.format("Result for the method getPreferredMarketplace - %s", result));
        request.setAttribute("result", result);
        request.getRequestDispatcher("AmazonAccountEffectiveMarketPlaceId.jsp").forward(request, response);
    }
}

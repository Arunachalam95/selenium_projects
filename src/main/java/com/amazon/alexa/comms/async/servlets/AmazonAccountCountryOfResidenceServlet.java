package com.amazon.alexa.comms.async.servlets;

import com.amazon.alexa.comms.async.scripts.AmazonAccountCountryOfResidence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.IOException;


/**
 * Servlet implementation class CountryofresidenceServlet
 */
@Log
public class AmazonAccountCountryOfResidenceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmazonAccountCountryOfResidenceServlet() {
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
        String preferredMarketPlace = request.getParameter("preferredMarketPlace");
        String countryOfResidence = request.getParameter("countryOfResidence");
        log.info(String.format("Parameters {email - %1$s, password - %2$s, preferredMarketPlace - %3$s and countryOfResidence - %4$s} from the JSP - AmazonAccountCountryOfResidence", email, password, preferredMarketPlace, countryOfResidence));

        AmazonAccountCountryOfResidence amazonAccountCountryOfResidence = new AmazonAccountCountryOfResidence();
        String result = amazonAccountCountryOfResidence.setCountryOfResidence(email, password, preferredMarketPlace, countryOfResidence);
        log.info(String.format("Result for the method getPreferredMarketplace - %s", result));
        request.setAttribute("result", result);
//        amazonAccountCountryOfResidence.signOutAmazonWebsite();
        request.getRequestDispatcher("AmazonAccountCountryOfResidence.jsp").forward(request, response);

    }

}


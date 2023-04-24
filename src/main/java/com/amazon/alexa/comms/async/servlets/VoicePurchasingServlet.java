package com.amazon.alexa.comms.async.servlets;

import com.amazon.alexa.comms.async.scripts.VoicePurchasing;
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
public class VoicePurchasingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoicePurchasingServlet() {
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
        String toggleType = request.getParameter("VPToggle");
        log.info(String.format("Parameters {email - %1$s, password - %2$s, toggleType - %3$s} from the JSP - VoicePurchasing", email, password, toggleType));

        VoicePurchasing voicePurchasing = new VoicePurchasing();
        String result = voicePurchasing.voicePurchasingToggle(email, password, toggleType);
        log.info(String.format("Result for the method getPreferredMarketplace - %s", result));
        request.setAttribute("result", result);
//        amazonAccountCountryOfResidence.signOutAmazonWebsite();
        request.getRequestDispatcher("VoicePurchasing.jsp").forward(request, response);

    }

}


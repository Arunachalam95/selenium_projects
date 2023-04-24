package com.amazon.alexa.comms.async.servlets;

import com.amazon.alexa.comms.async.scripts.AmazonAccountEffectiveMarketPlaceId;
import com.amazon.alexa.comms.async.scripts.AmazonAccountHouseHold;
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
public class AmazonAccountHouseHoldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmazonAccountHouseHoldServlet() {
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

        String userOneEmail = request.getParameter("userOneEmail");
        String userOnePassword = request.getParameter("userOnePassword");
        String userTwoEmail = request.getParameter("userTwoEmail");
        String userTwoPassword = request.getParameter("userTwoPassword");
        log.info(String.format("Parameters {userOneEmail - %1$s, userOnePassword - %2$s,userTwoEmail - %3$s, " +
                "userTwoPassword - %4$s,} from the JSP - AmazonAccountHouseHold", userOneEmail, userOnePassword, userTwoEmail, userTwoPassword));

        AmazonAccountHouseHold amazonAccountHouseHold = new AmazonAccountHouseHold();
        String result = amazonAccountHouseHold.addAmazonAccountsAsHouseHold(userOneEmail, userOnePassword, userTwoEmail, userTwoPassword);
        log.info(String.format("Result for the method addAmazonAccountsAsHouseHold - %s", result));
        request.setAttribute("result", result);
        request.getRequestDispatcher("AmazonAccountHouseHold.jsp").forward(request, response);
    }
}

package com.amazon.alexa.comms.async.servlets;

import com.amazon.alexa.comms.async.scripts.AccountCreation;
import com.amazon.alexa.comms.async.scripts.VoicePurchasing;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.IOException;


/**
 * Servlet implementation class CountryofresidenceServlet
 */
@Log
public class AccountCreationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreationServlet() {
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

        HttpSession accountCreationSession = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int startSeq = Integer.parseInt(request.getParameter("start"));
        int endSeq = Integer.parseInt(request.getParameter("end"));
        String preferredMarketPlace = request.getParameter("preferredMarketPlace");
        log.info(String.format("Parameters {email - %1$s, password - %2$s, startSeq - %3$s, endSeq - %4$s and preferredMarketPlace - %5$s} from the JSP - " +
                "TestAccountCreation", email, password, startSeq, endSeq, preferredMarketPlace));

        AccountCreation accountCreation = new AccountCreation();
        String result = accountCreation.createTestAccounts(email, password, startSeq, endSeq, preferredMarketPlace);
        log.info(String.format("Result for the method getPreferredMarketplace - %s", result));
        String[] accounts = result.split("\\.", 2);
        System.out.println(accounts[0]);
        System.out.println(accounts[1]);
        request.setAttribute("result", accounts[0]);
        accountCreationSession.setAttribute("filename", accounts[1]);
        request.getRequestDispatcher("TestAccountCreation.jsp").forward(request, response);

    }

}
package com.amazon.alexa.comms.async.servlets;

import com.amazon.alexa.comms.async.scripts.DownloadAlexaAPKAndSignInCodeSigner;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;


/**
 * Servlet implementation class CountryofresidenceServlet
 */
@Log
public class DownloadAlexaAPKAndSignInCodeSignerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadAlexaAPKAndSignInCodeSignerServlet() {
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

        String buildNumber = request.getParameter("buildNumber");
        String branchName = request.getParameter("branchName");
        String EnvironmentAndVersion = request.getParameter("EnvironmentAndVersion_Dropdown");
        String fireOSApkType = request.getParameter("fireOSApkType_Dropdown");

        log.info(String.format("Parameters {buildNumber - %1$s, branchName - %2$s, EnvironmentAndVersion - %3$s and fireOSApkType - %4$s} from the JSP - DownloadAlexaAPKAndSignInCodeSigner", buildNumber, branchName, EnvironmentAndVersion, fireOSApkType));

        DownloadAlexaAPKAndSignInCodeSigner downloadAlexaAPKAndSignInCodeSigner = new DownloadAlexaAPKAndSignInCodeSigner();
        List<String> result = downloadAlexaAPKAndSignInCodeSigner.downloadAndSignApk(buildNumber, branchName, EnvironmentAndVersion, fireOSApkType);
        log.info(String.format("Result for the method downloadAndSignApk - ", result));
        request.setAttribute("result", result);
        request.getRequestDispatcher("DownloadAlexaAPKAndSignInCodeSigner.jsp").forward(request, response);
    }
}


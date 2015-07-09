/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.music;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openrdf.OpenRDFException;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

/**
 *
 * @author edris
 */
@WebServlet(name = "MusicServlet", urlPatterns = {"/queryCatalog"})
public class MusicServlet extends HttpServlet {

    private final String sesameServerURl = "http://localhost:8080/openrdf-sesame/";
    private final String repositoryID = "ties452";

    /**
     * Handles the HTTP Get
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the query posted by the client
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String queryString = request.getParameter("queryString");
        String returnTable = null;
        String error = null;

        SesameClient rdfClient = new SesameClient(sesameServerURl, repositoryID);
        try {
            rdfClient.Connect();
            TupleQueryResult result = rdfClient.executeQuery(queryString);
            List<String> bindingNames = result.getBindingNames();

            returnTable = "<table border=1>";
            returnTable = returnTable + "<tr>";
            for (int i = 0; i < bindingNames.size(); i++) {
                returnTable = returnTable + "<td>" + bindingNames.get(i) + "</td>";
            }
            returnTable = returnTable + "</tr>";
            int j=0;
            while (result.hasNext()) {
                BindingSet bindingSet = result.next();
                returnTable = returnTable + "<tr>";
                for (int i = 0; i < bindingNames.size(); i++) {
                    if (bindingSet.getValue(bindingNames.get(i)) == null) {
                        returnTable = returnTable + "<td>  </td>";
                    } else {
                        String name= bindingNames.get(i);
                        String bindValue = bindingSet.getValue(name).stringValue();
                        returnTable = returnTable + "<td>" + bindValue + "</td>";
                    }
                }
                returnTable = returnTable + "</tr>";
                j++;
            }
            result.close();
            returnTable = returnTable +j+"Results found</table>";
        } catch (OpenRDFException ex) {
            Logger.getLogger(MusicServlet.class.getName()).log(Level.SEVERE, null, ex);
            error = ex.getLocalizedMessage();
        } 
        queryString = queryString.substring(queryString.lastIndexOf("#>") + 2);

        request.setAttribute("queryString", queryString);
        request.setAttribute("result", returnTable);
        request.setAttribute("error", error);


        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}

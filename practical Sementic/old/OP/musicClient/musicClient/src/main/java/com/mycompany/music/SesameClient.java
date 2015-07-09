/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.music;

import org.openrdf.OpenRDFException;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.Query;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

/**
 *
 * @author Edris
 */
public class SesameClient {

    private String sesameServer;
    private String repositoryID;
    private RepositoryConnection con = null;

    SesameClient(String sesameServer, String repositoryID) {
        this.sesameServer = sesameServer;
        this.repositoryID = repositoryID;
    }

    public void Connect() throws RepositoryException {

        Repository repo = new HTTPRepository(sesameServer, repositoryID);
        repo.initialize();
        this.con = repo.getConnection();

    }

    public TupleQueryResult executeQuery(String queryText) throws OpenRDFException {

        TupleQuery query = con.prepareTupleQuery(QueryLanguage.SPARQL, queryText);
        Query q = con.prepareQuery(QueryLanguage.SPARQL, queryText);
        if (q instanceof TupleQuery) {

             //return query.evaluate();

        } else if (q instanceof GraphQuery) {
            GraphQueryResult graphResult;

            // etc for other query types
            throw new RuntimeException("Graph not suppoeted yet ");
        }
           return query.evaluate();

    }

    public void close() throws RepositoryException {
        this.con.close();
    }
}

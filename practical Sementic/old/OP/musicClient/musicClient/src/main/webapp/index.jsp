<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="">
      
        <h1>&#9835; &#9835; Music catalog Repository &#9834; &#9834;</h1>
        <hr/>
        <form method="post" action="queryCatalog">
            <table border="0" cellpadding="2">
                <tbody>
                    <tr>
                        <td> 
                            <h4>Query:</h4>
                        </td>
                        <td>
                            <textarea  name="queryString" rows="16" cols="80">
PREFIX m:<http://users.jyu.fi/~edmuaman/practical_web/musicOntology.owl#>
PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>
PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>${requestScope.queryString}
                            </textarea>
                            <input type="submit"/> 
                        </td>
                    </tr>
                    <tr>
                        <td><h4>Results:</h4> </td>
                        <td>
                            <b style="color:red">${requestScope.error}</b>${requestScope.result}
                        </td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>

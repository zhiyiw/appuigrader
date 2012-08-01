<!--for administrator to upload rubric, query for grades/activities, modify students and other data-->
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<!DOCTYPE html>

<f:view>
    <html>
        <head>
        </head>
        <body>
        <h:form>
			<h:outputLabel value="Username or Password is NOT correct!! "></h:outputLabel>
			<br />
   	        <h:commandButton value="Back to Login" action="login.jsp" />
    	</h:form>
        </body>
    </html>
</f:view>
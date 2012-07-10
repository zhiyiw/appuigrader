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
			<h:outputLabel value="Year "></h:outputLabel>
        	<h:selectOneMenu value="#{menuoption.selectYear}">
   				<f:selectItems value="#{menuoption.getYearList()}" />
   			</h:selectOneMenu>
    	    
    	   	<h:outputLabel value="Term "></h:outputLabel>
        	<h:selectOneMenu value="#{menuoption.selectTerm}">
   				<f:selectItems value="#{menuoption.getTermList()}" />
   			</h:selectOneMenu>
    	    
    	    <br /><br />
    	    
    	    <h:commandButton value="Submit" action="assignmentsDisplay.jsp" />
	        <h:commandButton value="Reset" type="reset" />
    	</h:form>
        </body>
    </html>
</f:view>
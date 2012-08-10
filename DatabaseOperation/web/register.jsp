<!--For users to login to the system-->
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<!DOCTYPE html>

<f:view>
    <html>
        <head>
        </head>
        <body>
		<h:form id="uploadRubric" enctype="multipart/form-data">
			<h:outputLabel value="Year "></h:outputLabel>
        	<h:selectOneMenu value="#{menuoption.selectYear}">
   				<f:selectItems value="#{menuoption.getYearList()}" />
   			</h:selectOneMenu>
    	    
    	   	<h:outputLabel value="Term "></h:outputLabel>
        	<h:selectOneMenu value="#{menuoption.selectTerm}">
   				<f:selectItems value="#{menuoption.getTermList()}" />
   			</h:selectOneMenu>
    	    
    	    <br /><br />
		
            <h:outputLabel value="User Name"></h:outputLabel>
        	<h:inputText id="username" value="#{register.username}" required="true" />
        	<h:message for="username" />
        	<br />
        	<h:outputLabel value="Password"></h:outputLabel>
        	<h:inputText id="password" value="#{register.password}" required="true" />
        	<br />
        	<h:commandButton value="Register" action="#{register.addUser(menuoption.selectYear, menuoption.selectTerm)}" />
        	<h:messages globalOnly="true" />
        </h:form>	
        </body>
    </html>
</f:view>
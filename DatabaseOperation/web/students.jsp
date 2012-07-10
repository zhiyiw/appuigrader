<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
<html>
    <head>
    </head>
 
    <body>
 
    	<h1>JSF 2.0 + JDBC Example</h1>
 		<h:dataTable value="#{student.getStudentList()}" var="s">
    		<h:column>
    			<f:facet name="header">
    				<h:outputText value="StudentID"/>
    			</f:facet>
				<h:outputText value="#{s.studentID}"/>
    		</h:column>

    		<h:column>
    			<f:facet name="header">
    				<h:outputText value="Last Name"/>
				</f:facet>
				<h:outputText value="#{s.lname}"/>
    		</h:column> 
    	</h:dataTable>
 
    </body>
 
</html>
</f:view>
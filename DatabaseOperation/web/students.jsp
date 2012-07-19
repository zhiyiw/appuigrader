<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<html>
    <head>
    </head>
 
    <body>
 <f:view>
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
    	
    	<h:form>
            <h:commandButton value="Compare" action="print.jsp" />      
    </h:form>
    
 </f:view>
    </body>
 
</html>

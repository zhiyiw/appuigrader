<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>



	<h1>
	<h:outputLabel value="#{menuoption.selectYear}">
	</h:outputLabel>
	<h:outputLabel value="#{menuoption.selectTerm}">
	</h:outputLabel>
	</h1>
 	<h:dataTable value="#{assignment.getAssignmentList(menuoption.selectYear,menuoption.selectTerm)}" var="a">
    	 	<h:column>
    			<f:facet name="header">
    				<h:outputText value="Assignment"/>
    			</f:facet>
				<h:outputText value="#{a.assignmentName}"/>
    		</h:column>

    		<h:column>
    			<f:facet name="header">
    				<h:outputText value="Path"/>
				</f:facet>
				<h:outputText value="#{a.assignmentDirectory}"/>
    		</h:column> 
    	</h:dataTable>
</f:view>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

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
    		
    		<h:column>
    			<f:facet name="header">
    				<h:outputText value="PathModify"/>
				</f:facet>
				<h:outputLink value=""><h:outputText value="Edit"/></h:outputLink>
    		</h:column> 
    </h:dataTable>
    	<h:form id="uploadRubric" enctype="multipart/form-data">
            <h:panelGrid columns="3" border="1">
                    <h:outputLabel for="file" value="add new assignment" />
                    <t:inputFileUpload id="file" value="#{upload.uploadedFile}" required="true" />
                    <h:message for="file" style="color: red;" />
                    <h:panelGroup />
                    <h:commandButton value="Submit" action="#{upload.submit(menuoption.selectYear,menuoption.selectTerm)}" />
                    <h:message for="uploadForm" infoStyle="color: green;" errorStyle="color: red;" />
            </h:panelGrid>
        </h:form>
</f:view>
</body>
</html>
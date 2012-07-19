<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>


<f:view>
	<html>
<head>
</head>

<body>
    <h:outputText value="Test Result: " />
    <br />
	<h:outputText value="#{compare.compareFiles()}" />
	
    
</body>

	</html>
</f:view>
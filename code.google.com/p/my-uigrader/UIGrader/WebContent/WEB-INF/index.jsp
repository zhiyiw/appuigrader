<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html lang="en">
        <head>
            <title>File upload test</title>
        </head>
        <body>
            <h:form id="uploadForm" enctype="multipart/form-data">
                <h:panelGrid columns="3" border="1">
                    <h:outputLabel for="file" value="Select file from" />
                    <t:inputFileUpload id="file" value="#{bean.uploadedFile}" required="true" />
                    <h:message for="file" style="color: red;" />
                    <h:panelGroup />
                    <h:commandButton value="Submit" action="#{bean.submit}" />
                    <h:message for="uploadForm" infoStyle="color: green;" errorStyle="color: red;" />
                </h:panelGrid>
            </h:form>

            <h:outputLink value="#{bean.fileName}" rendered="#{bean.fileName != null}">
                Download back
            </h:outputLink>
        </body>
    </html>
</f:view>
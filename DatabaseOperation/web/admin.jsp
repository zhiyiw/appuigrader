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
		<h:form id="uploadRubric" enctype="multipart/form-data">
            <h:panelGrid columns="3" border="1">
                    <h:outputLabel for="file" value="Select file from" />
                    <t:inputFileUpload id="file" value="#{bean.uploadedFile}" required="true" />
                    <h:message for="file" style="color: red;" />
                    <h:panelGroup />
                    <h:commandButton value="Submit" action="#{bean.submit}" />
                    <h:message for="uploadForm" infoStyle="color: green;" errorStyle="color: red;" />
            </h:panelGrid>
        </h:form>
		<h:outputText value="#{assignment.addAssignment(bean.getFileName(), bean.getDirectory())}"/>		
        </body>
    </html>
</f:view>
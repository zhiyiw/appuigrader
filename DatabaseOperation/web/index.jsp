<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<!DOCTYPE html>

<f:view>
    <html lang="en">
        <head>
            <title>Navi Rule Case test</title>
        </head>
        <body>
            <%-- <h:form id="uploadForm" enctype="multipart/form-data">
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
            </h:outputLink> --%>
            <h:form>
            <h:commandButton value="naviTest" action="${navi.respons}" />
        	</h:form>
        </body>
    </html>
</f:view>
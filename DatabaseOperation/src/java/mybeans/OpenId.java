package mybeans;


import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.*;
import org.openid4java.message.*;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
 
/**
 * Authenticate the user against a openid provider
 * @author tdmarti
 */
@ManagedBean(name="openid")
@SessionScoped
public class OpenId implements java.io.Serializable {
 
    /** Creates a new instance of OpenId */
    public OpenId() {
    }
 
    private String userSuppliedId; //Users OpenID URL
    private String validatedId;
    private String openIdEmail;
    /* ... */
    private String onLoad;
    private ConsumerManager manager;
    private DiscoveryInformation discovered;
 
    public void login() throws IOException {
        manager = new ConsumerManager();
        validatedId = null;
        String returnToUrl = returnToUrl("/openid.xhtml");
        String url = authRequest(returnToUrl);
 
        if (url != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }
 
    /**
     * Create the current url and add another url path fragment on it.
     * Obtain from the current context the url and add another url path fragment at
     * the end
     * @param urlExtension f.e. /nextside.xhtml
     * @return the hole url including the new fragment
     */
    private String returnToUrl(String urlExtension) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String returnToUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                + context.getApplication().getViewHandler().getActionURL(context, urlExtension);
        return returnToUrl;
    }
 
    /**
     * Create an authentication request.
     * It performs a discovery on the user-supplied identifier. Attempt it to 
     * associate with the OpenID provider and retrieve one service endpoint 
     * for authentication. It adds some attributes for exchange on the AuthRequest.
     * A List of all possible attributes can be found on @see http://www.axschema.org/types/
     * @param returnToUrl
     * @return the URL where the message should be sent
     * @throws IOException
     */
    private String authRequest(String returnToUrl) throws IOException {
        try {
            List discoveries = manager.discover(userSuppliedId);
            discovered = manager.associate(discoveries);
            AuthRequest authReq = manager.authenticate(discovered, returnToUrl);
 
            FetchRequest fetch = FetchRequest.createFetchRequest();
//            fetch.addAttribute("email",
//                "http://schema.openid.net/contact/email", true);
            /* Some other attributes ... */
            
            if (userSuppliedId.contains("myopenid")){
            	  fetch.addAttribute("email", "http://schema.openid.net/contact/email", true);
            	  fetch.addAttribute("fullname", "http://schema.openid.net/namePerson", true);
            	/* ... */
            	} else {
            	  fetch.addAttribute("email", "http://axschema.org/contact/email", true);
            	  fetch.addAttribute("fullname", "http://axschema.org/namePerson", true);
            	/* ... */
            	}
 
            authReq.addExtension(fetch);
            return authReq.getDestinationUrl(true);
        } catch (OpenIDException e) {
            // TODO
        }
        return null;
    }
 
    public void verify() {
        ExternalContext context = javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        validatedId = verifyResponse(request);
    }
 
    /**
     * Set the class members with date from the authentication response.
     * Extract the parameters from the authentication response (which comes
     * in as a HTTP request from the OpenID provider). Verify the response,
     * examine the verification result and extract the verified identifier.
     * @param httpReq httpRequest
     * @return users identifier.
     */
    private String verifyResponse(HttpServletRequest httpReq) {
        try {
            ParameterList response =
                    new ParameterList(httpReq.getParameterMap());
 
            StringBuffer receivingURL = httpReq.getRequestURL();
            String queryString = httpReq.getQueryString();
            if (queryString != null && queryString.length() >0) {
                receivingURL.append("?").append(httpReq.getQueryString());
            }
 
            VerificationResult verification = manager.verify(
                    receivingURL.toString(),
                    response, discovered);
 
            Identifier verified = verification.getVerifiedId();
            if (verified != null) {
                AuthSuccess authSuccess =
                        (AuthSuccess) verification.getAuthResponse();
 
                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
                    FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);
 
                    List emails = fetchResp.getAttributeValues("email");
                    openIdEmail = (String) emails.get(0);
                     /* Some other attributes ... */
                }
                return verified.getIdentifier();
            }
        } catch (OpenIDException e) {
            // TODO
        }
        return null;
    }
 
    /**
     * hidden member for onLoad/Init event. 
     *@return always return the string pageLoaded
     */
    public String getOnLoad() {
        verify();
        return "pageLoaded";
    }
 
    /**
     * Getter and Setter Method
     */
    public String getUserSuppliedId() {
        return userSuppliedId;
    }
    public void setUserSuppliedId(String userSuppliedId) {
        this.userSuppliedId = userSuppliedId;
    }
    public String getValidatedId() {
        return validatedId;
    }
    public String getOpenIdEmail() {
        return openIdEmail;
    }
/* Other getters ... */
}
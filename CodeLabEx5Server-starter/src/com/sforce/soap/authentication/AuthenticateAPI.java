package com.sforce.soap.authentication;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.google.appengine.codelab.soap.server.EntityAPI;

@WebService
public class AuthenticateAPI {

	private static final Logger logger = Logger.getLogger(AuthenticateAPI.class.getCanonicalName());
	
    /**
     * Are the supplied saleforce.com userId and password valid ?
     * 
     * @param parameters
     * @return
     *     returns com.sforce.soap.authentication.AuthenticateResult
     */
    @WebMethod
    public boolean authenticate(String username, String password, String ip) {
    	logger.log(Level.SEVERE, "username ::" + username);
    	logger.log(Level.SEVERE, "password ::" + password);
    	logger.log(Level.SEVERE, "ip ::" + ip);
    	
    	if (password.length() > 8) return true;
    	else return false;
    }
}

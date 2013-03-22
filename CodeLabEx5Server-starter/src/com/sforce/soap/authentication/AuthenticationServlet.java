package com.sforce.soap.authentication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

@SuppressWarnings("serial")
public class AuthenticationServlet extends HttpServlet {

	static MessageFactory messageFactory;
	static AuthenticateAdapter authAdap;
	private static final Logger logger = Logger.getLogger(AuthenticationServlet.class.getCanonicalName());

	static {
		try {
			messageFactory = MessageFactory.newInstance();
			authAdap = new AuthenticateAdapter();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			logger.log(Level.SEVERE, "HttpServletRequest :: "+req);
			// Get all the headers from the HTTP request
			MimeHeaders headers = getHeaders(req);

			// Construct a SOAPMessage from the XML in the request body
			InputStream is = req.getInputStream();
			logger.log(Level.SEVERE, "InputStream :: "+is);
			SOAPMessage soapRequest = messageFactory.createMessage(headers, is);

			// Handle soapReqest
			SOAPMessage soapResponse = authAdap.handleAuthenticationRequest(soapRequest);
			logger.log(Level.SEVERE, "soapResponse :: "+soapResponse);

			// Write to HttpServeltResponse
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf-8\"");
			OutputStream os = resp.getOutputStream();
			soapResponse.writeTo(os);
			os.flush();
		} catch (SOAPException e) {
			throw new IOException("Exception while creating SOAP message.", e);
		}

	}

	@SuppressWarnings("rawtypes")
	static MimeHeaders getHeaders(HttpServletRequest req) {
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			logger.log(Level.SEVERE, "headerName :: "+headerName);
			logger.log(Level.SEVERE, "headerValue :: "+headerValue);
			StringTokenizer values = new StringTokenizer(headerValue, ",");
			while (values.hasMoreTokens()) {
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		return headers;
	}
}

package com.sforce.soap.outboundmessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@SuppressWarnings("serial")
public class OutboundMessageServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(OutboundMessageServlet.class.getCanonicalName());
	static MessageFactory messageFactory;
	private static final String NAMESPACE_URI = "http://soap.sforce.com/2005/09/outbound";
	private static final QName NOTIFICATIONS = new QName(NAMESPACE_URI, "notifications");
	
	static {
		try {
			messageFactory = MessageFactory.newInstance();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.getWriter().print("Please use the Post method. Get method is not supported");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		logger.log(Level.SEVERE, "Inside OutboundMessageServlet doPost method");
		MimeHeaders requestMimeHeaders = getHeaders(req);
		InputStream requestInputStream = req.getInputStream();
		try {
			logger.log(Level.SEVERE, "Inside OutboundMessageServlet doPost method :: Creating Message");
			SOAPMessage requestSoapMessage = messageFactory.createMessage(requestMimeHeaders, requestInputStream);
			
			// Handle soapReqest
			logger.log(Level.SEVERE, "Inside OutboundMessageServlet doPost method :: handling Outbound Message Request");
			SOAPMessage responseSoapMessage = handleOutboundMessageRequest(requestSoapMessage);
			logger.log(Level.SEVERE, "Inside OutboundMessageServlet doPost method :: soapResponse :: "+responseSoapMessage);

			// Write to HttpServeltResponse
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf-8\"");
			OutputStream os = resp.getOutputStream();
			responseSoapMessage.writeTo(os);
			os.flush();
			
		} catch (SOAPException e) {
			e.printStackTrace();
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
	
	private SOAPMessage handleOutboundMessageRequest(SOAPMessage request) throws SOAPException {
		
		SOAPBody soapBody = request.getSOAPBody();
		Iterator iterator = soapBody.getChildElements();
		Object responsePojo = null;
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof SOAPElement) {
				SOAPElement soapElement = (SOAPElement) next;
				QName qname = soapElement.getElementQName();
				logger.log(Level.SEVERE, "Inside OutboundMessageServlet doPost method :::: qname.getPrefix :: " + qname.getPrefix() + " :::: qname.getNamespaceURI :: " + qname.getNamespaceURI() + " :::: qname.getLocalPart :: " + qname.getLocalPart());
				if(NOTIFICATIONS.equals(qname)) {
					responsePojo = handleOMRequest(soapElement);
				}
			}
		}
		
		SOAPMessage soapResponse = messageFactory.createMessage();
		soapBody = soapResponse.getSOAPBody();
		if (responsePojo != null) {
			JAXB.marshal(responsePojo, new SAAJResult(soapBody));
		}
		else {
			SOAPFault fault = soapBody.addFault();
			fault.setFaultString("Unrecognized SOAP request.");
		}
		
		try {
			System.out.print("soapBody ::::");
			printXml(soapBody);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return soapResponse;
	}
	
	
	private NotificationsResponse handleOMRequest(SOAPElement soapElement) {
		DOMSource dSrc = new DOMSource(soapElement);
		System.out.println("dSrc ::::" + dSrc.getNode().getTextContent());
		
		try {
			System.out.print("soapElement ::::");
			printXml(soapElement);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		Notifications notifications = JAXB.unmarshal(dSrc, Notifications.class);
		
		System.out.println("getActionId ::::" + notifications.getActionId());
		System.out.println("getEnterpriseUrl ::::" + notifications.getEnterpriseUrl());
		System.out.println("getOrganizationId ::::" + notifications.getOrganizationId());
		System.out.println("getPartnerUrl ::::" + notifications.getPartnerUrl());
		System.out.println("getSessionId ::::" + notifications.getSessionId());
		
		for (ContactNotification note : notifications.getNotification()) {
			try {
				System.out.println("Contact getId ::::" + note.getId());
				System.out.println("Contact accountId ::::" + note.getSObject().accountId.getValue());
				System.out.println("Contact birthdate ::::" + note.getSObject().birthdate.getValue());
				System.out.println("Contact email ::::" + note.getSObject().email.getValue());
				System.out.println("Contact firstName ::::" + note.getSObject().firstName.getValue());
				System.out.println("Contact lastName ::::" + note.getSObject().lastName.getValue());
				System.out.println("Contact mailingPostalCode ::::" + note.getSObject().mailingPostalCode.getValue());
				System.out.println("Contact masterRecordId ::::" + note.getSObject().masterRecordId.getValue());
				System.out.println("Contact ownerId ::::" + note.getSObject().ownerId.getValue());
				System.out.println("Contact title ::::" + note.getSObject().title.getValue());
			}
			catch (NullPointerException npe) {
				System.out.println("An NPE occurred while printing Contact details. Please check the Contact data.");
			}
		}
		return processNotification(notifications);
	}
	
	private void printXml(org.w3c.dom.Node node) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(node);
		transformer.transform(source, result);

		String xmlString = result.getWriter().toString();
		System.out.println(xmlString);
	}
	
	private NotificationsResponse processNotification(Notifications notifications) {
		/*String user = request.getUsername();
		String pwd = request.getPassword();
	  	String ip = request.getSourceIp();
	  	boolean status = aapi.authenticate(user, pwd, ip);*/
		
		boolean status = false;
		
		Calendar c = Calendar.getInstance();
		int minute = c.get(Calendar.MINUTE);
		if (minute < 20) {
			status = true;
		}
		
		status = true;
		
		NotificationsResponse response = new NotificationsResponse();
		response.setAck(status);
		return response;
	}
}

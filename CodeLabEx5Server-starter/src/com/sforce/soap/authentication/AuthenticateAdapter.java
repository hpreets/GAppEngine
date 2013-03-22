package com.sforce.soap.authentication;

import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
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


public class AuthenticateAdapter {

	AuthenticateAPI aapi = new AuthenticateAPI();
	private static final String NAMESPACE_URI = "urn:authentication.soap.sforce.com";
	private static final QName AUTHENTICATE = new QName(NAMESPACE_URI, "Authenticate");
	
	private MessageFactory messageFactory;
	
	public AuthenticateAdapter() throws SOAPException {
		messageFactory = MessageFactory.newInstance();
	}
	
	private AuthenticateResult createOrUpdateProduct(Authenticate request) {
		  String user = request.getUsername();
		  String pwd = request.getPassword();
		  String ip = request.getSourceIp();
		  boolean status = aapi.authenticate(user, pwd, ip);
		  AuthenticateResult response = new AuthenticateResult();
		  response.setAuthenticated(status);
		  return response;
	}
	
	
	private Object handleAuthRequest(SOAPElement soapElement) {
		
		DOMSource dSrc = new DOMSource(soapElement);
		System.out.println("dSrc ::::" + dSrc.getNode().getTextContent());
		
		try {
			System.out.print("soapElement ::::");
			printXml(soapElement);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		Authenticate auth = JAXB.unmarshal(dSrc, Authenticate.class);
		/*Authenticate auth = new Authenticate();
		auth.setPassword("12345");
		auth.setUsername("hs@astadia.com");
		auth.setSourceIp("2.3.2.3");
		*/
		
		System.out.println("getPassword ::::" + auth.getPassword());
		System.out.println("getSourceIp ::::" + auth.getSourceIp());
		System.out.println("getUsername ::::" + auth.getUsername());
		return createOrUpdateProduct(auth);
	}
	
	public void printXml(org.w3c.dom.Node node) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(node);
		transformer.transform(source, result);

		String xmlString = result.getWriter().toString();
		System.out.println(xmlString);
	}
	
	public SOAPMessage handleAuthenticationRequest(SOAPMessage request) throws SOAPException {
		
		SOAPBody soapBody = request.getSOAPBody();
		Iterator iterator = soapBody.getChildElements();
		Object responsePojo = null;
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof SOAPElement) {
				SOAPElement soapElement = (SOAPElement) next;
				QName qname = soapElement.getElementQName();
				if(AUTHENTICATE.equals(qname)) {
					responsePojo = handleAuthRequest(soapElement);
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
}

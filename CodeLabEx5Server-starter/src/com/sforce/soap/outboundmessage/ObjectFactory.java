
package com.sforce.soap.outboundmessage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sforce.soap.outboundmessage package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ContactMasterRecordId_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "MasterRecordId");
    private final static QName _ContactTitle_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Title");
    private final static QName _ContactLastName_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "LastName");
    private final static QName _ContactLevelC_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Level__c");
    private final static QName _ContactFirstName_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "FirstName");
    private final static QName _ContactBirthdate_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Birthdate");
    private final static QName _ContactMailingPostalCode_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "MailingPostalCode");
    private final static QName _ContactOwnerId_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "OwnerId");
    private final static QName _ContactCreatedById_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "CreatedById");
    private final static QName _ContactEmail_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "Email");
    private final static QName _ContactAccountId_QNAME = new QName("urn:sobject.enterprise.soap.sforce.com", "AccountId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sforce.soap.outboundmessage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Notifications }
     * 
     */
    public Notifications createNotifications() {
        return new Notifications();
    }

    /**
     * Create an instance of {@link Contact }
     * 
     */
    public Contact createContact() {
        return new Contact();
    }

    /**
     * Create an instance of {@link SObject }
     * 
     */
    public SObject createSObject() {
        return new SObject();
    }

    /**
     * Create an instance of {@link NotificationsResponse }
     * 
     */
    public NotificationsResponse createNotificationsResponse() {
        return new NotificationsResponse();
    }

    /**
     * Create an instance of {@link ContactNotification }
     * 
     */
    public ContactNotification createContactNotification() {
        return new ContactNotification();
    }

    /**
     * Create an instance of {@link AggregateResult }
     * 
     */
    public AggregateResult createAggregateResult() {
        return new AggregateResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "MasterRecordId", scope = Contact.class)
    public JAXBElement<String> createContactMasterRecordId(String value) {
        return new JAXBElement<String>(_ContactMasterRecordId_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Title", scope = Contact.class)
    public JAXBElement<String> createContactTitle(String value) {
        return new JAXBElement<String>(_ContactTitle_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "LastName", scope = Contact.class)
    public JAXBElement<String> createContactLastName(String value) {
        return new JAXBElement<String>(_ContactLastName_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Level__c", scope = Contact.class)
    public JAXBElement<String> createContactLevelC(String value) {
        return new JAXBElement<String>(_ContactLevelC_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "FirstName", scope = Contact.class)
    public JAXBElement<String> createContactFirstName(String value) {
        return new JAXBElement<String>(_ContactFirstName_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Birthdate", scope = Contact.class)
    public JAXBElement<XMLGregorianCalendar> createContactBirthdate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ContactBirthdate_QNAME, XMLGregorianCalendar.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "MailingPostalCode", scope = Contact.class)
    public JAXBElement<String> createContactMailingPostalCode(String value) {
        return new JAXBElement<String>(_ContactMailingPostalCode_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "OwnerId", scope = Contact.class)
    public JAXBElement<String> createContactOwnerId(String value) {
        return new JAXBElement<String>(_ContactOwnerId_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "CreatedById", scope = Contact.class)
    public JAXBElement<String> createContactCreatedById(String value) {
        return new JAXBElement<String>(_ContactCreatedById_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "Email", scope = Contact.class)
    public JAXBElement<String> createContactEmail(String value) {
        return new JAXBElement<String>(_ContactEmail_QNAME, String.class, Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:sobject.enterprise.soap.sforce.com", name = "AccountId", scope = Contact.class)
    public JAXBElement<String> createContactAccountId(String value) {
        return new JAXBElement<String>(_ContactAccountId_QNAME, String.class, Contact.class, value);
    }

}

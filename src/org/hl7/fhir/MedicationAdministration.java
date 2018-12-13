//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.12 at 12:19:07 PM EDT 
//


package org.hl7.fhir;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * If the element is present, it must have either a @value, an @id, or extensions
 * 
 * <p>Java class for MedicationAdministration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MedicationAdministration">
 *   &lt;complexContent>
 *     &lt;extension base="{http://hl7.org/fhir}Resource">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{http://hl7.org/fhir}Identifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status" type="{http://hl7.org/fhir}MedicationAdministrationStatus"/>
 *         &lt;element name="patient" type="{http://hl7.org/fhir}ResourceReference"/>
 *         &lt;element name="practitioner" type="{http://hl7.org/fhir}ResourceReference"/>
 *         &lt;element name="encounter" type="{http://hl7.org/fhir}ResourceReference" minOccurs="0"/>
 *         &lt;element name="prescription" type="{http://hl7.org/fhir}ResourceReference"/>
 *         &lt;element name="wasNotGiven" type="{http://hl7.org/fhir}boolean" minOccurs="0"/>
 *         &lt;element name="reasonNotGiven" type="{http://hl7.org/fhir}CodeableConcept" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="whenGiven" type="{http://hl7.org/fhir}Period"/>
 *         &lt;element name="medication" type="{http://hl7.org/fhir}ResourceReference" minOccurs="0"/>
 *         &lt;element name="device" type="{http://hl7.org/fhir}ResourceReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dosage" type="{http://hl7.org/fhir}MedicationAdministration.Dosage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedicationAdministration", propOrder = {
    "identifier",
    "status",
    "patient",
    "practitioner",
    "encounter",
    "prescription",
    "wasNotGiven",
    "reasonNotGiven",
    "whenGiven",
    "medication",
    "device",
    "dosage"
})
public class MedicationAdministration
    extends Resource
{

    protected List<Identifier> identifier;
    @XmlElement(required = true)
    protected MedicationAdministrationStatus status;
    @XmlElement(required = true)
    protected ResourceReference patient;
    @XmlElement(required = true)
    protected ResourceReference practitioner;
    protected ResourceReference encounter;
    @XmlElement(required = true)
    protected ResourceReference prescription;
    protected Boolean wasNotGiven;
    protected List<CodeableConcept> reasonNotGiven;
    @XmlElement(required = true)
    protected Period whenGiven;
    protected ResourceReference medication;
    protected List<ResourceReference> device;
    protected List<MedicationAdministrationDosage> dosage;

    /**
     * Gets the value of the identifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Identifier }
     * 
     * 
     */
    public List<Identifier> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<Identifier>();
        }
        return this.identifier;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link MedicationAdministrationStatus }
     *     
     */
    public MedicationAdministrationStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link MedicationAdministrationStatus }
     *     
     */
    public void setStatus(MedicationAdministrationStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceReference }
     *     
     */
    public ResourceReference getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceReference }
     *     
     */
    public void setPatient(ResourceReference value) {
        this.patient = value;
    }

    /**
     * Gets the value of the practitioner property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceReference }
     *     
     */
    public ResourceReference getPractitioner() {
        return practitioner;
    }

    /**
     * Sets the value of the practitioner property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceReference }
     *     
     */
    public void setPractitioner(ResourceReference value) {
        this.practitioner = value;
    }

    /**
     * Gets the value of the encounter property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceReference }
     *     
     */
    public ResourceReference getEncounter() {
        return encounter;
    }

    /**
     * Sets the value of the encounter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceReference }
     *     
     */
    public void setEncounter(ResourceReference value) {
        this.encounter = value;
    }

    /**
     * Gets the value of the prescription property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceReference }
     *     
     */
    public ResourceReference getPrescription() {
        return prescription;
    }

    /**
     * Sets the value of the prescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceReference }
     *     
     */
    public void setPrescription(ResourceReference value) {
        this.prescription = value;
    }

    /**
     * Gets the value of the wasNotGiven property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getWasNotGiven() {
        return wasNotGiven;
    }

    /**
     * Sets the value of the wasNotGiven property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWasNotGiven(Boolean value) {
        this.wasNotGiven = value;
    }

    /**
     * Gets the value of the reasonNotGiven property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reasonNotGiven property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReasonNotGiven().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeableConcept }
     * 
     * 
     */
    public List<CodeableConcept> getReasonNotGiven() {
        if (reasonNotGiven == null) {
            reasonNotGiven = new ArrayList<CodeableConcept>();
        }
        return this.reasonNotGiven;
    }

    /**
     * Gets the value of the whenGiven property.
     * 
     * @return
     *     possible object is
     *     {@link Period }
     *     
     */
    public Period getWhenGiven() {
        return whenGiven;
    }

    /**
     * Sets the value of the whenGiven property.
     * 
     * @param value
     *     allowed object is
     *     {@link Period }
     *     
     */
    public void setWhenGiven(Period value) {
        this.whenGiven = value;
    }

    /**
     * Gets the value of the medication property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceReference }
     *     
     */
    public ResourceReference getMedication() {
        return medication;
    }

    /**
     * Sets the value of the medication property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceReference }
     *     
     */
    public void setMedication(ResourceReference value) {
        this.medication = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the device property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDevice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceReference }
     * 
     * 
     */
    public List<ResourceReference> getDevice() {
        if (device == null) {
            device = new ArrayList<ResourceReference>();
        }
        return this.device;
    }

    /**
     * Gets the value of the dosage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dosage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDosage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MedicationAdministrationDosage }
     * 
     * 
     */
    public List<MedicationAdministrationDosage> getDosage() {
        if (dosage == null) {
            dosage = new ArrayList<MedicationAdministrationDosage>();
        }
        return this.dosage;
    }

}
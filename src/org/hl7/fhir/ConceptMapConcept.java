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
 * A statement of relationships from one set of concepts to one or more other concept systems.
 * 
 * <p>Java class for ConceptMap.Concept complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConceptMap.Concept">
 *   &lt;complexContent>
 *     &lt;extension base="{http://hl7.org/fhir}BackboneElement">
 *       &lt;sequence>
 *         &lt;element name="system" type="{http://hl7.org/fhir}uri"/>
 *         &lt;element name="code" type="{http://hl7.org/fhir}code" minOccurs="0"/>
 *         &lt;element name="dependsOn" type="{http://hl7.org/fhir}ConceptMap.DependsOn" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="map" type="{http://hl7.org/fhir}ConceptMap.Map" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConceptMap.Concept", propOrder = {
    "system",
    "code",
    "dependsOn",
    "map"
})
public class ConceptMapConcept
    extends BackboneElement
{

    @XmlElement(required = true)
    protected Uri system;
    protected Code code;
    protected List<ConceptMapDependsOn> dependsOn;
    protected List<ConceptMapMap> map;

    /**
     * Gets the value of the system property.
     * 
     * @return
     *     possible object is
     *     {@link Uri }
     *     
     */
    public Uri getSystem() {
        return system;
    }

    /**
     * Sets the value of the system property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uri }
     *     
     */
    public void setSystem(Uri value) {
        this.system = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link Code }
     *     
     */
    public Code getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link Code }
     *     
     */
    public void setCode(Code value) {
        this.code = value;
    }

    /**
     * Gets the value of the dependsOn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dependsOn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDependsOn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptMapDependsOn }
     * 
     * 
     */
    public List<ConceptMapDependsOn> getDependsOn() {
        if (dependsOn == null) {
            dependsOn = new ArrayList<ConceptMapDependsOn>();
        }
        return this.dependsOn;
    }

    /**
     * Gets the value of the map property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the map property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMap().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptMapMap }
     * 
     * 
     */
    public List<ConceptMapMap> getMap() {
        if (map == null) {
            map = new ArrayList<ConceptMapMap>();
        }
        return this.map;
    }

}
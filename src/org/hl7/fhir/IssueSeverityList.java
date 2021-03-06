//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.12 at 12:19:07 PM EDT 
//


package org.hl7.fhir;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IssueSeverity-list.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IssueSeverity-list">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="fatal"/>
 *     &lt;enumeration value="error"/>
 *     &lt;enumeration value="warning"/>
 *     &lt;enumeration value="information"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IssueSeverity-list")
@XmlEnum
public enum IssueSeverityList {


    /**
     * The issue caused the action to fail, and no further checking could be performed.
     * 
     */
    @XmlEnumValue("fatal")
    FATAL("fatal"),

    /**
     * The issue is sufficiently important to cause the action to fail.
     * 
     */
    @XmlEnumValue("error")
    ERROR("error"),

    /**
     * The issue is not important enough to cause the action to fail, but may cause it to be performed suboptimally or in a way that is not as desired.
     * 
     */
    @XmlEnumValue("warning")
    WARNING("warning"),

    /**
     * The issue has no relation to the degree of success of the action.
     * 
     */
    @XmlEnumValue("information")
    INFORMATION("information");
    private final java.lang.String value;

    IssueSeverityList(java.lang.String v) {
        value = v;
    }

    public java.lang.String value() {
        return value;
    }

    public static IssueSeverityList fromValue(java.lang.String v) {
        for (IssueSeverityList c: IssueSeverityList.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

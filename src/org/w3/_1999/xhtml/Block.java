//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.12 at 12:19:07 PM EDT 
//


package org.w3._1999.xhtml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Block complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Block">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;choice>
 *             &lt;element ref="{http://www.w3.org/1999/xhtml}p"/>
 *             &lt;choice>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}h1"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}h2"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}h3"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}h4"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}h5"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}h6"/>
 *             &lt;/choice>
 *             &lt;element ref="{http://www.w3.org/1999/xhtml}div"/>
 *             &lt;choice>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}ul"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}ol"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}dl"/>
 *             &lt;/choice>
 *             &lt;choice>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}pre"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}hr"/>
 *               &lt;element ref="{http://www.w3.org/1999/xhtml}blockquote"/>
 *             &lt;/choice>
 *             &lt;element ref="{http://www.w3.org/1999/xhtml}table"/>
 *           &lt;/choice>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Block", propOrder = {
    "pOrH1OrH2"
})
@XmlSeeAlso({
    Blockquote.class
})
public class Block {

    @XmlElements({
        @XmlElement(name = "blockquote", type = Blockquote.class),
        @XmlElement(name = "h2", type = H2 .class),
        @XmlElement(name = "h6", type = H6 .class),
        @XmlElement(name = "h3", type = H3 .class),
        @XmlElement(name = "pre", type = Pre.class),
        @XmlElement(name = "hr", type = Hr.class),
        @XmlElement(name = "ul", type = Ul.class),
        @XmlElement(name = "p", type = P.class),
        @XmlElement(name = "h4", type = H4 .class),
        @XmlElement(name = "dl", type = Dl.class),
        @XmlElement(name = "div", type = Div.class),
        @XmlElement(name = "h1", type = H1 .class),
        @XmlElement(name = "table", type = Table.class),
        @XmlElement(name = "ol", type = Ol.class),
        @XmlElement(name = "h5", type = H5 .class)
    })
    protected List<Object> pOrH1OrH2;

    /**
     * Gets the value of the pOrH1OrH2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pOrH1OrH2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPOrH1OrH2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Blockquote }
     * {@link H2 }
     * {@link H6 }
     * {@link H3 }
     * {@link Pre }
     * {@link Hr }
     * {@link Ul }
     * {@link P }
     * {@link H4 }
     * {@link Dl }
     * {@link Div }
     * {@link H1 }
     * {@link Table }
     * {@link Ol }
     * {@link H5 }
     * 
     * 
     */
    public List<Object> getPOrH1OrH2() {
        if (pOrH1OrH2 == null) {
            pOrH1OrH2 = new ArrayList<Object>();
        }
        return this.pOrH1OrH2;
    }

}

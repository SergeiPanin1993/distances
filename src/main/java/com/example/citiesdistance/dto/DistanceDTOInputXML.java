package com.example.citiesdistance.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@JacksonXmlRootElement(namespace = "http://namespacenamespace.com", localName = "root")
public class DistanceDTOInputXML {

    @XmlElementWrapper
    @XmlElement(name="cityDistance")
    private List<DistanceDTO> allDistances;

    public List<DistanceDTO> getAllDistances() {
        return allDistances;
    }

    public void setAllDistances(List<DistanceDTO> allDistances) {
        this.allDistances = allDistances;
    }

    public DistanceDTOInputXML(List<DistanceDTO> allDistances) {
        this.allDistances = allDistances;
    }

    public DistanceDTOInputXML() {
    }
}

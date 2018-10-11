package org.openmrs.module.kenyaemrCharts.htmlMetadata;

/**
 * A model for data point i.e <obs></obs> tag
 */
public class FormDataPoint {
    String conceptUUID;
    String conceptName;
    String generatedName;
    String preferredName;
    String dataType;

    public String getConceptUUID() {
        return conceptUUID;
    }

    public void setConceptUUID(String conceptUUID) {
        this.conceptUUID = conceptUUID;
    }

    public String getConceptName() {
        return conceptName;
    }

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }

    public String getGeneratedName() {
        return generatedName;
    }

    public void setGeneratedName(String generatedName) {
        this.generatedName = generatedName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}

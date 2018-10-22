package org.openmrs.module.kenyaemrCharts.htmlMetadata;

/**
 * A model for data point i.e <obs></obs> tag
 */
public class FormDataPoint {
    private String conceptUUID;
    private Integer conceptId;
    private String conceptName;
    private String generatedName;
    private String preferredName;
    private String dataType;

    public String getConceptUUID() {
        return conceptUUID;
    }

    public void setConceptUUID(String conceptUUID) {
        this.conceptUUID = conceptUUID;
    }

    public Integer getConceptId() {
        return conceptId;
    }

    public void setConceptId(Integer conceptId) {
        this.conceptId = conceptId;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        FormDataPoint pn = (FormDataPoint) o;
        return pn.getConceptId().equals(this.getConceptId());
    }
    @Override
    public int hashCode() {
        return getConceptId().hashCode();
    }
}

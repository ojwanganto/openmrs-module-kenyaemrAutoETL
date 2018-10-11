package org.openmrs.module.kenyaemrCharts.htmlMetadata;

/**
 * A model for data point i.e <obs></obs> tag
 */
public class FormDataPoint {
    String conceptUUID;
    String dataPointConceptName;
    String dataPointGeneratedName;
    String dataPointPreferredName;

    public String getConceptUUID() {
        return conceptUUID;
    }

    public void setConceptUUID(String conceptUUID) {
        this.conceptUUID = conceptUUID;
    }

    public String getDataPointConceptName() {
        return dataPointConceptName;
    }

    public void setDataPointConceptName(String dataPointConceptName) {
        this.dataPointConceptName = dataPointConceptName;
    }

    public String getDataPointGeneratedName() {
        return dataPointGeneratedName;
    }

    public void setDataPointGeneratedName(String dataPointGeneratedName) {
        this.dataPointGeneratedName = dataPointGeneratedName;
    }

    public String getDataPointPreferredName() {
        return dataPointPreferredName;
    }

    public void setDataPointPreferredName(String dataPointPreferredName) {
        this.dataPointPreferredName = dataPointPreferredName;
    }
}

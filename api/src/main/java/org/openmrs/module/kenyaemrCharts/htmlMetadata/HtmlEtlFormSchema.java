package org.openmrs.module.kenyaemrCharts.htmlMetadata;

import java.util.List;

public class HtmlEtlFormSchema {
    private String formUUID;
    private String formName;
    private String generatedTableName;
    private List<FormDataPoint> dataPoints;
    private String ddlQueryStatement;
    private String dmlQueryStatement;
    private String inCrementalUpdatesQueryStatement;
    private Integer voided;

    public HtmlEtlFormSchema(String formUUID, String formName) {
        this.formUUID = formUUID;
        this.formName = formName;
    }

    public HtmlEtlFormSchema(String formUUID, String formName, List<FormDataPoint> dataPoints) {
        this.formUUID = formUUID;
        this.formName = formName;
        this.dataPoints = dataPoints;
    }

    public String getFormUUID() {
        return formUUID;
    }

    public void setFormUUID(String formUUID) {
        this.formUUID = formUUID;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getGeneratedTableName() {
        return generatedTableName;
    }

    public void setGeneratedTableName(String generatedTableName) {
        this.generatedTableName = generatedTableName;
    }

    public List<FormDataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<FormDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public String getDdlQueryStatement() {
        return ddlQueryStatement;
    }

    public void setDdlQueryStatement(String ddlQueryStatement) {
        this.ddlQueryStatement = ddlQueryStatement;
    }

    public String getDmlQueryStatement() {
        return dmlQueryStatement;
    }

    public void setDmlQueryStatement(String dmlQueryStatement) {
        this.dmlQueryStatement = dmlQueryStatement;
    }

    public String getInCrementalUpdatesQueryStatement() {
        return inCrementalUpdatesQueryStatement;
    }

    public void setInCrementalUpdatesQueryStatement(String inCrementalUpdatesQueryStatement) {
        this.inCrementalUpdatesQueryStatement = inCrementalUpdatesQueryStatement;
    }

    public Integer getVoided() {
        return voided;
    }

    public void setVoided(Integer voided) {
        this.voided = voided;
    }
}

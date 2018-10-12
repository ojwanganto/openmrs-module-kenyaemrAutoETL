package org.openmrs.module.kenyaemrCharts.htmlMetadata;

import org.openmrs.Concept;
import org.openmrs.module.kenyaemrCharts.queryTemplate.DDLTemplate;

public class FormMetadataUtils {
    /**
     * Gets concept datatype
     * @param concept
     * @return String equivalent of the datatype
     */
    public static String getConceptDatatype(Concept concept) {
        if (concept == null)
            return null;

        if (concept.getDatatype().isCoded()) {
            return "Coded";
        } else if (concept.getDatatype().isBoolean()) {
            return "Boolean";
        } else if (concept.getDatatype().isText()) {
            return "Text";
        } else if (concept.getDatatype().isDateTime() || concept.getDatatype().isDate()) {
            return "Datetime";
        } else if (concept.getDatatype().isNumeric()) {
            return "Numeric";
        }
        return null;
    }

    /**
     * Generates toString equivalent of HtmlEtlFormSchema.
     * @param schema
     */
    public static void htmlFormSchemaPrintout(HtmlEtlFormSchema schema) {

        StringBuilder builder = new StringBuilder();
        builder.append("Form: ").append(schema.getFormName()).append("\n");
        builder.append("Form Table: ").append(schema.getGeneratedTableName()).append("\n");
        builder.append("Form UUID: ").append(schema.getFormUUID()).append("\n");
        builder.append("Data Points: ").append("\n");

        for (FormDataPoint point : schema.getDataPoints()) {
            builder.append("\tObs tag: ").append(point.getConceptUUID()).append("\n");
            builder.append("\tConcept ID: ").append(String.valueOf(point.getConceptId())).append("\n");
            builder.append("\tConcept Name: ").append(point.getConceptName()).append("\n");
            builder.append("\tGenerated Name: ").append(point.getGeneratedName()).append("\n");
            builder.append("\tConcept datatype: ").append(point.getDataType()).append("\n");
            builder.append("-------------------------------------------------").append("\n");
        }
        builder.append("################################# End ################################");
        System.out.println(builder.toString());
    }

    /**
     * Constructs DDL statement for creating ETL table from schema information
     * @param schema
     * @return SQL statement
     */
    public static String buildHtmlEtlTableDDL(HtmlEtlFormSchema schema) {
        if (schema == null)
            return null;
        DDLTemplate ddlTemplate = new DDLTemplate();
        return ddlTemplate.generateQuery(schema);
    }

    /**
     * Constructs DML statement for creating ETL table from schema information
     * @param schema
     * @return SQL statement
     */
    public static String buildHtmlEtlTableDML(HtmlEtlFormSchema schema) {
        return null;
    }

    public static String createDatabase() {

        return null;
    }

}

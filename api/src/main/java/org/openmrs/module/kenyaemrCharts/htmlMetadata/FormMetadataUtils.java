package org.openmrs.module.kenyaemrCharts.htmlMetadata;

import org.openmrs.Concept;
import org.openmrs.module.kenyaemrCharts.queryTemplate.DDLTemplate;
import org.openmrs.module.kenyaemrCharts.queryTemplate.DMLTemplate;

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
        if (schema == null)
            return null;
        DMLTemplate dmlTemplate = new DMLTemplate();
        return dmlTemplate.generateQuery(schema);
    }

    /**
     * Should define the default database.
     * TODO: complete this
     * @return
     */
    public static String createDatabase() {

        return null;
    }

}

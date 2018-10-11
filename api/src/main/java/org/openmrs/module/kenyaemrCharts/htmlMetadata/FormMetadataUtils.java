package org.openmrs.module.kenyaemrCharts.htmlMetadata;

import org.openmrs.Concept;

public class FormMetadataUtils {
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

    public static void htmlFormSchemaPrintout(HtmlEtlFormSchema schema) {

        StringBuilder builder = new StringBuilder();
        builder.append("Form: ").append(schema.getFormName()).append("\n");
        builder.append("Form UUID: ").append(schema.getFormUUID()).append("\n");
        builder.append("Data Points: ").append("\n");

        for (FormDataPoint point : schema.getDataPoints()) {
            builder.append("\tObs tag: ").append(point.getConceptUUID()).append("\n");
            builder.append("\tConcept Name: ").append(point.getConceptName()).append("\n");
            builder.append("\tGenerated Name: ").append(point.getGeneratedName()).append("\n");
            builder.append("\tConcept datatype: ").append(point.getDataType()).append("\n");
            builder.append("-------------------------------------------------").append("\n");
        }
        builder.append("################################# End ################################");
        System.out.println(builder.toString());
    }
}

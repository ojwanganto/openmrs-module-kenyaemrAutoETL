package org.openmrs.module.kenyaemrCharts.queryTemplate;

import org.openmrs.module.kenyaemrCharts.htmlMetadata.FormDataPoint;
import org.openmrs.module.kenyaemrCharts.htmlMetadata.HtmlEtlFormSchema;

import java.util.Set;

public class DDLTemplate extends QueryTemplate {

    @Override
    public String generateQuery(HtmlEtlFormSchema schema) {
        if (schema == null)
            return null;
        StringBuilder builder = new StringBuilder();
        Set<FormDataPoint> dataPoints = schema.getDataPoints();
        String generatedTableName = QueryTemplate.DATABASE_NAME.concat(".").concat(schema.getGeneratedTableName());
        builder.append("DROP TABLE IF EXISTS ").append(generatedTableName).append(" ; \n");
        builder.append(getQueryHeader(generatedTableName))
                .append(buildQueryBody(dataPoints))
                .append(buildQueryTail(dataPoints));
        return builder.toString();
    }

    private String getQueryHeader(String tableName) {
        if (tableName == null)
            return null;
        return "create table :tableName ( \n".replace(":tableName", tableName);
    }

    private String buildQueryBody(Set<FormDataPoint> dataPoints) {
        if (dataPoints == null || dataPoints.size() == 0)
            return null;
        // iterate through data points
        StringBuilder builder = new StringBuilder();
        builder.append(getStandardDeclarationFields());
        for (FormDataPoint dataPoint : dataPoints) {
            builder.append("\t").append(dataPoint.getGeneratedName().toLowerCase())// indent a bit
                    .append(" ")
                    .append(getTypeFromDataPoint(dataPoint))
                    .append(", \n");
        }
        return builder.toString();

    }

    private String getTypeFromDataPoint(FormDataPoint dataPoint) {
        if (dataPoint == null || null == dataPoint.getDataType() || "".equals(dataPoint.getDataType()))
            return null;
        String pointDataType = dataPoint.getDataType();
        String columnTypeString = null;
        if (pointDataType.equals("Coded")) {
            columnTypeString = "INT(11)";
        } else if (pointDataType.equals("Boolean")) {
            columnTypeString = "VARCHAR(10)";
        } else if (pointDataType.equals("Datetime")) {
            columnTypeString = "DATETIME";
        } else if (pointDataType.equals("Text")) {
            columnTypeString = "VARCHAR(255)";
        } else if (pointDataType.equals("Numeric")) {
            columnTypeString = "DOUBLE";
        }

        return columnTypeString;
    }

    private String buildQueryTail(Set<FormDataPoint> dataPoints) {
        if (dataPoints == null || dataPoints.size() == 0)
            return null;
        return "\tvoided INT(11)\n);\n";
    }

    private String getStandardDeclarationFields() {
        StringBuilder builder = new StringBuilder();
        builder.append("\tpatient_id INT(11) NOT NULL").append(", \n")
                .append("\tvisit_id INT(11) DEFAULT NULL").append(", \n")
                .append("\tvisit_date DATE").append(", \n")
                .append("\tlocation_id INT(11) DEFAULT NULL").append(", \n")
                .append("\tencounter_id INT(11) NOT NULL PRIMARY KEY").append(", \n")
                .append("\tcreator INT(11) DEFAULT NULL").append(", \n")
                .append("\tdate_created DATE").append(", \n");


        return builder.toString();

    }
}

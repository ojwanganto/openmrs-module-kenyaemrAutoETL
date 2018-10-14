package org.openmrs.module.kenyaemrCharts.queryTemplate;

import org.openmrs.module.kenyaemrCharts.htmlMetadata.FormDataPoint;
import org.openmrs.module.kenyaemrCharts.htmlMetadata.HtmlEtlFormSchema;

import java.util.List;

public class DDLTemplate extends QueryTemplate {

    @Override
    public String generateQuery(HtmlEtlFormSchema schema) {
        if (schema == null)
            return null;
        StringBuilder builder = new StringBuilder();
        List<FormDataPoint> dataPoints = schema.getDataPoints();
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

    private String buildQueryBody(List<FormDataPoint> dataPoints) {
        if (dataPoints == null || dataPoints.size() == 0)
            return null;
        // iterate through data points
        StringBuilder builder = new StringBuilder();
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
        if (pointDataType.equals("Coded") || pointDataType.equals("Numeric")) {
            columnTypeString = "INT(11)";
        } else if (pointDataType.equals("Boolean")) {
            columnTypeString = "VARCHAR(10)";
        } else if (pointDataType.equals("Datetime")) {
            columnTypeString = "DATETIME";
        } else if (pointDataType.equals("Text")) {
            columnTypeString = "VARCHAR(255)";
        }

        return columnTypeString;
    }

    private String buildQueryTail(List<FormDataPoint> dataPoints) {
        if (dataPoints == null || dataPoints.size() == 0)
            return null;
        return "\tvoided INT(11)\n)";
    }
}

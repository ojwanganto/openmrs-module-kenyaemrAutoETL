package org.openmrs.module.kenyaemrCharts.queryTemplate;

import org.openmrs.module.kenyaemrCharts.htmlMetadata.FormDataPoint;
import org.openmrs.module.kenyaemrCharts.htmlMetadata.HtmlEtlFormSchema;

import java.util.List;

public class DMLTemplate extends QueryTemplate {
    @Override
    public String generateQuery(HtmlEtlFormSchema schema) {
        if (schema == null)
            return null;
        StringBuilder builder = new StringBuilder();
        List<FormDataPoint> dataPoints = schema.getDataPoints();
        String generatedTableName = QueryTemplate.DATABASE_NAME.concat(".").concat(schema.getGeneratedTableName());
        builder.append(getQueryHeader(generatedTableName, dataPoints))
                .append(buildQueryBody(dataPoints))
                .append(buildQueryTail(dataPoints));
        return builder.toString();
    }

    private String getQueryHeader(String tableName, List<FormDataPoint> dataPoints) {
        if (tableName == null)
            return null;
        StringBuilder header = new StringBuilder();
        header.append("insert into :tableName ( \n".replace(":tableName", tableName));
        for (FormDataPoint dataPoint : dataPoints) {
            header.append("\t").append(dataPoint.getGeneratedName().toLowerCase()).append(", \n");
        }
        header.append("voided").append(")");
        return header.toString();

    }

    private String buildQueryBody(List<FormDataPoint> dataPoints) {
        if (dataPoints == null || dataPoints.size() == 0)
            return null;
        // iterate through data points
        StringBuilder builder = new StringBuilder();
        for (FormDataPoint dataPoint : dataPoints) {
            builder.append("\t")
                    .append(getTypeFromDataPoint(dataPoint))
                    .append(", \n");
        }
        return builder.toString();

    }

    private String getTypeFromDataPoint(FormDataPoint dataPoint) {
        if (dataPoint == null || null == dataPoint.getDataType() || "".equals(dataPoint.getDataType()))
            return null;
        String pointDataType = dataPoint.getDataType();
        StringBuilder builder = new StringBuilder();
        String columnTypeString = null;
        if (pointDataType.equals("Coded")) {
            builder.append("max(if(o.concept_id=:conceptId,o.value_coded,null))").append(" as ").append(dataPoint.getGeneratedName());
        } else if (pointDataType.equals("Boolean")) {
            builder.append("max(if(o.concept_id=:conceptId,o.value_coded,null))").append(" as ").append(dataPoint.getGeneratedName());
        } else if (pointDataType.equals("Datetime")) {
            builder.append("max(if(o.concept_id=:conceptId,o.value_datetime,null))").append(" as ").append(dataPoint.getGeneratedName());
        } else if (pointDataType.equals("Text")) {
            builder.append("max(if(o.concept_id=:conceptId,o.value_text,null))").append(" as ").append(dataPoint.getGeneratedName());
        } else if (pointDataType.equals("Numeric")) {
            builder.append("max(if(o.concept_id=:conceptId,o.value_numeric,null))").append(" as ").append(dataPoint.getGeneratedName());
        }

        return builder.toString().replace(":conceptId", String.valueOf(dataPoint.getConceptId()));
    }

    private String buildQueryTail(List<FormDataPoint> dataPoints) {
        if (dataPoints == null || dataPoints.size() == 0)
            return null;
        return "\te.voided\n)";
    }
}

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
            builder.append("\t").append(dataPoint.getGeneratedName())// indent a bit
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
            columnTypeString = " INT(11) ";
        } else if (pointDataType.equals("Boolean")) {
            columnTypeString = " VARCHAR(10) ";
        } else if (pointDataType.equals("Datetime")) {
            columnTypeString = " DATETIME ";
        } else if (pointDataType.equals("Text")) {
            columnTypeString = " VARCHAR(255) ";
        }

        return columnTypeString;
    }

    private String buildQueryTail(List<FormDataPoint> dataPoints) {
        if (dataPoints == null || dataPoints.size() == 0)
            return null;
        return "\tvoided INT(11)\n)";
    }

    /*String sampleQuery= "create table kenyaemr_etl.etl_hiv_enrollment(\n" +
                "uuid char(38) ,\n" +
                "patient_id INT(11) NOT NULL,\n" +
                "visit_id INT(11) DEFAULT NULL,\n" +
                "visit_date DATE,\n" +
                "location_id INT(11) DEFAULT NULL,\n" +
                "encounter_id INT(11) NOT NULL PRIMARY KEY,\n" +
                "encounter_provider INT(11),\n" +
                "patient_type INT(11),\n" +
                "date_first_enrolled_in_care DATE,\n" +
                "entry_point INT(11),\n" +
                "transfer_in_date DATE,\n" +
                "facility_transferred_from VARCHAR(255),\n" +
                "district_transferred_from VARCHAR(255),\n" +
                "date_started_art_at_transferring_facility DATE,\n" +
                "date_confirmed_hiv_positive DATE,\n" +
                "facility_confirmed_hiv_positive VARCHAR(255),\n" +
                "arv_status INT(11),\n" +
                "name_of_treatment_supporter VARCHAR(255),\n" +
                "relationship_of_treatment_supporter INT(11),\n" +
                "treatment_supporter_telephone VARCHAR(100),\n" +
                "treatment_supporter_address VARCHAR(100),\n" +
                "date_of_discontinuation DATETIME,\n" +
                "discontinuation_reason INT(11),\n" +
                "date_created DATE,\n" +
                "voided INT(11),\n" +
                "constraint foreign key(patient_id) references kenyaemr_etl.etl_patient_demographics(patient_id),\n" +
                "CONSTRAINT unique_uuid UNIQUE(uuid),\n" +
                "index(patient_id),\n" +
                "index(visit_id),\n" +
                "index(visit_date),\n" +
                "index(date_started_art_at_transferring_facility),\n" +
                "index(arv_status),\n" +
                "index(date_confirmed_hiv_positive),\n" +
                "index(entry_point),\n" +
                "index(transfer_in_date),\n" +
                "index(date_first_enrolled_in_care),\n" +
                "index(entry_point, transfer_in_date, visit_date, patient_id)\n" +
                "\n" +
                ");";*/
}

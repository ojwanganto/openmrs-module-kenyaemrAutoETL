package org.openmrs.module.kenyaemrCharts.queryTemplate;

import org.openmrs.module.kenyaemrCharts.htmlMetadata.HtmlEtlFormSchema;

public abstract class QueryTemplate {
    public static final String DATABASE_NAME = "reporting_etl";

    public abstract String generateQuery(HtmlEtlFormSchema schema);
}

package org.openmrs.module.kenyaemrCharts.forms;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openmrs.Concept;
import org.openmrs.Form;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.htmlformentry.HtmlForm;
import org.openmrs.module.kenyacore.form.FormDescriptor;
import org.openmrs.module.kenyacore.form.FormManager;
import org.openmrs.module.kenyacore.form.FormUtils;
import org.openmrs.module.kenyaemrCharts.htmlMetadata.FormDataPoint;
import org.openmrs.module.kenyaemrCharts.htmlMetadata.FormMetadataUtils;
import org.openmrs.module.kenyaemrCharts.htmlMetadata.HtmlEtlFormSchema;
import org.openmrs.ui.framework.resource.ResourceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormProcessor {



    public static void getAllForms(FormManager formManager, ResourceFactory resourceFactory) {

        ConceptService conceptService = Context.getConceptService();

        List<FormDescriptor> formList = new ArrayList<FormDescriptor>(formManager.getAllFormDescriptors());
        System.out.println("############################# HTML Form Processor #########################");
        String triageFormHtml = null;
        for(FormDescriptor formDescriptor : formList) {
            String targetUuid = formDescriptor.getTargetUuid();
            Form form = Context.getFormService().getFormByUuid(targetUuid);
            if(form != null) {

                HtmlForm htmlForm = null;
                try {
                    htmlForm = FormUtils.getHtmlForm(form, resourceFactory);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (htmlForm != null && htmlForm.getName().equals("Triage")) {

                    HtmlEtlFormSchema schema = new HtmlEtlFormSchema(targetUuid, htmlForm.getName());
                    //process generated table name
                    String generatedTableName = StringUtils.replace(htmlForm.getName()," ", "_");
                    generatedTableName = StringUtils.replace(generatedTableName,"(", "_");
                    generatedTableName = StringUtils.replace(generatedTableName,")", "_");
                    schema.setGeneratedTableName(generatedTableName.toLowerCase());
                    triageFormHtml = htmlForm.getXmlData();
                    Document doc = Jsoup.parse(triageFormHtml);
                    Element htmlform = doc.select("htmlform").first();
                    Elements obsTags = htmlform.select("obs");

                    List<FormDataPoint> dataPoints = new ArrayList<FormDataPoint>();
                    for (Element obsTag : obsTags) {
                        String conceptUUId = obsTag.attr("conceptId");
                        Concept concept = conceptService.getConceptByUuid(conceptUUId);
                        String dataType = FormMetadataUtils.getConceptDatatype(concept);
                        FormDataPoint dataPoint = new FormDataPoint();
                        dataPoint.setConceptUUID(conceptUUId);
                        dataPoint.setConceptId(concept.getConceptId());
                        dataPoint.setConceptName(concept.getName().getName());
                        dataPoint.setDataType(dataType);
                        dataPoint.setGeneratedName(concept.getName().getName().
                                replace(' ','_').
                                replace('(', '_').
                                replace(')','_'));
                        dataPoints.add(dataPoint);

                    }
                    schema.setDataPoints(dataPoints);
                    //FormMetadataUtils.htmlFormSchemaPrintout(schema);
                    System.out.println("####################################### DDL Query ########################################");

                    System.out.println(FormMetadataUtils.buildHtmlEtlTableDDL(schema));
                    break;
                }
            }
        }
    }
}

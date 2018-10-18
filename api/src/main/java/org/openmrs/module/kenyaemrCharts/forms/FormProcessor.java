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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormProcessor {



    public static void getAllForms(FormManager formManager, ResourceFactory resourceFactory) {

        ConceptService conceptService = Context.getConceptService();

        List<FormDescriptor> formList = new ArrayList<FormDescriptor>(formManager.getAllFormDescriptors());
        List<String> whiteList = Arrays.asList(
                "e958f902-64df-4819-afd4-7fb061f59308",
                "37f6bd8d-586a-4169-95fa-5781f987fe62",
                "59ed8e62-7f1f-40ae-a2e3-eabe350277ce",
                "0038a296-62f8-4099-80e5-c9ea7590c157",
                "22c68f86-bbf0-49ba-b2d1-23fa7ccf0259",
                "e4b506c1-7379-42b6-a374-284469cba8da",
                "83fb6ab2-faec-4d87-a714-93e77a28a201"
        );

        List<String> blackList = Arrays.asList(
                "04295648-7606-11e8-adc0-fa7ae01bbebc"
        );
        System.out.println("############################# HTML Form Processor #########################");
        String formHtml = null;
        for(FormDescriptor formDescriptor : formList) {
            String targetUuid = formDescriptor.getTargetUuid();
            Form form = Context.getFormService().getFormByUuid(targetUuid);
            if (blackList.contains(targetUuid)) {
                continue;
            }
          if(form != null) {

                HtmlForm htmlForm = null;
                try {
                    htmlForm = FormUtils.getHtmlForm(form, resourceFactory);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (htmlForm != null && whiteList.contains(targetUuid)) {
                    System.out.println("Html Form: " + htmlForm.getUuid() + " , Name: " + htmlForm.getName());

                    HtmlEtlFormSchema schema = new HtmlEtlFormSchema(targetUuid, htmlForm.getName());
                    //process generated table name
                    String generatedTableName = StringUtils.replace(htmlForm.getName()," ", "_");
                    generatedTableName = StringUtils.replace(generatedTableName,"(", "_");
                    generatedTableName = StringUtils.replace(generatedTableName,")", "_");
                    schema.setGeneratedTableName(generatedTableName.toLowerCase());
                    formHtml = htmlForm.getXmlData();
                    Document doc = Jsoup.parse(formHtml);
                    for( Element element : doc.select("repeat") ){
                        element.remove();
                    }

                    for( Element element : doc.select("obsgroup") ){
                        element.remove();
                    }

                    Element htmlform = doc.select("htmlform").first();

                    Elements obsTags = htmlform.select("obs");

                    Set<FormDataPoint> dataPoints = new HashSet<FormDataPoint>();
                    for (Element obsTag : obsTags) {
                        String conceptUUId = obsTag.attr("conceptId");
                        Concept concept = null;
                        if (conceptService.getConceptByUuid(conceptUUId) != null) {
                            concept = conceptService.getConceptByUuid(conceptUUId);
                        } else {
                            concept = conceptService.getConcept(conceptUUId);
                        }

                        if (concept == null) {
                            System.out.println("Concept UUID Invalid: " + conceptUUId);
                            continue;
                        }
                        String dataType = FormMetadataUtils.getConceptDatatype(concept);
                        FormDataPoint dataPoint = new FormDataPoint();
                        dataPoint.setConceptUUID(conceptUUId);
                        dataPoint.setConceptId(concept.getConceptId());
                        dataPoint.setConceptName(concept.getName().getName());
                        dataPoint.setDataType(dataType);
                        dataPoint.setGeneratedName(concept.getName().getName().
                                replace(' ','_').
                                replace('/','_').
                                replace(',','_').
                                replace('-','_').
                                replace('(', '_').
                                replace(')','_'));
                        dataPoints.add(dataPoint);

                    }
                    schema.setDataPoints(dataPoints);
                    //FormMetadataUtils.htmlFormSchemaPrintout(schema);
                    System.out.println("####################################### DDL Query ########################################");

                    System.out.println(FormMetadataUtils.buildHtmlEtlTableDDL(schema));
                    System.out.println(FormMetadataUtils.buildHtmlEtlTableDML(schema));

                }
            }
        }
    }
}

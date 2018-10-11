package org.openmrs.module.kenyaemrCharts.forms;

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
import org.openmrs.ui.framework.resource.ResourceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormProcessor {



    public static void getAllForms(FormManager formManager, ResourceFactory resourceFactory) {

        ConceptService conceptService = Context.getConceptService();

        List<FormDescriptor> formList = new ArrayList<FormDescriptor>(formManager.getAllFormDescriptors());
        System.out.println("----------------------------------------------------------------------------------");
        String triageFormHtml = null;
        for(FormDescriptor formDescriptor : formList) {
            Form form = Context.getFormService().getFormByUuid(formDescriptor.getTargetUuid());
            if(form != null) {

                HtmlForm htmlForm = null;
                try {
                    htmlForm = FormUtils.getHtmlForm(form, resourceFactory);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (htmlForm != null && htmlForm.getName().equals("Triage")) {
                    //System.out.println("Form Details: Name: " + htmlForm.getName() + ", HTML: " + htmlForm.getXmlData());
                    triageFormHtml = htmlForm.getXmlData();
                    break;
                }
            }
        }

        if (triageFormHtml != null) {
            Document doc = Jsoup.parse(triageFormHtml);
            Element htmlform = doc.select("htmlform").first();
            Elements obsTags = htmlform.select("obs");

            for (Element obsTag : obsTags) {
                String conceptUUId = obsTag.attr("conceptId");
                Concept concept = conceptService.getConceptByUuid(conceptUUId);
                String dataType = getConceptDatatype(concept);
                System.out.println("Obs tag: " + conceptUUId +
                        "Concept Name: " + conceptService.getConceptByUuid(conceptUUId).getName().getName() +
                        ", Datatype: " + dataType
                );
            }

            //System.out.println("Read html form: " + htmlform.html());
        }


    }

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
}

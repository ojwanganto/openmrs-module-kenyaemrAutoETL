package org.openmrs.module.kenyaemrCharts.forms;

import org.openmrs.Form;
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


        List<FormDescriptor> formList = new ArrayList<FormDescriptor>(formManager.getAllFormDescriptors());

        for(FormDescriptor formDescriptor : formList) {
            Form form = Context.getFormService().getFormByUuid(formDescriptor.getTargetUuid());
            HtmlForm htmlForm = null;
            try {
                htmlForm = FormUtils.getHtmlForm(form, resourceFactory);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Form HTML" + htmlForm.getXmlData());
        }


    }
}

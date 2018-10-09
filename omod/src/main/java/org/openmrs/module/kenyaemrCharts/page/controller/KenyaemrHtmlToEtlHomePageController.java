package org.openmrs.module.kenyaemrCharts.page.controller;


import org.openmrs.module.kenyacore.form.FormManager;
import org.openmrs.module.kenyaemrCharts.forms.FormProcessor;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.resource.ResourceFactory;

/**
 * Controller class for html to etl table page
 */
@AppPage("htmltoetlgenerator.home")
public class KenyaemrHtmlToEtlHomePageController {

    public void controller(@SpringBean FormManager formManager, @SpringBean ResourceFactory resourceFactory){
        System.out.println("Preparing to process html forms");
        FormProcessor.getAllForms(formManager, resourceFactory);

    }
}

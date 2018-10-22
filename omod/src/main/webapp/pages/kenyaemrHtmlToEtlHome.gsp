<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to home", href: ui.pageLink("kenyaemr", "userHome") ]
    ]

    ui.includeJavascript("uicommons", "emr.js")
    ui.includeJavascript("uicommons", "angular.min.js")
    ui.includeJavascript("uicommons", "angular-app.js")
    ui.includeJavascript("uicommons", "angular-resource.min.js")
    ui.includeJavascript("uicommons", "angular-common.js")
    ui.includeJavascript("uicommons", "angular-ui/ui-bootstrap-tpls-0.11.2.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeJavascript("orderentryui", "bootstrap.min.js")


    ui.includeJavascript("uicommons", "filters/display.js")
    ui.includeJavascript("uicommons", "filters/serverDate.js")
    ui.includeJavascript("uicommons", "services/conceptService.js")
    ui.includeJavascript("uicommons", "services/drugService.js")
    ui.includeJavascript("uicommons", "services/encounterService.js")
    ui.includeJavascript("uicommons", "services/orderService.js")
    ui.includeJavascript("uicommons", "services/session.js")

    ui.includeJavascript("uicommons", "directives/select-concept-from-list.js")
    ui.includeJavascript("uicommons", "directives/select-order-frequency.js")
    ui.includeJavascript("uicommons", "directives/select-drug.js")
    ui.includeJavascript("orderentryui", "order-model.js")
    ui.includeJavascript("orderentryui", "order-entry.js")
    ui.includeJavascript("kenyaemrCharts", "htmlEtl.js")

    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")
    ui.includeCss("orderentryui", "drugOrders.css")
    ui.includeCss("uicommons", "styleguide/jquery-ui-1.9.2.custom.min.css")
    ui.includeCss("orderentryui", "index.css")


    ui.includeCss("orderentryui", "bootstrap.min.css")
    ui.includeCss("orderentryui", "labOrders.css")
%>
<style type="text/css">
#new-order input {
    margin: 5px;
}

th, td {
    text-align: left;
}
</style>
<script type="text/javascript">

    window.OpenMRS = window.OpenMRS || {};

</script>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Navigation", items: menuItems ]) }
    </div>
</div>

<div class="ke-page-content">
    <div id="htmls-etl-app" ng-controller="HtmlEtlCtrl" ng-init='init()'>
        <div class="ui-tabs">

            <div class="ui-tabs-panel ui-widget-content">
                <h3>ETL</h3>

                <div id="program-tabs" class="ke-tabs">
                    <div class="ke-tabmenu">
                        <div class="ke-tabmenu-item" data-tabid="active_orders">ETL Processing</div>

                    </div>

                    <div class="ke-tab" data-tabid="active_orders" style="padding-top: 10px">
                        <form>
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">
                                        Process ETL
                                    </h4>
                                </div>

                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <tr>
                                                <th>Form</th>
                                                <th>Form Name</th>
                                                <th>Data Points</th>
                                                <th>Actions</th>
                                            </tr>
                                            <tr ng-repeat="f in form">
                                                <td>
                                                    {{ f.name  }}
                                                </td>
                                                <td>
                                                    {{ f.formName }}
                                                </td>
                                                <td>
                                                    {{ f.ddlQuery }}
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-warning" data-toggle="modal"
                                                            data-target="#generalMessage" ng-click="showDdlQuery(test)">
                                                        DDL
                                                    </button>
                                                    <button type="button" class="btn btn-warning" data-toggle="modal"
                                                            data-target="#generalMessage" ng-click="showDmlQuery(test)">
                                                        DML
                                                    </button>
                                                </td>

                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>

        </div>
        <!-- spinner modal -->
        <div class="modal fade" id="spinner" tabindex="-1" role="dialog" aria-labelledby="spinnerModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-body modal-header-primary">
                        <div>
                            <i class="fa fa-spinner fa-spin" style="font-size:30px"></i> Saving...
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- general message modal -->
        <div class="modal fade" id="generalMessage" tabindex="-1" role="dialog" aria-labelledby="generalMessageModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header modal-header-warning">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <div>
                            Active Order Already exits. Please check the Active Orders Tab to cancel the order and proceed.
                            Further building on the psychological aspect of employee engagement, there are researchers that define employee engagement by distinguishing it from closely related concepts within organizational behavior such as job involvement and organizational commitment. May et al. (2004, p. 12) argue that compared to job involvement employee engagement relates more to how individuals employ themselves when performing a task at work, and similarly to the definition given by Kahn (1990) engaged employees will actively make use of emotions, behaviors, and cognitions. In contrast, job involvement is more concerned with one’s personal psychological identification with the job. The concepts are however related and engagement can be thought of as an antecedent to job involvement; employees who are highly engaged in their jobs can come to feel as though the job situation is central to them and to his or her identity (May et al., 2004, p. 12).
                            Current research that connects the concepts of employee engagement and motivation has predominantly been done from the perspective of the employee, aiming to identify the drivers that employees value the most in their engagement. We can also see that managers clearly play a large role in the engagement of their employees, and a significant amount of employees mention management and work relationships as one of the main drivers behind their engagement at work (Towers Perrin, 2008; Scott & McMullen, 2010; Society for Human Resource Management, 2013). Therefore, in order to advance prior research, it should be explored how managers perceive the influence of extrinsic and intrinsic motivation on employee engagement. This study gives insight into how managers within the public and private sectors perceive their own abilities to influence employees’ engagement through extrinsic and intrinsic motivational factors, and will complement the existing literature in this field which is largely based on the employee perspective. Additionally, the studies that have been conducted have been of quantitative nature, surveying employees of organizations to uncover the drivers behind engagement. This study aims to provide more in-depth information and develop a deeper understanding of managers’ perceptions and following actions, and whether or not they are in line with what employees
                            have claimed to engage them, which leads us to our formulated research question.
                            Active Order Already exits. Please check the Active Orders Tab to cancel the order and proceed.
                            Further building on the psychological aspect of employee engagement, there are researchers that define employee engagement by distinguishing it from closely related concepts within organizational behavior such as job involvement and organizational commitment. May et al. (2004, p. 12) argue that compared to job involvement employee engagement relates more to how individuals employ themselves when performing a task at work, and similarly to the definition given by Kahn (1990) engaged employees will actively make use of emotions, behaviors, and cognitions. In contrast, job involvement is more concerned with one’s personal psychological identification with the job. The concepts are however related and engagement can be thought of as an antecedent to job involvement; employees who are highly engaged in their jobs can come to feel as though the job situation is central to them and to his or her identity (May et al., 2004, p. 12).
                            Current research that connects the concepts of employee engagement and motivation has predominantly been done from the perspective of the employee, aiming to identify the drivers that employees value the most in their engagement. We can also see that managers clearly play a large role in the engagement of their employees, and a significant amount of employees mention management and work relationships as one of the main drivers behind their engagement at work (Towers Perrin, 2008; Scott & McMullen, 2010; Society for Human Resource Management, 2013). Therefore, in order to advance prior research, it should be explored how managers perceive the influence of extrinsic and intrinsic motivation on employee engagement. This study gives insight into how managers within the public and private sectors perceive their own abilities to influence employees’ engagement through extrinsic and intrinsic motivational factors, and will complement the existing literature in this field which is largely based on the employee perspective. Additionally, the studies that have been conducted have been of quantitative nature, surveying employees of organizations to uncover the drivers behind engagement. This study aims to provide more in-depth information and develop a deeper understanding of managers’ perceptions and following actions, and whether or not they are in line with what employees
                            have claimed to engage them, which leads us to our formulated research question.
                            Active Order Already exits. Please check the Active Orders Tab to cancel the order and proceed.
                            Further building on the psychological aspect of employee engagement, there are researchers that define employee engagement by distinguishing it from closely related concepts within organizational behavior such as job involvement and organizational commitment. May et al. (2004, p. 12) argue that compared to job involvement employee engagement relates more to how individuals employ themselves when performing a task at work, and similarly to the definition given by Kahn (1990) engaged employees will actively make use of emotions, behaviors, and cognitions. In contrast, job involvement is more concerned with one’s personal psychological identification with the job. The concepts are however related and engagement can be thought of as an antecedent to job involvement; employees who are highly engaged in their jobs can come to feel as though the job situation is central to them and to his or her identity (May et al., 2004, p. 12).
                            Current research that connects the concepts of employee engagement and motivation has predominantly been done from the perspective of the employee, aiming to identify the drivers that employees value the most in their engagement. We can also see that managers clearly play a large role in the engagement of their employees, and a significant amount of employees mention management and work relationships as one of the main drivers behind their engagement at work (Towers Perrin, 2008; Scott & McMullen, 2010; Society for Human Resource Management, 2013). Therefore, in order to advance prior research, it should be explored how managers perceive the influence of extrinsic and intrinsic motivation on employee engagement. This study gives insight into how managers within the public and private sectors perceive their own abilities to influence employees’ engagement through extrinsic and intrinsic motivational factors, and will complement the existing literature in this field which is largely based on the employee perspective. Additionally, the studies that have been conducted have been of quantitative nature, surveying employees of organizations to uncover the drivers behind engagement. This study aims to provide more in-depth information and develop a deeper understanding of managers’ perceptions and following actions, and whether or not they are in line with what employees
                            have claimed to engage them, which leads us to our formulated research question.
                            Active Order Already exits. Please check the Active Orders Tab to cancel the order and proceed.
                            Further building on the psychological aspect of employee engagement, there are researchers that define employee engagement by distinguishing it from closely related concepts within organizational behavior such as job involvement and organizational commitment. May et al. (2004, p. 12) argue that compared to job involvement employee engagement relates more to how individuals employ themselves when performing a task at work, and similarly to the definition given by Kahn (1990) engaged employees will actively make use of emotions, behaviors, and cognitions. In contrast, job involvement is more concerned with one’s personal psychological identification with the job. The concepts are however related and engagement can be thought of as an antecedent to job involvement; employees who are highly engaged in their jobs can come to feel as though the job situation is central to them and to his or her identity (May et al., 2004, p. 12).
                            Current research that connects the concepts of employee engagement and motivation has predominantly been done from the perspective of the employee, aiming to identify the drivers that employees value the most in their engagement. We can also see that managers clearly play a large role in the engagement of their employees, and a significant amount of employees mention management and work relationships as one of the main drivers behind their engagement at work (Towers Perrin, 2008; Scott & McMullen, 2010; Society for Human Resource Management, 2013). Therefore, in order to advance prior research, it should be explored how managers perceive the influence of extrinsic and intrinsic motivation on employee engagement. This study gives insight into how managers within the public and private sectors perceive their own abilities to influence employees’ engagement through extrinsic and intrinsic motivational factors, and will complement the existing literature in this field which is largely based on the employee perspective. Additionally, the studies that have been conducted have been of quantitative nature, surveying employees of organizations to uncover the drivers behind engagement. This study aims to provide more in-depth information and develop a deeper understanding of managers’ perceptions and following actions, and whether or not they are in line with what employees
                            have claimed to engage them, which leads us to our formulated research question.
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button"  data-dismiss="modal" ng-click="closeModal()">Close</button>
                    </div>
                </div>
            </div>
        </div>


    </div>


</div>

</div>
<script type="text/javascript">
    // manually bootstrap angular app, in case there are multiple angular apps on a page
    angular.bootstrap('#htmls-etl-app', ['htmlEtl']);

</script>

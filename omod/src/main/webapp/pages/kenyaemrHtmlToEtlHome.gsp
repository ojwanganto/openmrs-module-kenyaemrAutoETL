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
    ui.includeJavascript("kenyaemrCharts", "bootstrap.min.js")


    ui.includeJavascript("kenyaemrCharts", "htmlEtl.js")
    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")
    ui.includeCss("uicommons", "styleguide/jquery-ui-1.9.2.custom.min.css")
    ui.includeCss("kenyaemrCharts", "bootstrap.min.css")
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
    window.OpenMRS.Etlforms = ${forms}

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
                <h3>Auto-ETL Output</h3>

                <div id="program-tabs" class="ke-tabs">
                    <div class="ke-tabmenu">
                        <div class="ke-tabmenu-item" data-tabid="auto_etl_output">Metadata Processing</div>

                    </div>

                    <div class="ke-tab" data-tabid="auto_etl_output" style="padding-top: 10px">
                        <form>
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">

                                    </h4>
                                </div>

                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <tr>
                                                <th>Form Name</th>
                                                <th>Generated Table Name</th>
                                                <th>No. of Data Points</th>
                                                <th>Actions</th>
                                            </tr>
                                            <tr ng-repeat="f in form">
                                                <td>
                                                    {{ f.formName  }}
                                                </td>
                                                <td>
                                                    {{ f.tableName }}
                                                </td>
                                                <td>
                                                    {{ f.dataPoints }}
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-secondary" data-toggle="modal"
                                                            data-target="#ddlMessage" ng-click="showDdlQuery(f.ddlStatement)">
                                                        DDL
                                                    </button>
                                                    <button type="button" class="btn btn-secondary" data-toggle="modal"
                                                            data-target="#generalMessage" ng-click="showDmlQuery(f.dmlStatement)">
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

        <!-- dml message modal -->
        <div class="modal fade" id="generalMessage" tabindex="-1" role="dialog" aria-labelledby="generalMessageModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header modal-header-warning">
                        <h2 class="modal-title" id="generalMessageModalCenterTitle">DML</h2>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <div>
                           {{dmlObj}}
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button"  data-dismiss="modal" ng-click="closeModal()">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- ddl message modal -->
        <div class="modal fade" id="ddlMessage" tabindex="-1" role="dialog" aria-labelledby="ddlMessageModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header modal-header-warning">
                        <h5 class="modal-title" id="ddlMessageModalCenterTitle">DDL</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <div>
                            {{ddlObj}}
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

angular.module('htmlEtl', ['orderService', 'encounterService', 'uicommons.filters', 'uicommons.widget.select-concept-from-list',
    'uicommons.widget.select-order-frequency', 'uicommons.widget.select-drug', 'session', 'orderEntry']).


controller('HtmlEtlCtrl', ['$scope', '$window', '$location', '$timeout', '$q', 'OrderService', 'EncounterService', 'SessionInfo', 'OrderEntryService',
    function($scope, $window, $location, $timeout, $q, OrderService, EncounterService, SessionInfo, OrderEntryService) {

        // TODO changing dosingType of a draft order should reset defaults (and discard non-defaulted properties)


        var forms = [
            {
                name:"Triage",
                formName:"Triage",
                ddlQuery:"test"

            },
            {
                name:"Triage",
                formName:"Triage",
                ddlQuery:"test"

            }];
        var etlForms = OpenMRS.Etlforms;
        $scope.init = function() {

            $timeout(function() {
                $q.all(etlForms)
                    .then(function(results) {
                        $scope.form =results ;
                        console.log('results',results);
                    });

            },500);
        };

        $scope.selectedRow = null;
        $scope.showDmlQuery = function(test) {
            $scope.dmlObj=test;

        };
        $scope.showDdlQuery = function(test) {
            $scope.ddlObj = test

        };



        $scope.closeModal = function() {
            $('#generalMessage').modal('hide');
            $('#ddlMessage').modal('hide');

        };

        // events

        $scope.$on('added-dc-order', function(dcOrder) {
            $timeout(function() {
                angular.element('#draft-orders input.dc-reason').last().focus();
            });
        });

    }]);
$(document).ready(function(){
    $('.datepicker').datepicker({
        format: "dd/mm/yyyy",
        clearBtn: "false",
        language: "es",
        autoclose: true,
        orientation: "top",
        defaultViewDate: {
            month: '04',
            day:'15',
            year: '1990'
        },
    });

    $('.datepickerdown').datepicker({
        format: "dd/mm/yyyy",
        todayBtn: "linked",
        language: "es",
        autoclose: true,
        orientation: "bottom",
    });
});
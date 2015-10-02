$(function() {
    $.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );
    $( "#beginDate" ).datepicker({
        onClose: function( selectedDate ) {
            var dateMin = new Date($("#beginDate").datepicker( "getDate" ));
            dateMin.setDate(dateMin.getDate() + 1);
            $( "#endDate" ).datepicker( "option", "minDate", dateMin);
        }
    });
    $( "#endDate" ).datepicker({
        onClose: function( selectedDate ) {
            var dateMax = new Date($("#endDate").datepicker( "getDate" ));
            dateMax.setDate(dateMax.getDate() + 1);
            $( "#beginDate" ).datepicker( "option", "maxDate", selectedDate );
        }
    });

    $('#anyDate').click(function() {
        if ($(this).is(':checked')) {
            $( "#endDate" ).datepicker("setDate", null );
            $( "#beginDate" ).datepicker("setDate", null );
            $( "#endDate" ).datepicker( "option", "disabled", true );
            $( "#beginDate" ).datepicker( "option", "disabled", true );
        }else{
            $( "#endDate" ).datepicker( "option", "disabled", false );
            $( "#beginDate" ).datepicker( "option", "disabled", false );
        }
    });


    $( "#location" ).autocomplete({
        source: function (request, response) {
            $.getJSON("/search/hotel/?term=" + request.term, function (data) {
                response($.map(data, function (item) {
                    return {
                        label: item.cidade +" - "+item.nome,
                        value: item.id
                    }
                }));
            });
        },
        minLength: 3,
        delay: 100 ,
        select: function (event, ui) {
            $("#location").val(ui.item.label);
            $("#locationId").val(ui.item.value);
            return false;
        },
        focus: function (event, ui) {
            $("#location").val(ui.item.label);
            $("#locationId").val(ui.item.value);
            return false;
        }
    });

});
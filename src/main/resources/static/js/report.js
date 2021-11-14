$(document).ready(function(){
    $('#btn').click(function(){
        loadSale();
    })
    
});

function loadSale(){
    var promiseListTable = $.ajax({
        url: 'http://localhost/store/api/sale/list',
        contentType:'application/json', 
        type:'GET'
    });
    promiseListTable.done(function(data,textStatus,jqXHR){
        console.log(data);
        var list = data.data;
        var tableBody = $('#bodyReport');
        tableBody.html('');

        $.each(list, function(index, element){
            console.log(element);
            let tableRow = $('<tr/>');
            
            let tableData = addCellData(element.date);
            tableRow.append(tableData);

            tableData = addCellData(element.discount);
            tableRow.append(tableData);

            let neto = element.total - element.iva;
            tableData = addCellData(neto);
            tableRow.append(tableData);
            
            tableData = addCellData(element.iva);
            tableRow.append(tableData);

            tableData = addCellData(element.total);
            tableRow.append(tableData);

            tableBody.append(tableRow);
        }) 
        
    });
    
    promiseListTable.fail(function(jqXHR, textStatus, errorThrown) { 
        console.log("Error");
        console.log(jqXHR);
    })
    promiseListTable.always(function(jqXHROrData, textStatus, jqXHROrErrorThrown) { 
        console.log("complete"); 
    })
    
}

function addCellData(data){
    let tableData = $('<td/>');
    tableData.html(data)
    return tableData;
}
function addCellButton(data){
    tableData = $('<td>');
    tableData.html(data)
    return tableData;
}

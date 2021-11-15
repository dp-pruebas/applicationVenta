const domain = 'http://localhost:8080';

$(document).ready(function(){
    $('#btnOpenInsertSaleModal').click(function(){
        openInsertSaleModal()
    });
    $('#btnAddDetail').click(function(){
        insertDetailLocalStorage();
    });
    loadSale();
    
});

function insertDetailLocalStorage(){
    let producId = $('#cboProduct-insert').val();
    let amount = $('#txtAmount-insert').val();
    listProduct = JSON.parse(localStorage.getItem('product'));
    let product=null; 
    element = listProduct[producId];
    let amountElement = element.amount;
    let priceElement = element.price;
    
    if(amountElement >= amount){
        amountElement = amountElement - amount;
        let subtotal = amount * priceElement;
        detail = {
            'productId': producId,
            'amount':amount,
            'subtotal': subtotal,
        }
        element.amount = amountElement;
        listProduct[producId] = element;

        let table = $('#tableDetailModal-insert');
        let tr = $('<tr/>');
        let td = $('<td/>');
        td.html(detail.productId);
        tr.append(td);
        td = $('<td/>');
        td.html(detail.amount);
        tr.append(td);
        td = $('<td/>');
        td.html(detail.subtotal);
        tr.append(td);
        table.append(tr);
        localStorage.setItem('product',listProduct[producId]);
    }
    
}

function openInsertSaleModal(){
    var date = new Date();
    let dateText = addDigit(date.getDate(),2,'0')+'-'+addDigit(date.getMonth()+1,2,'0')+'-'+date.getFullYear();
    $('#txtDate-insert').val(dateText);
    loadCboProductInsert()
    openModalInsert();
}
function loadCboProductInsert(){
    var promiseLoadCboProduct = $.ajax({
        url: domain + '/store/api/product/list',
        contentType:'application/json', 
        type:'GET'
    });
    promiseLoadCboProduct.done(function(data,textStatus,jqXHR){
        let list = data.data;
        let cbo = $('#cboProduct-insert');
        cbo.html('');
        listProduct = [];
        $.each(list, function(index, element){
            listProduct[element.id] = element;
            op = $('<option/>');
            op.val(element.id);
            op.html(element.name+' cant:'+element.amount+' - $'+element.price  );
            cbo.append(op);
        });
        localStorage.setItem('product',JSON.stringify(listProduct)); 
    });
    promiseLoadCboProduct.fail(function(jqXHR, textStatus, errorThrown){
        console.log(jqXHR);

    })
}
function addDigit(num, size, character){
    num = String(num);
    for(let i=0; i < size;i++){
        if(num.length < size){
            num = character + num;
        }
    }
    return num;
}


function loadSale(){
    var promiseListTable = $.ajax({
        url: domain + '/store/api/sale/list',
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
            
            let tableData = addCellData(element.id);
            tableRow.append(tableData);

            tableData = addCellData(element.date);
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

            tableData = addCellButton(element.id);
            tableRow.append(tableData);

            tableBody.append(tableRow);
        }) 
        
    });
    promiseListTable.fail(function(jqXHR, textStatus, errorThrown) { 
        console.log("Error");
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
    let tableData = $('<td>');
    let btnEdit = $('<button/>');
    btnEdit.html('Editar');
    btnEdit.attr('onClick','editSale('+data+');');
    btnEdit.addClass('btn');
    btnEdit.addClass('btn-primary');
    btnEdit.addClass('btn-sm');
    tableData.append(btnEdit);
    let btnDelete = $('<button/>');
    btnDelete.html('Eliminar');
    btnDelete.attr('onClick','deleteSale('+data+');');
    btnDelete.addClass('btn');
    btnDelete.addClass('btn-danger');
    btnDelete.addClass('btn-sm');
    tableData.append(btnDelete);
    let btnView = $('<button/>');
    btnView.html('Ver');
    btnView.attr('onClick','viewSale('+data+');');
    btnView.addClass('btn');
    btnView.addClass('btn-secondary');
    btnView.addClass('btn-sm');
    tableData.append(btnView);
    return tableData;
}

function editSale(id){
    alert('edit: '+id);
}

function deleteSale(id) {
    let promiseDeleteSale = null;
    if(confirm("Esta seguro del eliminar la venta "+id)){
        promiseDeleteSale = $.ajax({
            url: domain + '/store/api/sale/delete/'+id,
            contentType:'application/json', 
            type:'GET'
        });
    }
    
    promiseDeleteSale.done(function(data,textStatus,jqXHR){
        if(data.code == 0){
            let affectedRows = data.data;
            loadSale();
            alert('se han eliminado '+affectedRows+' ventas');
        }
    });
    promiseDeleteSale.fail(function(jqXHR, textStatus, errorThrown){
        console.log(jqXHR);
    });
    promiseDeleteSale.always(function(jqXHROrData, textStatus, jqXHROrErrorThrown) { 
        console.log("complete delete"); 
    })
}

function viewSale(id){
    var promiseModal = $.ajax({
        url: domain + '/store/api/sale/get/'+id,
        contentType:'application/json', 
        type:'GET'
    });
    var primiseDetailTableModal = $.ajax({
        url: domain + '/store/api/sale/detail/'+id,
        contentType:'application/json', 
        type:'GET'
    });
    promiseModal.done(function(data,textStatus,jqXHR){
        let element = data.data;
        $('#txtDate-view').val(element.date);
        $('#txtDiscount-view').val(element.discount);
        $('#txtIva-view').val(element.iva);
        let neto = element.total - element.iva;
        $('#txtNeto-view').val(neto);
        $('#txtTotal-view').val(element.total);
        openModalView();
    });
    promiseModal.fail(function(jqXHR, textStatus, errorThrown) {
        console.log(jqXHR);
    });

    primiseDetailTableModal.done(function(data,textStatus,jqXHR){
        var list = data.data;
        var tableBody = $('#tableDetailModal-view');
        tableBody.html('');

        $.each(list, function(index, element){
            console.log(element);
            let tableRow = $('<tr/>');
            
            let tableData = addCellData(element.productId);
            tableRow.append(tableData);
            
            tableData = addCellData(element.amount);
            tableRow.append(tableData);

            tableData = addCellData(element.subtotal);
            tableRow.append(tableData);
            tableBody.append(tableRow);
        });
    });
    primiseDetailTableModal.fail(function(jqXHR, textStatus, errorThrown) {
        console.log(jqXHR);
    });
    
}
function openModalView(){
    $('#viewModal').show();
}
function closeModalView(){
    $('#viewModal').hide();
}
function openModalInsert(){
    $('#insertModal').show();
}
function closeModalInsert(){
    $('#insertModal').hide();
}

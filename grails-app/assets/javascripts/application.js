// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-3.3.1.min
//= require bootstrap
//= require popper.min
//= require_self

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode;
   if (charCode != 46 && charCode > 31 
     && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function displayFileName(id){
   var path = $(id).val();
   var fileName = path.substring(path.lastIndexOf('\\')+1);
   $(id).next('.custom-file-label').html(fileName);
}

function creditCardFormat(id){
   $(id).val($(id).val().replace(/(\d{4})(\d+)/g, '$1 $2'))
}

function formatNumber(event){
   let value =  event.target.value || '';
   value = value.replace(/[^0-9 ]/,'');
   event.target.value = value;
}

function formatDatePickers(elements){
   elements.forEach(function(entry) {
      let dateElements = ['month','day','year']
      dateElements.forEach(function(append) {
         $("#" + entry + "_" + append).addClass("form-control");
      });
  });
}

formatDatePickers(['birthDate','expirationDate'])
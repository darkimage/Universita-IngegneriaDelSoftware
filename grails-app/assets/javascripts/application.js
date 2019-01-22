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
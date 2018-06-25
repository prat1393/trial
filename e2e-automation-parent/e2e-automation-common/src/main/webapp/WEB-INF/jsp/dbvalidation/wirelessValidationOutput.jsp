<%@ page import ="java.util.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<script src="//mrrio.github.io/jsPDF/dist/jspdf.debug.js"></script>
<script>
function fnExcelReport()
{
    var tab_text="<table border='1px'><tr bgcolor='#87AFC6'>";
    var textRange;
    var j=0;
    tab = document.getElementById('example1'); // id of table
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input paramsss
    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
    }

    tab_text=tab_text+"</table>";
    /* var result = "data:application/vnd.ms-excel,";
    this.href = tab_text;
    this.download = "my-custom-filename.xls";
    return true; */

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 
    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))     
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Thanks");
    }  
    else 
     sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));   
  	
    return (sa);
}

</script>
<script>
	function show1(){
	  document.getElementById('allQuery').style.display ='block';
	  document.getElementById('individualQuery').style.display ='none';
	}
	function show2(){
		document.getElementById('allQuery').style.display ='none';
		  document.getElementById('individualQuery').style.display ='block';
	}
</script>


 </head>
<body>
<div class="wrapper">

 <%@include file="../template.jsp" %> 

<div class="content-wrapper"> 
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Wireless Validation
        <small>${release}</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Forms</a></li>
        <li class="active">General Elements</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">

      <!-- SELECT2 EXAMPLE -->
      <div class="box box-default">
        <div class="box-header with-border">
          <h3 class="box-title">Input Form</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
          </div>
        </div>
         <div class="box-body">
          <div class="row">
            <div class="col-md-12">
            <form:form action="wirelessvalidationoutput" method="POST" modelAttribute="banlist">
            <div class="form-group">
               <label>Select BAN-MarketCode-CTN to validate</label>
                <br>
                <form:select path="ban" class="form-control select2" multiple="multiple" data-placeholder="SelectBAN" style="width: 50%;">
    				<form:option value="ALL" label="ALL" />
    				<form:options items="${bans}"/>
				</form:select>
            </div>
            <div class="form-group"><!-- class="minimal" -->
				<label >Select Input Method for Validation</label><br>				
				<form:radiobutton path="e2eQueries" value="allQueries" onclick="show1()" label="E2E Backend"/>
				<form:radiobutton path="e2eQueries" value="individualQueries" onclick="show2()" label="Individual Backend"/>
			</div>  
			<div class="form-group" id="allQuery">
				<div class="col-xs-12">
          			<div class="box box-default">
            			<div class="box-header with-border">
              				<h3 class="box-title">Wireless Validation Backends</h3>
          			 	 </div>	
           				 <div class="box-body">
              				<label class="btn btn-default" data-toggle="modal" data-target="#modal-default" 
              					title="Check BAN status in OMS,Check Validation in TBBAN,Check Address">
               					CCR Backend
              				</label>
              				<label class="btn btn-info">
                				MPS Backend
              				</label>
             				<label class="btn btn-danger" data-toggle="modal" data-target="#modal-danger">
                				Enabler Backend
              				</label>
              				<label class="btn btn-warning" data-toggle="modal" data-target="#modal-warning">
               				 	TLG Backend
              				</label>
              				<label class="btn btn-success" data-toggle="modal" data-target="#modal-success">
                				BATCH Backend
              				</label>
              				
            </div>
          </div>
        </div>		
			</div> 
             <div class="form-group" id="individualQuery" style="display:none;">
            	 <div class="box box-default">
            		<div class="box-header with-border">
              			<h3 class="box-title">Individual  Backend Validation</h3>
          			 </div>	
          		<div class="box-body">	 
             	<div class="col-md-4">
              	<label>CCR Backend</label>
                <br>
                
                <form:select path="query" class="form-control select2" multiple="multiple" data-placeholder="Select CCR Query"
                        style="width: 75%;">
                  <form:option value="CCR_WLS_ALL">ALL</form:option>
    			  <form:options items="${ccrQueries}"/>
                </form:select> 
                </div>
                <div class="col-md-4">
                <label>MPS Backend</label>
                <br>
                <form:select path="query" class="form-control select2" multiple="multiple" data-placeholder="Select MPS Query"
                        style="width: 75%;">
                       <form:option value="MPS_WLS_ALL">ALL</form:option>
                       <form:options items="${mpsQueries}"/>
                </form:select> 
                </div>
                <div class="col-md-4">
               	<label>Enabler Backend</label>
                <br>
                <form:select path="query" class="form-control select2" multiple="multiple" data-placeholder="Select Enabler Query"
                        style="width: 75%;">
                  <form:option value="ENABLER_WLS_ALL">ALL</form:option>
                  <form:options items="${enablerQueries}"/>
                </form:select> 
                </div>
                <br>
                <br>
                <br>
                <br>
                <div class="col-md-4">
             	<label>TLG Backend</label>
                <br>
                <form:select path="query" class="form-control select2" multiple="multiple" data-placeholder="Select TLG Query"
                        style="width: 75%;">
                  <form:option value="TLG_WLS_ALL">ALL</form:option>
                  <form:options items="${tlgQueries}"/>
                </form:select> 
                </div>
                <div class="col-md-4">
                <label>Batch Backend</label>
                <br>
                <form:select path="query" class="form-control select2" multiple="multiple" data-placeholder="Select Batch Query"
                        style="width: 75%;">
                  <form:option value="BATCH_WLS_ALL">ALL</form:option>
                  <form:options items="${batchQueries}"/>
                </form:select> 
                </div>
                </div>
              </div>
             </div> 
              
              
              
               <!-- <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
              </div> -->
            <button type="submit" class="btn btn-primary" name="wirelesssubmit">Submit</button>
            </form:form>
             </div>
          </div>
        </div>
      </div>
      
     
      
      
      <c:if test="${not empty resultList}">
      <div class="row">
        <div class="col-xs-12">
          <div class="box box-danger">
            <div class="box-header">
              <h3 class="box-title" style="margin-right:30px">Output Table</h3>     
              
              <%-- <c:forEach var="entry" items="${map}">
              	<c:if test="${entry.value == 'Fail'}">
   						<span class="label label-danger" style="margin-right: 15px;">${entry.key}</span>
  					</c:if>
  				<c:if test="${entry.value == 'Pass'}">
    					<span class="label label-success" style="margin-right:15px">${entry.key}</span>
  					</c:if>    
            </c:forEach> --%>
                       
              <button type="submit" class="btn btn-primary pull-right" style="float:right" onClick="fnExcelReport()">
              	<i class="fa fa-download"></i>
              	 Export to Excel
              	</button>
              <!-- <button type="submit" class="btn btn-primary" style="float:right;margin-right: 15px;" onclick="javascript:pdfExport()">Export to PDF</button> -->
              <!-- <button type="submit" class="btn btn-primary" style="float:right;margin-right: 15px;" onclick="myFunction()">
              	<i class="fa fa-credit-card"></i>
              	Reset
              </button> -->
              
             <!-- <input type="button" name="Submit" value="Reset" style="float:right" onclick="myFunction()"/> 
              <input type="button" name="Submit"value="Export to Excel" style="float:right" onClick="fnExcelReport()"/> --> 
              <!-- <input type="button" name="Submit"value="Export to PDF" style="float:right"/>  -->
            </div>
           
            <!-- /.box-header -->
            <div class="box-body">
           <%--  <form:form action="query" method="POST" modelAttribute="banlist"> --%>
            <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>BAN</th>
                  <th>Backend</th>
                  <th>Query Name</th>
                  <th>DB Value</th>
                  <th>Status</th>
                  <th>Query</th>
                </tr>
                </thead>
                <tbody>               
                  <c:forEach var="listValue" items="${resultList}">
					 <tr>${listValue}</tr>  
					</c:forEach>	
                </tbody>
              </table>
             <%--  </form:form> --%>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
    	
	</c:if>
    </section>
    <!-- /.content -->
  
  
    
</div> 

<%-- <%@include file="footer.jsp" %>  --%>
</div>

<script>
  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>


<script>
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
    //Money Euro
    $('[data-mask]').inputmask()

    //Date range picker
    $('#reservation').daterangepicker()
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A' })
    //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
      }
    )

    //Date picker
    $('#datepicker').datepicker({
      autoclose: true
    })

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
      checkboxClass: 'icheckbox_minimal-red',
      radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass   : 'iradio_flat-green'
    })

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
  })
</script>


</body>
</html>

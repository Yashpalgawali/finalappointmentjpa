/**
 * 
 */
 $(document).ready(function(){
		$('#company').select2({
			theme	:	'classic',
			width	:	'resolve'
		});
	})
 
 function getdepartmentbycomp(cid)
{
	let dept_arr="";
	var app_name = $('#app_name').val();
	$('#depttable').dataTable().fnClearTable();
	$('#depttable').dataTable().fnDestroy();
	
	$.ajax({
		url      : "/"+app_name+"/getdeptbycompid/"+cid,
		// url		: 'getdeptbycompid/'+cid,
		type	:	'GET',
		dataType: 	'JSON',
		success : function(result){
			let cnt =1;
			for(let i=0;i<result.length;i++){
				dept_arr =  dept_arr 
							+"<tr><td>" +(cnt++)
							+"</td><td>"+result[i].dept_name
							+"</td><td>"+result[i].company.comp_name	
							+"</td><td><a href= 'editdeptbyid/"+result[i].dept_id+"'><i class='fa fa-edit'></i>Edit</a></td></tr>"
							// +"</td><td><a href= '/editdeptbyid/"+result[i].dept_id+"'><i class='fa fa-edit'></i>Edit</a></td></tr>"
			}
			$(dept_arr).appendTo('#deptbody');
			$('#depttable').DataTable();
		}
	});
}
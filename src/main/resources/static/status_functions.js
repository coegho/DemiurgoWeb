$(".remodal a").ready(function() {
	$(".remodal a").click(function() { return confirm("Are you sure you want to destroy this object?")});
});

$(".obj").ready(function() {
	invs.forEach(function(inv, invindex) {
		inv.objects.forEach(function(obj, objindex) {
			$("#inv"+invindex+"-obj"+objindex).data("obj", obj)
			  .dblclick(function() { loadObjModal(obj) });
		});
	});
});

function loadObjModal(item) {
	$("#inv_fields").html("");
	
	$("#inv_objname").text(item.name);
	
	$("#inv_v_name").text(item.name);
	
	if(item.imgUrl != null) {
		$("#inv_obj_img").html(jQuery("<img />").attr("src", item.imgUrl));
	}
	else {
		$("#inv_obj_img").html("");
	}
	
	if(item.description != null) {
		$("#inv_description").text("\""+ item.description + "\"");
	}
	else
		$("#inv_description").text("");
	item.fields.forEach(function(f, i) {
		$("#inv_fields")
		.append("<p>"+ f.name+ " = '"+ f.value+ "'</p>");
	});
	location.hash = "#object";
}
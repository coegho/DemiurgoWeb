/* VARIABLES */

$("decisions").ready(
		function() {
			room.users.forEach(
					function(u, i) {
						$("#user-"+u.name+" .userobj .obj")
						.data("obj", u.obj)
						.data("dropvalue", "#"+u.obj.id)
						.draggable({
							revert : true
						})
						.dblclick(function() {loadObjModal(u.obj)});
						$("#user-"+u.name+" .user").draggable({revert : true})
						.data("dropvalue", "$"+u.name)
					});
		});

$("#variables").ready(
		function() {
			$(".var").each(
					function() {
						$(this).dblclick(function() {
							$(this).toggleClass("selected")
							
						}).draggable({ revert : true }).data("dropvalue", $(this).children(".varname").text());
						$(this).children(".var-close").click(vardelwarning);
					});
		});

function vardelwarning() {
	return confirm("Are you sure you want to delete variable?")
}

/* OBJECTS */
var noobj;
var selectedItem = undefined;

$("#selected_obj").ready(function() {
	noobj = $("#selected_obj").html();
	room.objects.forEach(function(item, index) {
		$("#obj"+item.id)
			.data("obj", item)
			.data("dropvalue", "#"+item.id)
					.dblclick(function() { loadObjModal(item) })
					.draggable({
						revert : true
					})
	});
});


$(".roomselector .selectroom div").ready(function() {
	$(".roomselector .selectroom div").each(function(i, li) {
		$(li).draggable({revert : true}).data("dropvalue", "@"+$(li).children("span").text());
	}); 
});

$(".lapels").ready(function() {
	$(".lapels").droppable({
		tolerance : "pointer",
		accept: ".obj",
		drop : function(event, ui){
			selectObject($(ui.draggable).data("obj"));
		}
	});
	
	room.objects.forEach(function(item, index) {
		$("#lapel-"+item.id)
			.click(function() { selectObject(item); return false } )
			.hover(function(){ showObject(item)},
					function() { showObject(selectedItem); })
	});
});

$("#code").ready(function() {
	$("#code").droppable({
		tolerance: "pointer",
		drop : function(event, ui) {
			$("#code").val(function(i, val) {
				return val +$(ui.draggable).data("dropvalue") + " "
			}).focus()
		}
	});
});


function v_name(item) {
	var v_name = item.classname;
	item.fields.forEach(function(f, i) {
		if(f.name == "v_name") {
			v_name = f.value;
		}
	});
	return v_name;
}

function makeVariable(name, type, value) {
	return jQuery("<div />").addClass("var " +getTypeCss(type))
			.attr("title", type)
			.append(
					jQuery("<span />").text(name).addClass(
							"varname"),
					jQuery("<span />").text(
							((value.length > 10)?value.substring(0,7)+"...":value)).addClass(
							"prev"),
					jQuery("<span />").text(value)
							.addClass("varval")).dblclick(function() {
				$(this).toggleClass("selected")
				
			}).draggable({ revert : true });
}

function selectObject(item) {
	id = item.id;
	showObject(item);
	selectedItem = item;
	$(".obj.selected").removeClass("selected");
	$(".lapel.selected").removeClass("selected");
	$(".lapel.temporal").remove();
	$("#obj"+id).addClass("selected");
	$("#lapel-"+id).addClass("selected");
	if(!$("#lapel-"+id).length) {
		$(".lapels").prepend(jQuery("<div/>",
				{ "id": "lapel-"+id,
				   "class": "lapel temporal float"})
				   .append(jQuery("<a/>", {"href": "#"}).text("#"+id))
				   .click(function() { return false } ));
	}
}


function loadObjModal(item) {
	$("#modal_fields").html("");
	
	$("#v_name").text(item.name);
	
	$("#classname").text(item.classname);
	
	$("#objid").text("#"+item.id);
	
	if(item.imgUrl != null) {
		$("#obj_img").html(jQuery("<img />").attr("src", item.imgUrl));
	}
	else {
		$("#obj_img").html("");
	}
	
	if(item.description != null) {
		$("#description").text("\""+ item.description + "\"");
	}
	else
		$("#description").text("");
	item.fields.forEach(function(f, i) {
		$("#modal_fields")
		.append("<p>"+ f.type + " " + f.name+ " = "+ f.value+ "</p>");
	});
	$(".remodal a")//.attr("href", "destroyobj?id="+item.id+"&back=room?path="+ $("#path").text());
	.data("dropvalue", "#"+item.id);
	location.hash = "#object";
}

function showObject(item) {
	$(".objpanel").hide();
	//showing
	if(item === undefined) {
		$("#objpanel").show();
	} else {
		var id = item.id;
		if($("#objpanel-"+id).length) {
			$("#objpanel-"+id).show();
			
		}else {
			//create new panel
			$("#allobjpanels").append(jQuery("<div/>",
					{id : "objpanel-"+id})
					.addClass("objpanel")
					.html($("#objpanel").html()));
			
			//var item = $("#obj"+id).data("obj");
			$("#objpanel-"+id+ " h2").text(item.name + " ("+item.classname+")");
			item.fields.forEach(function(f, i) {
				$("#objpanel-"+id+ " .fields")
					.append(makeVariable(f.name, f.type, f.value).data("dropvalue", "#"+item.id+"."+f.name));
			});
			
			item.methods.forEach(function (m, i) {
				$("#objpanel-"+id+ " .methods").append(makeMethod(m)
						.draggable(
							{ revert : true ,
							  zIndex: 100,
							  start : function(event, ui) {
								  $(this).data("dropvalue",
											"#"+item.id+"."+m.name+" ( "+
											$.map($(this).children("input"), function(e,i){return e.value}).join(", ")+" ) ")
							  } }));
			});
			
			item.inventories.forEach(function(inventory, i) {
				$("#objpanel-"+id+ " .inventorypanel").append(
						jQuery("<div />",
								{"class": "inv-"+inventory.name+" data_select inventory"})
						 .append(jQuery("<h4/>")
								 .append(makeVariable(inventory.name, "INVENTORY", "%")
										 .data("dropvalue", "#"+item.id+"."+inventory.name)))
						 );
				inventory.objects.forEach(function(item, index) {
					jQuery("<div/>", {
						id : "obj" + item.id,
						class : "obj",
						})
					.data("obj", item)
					.data("dropvalue", "#"+id+"."+inventory.name+"["+index+"]")
					.append(jQuery("<img/>").attr("src", "images/icons/requiario.png"))
					.append("<span class='varname'>[" + index + "] </span>")
					.append("<span>" + item.name + "</span>")
					.appendTo("#objpanel-"+id+ " .inv-"+inventory.name)
					.dblclick(function(){ loadObjModal(item) })
					.draggable({
						revert : true
					});
				});
				$("#objpanel-"+id+ " .inv-"+inventory.name).append("<div style='clear:both;'></div>");
			});
			
		}
		}
}


function makeMethod(m) {
	var met = jQuery("<div />").addClass("method").append(
			jQuery("<span />").addClass("metname").text(m.name)
			);
	m.args.forEach(function(arg, index) {
		//jQuery("<label />", {"for" : "arg-"+m.name+"-"+arg, "text" : arg}).appendTo(met);
		jQuery("<input />",
				{"id" : "arg-"+m.name+"-"+arg, "placeholder" : arg})
				.appendTo(met).droppable({
					tolerance: "pointer",
					drop : function(event, ui) {
						$(this).val(function(i, val) {
							return $(ui.draggable).data("dropvalue")
						})}});
	})
	return met
	
}

$(".remodal a").ready(function() {
	//$(".remodal a").click(function() { return confirm("Are you sure you want to destroy this object?")});
	$(".remodal a").click(function() {
		myCodeMirror.setValue(myCodeMirror.getValue()+
				"\n destroy("+$(this).data("dropvalue") + ") "
				);
		myCodeMirror.focus();
		return true;
	});

});

function getTypeCss(type) {
	if(type.endsWith("[]")) {
		return "arrayvar";
	}
	if(type.startsWith("OBJECT")) {
		return "objvar";
	}
	return type.toLowerCase() + "var";
}
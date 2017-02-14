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
						});
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
var selectedId = -1;

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
			selectObject($(ui.draggable).data("obj").id);
		}
	});
	
	room.objects.forEach(function(item, index) {
		$("#lapel-"+item.id)
			.click(function() { selectObject(item.id); return false } )
			.hover(function(){ showObject(item.id)},
					function() { showObject(selectedId); })
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


/*function makeObject(item) {
	return jQuery("<div/>", {
		class : "obj",
		})
	.data("obj", item)
	.data("dropvalue", "#"+item.id)
	.append("<span>" + v_name(item) + "</span>")
	.append("<span class='varname'> (#" + item.id + ")</span>")
	.hover(function() { showObject(item.id) },
		   function() { showObject(selectedId); })
	.click(function() {
		selectObject(item.id)})
	.dblclick(function() { loadObjModal(item) })
	.draggable({
		revert : true
	});
}*/

function v_name(item) {
	var v_name = item.classname;
	item.fields.forEach(function(f, i) {
		if(f.name == "v_name") {
			v_name = f.value;
		}
	});
	return v_name;
}

function makeVariable(v) {
	return jQuery("<div />").addClass("var " +getTypeCss(v.type))
			.attr("title", v.type)
			.append(
					jQuery("<span />").text(v.name).addClass(
							"varname"),
					jQuery("<span />").text(
							((v.value.length > 10)?v.value.substring(0,7)+"...":v.value)).addClass(
							"prev"),
					jQuery("<span />").text(v.value)
							.addClass("varval")).dblclick(function() {
				$(this).toggleClass("selected")
				
			}).draggable({ revert : true });
}

function selectObject(id) {
	showObject(id);
	selectedId = id;
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
	$(".remodal a").attr("href", "destroyobj?id="+item.id+"&back=room?path="+ $("#path").text());
	location.hash = "#object";
}

function showObject(id) {
	if($("#objpanel").data("showing") != id) {

		$(".objpanel").hide();
		//showing
		if(id == -1) {
			$("#objpanel").show();
		} else if($("#objpanel-"+id).length) {
			$("#objpanel-"+id).show();
			
		}else {
			//create new panel
			$("#allobjpanels").append(jQuery("<div/>",
					{id : "objpanel-"+id})
					.addClass("objpanel")
					.html($("#objpanel").html()));
			
			var item = $("#obj"+id).data("obj");
			$("#objpanel-"+id+ " h2").text(item.name + " ("+item.classname+")");
			item.fields.forEach(function(f, i) {
				$("#objpanel-"+id+ " .fields")
					.append(makeVariable(f).data("dropvalue", "#"+item.id+"."+f.name));
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
								{"class": "inv-"+inventory.name+" data_select"})
						 .append("<h4>"+inventory.name+"</h4>"));
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
	$(".remodal a").click(function() { return confirm("Are you sure you want to destroy this object?")});
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
/* VARIABLES */

$("decisions").ready(
		function() {
			room.users.forEach(
					function(u, i) {
						$("#user-"+u.name+" .userobj")
						.append(makeObject(u.obj));
						$("#user-"+u.name+" h2").draggable({revert : true})
						.data("dropvalue", "$"+u.name)
					});
		});

$("#variables").ready(
		function() {
			room.variables.forEach(
					function(v, i) {
						$("#variables").append(makeVariable(v)
						.data("dropvalue", v.name).append(jQuery("<a />",
								{text:"x", class:"var-close", href:"delvar?path="+room.longPath+"&var="+v.name})
								.click(function() { return confirm("Are you sure you want to delete variable "+v.name+"?") })))
					});
		});

/* OBJECTS */
var noobj;
var selectedId = -1;

$("#selected_obj").ready(function() {
	noobj = $("#selected_obj").html();
	room.objects.forEach(function(item, index) {
		$("#objects").append(makeObject(item).attr("id", "obj"+item.id));
	});
});


$(".roomselector .selectroom div").ready(function() {
	$(".roomselector .selectroom div").each(function(i, li) {
		$(li).draggable({revert : true}).data("dropvalue", "@"+$(li).text());
	}); 
});

$("#code").ready(function() {
	$("#code").droppable({
		drop : function(event, ui) {
			$("#code").val(function(i, val) {
				return val +$(ui.draggable).data("dropvalue") + " "
			}).focus()
		}
	});
});


function makeObject(item) {
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
}

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
	return jQuery("<div />").addClass(
			"var" + (v.type.startsWith("OBJECT") ? " objvar" : ""))
			.append(
					jQuery("<span />").text(v.name).addClass(
							"varname"),
					jQuery("<span />").text("'"
							+ ((v.value.length > 10)?v.value.substring(0,10)+"...":v.value) + "'").addClass(
							"prev"),
					jQuery("<span />").text("'" + v.value + "'")
							.addClass("varval")).dblclick(function() {
				$(this).toggleClass("selected")
				
			}).draggable({ revert : true });
}

function selectObject(id) {
		showObject(id);
		selectedId = id;
		$(".obj.selected").removeClass("selected");
		$("#obj"+id).addClass("selected");
	//}
}

function loadObjModal(item) {
	$("#selected_obj").html("<h1>"+item.classname+"</h1><h2>#" + item.id + "</h2>");
	$("#obj_img").html("");
	item.fields.forEach(function(f, i) {
		if(f.name == "v_imgurl") {
			$("#obj_img").html(jQuery("<img />").attr("src", f.value));
			
		}
		else if(f.name == "v_name") {
			$("#v_name").text(f.value);
		}
		else
			$("#selected_obj")
			.append("<p>"+ f.type+ " "+ f.name+ " = '"+ f.value+ "'</p>");
	});
	location.hash = "#object";
}

function showObject(id) {
	if($("#selected_obj").data("showing") != id) {
		if(id == -1) {
			$("#fields").html("");
			$("#methods").html("");
			$("#inventorypanel").html("");
		}
		else {
			var item = $("#obj"+id).data("obj");
			$("#fields").html("");
			$("#methods").html("");
			$("#inventorypanel").html("");
			item.fields.forEach(function(f, i) {
				$("#fields").append(makeVariable(f).data("dropvalue", "#"+item.id+"."+f.name));
			});
			
			item.methods.forEach(function (m, i) {
				$("#methods").append(makeMethod(m).data("dropvalue",
						"#"+item.id+"."+m.name+" ( "+
						m.args.map(function(x){return "?"}).join(", ")+" ) "));
			});
			
			item.inventories.forEach(function(inventory, i) {
				$("#inventorypanel").append(
						jQuery("<details />",
								{id: "inv-"+inventory.name,
								 class: "data_select"})
						 .append("<summary>"+inventory.name+"</summary>"));
				inventory.objects.forEach(function(item, index) {
					jQuery("<div/>", {
						id : "obj" + item.id,
						class : "obj",
						})
					.data("obj", item)
					.data("dropvalue", "#"+item.id)
					.append("<span class='varname'>[" + index + "] -></span>")
					.append("<span> (" + item.classname + ")</span>")
					.appendTo("#inv-"+inventory.name)
					.dblclick(function(){ loadObjModal(item) })
					.draggable({
						revert : true
					});
				});
				$("#inv-"+inventory.name).append("<div style='clear:both;'></div>");
			});
			
			
		}
	}
}

function makeMethod(m) {
	return jQuery("<div />").addClass("method")
			.html(
					((m.returnArg != null)?"<span class='margs'>"+m.returnArg+" = </span>":"")
					+ "<span class='mname'>"+m.name+"</span> ("
					+ "<span class='margs'> " + m.args.join(", ") + "</span> )")
					.dblclick(function() {
				$(this).toggleClass("selected")
				
			}).draggable({ revert : true });
}
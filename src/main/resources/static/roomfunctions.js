/* VARIABLES */

$("#variables").ready(
		function() {
			room.variables.forEach(
					function(v, i) {
						$("#variables").append(makeVariable(v)
						.data("dropvalue", v.name))
					});
		});

/* OBJECTS */
var noobj;
var selectedId = -1;

$("#selected_obj").ready(function() {
	noobj = $("#selected_obj").html();
	room.objects.forEach(function(item, index) {
		jQuery("<div/>", {
			id : "obj" + item.id,
			class : "obj",
			})
		.data("obj", item)
		.data("dropvalue", "#"+item.id)
		.append("<span class='varname'>#" + item.id + "</span>")
		.append("<span> (" + item.classname + ")</span>")
		.appendTo("#objects")
		.hover(function() { showObject(item.id) },
			   function() { showObject(selectedId); })
			.dblclick(function() {
				selectObject(item.id)
			}).draggable({
				revert : true
			});
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
	if ($("#obj"+id).hasClass("selected")) {
		selectedId = -1;
		$(".obj.selected").removeClass("selected");
	} else {
		showObject(id);
		selectedId = id;
		$(".obj.selected").removeClass("selected");
		$("#obj"+id).addClass("selected");
	}
}

function showObject(id) {
	if($("#selected_obj").data("showing") != id) {
		$("#selected_obj").data("showing", id);
		if(id == -1) {
			$("#selected_obj").html(noobj);
			$("#obj_img").html("");
			$("#fields").html("");
			$("#methods").html("");
		}
		else {
			var item = $("#obj"+id).data("obj");
			$("#selected_obj").html("<h1>#" + item.id + " :: " + item.classname + "</h1>");
			$("#obj_img").html("");
			$("#fields").html("");
			$("#methods").html("");
			item.fields.forEach(function(f, i) {
				if(f.name != "img_url") {
					$("#selected_obj")
						.append("<p>"+ f.type+ " "+ f.name+ " = '"+ f.value+ "'</p>");
					
				}
				else
					$("#obj_img").html(jQuery("<img />").attr("src", f.value));
				$("#fields").append(makeVariable(f).data("dropvalue", "#"+item.id+"."+f.name));
			});
			
			item.methods.forEach(function (m, i) {
				$("#methods").append(makeMethod(m).data("dropvalue",
						"#"+item.id+"."+m.name+" ( "+
						m.args.map(function(x){return "?"}).join(", ")+" ) "));
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
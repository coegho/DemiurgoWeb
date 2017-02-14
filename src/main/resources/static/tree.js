$(".expandclasses").ready(function() {
	$(".expandclasses").each(function(){
		$(this).click(function(){
			$(this).parent().children("ul").toggle()
		})
	})
});

$("#expandall").ready(function() {
$("#expandall").click(function(){
	$(".tree ul ul").show()
})})

$("#collapseall").ready(function() {
$("#collapseall").click(function(){
	$(".tree ul ul").hide()
})})
var blockWidth = 16,
	blockHeight = 16,
	blockMargin = 2,
	width,
	height,
	selectedTeam = teams[0],
	paused = false;

$(".button_pause").click(function() {
	paused = !paused;
	$(this).css("background-image", "url('" + (paused ? "img/play.png" : "img/pause.png") + "')");
});

var canvas = $("#canvas")[0];
function resize() {
	canvas.width = 0;
	canvas.height = 0;
	canvas.width = window.innerWidth - 1;
	canvas.height = window.innerHeight - 1;
}
resize();

$(window).on("resize",function() {
	resize();
});

// Fit the number of blocks into the window
width = Math.ceil(window.innerWidth / (blockWidth + blockMargin));
height = Math.ceil((window.innerHeight - 16) / (blockHeight + blockMargin));

var mouse = {x:-1,y:-1};

function updateMouse(e) {
	mouse.x = Math.floor(e.pageX / (blockWidth + blockMargin));
	mouse.y = Math.floor((e.pageY-16) / (blockHeight + blockMargin));
	mouse.pageX = e.pageX;
	mouse.pageY = e.pageY;
}

$("canvas").mousemove(function(e) {
	updateMouse(e);
});

var down = false;
$("canvas").mousedown(function(e) {
	down = true;
}).mouseup(function(e) {
	down = false;
}).mouseleave(function() {
	mouse.x = -1;
	mouse.y = -1;
});

$("canvas").click(function(e) {
	updateMouse(e);
	if (e.pageY > 16) {
		onClick();
	}
});
var ctx = $("#canvas")[0].getContext("2d");

var grid = [];

var width = 40,
	height = 40;

function createGridBlock(x, y) {
	return {
		"x": x,
		"y": y,
		"color": 
	}
}
	
for (var x = 0; x < width; x++) {
	grid[x] = [];
	for (var y = 0; y < height; y++) {
		grid[x][y] = createGridBlock(x, y);
	}
}

function update() {
	
}

function render() {
	ctx.clearRect(0, 0, ctx.width, ctx.height);
	
	for (var x = 0; x < width; x++) {
		for (var y = 0; y < width; x++) {
			
		}
	}
}

setInterval(update, 100);
setInterval(render, 10);
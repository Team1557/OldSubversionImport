var	grid = true,
	gridMargin = 0,
	gridColor = "#545454";
	
var renderInterval = 1000 / 30;

function loadImage(url) {
	var img = new Image();
	img.src = "img/" + url;
	return img;
}

var images = {
	"cycle": loadImage("cycle.png"),
	"defense": loadImage("defense.png"),
	"king": loadImage("king.png"),
	"nuke": loadImage("nuke.png"),
	"suicide": loadImage("suicide.png"),
}

function lerp(x, y, a) {
	return x + (y - x) * a;
}

var interpolate = {
	a: 0
};
var tween = new TWEEN.Tween( interpolate )
	.to({ a: 1 }, updateInterval / 2)
	.easing(TWEEN.Easing.Cubic.InOut);
var tween2 = new TWEEN.Tween( interpolate )
	.to({ a: 1 }, updateInterval / 2)
	.easing(TWEEN.Easing.Linear.None)
	.onComplete(function() {
		// Reset the tween
		interpolate.a = 0;
		
		// Update, rinse, repeat
		update();
		tween.start();
	});

// Set the waiting tween to go after the visual tween
tween.chain(tween2);

// Start the loop
tween.start();

// Prepare rendering
var ctx = canvas.getContext("2d");

// Render
function render() {
	// Clear the board
	ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
	
	if (!paused) {
		// Update tween motions
		TWEEN.update();
	} else {
		interpolate.a = 1;
	}
	
	// Draw the grid
	if (grid) {
		ctx.fillStyle = gridColor;
		for (var x = 0; x < width; x++) {
			for (var y = 0; y < height; y++) {
				ctx.fillRect(
					gridMargin + x * (blockWidth + blockMargin), 
					gridMargin + y * (blockHeight + blockMargin),
					blockWidth - gridMargin * 2, 
					blockHeight - gridMargin * 2);
			}
		}
	}
	
	dots.forEach(function (dot) {
		// Color to this block's team
		ctx.fillStyle = dot.team.color;
		
		// Interpolate between the previous position, and the current position
		var x = lerp(dot.oldX, dot.x, interpolate.a),
			y = lerp(dot.oldY, dot.y, interpolate.a);
		
		// Determine the rendering size
		var sizeX,
			sizeY;
		if (dot.team == dot.oldTeam) {
			// Normal block size
			sizeX = sizeY = blockWidth;
		} else {
			// The block is morphing between teams, make it look small
			sizeX = sizeY = blockWidth * interpolate.a;
		}
		
		x = x * (blockWidth + blockMargin);
		y = y * (blockHeight + blockMargin);
		
		ctx.fillRect(
			(blockWidth-sizeX)/2 + x, 
			(blockHeight-sizeY)/2 + y, 
			sizeX, 
			sizeY);
		if (dot.type != undefined && dot.type.image != undefined) {
			ctx.drawImage(images[dot.type.image], x, y);
		}
	});
	
	ctx.fillStyle = "rgba(225,225,225,0.5)";
	ctx.fillRect(
		mouse.x * (blockWidth + blockMargin),
		mouse.y * (blockHeight + blockMargin),
		blockWidth,
		blockHeight);
	
	var hover = get(mouse.x, mouse.y);
	if (hover != undefined) {
		var h = window.innerHeight - 8;
		ctx.font = "16px 'Roboto', sans-serif";
		ctx.fillStyle = hover.team.color;
		ctx.fillText(hover.team.name, 0, h);
		h -= 16;
		if (hover.type != undefined && hover.type.description != undefined) {
			ctx.fillStyle = "white";
			ctx.fillText(hover.type.description, 0, h);
		}
	}
}

setInterval(render, renderInterval);
update();
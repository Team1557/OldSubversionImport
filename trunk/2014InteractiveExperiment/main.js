var ctx = $("#canvas")[0].getContext("2d");

var img = new Image(); img.src="stickman.png";

var sticks = [];

var colors = [
	"#FF0000",
	"orange",
	"yellow",
	"#33AA33",
	"blue",
	"purple",
	"black",
];

var idCount = 0,
	max = 200,
	spread = 400;

function spawnNew(color) {
	return sticks[sticks.length] = {
		"x": 200 + Math.random()*400,
		"y": 200 + Math.random()*400,
		"id": idCount + 1,
		"color": color,
		"life": 5000 + 5000 * Math.random(),
		"breed": 0,
	};
}
	
for (var i = 0; i < max; i++) {
	spawnNew(colors[Math.floor(Math.random() * colors.length)]);
}

var speed = 10,
	jump = 5;

setInterval(function() {
	ctx.clearRect(0,0,800,800);
	sticks.forEach(function(p) {
		p.life -= speed;
		p.breed -= speed;
		
		if (p.life < 0) {
			sticks.splice(sticks.indexOf(p), 1);
		}
		
		p.x = p.x + (Math.random() - .5) * jump;
		p.y = p.y + (Math.random() - .5) * jump;
		
		if (p.x < 250) {
			p.x += 0.1;
		} else if (p.x > 350) {
			p.x -= 0.1;
		}
		
		
		if (p.y < 200) {
			p.y += 0.1;
		} else if (p.y > 400) {
			p.y -= 0.1;
		}
	});
	
	sticks.forEach(function(p) {
		sticks.forEach(function(p2) {
			if (Math.abs(p.x - p2.x) < 6 && Math.abs(p.y - p2.y) < 6) {
				if (p.color == p2.color && p.breed < 0 && p2.breed < 0) {
					if (Math.random() < 0.01) {
						p.breed = 3000 + Math.random() * 3000;
						p2.breed = 3000 + Math.random() * 3000;
						var newStick = spawnNew(p.color);
						newStick.breed = 3000;
						newStick.x = (p.x+p2.x)/2;
						newStick.y = (p.y+p2.y)/2;
					}
				} else {
					if (Math.random() < 0.5) {
						p.color = p2.color;
					} else {
						p2.color = p.color;
					}
				}
			}
		});
	});
	
	sticks.sort(function(a,b){
		return a.y > b.y;
	});
	
	var colorCounts = {};
	sticks.forEach(function(p) {
		if (colorCounts[p.color] == undefined) {
			colorCounts[p.color] = 1;
		} else {
			colorCounts[p.color]++;
		}
	
		ctx.drawImage(img, Math.floor(p.x), Math.floor(p.y));
		
		ctx.strokeStyle=p.id == 42 ? "red": "black"
		ctx.lineWidth=p.id==42 ? 20 : 10;
		ctx.beginPath();
		ctx.arc(p.x + 15, p.y + 8, 3,0,2*Math.PI);
		ctx.stroke();
		
		ctx.strokeStyle = p.color;
		ctx.lineWidth=10;
		ctx.beginPath();
		ctx.arc(p.x + 15, p.y + 8, 2,0,2*Math.PI);
		ctx.stroke();
		//ctx.fillStyle = "black";
		//ctx.fillText("Branticus " + p.id, p.x - 10, p.y - 3);
	});
	
	var str = "";
	for (var i=0;i<colors.length;i++) {
		var count = colorCounts[colors[i]]
		if (count != undefined)
			str += "<div style='background-color:"+colors[i]+";color:white;padding:3px;width:"+(count/max*100)+"%'>"+count+"</div><br/>";
	}
	
	if (Object.getOwnPropertyNames(colorCounts).length <= 1) 
		speed = 100000000000;
	$("#counter").html(str);
}, speed);
var types = {
	"king": {
		"image": "king",
		"name": "King",
		"description": "Hail to the king, baby!"
	},
	"shield": {
		"image": "defense",
		"name": "Knight",
		"description": "The knight is unmatched in battle."
	},
	"shapeshifter": {
		"image": "cycle",
		"name": "Shapeshifter",
		"description": "The shapeshifter occasionally changes itself and it's neighbors to a random team.",
		"update": function() {
			this.type.cycle = (this.type.cycle || 0) + 1;
			
			if (this.type.cycle > 20) {
				this.team = teams[random(0, teams.length-1)];
				ai.infect(this, get(this.x, this.y - 1));
				ai.infect(this, get(this.x, this.y + 1));
				ai.infect(this, get(this.x - 1, this.y));
				ai.infect(this, get(this.x + 1, this.y));
				this.type.cycle = 0;
			}
		}
	},
	"nuke": {
		"image": "nuke",
		"name": "Bomber",
		"description": "The bomber is a ticking time-bomb",
		"update": function() {
			if (Math.random() < 0.01) {
				for (var x = -2; x <= 2; x++) {
					for (var y = -2; y <= 2; y++) {
						ai.attack(this, get(this.x - x, this.y - y));
					}
				}
				
				kill(this);
			}
		}
	},
	"suicide": {
		"image": "suicide",
		"name": "Boomer",
		"description": "The boomer will explode, and spread it's team when infected.",
		"infected": function() {
			for (var x = -2; x <= 2; x++) {
				for (var y = -2; y <= 2; y++) {
					ai.infect(this, get(this.x - x, this.y - y));
				}
			}
			kill(this);
		}
	}
};
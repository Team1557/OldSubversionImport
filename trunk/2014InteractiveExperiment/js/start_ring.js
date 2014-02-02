/**
 *  Creates a ring of alternating teams
 */
function ring(r) {
	var i = 0;
	for (var a = 0; a < Math.PI * 2; a += 0.1) {
		var x = width/2 + Math.cos(a) * r, 
			y = height/2 + Math.sin(a) * r
		if (get(x, y) == undefined) {
			var dot = create(teams[Math.floor(teams.length * (a/(Math.PI*2)))], x, y);
			
			/*switch (random(1,50)) {
				case 1:
					dot.type = types.king;
					break;
				case 2:
				case 3:
					dot.image = types.shield;
					break;
			}*/
		}
	}
}

ring(5);
ring(10);

create(teams[0], width / 2, height / 2).type = types.shapeshifter;
create(teams[0], width / 2 + 9, height / 2).type = types.nuke;
create(teams[0], width / 2 - 9, height / 2).type = types.suicide;
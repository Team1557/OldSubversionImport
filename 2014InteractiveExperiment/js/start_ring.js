/**
 *  Creates a ring of alternating teams
 */
function ring(r, team) {
	var i = 0;
	for (var a = 0; a < Math.PI * 2; a += 0.1) {
		var x = width/2 + Math.cos(a) * r, 
			y = height/2 + Math.sin(a) * r
		if (get(x, y) == undefined) {
			create(team, x, y);
		}
	}
}

for (var i = 3; i < 18; i+=3) {
	ring(i, teams[(i/3) % teams.length]);
}
create(teams[0], width / 2, height / 2).type = types.shapeshifter;
create(teams[0], width / 2 + 7, height / 2).type = types.nuke;
create(teams[0], width / 2 - 7, height / 2).type = types.suicide;
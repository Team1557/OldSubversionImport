module HighGoal(rot,mir) {
    rotate(a=rot) {
        mirror(v=mir) {
            rotate(a=[90,0,0]) {
                translate([-144,-21.5,-0.5]) {
                    difference() {
                        union() {
                            cube([144,43,1]);
                            translate([0,21.5,0]) {
                                cylinder(h=1, r=21.5);
                            }
                        }
                        union() {
                            translate([3,3,0]) {
                                cube([138,37,1]);
                            }
                            translate([0,21.5,0]) {
                                cylinder(h=1, r=18.5);
                            }
                        }
                    }
                }
            }
        }
    }
}
module DualHighGoal() {
    translate([0,0,0]) {
        HighGoal();
    }
    translate([0,0,0]) {
        HighGoal(0,[0,1,0]);
    }
}

DualHighGoal();
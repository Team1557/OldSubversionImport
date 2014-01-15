module HighGoal(rot,mir) {
    rotate(a=rot) {
        mirror(v=mir) {
            rotate(a=[90,0,0]) {
                translate([-125.5,-21.5,-0.75]) {
                    difference() {
                        union() {
                            cube([125.5,43,1.5]);
                            translate([0,21.5,0]) {
                                cylinder(h=1.5, r=21.5);
                            }
                        }
                        union() {
                            translate([3,3,0]) {
                                cube([119.5,37,1.5]);
                            }
                            translate([0,21.5,0]) {
                                cylinder(h=1.5, r=18.5);
                            }
                        }
                    }
                }
            }
        }
    }
}
module DualHighGoal() {
    translate([-6,-0.75,-21.5]) {
        cube([12,1.5,43]);
    }
    translate([3,0,0]) {
        HighGoal();
    }
    translate([-3,0,0]) {
        HighGoal(0,[0,1,0]);
    }
}

DualHighGoal();
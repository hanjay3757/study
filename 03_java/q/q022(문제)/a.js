function bb(){const age = ["1살", "2살", "3살", "4살", "5살", "6살"];
const sp = ["페르시안", "렉돌", "코숏", "러시안블루", "폴드"];
const animal = ["야옹", "새싹", "초롱", "별", "화", "토"];
const ageIndex = Math.floor(Math.random()*age.length);
const spIndex = Math.floor(Math.random()*sp.length);
const animalIndex = Math.floor(Math.random() * animal.length);
var catb
var catc

cata = age[ageIndex];
catb = sp[spIndex];
catc = animal[animalIndex];
var v = cata+ " 먹은 " +catb+ " " + catc 
+"이";
alert(v);}
bb();
document.write(112);

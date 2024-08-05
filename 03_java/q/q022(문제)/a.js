const age = ["1살", "2살", "3살", "4살", "5살", "6살"];
const sp = ["페르시안", "렉돌", "코숏", "러시안블루", "폴드"];
const animal = ["야옹이", "새싹", "초롱", "별", "화이", "토리"];
const ageIndex = Math.floor(Math.random()*age.length);
const spIndex = Math.floor(Math.random()*sp.length);
const animalIndex = Math.floor(Math.random() * animal.length);
var cata 
var catb
var catc

cata = age[ageIndex];
catb = sp[spIndex];
catc = animal[animalIndex];
var v = cata+" "+catb+ " "+ catc;
document.write(v);

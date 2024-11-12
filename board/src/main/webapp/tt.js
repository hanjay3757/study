var n = document.getElementById('name');
var b = document.getElementById('b');
var s = document.getElementById('myForm');

s.addEventListener('submit', function(event) {
   event.preventDefault();
   alert(n.value);
   b.innerHTML = "<a href='b.jsp?name="+n.value+"'>야옹</a>";
}
);


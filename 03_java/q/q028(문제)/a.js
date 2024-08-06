function br(){
    document.write("<br>");
    document.write("<br>");
}
for(i=1; i<=10; i++){
    document.write(i);
    if(i%2==1){
        document.write("홀수 ");
    }else{
        document.write("짝수 ");
    }
    br();
}
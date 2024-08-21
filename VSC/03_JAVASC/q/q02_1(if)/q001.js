window.onload = function(){ // html 문서가 다 읽어진 후에 실행되는 함수임. 이걸 써야 아래 h1 태그(id=a)에 대한 접근이 가능해짐


        var a = 1;
        var b = 10;
    
        //조건문 1
        // if ( 조건식 ) 
        if ( a < b ) 
        {   // 코드 블럭 시작
    
            //조건이 맞았을 때 실행되는 코드들..
            
            document.write("참");
    
        }   // 코드 블럭 끝
    
        document.write("<br>");
        document.write("<br>");
    
        //조건문 2
        // if ( 조건식 ) 
        if ( a > b ) 
        {   // 코드 블럭 시작
    
            //조건이 맞았을 때 실행되는 코드들..
            
            document.write("2참");
    
        }   // 코드 블럭 끝
        else    // 조건이 안 맞았을 때 실행
        {
            document.write("2거짓");
    
        }
    
        document.write("<br>");
        document.write("<br>");
    
        //조건문 3
        if ( b == 1 ) 
        {   // 코드 블럭 시작
            //조건이 맞았을 때 실행되는 코드들..
            document.write("b 는 1임");
    
        }   // 코드 블럭 끝
        else if( b == 2)    // 다른 조건
        {
            document.write("b 는 2임");
        }
        else if( b == 3)    // 다른 조건
        {
            document.write("b 는 3임");
        }
        else
        {
            document.write("해당없음");
        }
    
    }
//     참이냐 거짓이냐에따라 적용
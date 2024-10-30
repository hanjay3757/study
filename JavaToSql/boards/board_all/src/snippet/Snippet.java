package snippet;

public class Snippet {
	SQL에서 WHERE NOT는 특정 조건을 만족하지 않는 행(row)을 필터링할 때 사용됩니다. 이는 WHERE 절과 반대의 의미를 갖습니다. NOT 연산자를 사용하여 조건을 부정하면, 해당 조건이 참이 아닌 행을 반환합니다.
	
	예시를 통해 설명하겠습니다.
	
	1. 기본 구조
	sql
	코드 복사
	SELECT * 
	FROM 테이블명
	WHERE NOT 조건;
	이 구문은 조건을 만족하지 않는 모든 행을 반환합니다.
	
	2. 예시
	예를 들어, employees라는 테이블에서 나이가 30살이 아닌 직원들을 조회하고 싶다면:
	
	sql
	코드 복사
	SELECT * 
	FROM employees
	WHERE NOT age = 30;
	위 쿼리는 나이가 30이 아닌 모든 직원의 데이터를 반환합니다.
	
	3. NOT IN과 함께 사용
	NOT은 종종 IN과 함께 사용되며, 특정 값들이 아닌 데이터들을 필터링할 수 있습니다.
	
	sql
	코드 복사
	SELECT * 
	FROM employees
	WHERE department NOT IN ('HR', 'Sales');
	이 쿼리는 부서가 'HR'이나 'Sales'가 아닌 직원들의 데이터를 반환합니다.
	
	4. NOT LIKE와 함께 사용
	문자열 패턴을 부정할 때는 LIKE와 함께 사용됩니다.
	
	sql
	코드 복사
	SELECT * 
	FROM products
	WHERE name NOT LIKE 'A%';
	이 쿼리는 이름이 'A'로 시작하지 않는 모든 제품을 조회합니다.
	
	결론적으로, WHERE NOT은 원하는 조건의 반대 조건을 필터링할 때 유용하게 사용됩니다.
}


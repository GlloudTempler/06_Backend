-- 부서 테이블에서 부서명과 위치를 매개변수로 받아 조회하는 함수
SELECT D.* FROM TB_DEPT D
WHERE D.DNAME = 'SALES'
AND D.LOC = 'CHICAGO';

SELECT E.* FROM TB_EMP E
ORDER BY SALARY DESC, ENAME ASC;

SELECT E.* FROM TB_EMP E
WHERE E.COMMISSION IS NULL
AND SALARY >= 2000;

SELECT E.* FROM TB_EMP E
WHERE E.HIREDATE LIKE '1982%'
ORDER BY HIREDATE DESC;

SELECT SUM(D.DNO) AS sumVar
      ,AVG(D.DNO) AS avgVar
      ,MAX(D.DNO) AS maxVar
      ,MIN(D.DNO) AS minVar
FROM TB_DEPT D;

SELECT DNO, JOB,
       SUM(E.SALARY * 30) AS sumSalary
FROM TB_EMP E
GROUP BY DNO, JOB
ORDER BY DNO;

SELECT DNO,
       TRUNC(AVG(E.SALARY * 30)) AS avgSalary
FROM TB_EMP E
GROUP BY DNO;

SELECT UPPER(D.DNAME) AS upperDname
     , LOWER(D.DNAME) AS lowerDname
     , Trim(' Oracle ') As trimOracle
     , TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') AS charSysdate
FROM TB_DEPT D;

--    TODO : 6) case when 사용 쿼리
--           dno <= 20 연말 보너스 100%
--           dno > 20 연발 보너스 200%
--           모두 아니면 연말 보너스 없음
SELECT D.DNO,
       CASE WHEN D.DNO < 20 THEN '연말 보너스 100%'
            WHEN D.DNO > 20 THEN '연말 보너스 200%'
            ELSE '연말 보너스 없음'
       END AS incentive
FROM TB_DEPT D;

-- 사원수, 최고 급여 사원
SELECT COUNT(ENO) AS countEno,
       MAX(SALARY) AS sumSalary
FROM TB_EMP E;

-- 가장오래된 사원, 최근 사원
SELECT E.ENAME, HIREDATE,
            DECODE(HIREDATE, MIN(HIREDATE), '가장 오래된 사원',
                             MAX(HIREDATE), '가장 최근 사원',
                             '디폴트') AS 입사일
FROM TB_EMP E group by E.ENAME, HIREDATE;     

-- 사원 테이블에서 가장 오래된 입사일과 가장 빠른 입사일 출력
SELECT MAX(HIREDATE) AS maxHiredate,
       MIN(HIREDATE) AS minHiredate
FROM TB_EMP;       

-- dname like
SELECT D.* FROM TB_DEPT D
WHERE D.DNAME LIKE '%S%';

SELECT E.* FROM TB_EMP E
WHERE E.ENAME LIKE '%E%';


-- 부서 + 사원
SELECT D.*, E.ENO, E.ENAME, E.SALARY
FROM TB_DEPT D, TB_EMP E
WHERE D.DNO = E.DNO;
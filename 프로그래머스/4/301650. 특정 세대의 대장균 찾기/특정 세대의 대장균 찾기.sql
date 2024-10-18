-- 코드를 작성해주세요
WITH RECURSIVE Generations AS (
    SELECT ID, 1 AS Generation
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL

    UNION ALL
    
    SELECT E.ID, G.Generation + 1
    FROM ECOLI_DATA E
    JOIN Generations G ON E.PARENT_ID = G.ID
)
SELECT ID
FROM Generations
WHERE Generation = 3
ORDER BY ID;
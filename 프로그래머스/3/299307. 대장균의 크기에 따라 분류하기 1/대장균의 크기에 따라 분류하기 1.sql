-- 코드를 작성해주세요
# SELECT ID, 
#         CASE 
#             WHEN SIZE_OF_COLONY <= 100 THEN 'LOW'
#             WHEN SIZE_OF_COLONY <= 1000 THEN 'MEDIUM'
#             ELSE 'HIGH' 
#         END 
#         AS SIZE
# FROM ECOLI_DATA 
# ORDER BY ID
select id, 
    case when size_of_colony <= 100 then'LOW' 
        when size_of_colony <= 1000 then 'MEDIUM'
        else 'HIGH' 
        END as size
from ECOLI_DATA 
order by id asc;

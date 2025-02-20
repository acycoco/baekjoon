-- 코드를 입력하세요
# SELECT member_name, review_text, review_date
# from member_profile m join rest_review r
# on m.member_id = r.member_id
# where member_id in (select member_id from rest_review group by member_id )


with ReviewCount as(
select member_id, count(*) as review_count
from rest_review
group by member_id
),

MaxReview As (
select member_id
    from ReviewCount
    where review_count = (Select max(review_count) from ReviewCount)
)
SELECT MP.MEMBER_NAME, RR.REVIEW_TEXT, DATE_FORMAT(RR.REVIEW_DATE, "%Y-%m-%d") as review_date
FROM REST_REVIEW RR
JOIN MEMBER_PROFILE MP ON RR.MEMBER_ID = MP.MEMBER_ID
WHERE RR.MEMBER_ID IN (SELECT MEMBER_ID FROM MaxReview)
ORDER BY RR.REVIEW_DATE ASC, RR.REVIEW_TEXT ASC;
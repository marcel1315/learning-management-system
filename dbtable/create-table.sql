-- 배너
# CREATE TABLE `TABLE` (
# `COL13` <Undefined Data Type> NOT NULL COMMENT '배너_ID', -- 배너_ID
# `COL`   <Undefined Data Type> NULL     COMMENT '이미지정보', -- 이미지정보
# `COL3`  <Undefined Data Type> NULL     COMMENT '제목', -- 제목
# `COL2`  <Undefined Data Type> NULL     COMMENT '내용', -- 내용
# `COL4`  <Undefined Data Type> NULL     COMMENT '클릭했을때_이동하는_경로', -- 클릭했을때_이동하는_경로
# `COL5`  <Undefined Data Type> NULL     COMMENT '타켓구분', -- 타켓구분
# `COL6`  <Undefined Data Type> NULL     COMMENT '게시기간_시작일', -- 게시기간_시작일
# `COL7`  <Undefined Data Type> NULL     COMMENT '게시기간_종료일', -- 게시기간_종료일
# `COL8`  <Undefined Data Type> NULL     COMMENT '게시여부', -- 게시여부
# `COL9`  <Undefined Data Type> NULL     COMMENT '등록자', -- 등록자
# `COL10` <Undefined Data Type> NULL     COMMENT '등록일자', -- 등록일자
# `COL11` <Undefined Data Type> NULL     COMMENT '수정자', -- 수정자
# `COL12` <Undefined Data Type> NULL     COMMENT '수정일자' -- 수정일자
# )
# COMMENT '배너';

# -- 배너
# ALTER TABLE `TABLE`
# ADD CONSTRAINT `PK_TABLE` -- 배너 기본키
# PRIMARY KEY (
# `COL13` -- 배너_ID
# );

-- 강좌_정보
CREATE TABLE `COURSE` (
`COURSE_ID`   INT           NOT NULL COMMENT 'ID', -- ID
`IMAGE_PATH`  VARCHAR(255)  NULL     COMMENT '이미지', -- 이미지
`KEYWORD`     VARCHAR(255)  NULL     COMMENT '키워드', -- 키워드
`SUBJECT`     VARCHAR(255)  NULL     COMMENT '제목', -- 제목
`SUMMARY`     VARCHAR(1000) NULL     COMMENT '요약문구', -- 요약문구
`CONTENTS`    TEXT          NULL     COMMENT '상세내용', -- 상세내용
`PRICE`       INT           NULL     COMMENT '정가', -- 정가
`SALE_PRICE`  INT           NULL     COMMENT '현재_판매가', -- 현재_판매가
`SALE_END_DT` DATETIME      NULL     COMMENT '할인_종료일' -- 할인_종료일
)
COMMENT '강좌_정보';

-- 강좌_정보
ALTER TABLE `COURSE`
ADD CONSTRAINT `PK_ COURSE` -- 강좌_정보 기본키
PRIMARY KEY (
`COURSE_ID` -- ID
);

ALTER TABLE COURSE
MODIFY COLUMN `COURSE_ID` INT NOT NULL AUTO_INCREMENT COMMENT 'ID';

-- 회원
CREATE TABLE `MEMBER` (
`EMAIL`     VARCHAR(255) NOT NULL COMMENT '이메일', -- 이메일
`USER_NAME` VARCHAR(20)  NULL     COMMENT '이름', -- 이름
`PHOME`     VARCHAR(12)  NULL     COMMENT '휴대폰번호', -- 휴대폰번호
`PASSWORD`  VARCHAR(255) NULL     COMMENT '비밀번호' -- 비밀번호
)
COMMENT '회원';

-- 회원
ALTER TABLE `MEMBER`
ADD CONSTRAINT `PK_MEMBER` -- 회원 기본키
PRIMARY KEY (
`EMAIL` -- 이메일
);

-- 수강정보
CREATE TABLE `TAKE_COURSE` (
`TAKE_COURSE_ID` INT          NOT NULL COMMENT '수강_ID', -- 수강_ID
`COURSE_ID`      INT          NULL     COMMENT 'ID', -- ID
`EMAIL`          VARCHAR(255) NULL     COMMENT '이메일', -- 이메일
`PAY_PRICE`      INT          NULL     COMMENT '결제금액', -- 결제금액
`STATUS`         VARCHAR(20)  NULL     COMMENT '수강정보_상태' -- 수강정보_상태
)
COMMENT '수강정보';

-- 수강정보
ALTER TABLE `TAKE_COURSE`
ADD CONSTRAINT `PK_TAKE_COURSE` -- 수강정보 기본키
PRIMARY KEY (
`TAKE_COURSE_ID` -- 수강_ID
);

ALTER TABLE `TAKE_COURSE`
MODIFY COLUMN `TAKE_COURSE_ID` INT NOT NULL AUTO_INCREMENT COMMENT '수강_ID';

-- 수강정보
ALTER TABLE `TAKE_COURSE`
ADD CONSTRAINT `FK_ COURSE_TO_TAKE_COURSE` -- 강좌_정보 -> 수강정보
FOREIGN KEY (
`COURSE_ID` -- ID
)
REFERENCES COURSE ( -- 강좌_정보
`COURSE_ID` -- ID
);

-- 수강정보
ALTER TABLE `TAKE_COURSE`
ADD CONSTRAINT `FK_MEMBER_TO_TAKE_COURSE` -- 회원 -> 수강정보
FOREIGN KEY (
`EMAIL` -- 이메일
)
REFERENCES `MEMBER` ( -- 회원
`EMAIL` -- 이메일
);
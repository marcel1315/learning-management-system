# fastlms

## 사용기술

- Java 1.8
- Spring Boot 2.5.4
- Spring Boot web
- Spring Boot jpa
- Spring Boot mail (이메일 인증에 사용)
- Spring Boot security
- MariaDB
- MyBatis
- Thymeleaf
- Lombok
- bxslider (웹 페이지의 슬라이더)

## 기능

- 기존 구현되어 있던 기능
  - 회원가입 및 가입 인증메일 전송
  - 로그인 및 로그아웃
  - 비밀번호 찾기(비밀번호 초기화 기능)
  - 관리자(백오피스) 회원 관리
  - 관리자(백오피스) 카테고리 관리
  - 관리자(백오피스) 강좌 관리

- 새롭게 구현된 기능
  - 회원 로그인시 로그인 히스토리(로그) 기능
  - 관리자 회원 상세 정보에 로그인 목록 보기 기능
  - 배너관리(백오피스 기능)
  - 프론트 배너 노출 기능

## application.yml 설정

- 자신의 DB 설정에 맞는 host, port, username, password를 넣어주세요.
```yaml
  datasource:
    url: jdbc:mariadb://localhost:3306/minicampus
    driver-class-name: org.mariadb.jdbc.Driver
    username: minicampus_user
    password: a1234
```

- 자신의 Gmail 계정 주소와 앱 비밀번호 입력합니다. 인증 메일을 보내는 계정으로 사용됩니다.
```yaml
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
```

- 서버에서 사용할 공간의 디렉토리를 넣어줍니다. 서버에서 쓰기가 가능한 위치여야 합니다. (주의: 디렉토리의 마지막에 '/'를 꼭 붙여주세요.)
```yaml
file:
  local-file-root: /your/path/to/file/
```

## 관리자 권한 얻기

1. 회원 가입 및 이메일 인증
2. DB에 직접 연결하여, member 테이블에서 해당 이메일의 admin_yn의 값을 true로 변경
3. 해당 이메일로 로그인
4. localhost:8080/admin/main.do 로 접속
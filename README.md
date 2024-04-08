# fastlms



## 설정

- application.yml 파일에 DB 설정에 맞는 사용자 이름과 비밀번호를 넣어주세요.

- application.yml 파일에 자신의 Gmail 계정 주소와 앱 비밀번호 입력합니다. 인증 메일을 보내는 계정으로 사용됩니다.
```yaml
  mail:
    host: smtp.gmail.com
    port: 587
    username: chungchung1315@gmail.com
    password: yourapppassword    

```

- application.yml 파일에 서버에서 사용할 공간의 디렉토리를 넣어줍니다. 서버에서 쓰기가 가능한 위치여야 합니다.
```yaml
file:
  local-file-root: /your/path/to/file/

```
주의: 디렉토리의 마지막에 '/'를 꼭 붙여주세요.
# 프로젝트 개요
대출 이자율을 입력하였을때, 주식, 채권 정보를 토대로 얼마만큼의 수익을 낼수 있는지 확인해 볼수 있는 서비스.

해당 레포지토리는 주식, 채권 정보를 얻어와 저장하는 batch 레포지토리

https://vine-honesty-22f.notion.site/13ef77777eef4cd5a2d917064fd28f7d?pvs=4

## 실행방법

1. docker 설치
```
아래 홈페이지 에서 docker desktop 설치
https://www.docker.com/products/docker-desktop/
```

2. 프로젝트 실행
```
$ ./gradlew bootJar
$ make up
```

3. docker desktop에 컨테이너가 잘 동작되었는지 확인


etc. docker container 말고 intellij에서 구동하고 싶을때
```
1. docker container 에서 데이터 베이스를 제외한 애플리케이션 컨테이너 삭제
2. intellij 에서 run
3. 이후 테스트
```

주의사항
```
db 데이터가 로컬에 저장되고, 초기 빌드 후 계속 해당 데이터를 참조하기 때문에
db 스키마가 변경되거나 하는 경우는 로컬 데이터를 삭제해야합니다.
$ make down
$ make up
```
```
애플리케이션도 마찬가지로 이미지를 빌드 한뒤 사용하는 것이기 때문에,
jar 파일을 만든 뒤, 이미지를 재빌드해서 써야합니다.
$ ./gradlew build
$ ./gradlew bootJar
$ make build
% make up
```
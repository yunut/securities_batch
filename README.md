# 프로젝트 개요
대출 이자율을 입력하였을때, 주식, 채권 정보를 토대로 얼마만큼의 수익을 낼수 있는지 확인해 볼수 있는 서비스.

해당 레포지토리는 주식, 채권 정보를 얻어와 저장하는 batch 레포지토리

## 실행방법

1. docker 설치
```aidl
아래 홈페이지 에서 docker desktop 설치
https://www.docker.com/products/docker-desktop/
```

2. gradle 빌드
```aidl
.gradlew build
```

3. docker desktop에 컨테이너가 잘 동작되었는지 확인


etc. docker container 말고 intellij에서 구동하고 싶을때
```aidl
1. docker container 에서 데이터 베이스를 제외한 애플리케이션 컨테이너 삭제
2. intellij 에서 run
```
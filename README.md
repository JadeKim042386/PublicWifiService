# 내 위치 기반 공공 와이파이 정보를 제공하는 웹 서비스

## Development Environment

- IDE: Intellij IDEA Ultimate 2023.2.4
- Build System: Gradle 8.5
- JDK: 17

## Tech Stack

- Spring Boot 2.7
- JPA
- MySQL, H2
- JSP
- [Open API](https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do) [[guide](docs/public_wifi_open_api_doc.md)]

## Features

- 내 위치 정보를 입력하면 가까운 위치에 있는 와이파이 정보 조회
- 가까운 와이피이 검색 기록을 저장/조회/삭제
- 와이파이 상세 정보 조회
- 즐겨찾기 그룹 CRUD
- 와이파이를 즐겨찾기 그룹에 추가/삭제
- 즐겨찾기 조회

## ERD

![](docs/public_wifi_db_erd.png)

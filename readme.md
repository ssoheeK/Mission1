# 위치 기반 공공 와이파이 정보 제공



## Description

> 2020.03. - 2020.10.
<br>

### Summary

* 서울시 OpenAPI를 활용하여 내 위치와 알고자 하는 위치 기반으로 공공 WIPI정보 제공
* 내 위치 히스토리에 대한 저장
<br>
<br>



## About Project

### Machine

### FE

<img src="https://img.shields.io/badge/Language-Javascript-yellow?style=flat"/><img src="https://img.shields.io/badge/JSP-red?style=flat"/>

* javascript의 navigator를 사용하여 내 위치 정보 획득
* 획득한 위치 정보를 통해 근처 WIPI에 대한 정보를 서버에 request 
<br>

### BE

<img src="https://img.shields.io/badge/Language-Java-orange?style=flat"/><img src="https://img.shields.io/badge/DB-MySQL-blue?style=flat"/>

* MySQL 사용자 DB 생성
* 사용자가 실행한 조미료 데이터를 조미료통의 NodeMCU에게 json으로 전송
<br>
<br>

## Results

### Function

- 내 위치 가져오기를 통해 현재 나의 위치를 위도와 경도로 알 수 있으며 DB에 데이터 insert
- Open API 와이파이 정보 가져오기를 통해 서울시 공공 WIPI정보를 json데이터로 획득하고 GSon라이브러리를 이용한 parsing과정을 통해 dto형태로 데이터를 획득하고, 데이터베이스에 저장
- 획득한 내 위치 정보를 통해 반경 1km안의 WIPI정보를 알 수 있음 -> sql 조회문에서 수식을 이용해 거리정보까지 한번에 select
- 위치 히스토리 목록을 통해 '내 위치 가져오기'를 통해 획득한 나의 위치 목록을 확인 할 수 있음

<br>
<br>

***
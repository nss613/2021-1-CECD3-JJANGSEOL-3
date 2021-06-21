# 2021-1-CECD3-JJANGSEOL-3
## IoT sensor data 수집 및 CNN 활용을 통한 독거노인 우울증상 초기감지 with 고그린테크

## 짱설팀
* <b> 2017112076 이미란 (팀장) </b>
* <b> 2013112016 노승수 </b>
* <b> 2017112091 박지호 </b>
* <b> 2016110413 박희상 <b>

## 1. 개발 배경
### 1. 독거노인의 증가, 독거노인의 높은 자살률

2000년 ~ 2020년 독거노인 비율             |  OECD 주요국 65세 이상 자살률
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/46514182/122636989-3058fc80-d127-11eb-867d-00ba987d220d.png) |  <img width = 350 src = "https://user-images.githubusercontent.com/46514182/122637041-7ada7900-d127-11eb-9ecc-e09a05b416c1.png">

독거노인은 경제상황이나 신체 건강의 어려움도 있지만 정신건강도 매우 취약하다.  
한국은 OECD 국가들 중 노인 자살률이 높은 국가이다.  
한국 노인 중 독거노인의 자살률은 더 높다.

### 2. 이에 따른 사회복지공무원의 업무 부담 증가로 늘어나는 독거노인의 수를 감당할 대안 필요

복지사 1명당 담당하는 대상자             |  읍면동 사회복지공무원 확충 계획
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/46514182/122637283-b3c71d80-d128-11eb-8008-9dd314d7c161.png) |  <img width = 350 src = "https://user-images.githubusercontent.com/46514182/122637222-65b21a00-d128-11eb-9ab9-1463f3377399.png">

## 2. 개발 목적
### 센서 데이터와 인공지능 딥러닝을 활용하여 독거노인의 우울 증상을 초기에 감지하여 심리적 외로움으로 인한 독거노인의 자살 및 고독사를 예방한다.

## 3. 개발도구 및 작동원리
<b> 독거노인 응급안전안심서비스 차세대 댁내 장비 사용하여 센서데이터 수집 후 그래프로 시각화  </b>

<img width = 600 src="https://user-images.githubusercontent.com/46514182/122636316-9ba0cf80-d123-11eb-9e25-d35dddcf3c12.png">

<b>우울 증상 조기 감지 CNN MODEL 생성 </b>

<img width = 600 src="https://user-images.githubusercontent.com/46514182/122636788-09e69180-d126-11eb-9770-7168fed18dd0.png">

<b>CNN MODEL 활용한 독거노인 행동 패턴 분석 </b>

<img width = 600 src="https://user-images.githubusercontent.com/46514182/122636925-c6d8ee00-d126-11eb-8b2a-df3ab1efa809.png">

## 4. 개발환경
<p>
<img height = 30 src="https://img.shields.io/badge/Java-orange">
<img height = 30 src="https://img.shields.io/badge/AndroidStudio-green">
<img height = 30 src="https://img.shields.io/badge/firebase-blue">
<img height = 30 src="https://img.shields.io/badge/MariaDB-purple">
</p>

## 5. 주요 기능
* 사용자 맞춤형 UI 제공
* 게이트웨이로 들어오는 센서 데이터 값을 수신 후 파싱
* CNN 모델 학습용 그래프 생성

## 6. 진행 사항
1. 센서 데이터 Raw Data 분석
<img width = 600 src="https://user-images.githubusercontent.com/46514182/122638957-36080f80-d132-11eb-97d5-3bcd4a28837f.png">
<img width = 800 src="https://user-images.githubusercontent.com/46514182/122639176-5daba780-d133-11eb-881b-f39a90851c4c.png">

2. 센서 데이터 Parsing 후 데이터베이스에 저장
<img width = 400 src="https://user-images.githubusercontent.com/46514182/122637558-5633d080-d12a-11eb-90aa-71255240cb5e.png">
</br>추후 서버에 Maria DB 설치후 Maria DB에 연동 예정

## 7. 향후 계획
1. 센서 데이터 활용하여 대상자 생활 패턴 파악
2. 생활 패턴 데이터를 시각화 -> 그래프 생성하여 모니터링
3. 생활 패턴 그래프 활용하여 CNN 학습 모델 구축
4. 구축된 CNN 모델 활용하여 대상자의 우울 증상 초기 감지

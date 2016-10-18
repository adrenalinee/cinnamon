# cinnamon

[![Build Status](https://travis-ci.org/adrenalinee/cinnamon.svg?branch=master)](https://travis-ci.org/adrenalinee/cinnamon)

콘솔 스타일의 웹 어플리케이션 개발을 위한 플러그인기반 웹 플랫폼 개발을 목표로 하고 있습니다.

향후 플러그인방식으로 기능을 추가할 수 있고 외부 서비스와 연동을 통해 웹 어플리케이션 개발자에게 필요한 다양한 기능을
추가해나갈 예정입니다.

현재는 웹 어플리케이션의 메뉴구조관리, 사용자 권한 등 기본적인 서버관리 기능을 포함하고 있으며
개발하는 웹 어플리케이션의 비지니스로직 개발을 쉽게 할 수 있도록 몇가지 편의 사항을 지원하고 있습니다.

## concept
보통 서버 프로젝트진행시 프로젝트의 효율적인 운영을 위해서
웹 관리자 콘솔 기능이 꼭 필요합니다. 그러나 보통은 프로젝트 개발기간중 뒤늦게 급하가 개발되어 품질이 낮아지거나, 아예 안만들어지기도 합니다.
또한 대부분의 프로젝트가 기본적인 기능은 거의 비슷함에도 불구하고 매번 새롭게 웹 어드민 사이트가 개발되어 집니다. 이런 상황을 개선할 목적으로
이 프로젝트를 시작하게 되었습니다. 고품질의 웹 어드민 콘솔 개발이나 더 나아가 고품질의 웹 어플리케이션을 개발하기 위한 기본 프레임워크 혹은 기본 플랫폼
이 되는 것이 이 프로젝트의 중요한 달성과제입니다.

## feature
- [google material design](https://material.google.com) 적용
- TODO

## screenshot
<img src="https://github.com/adrenalinee/cinnamon/blob/master/screenshot/initWizard.jpg" />

<img src="https://github.com/adrenalinee/cinnamon/blob/master/screenshot/login.jpg" />

<img src="https://github.com/adrenalinee/cinnamon/blob/master/screenshot/sites.jpg" />

<img src="https://github.com/adrenalinee/cinnamon/blob/master/screenshot/mobileRole.jpg" />

<img src="https://github.com/adrenalinee/cinnamon/blob/master/screenshot/mobileNav.jpg" />



# Quick start

```shell
$ git clone https://github.com/adrenalinee/cinnamon.git
$ cd cinnamon
$ mvn install
$ cd cinnamon-core-stater
$ mvn spring-boot:run
```

웹 브라우저에서 http://localhost:8080 접속

# 0.3version roadmap
## 플러그인 형태로 기능을 추가해나갈 수 있는 구조로 개선
## back-end
- 데이터 베이스
 - 권한 관련 도메인 구조 개선
- 기능개선
 - 역할및 권한 수정하기 쉽도록 수정
 - 이메일 템플릿 기능 완성
 - 각 기능의 목록페이지 개선
- 성능개선
 - 케시를 사용하도록 수정
 - 일부 쿼리 최적화
- 보안 개선
 - ajax call 도 권한 처리가 제대로 적용되도록 수정
- apps 개선
 - rest api 권한 관리 처리 제대로 되도록 수정
 - web으로 클라이언트 관리 되도록 기능 추가

## front-end
- 완전한 single page application으로 실행되도록 수정

## 기타
- 향후 다국어가 지원가능하도록 수정
- 테스트 코드 작성
- 상세한 개발 문서 작성

# Used technologies

## back-end
- java8
- groovy
- [springframework](https://spring.io)
 - spring-boot
 - spring-security
 - spring-data-jpa
- [queryDsl](http://www.querydsl.com)
- [thymeleaf](http://www.thymeleaf.org)


## front-end
- [angularjs](https://angularjs.org)
- [angular-material](https://material.angularjs.org)

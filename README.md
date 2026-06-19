# 🛒 XMPY-MALL

쇼핑몰 풀스택 팀 프로젝트 - Spring Boot 백엔드 + React 프론트엔드 기반의 커머스 플랫폼

---

## 📌 프로젝트 개요

| 항목 | 내용 |
|------|------|
| 프로젝트 유형 | 팀 프로젝트 |
| 기간 | 2025 |
| 배포 | 미배포 (로컬 실행) |

---

## 🛠️ 기술 스택

Backend: Java, Spring Boot, MyBatis, Spring Security + JWT, Lombok, Validation, MySQL

Frontend: React, Vite

---

## ✨ 주요 기능

- 회원: 회원가입 / 로그인 / JWT 인증 / 마이페이지
- 상품: 카테고리별 상품 목록 / 상품 상세 / 썸네일 / 색상 사이즈 선택
- 재고: 옵션별 재고 관리
- 주문: 장바구니 / 주문 생성 / 주문 내역 조회
- 리뷰: 상품 리뷰 작성 / 조회
- 문의: 1:1 문의 / 공지사항
- 관리자: 주문 관리 / 리뷰 관리 / 상품 등록 (Admin 전용)

---

## ⚙️ 로컬 실행 방법

Backend
1. git clone https://github.com/XMPY-MALL/XMPY-MALL-BACK.git
2. MySQL DB 생성: CREATE DATABASE xmpy_mall DEFAULT CHARACTER SET utf8mb4;
3. application.yml 설정 후 ./mvnw spring-boot:run

Frontend
1. git clone https://github.com/XMPY-MALL/XMPY-MALL-FRONT.git
2. npm install
3. npm run dev

---

## 🌿 브랜치 전략

| 브랜치 | 용도 |
|--------|------|
| main | 최종 통합 브랜치 |
| feature/* | 기능별 개발 브랜치 |

---

## 👥 팀 구성

| 이름 |
|------|
| 박정빈 |
| 문서현 |
| 양준우 |

---

## 📝 커밋 컨벤션

feat: 새 기능 추가
fix: 버그 수정
refactor: 리팩토링
docs: 문서 수정
style: 포맷 변경
chore: 빌드·설정 변경

# aswemake 과제

실행 방법

mariaDB에 aswemake 데이터베이스 생성 후 계정 생성

사용자 : aswemake

암호 : 1234

Postman을 활용한 테스트 방식

# DB
Member
user, admin
#### USER pk : 1
- user 1의 주문 목록에 3가지 상품 등록
#### ADMIN pk : 2

### 비밀번호 : 1234

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/44eba5bd-784c-40f2-bb10-90f79e8a7775)

Product

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/99a5a297-b161-4f5e-9144-06f3d68687bf)

PrevProductInfo

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/473a117b-36ce-41b4-9a40-4b0af4e06310)

OrderItem

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/88b1d88b-0a51-4751-ad40-a2ebb9b63990)

Coupon

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/4775d347-5e91-4c9d-aa3a-cde62322436e)

<br>

# API
### Member
### 회원가입
- URL : POST "member/api/signup"

param : loginId, password, role

새로운 회원을 등록하는데 사용합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/f7a5d924-2275-4168-a998-27618c29e703)


### 로그인

Authorization에 Basic Auth 선택

username, password 회원가입 한 회원 혹은 db에 설정된 admin, user로 입력 후

이후 모든 테스트를 진행합니다.


![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/3874e7d8-10b1-4b53-8812-899bf96126ea)


<br>

### Product
### 상품 목록
- URL : GET "product/api/list"

사용자가 상품의 목록을 조회하는데 사용합니다.
Role의 경우 USER 혹은 ADMIN으로 입력합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/177e70e2-476c-477b-9081-cfc57dbde43e)


<br>

### 상품 등록

- URL : POST "product/api/add"

param : name, price

관리자만이 접근 할 수 있으며 상품을 등록하는데 사용합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/b3cd9dab-1f53-48b8-b273-8182c05b94a1)


<br>

### 상품 수정

- URL : PUT "product/api/update/{productId}"

param : name, price

관리자만이 접근 할 수 있으며 상품을 수정하는데 사용합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/c9b873b1-5247-48dc-96ea-45884395716c)


<br>

### 상품 삭제

- URL : DELETE "product/api/delete/{productId}"

관리자만이 접근 할 수 있으며 상품을 삭제하는데 사용합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/6abebcdf-98bb-4adc-abab-32cb15444216)


<br>

### 수정 내역 조회

- URL : GET "product/api/prev/{productId}"

수정된 상품의 수정 내역을 조회하는데 사용합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/a870c769-74dd-4513-891e-f76af9920494)


<br>

### OrdeItem
### 주문 목록 조회
- URL : GET "orderList/api/list/{memberId}"

사용자의 주문 목록을 확인하는데 사용합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/ab914770-2c7d-45a1-b6c4-6ecb5af73e35)


<br>

### 주문 추가

- URL : POST "orderLsit/api/add"

param : productId, memberId, count

사용자가 주문 목록에 상품의 개수, 등록한 상품의 pk, 사용자의 pk를 저장합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/5e7ba0db-27a0-4e3b-b406-b0bbcd622c71)



<br>

### 주문 목록 삭제

- URL : DELETE "orderList/api/delete/{memberId}/{productId}"

주문 목록에 있는 상품을 삭제하는데 사용합니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/70d5d33d-07d6-465e-b6a5-19b640c0ec7a)


<br>

### 주문 목록 총 가격

- URL : GET "orderList/api/total/{memberId}"

사용자의 주문 목록에 있는 상품들의 총 가격을 나타내줍니다.

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/50b64092-c52f-420a-95b9-73d71eea7e31)


<br>

### Coupon
### 전체 상품 쿠폰
- URL : POST "coupon/api/whole"

param : couponName, memberId

param에 담긴 사용자의 아이디를 사용해서 주문 목록을 조회 후

couponName = wholeFix -> 전체 상품 가격의 고정 가격을 할인해줍니다. (3000원)

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/983a210b-9f0c-43bc-9222-6b98aff93ea8)


couponName = wholeProportion -> 전체 상품 가격의 비율 할인을 해줍니다. (30%)

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/8635ead3-0ec9-4da8-848d-48bf72b22fe5)



<br>

### 개별 상품 쿠폰

- URL : POST "coupon/api/specific"

param : couponName, memberId, productId

param에 담긴 사용자의 아이디를 사용해서 주문 목록을 조회 후

productId를 사용해 특정 상품을 찾아 온 후

couponName = specificFix -> 특정 상품 가격의 고정 가격을 할인해줍니다. (개별 적용 개당 300원)

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/bec8fe81-ac0b-4be8-9fb4-c91e652d0296)


couponName = specificProportion -> 특정 상품 가격의 비율 할인을 해줍니다. (개별 적용 개당 10%)

![image](https://github.com/Hyeoonnm/aswemake/assets/105695601/ae091147-e6bf-43de-9335-6abdf4311b20)














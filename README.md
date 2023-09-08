# aswemake 과제

실행 방법

mariaDB에 aswemake 데이터베이스 생성 후 계정 생성

사용자 : aswemake

암호 : 1234

<br>

# API
### Member
### 엔드포인트
- URL : POST "member/api/signup"

param : loginId, password, role

새로운 회원을 등록하는데 사용합니다.

<br>

### Product
### 엔드포인트
- URL : GET "product/api/list"

사용자가 상품의 목록을 조회하는데 사용합니다.

<br>
- URL : POST "product/api/add"

param : name, price

관리자만이 접근 할 수 있으며 상품을 등록하는데 사용합니다.

<br>
- URL : PUT "product/api/update/{productId}"

param : name, price

관리자만이 접근 할 수 있으며 상품을 수정하는데 사용합니다.

<br>
- URL : DELETE "product/api/delete/{productId}"

관리자만이 접근 할 수 있으며 상품을 삭제하는데 사용합니다.

<br>
- URL : GET "product/api/prev/{productId}"

수정된 상품의 수정 내역을 조회하는데 사용합니다.

<br>

### OrdeItem
### 엔드포인트
- URL : GET "orderList/api/list/{memberId}"

사용자의 주문 목록을 확인하는데 사용합니다.

<br>
- URL : POST "orderLsit/api/add"

param : productId, memberId, count

사용자가 주문 목록에 등록한 상품의 개수, 등록한 상품의 pk, 사용자의 pk를 저장합니다.

<br>
- URL : DELETE "orderList/api/delete/{memberId}/{productId}"

주문 목록에 있는 상품을 삭제하는데 사용합니다.

<br>
- URL : GET "orderList/api/total/{memberId}"

사용자의 주문 목록에 있는 상품들의 총 가격을 나타내줍니다.

<br>

### Coupon
### 엔드포인트
- URL : POST "coupon/api/whole"

param : couponName, memberId
param에 담긴 사용자의 아이디를 사용해서 주문 목록을 조회 후

couponName = wholeFix -> 전체 상품 가격의 고정 가격을 할인해줍니다.

couponName = wholeProportion -> 전체 상품 가격의 비율 할인을 해줍니다.


<br>
- URL : POST "coupon/api/specific"

param : couponName, memberId, productId
param에 담긴 사용자의 아이디를 사용해서 주문 목록을 조회 후 productId를 사용해 특정 상품을 찾아 온 후

couponName = specificFix -> 특정 상품 가격의 고정 가격을 할인해줍니다. (개별 적용)

couponName = specificProportion -> 특정 상품 가격의 비율 할인을 해줍니다. (개별 적용)











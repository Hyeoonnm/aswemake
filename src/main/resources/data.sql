INSERT INTO product(name, price, create_date)
VALUES ('상품1', 1000, SYSDATE()),
       ('상품2', 2000, SYSDATE()),
       ('상품3', 3000, SYSDATE()),
       ('상품4', 4000, SYSDATE()),
       ('상품5', 5000, SYSDATE());

INSERT INTO coupon(coupon_range, coupon_type, name)
VALUES ('whole', 'fix', 'wholeFix'),
       ('whole', 'proportion', 'wholeProportion'),
       ('specific', 'fix', 'specificFix'),
       ('specific', 'proportion', 'specificProportion');
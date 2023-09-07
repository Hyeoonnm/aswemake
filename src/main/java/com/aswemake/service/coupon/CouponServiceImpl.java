package com.aswemake.service.coupon;

import com.aswemake.dao.CouponDAO;
import com.aswemake.dao.OrderItemDAO;
import com.aswemake.entity.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CouponServiceImpl implements CouponService {
    private final CouponDAO couponDAO;
    private final OrderItemDAO orderItemDAO;

    @Override
    public int whole(Long memberId, String couponName) {
        int fix = 3000;
        double proportion = 0.7;
        int productTotalPrice = 0;

//        CouponEntity byName = couponDAO.findByName(couponName);
        List<OrderItemEntity> allByMemberId = orderItemDAO.findAllByMemberId(memberId);
//        byName.getName()
        if (couponName.equals("wholeFix")) {
            for (OrderItemEntity x :
                 allByMemberId) {
                productTotalPrice += x.getProduct().getPrice() * x.getCount();
            }
            if (productTotalPrice - fix < 1) {
                return 0;
            }
            return productTotalPrice - fix;

        } else if (couponName.equals("wholeProportion")) {
            for (OrderItemEntity x :
                    allByMemberId) {
                productTotalPrice += x.getProduct().getPrice() * x.getCount();
            }
            return (int)(productTotalPrice * proportion);
        }
        return -1;
    }


    @Override
    public int specific(Long memberId, String couponName, Long productId) {
        int fix = 300;
        double proportion = 0.9;
        int productTotalPrice = 0;

//        CouponEntity byName = couponDAO.findByName(couponName);
        List<OrderItemEntity> allByMemberId = orderItemDAO.findAllByMemberId(memberId);
//        byName.getName()
        if (couponName.equals("specificFix")) {
            for (OrderItemEntity x :
                    allByMemberId) {
                int cnt = x.getCount();
                if (x.getProduct().getId() == productId) {
                    productTotalPrice += (x.getProduct().getPrice() - fix) * cnt;
                } else {
                    productTotalPrice += x.getProduct().getPrice() * x.getCount();
                }
            }
            return productTotalPrice;

        } else if (couponName.equals("specificProportion")) {
            for (OrderItemEntity x : allByMemberId) {
                int cnt = x.getCount();
                if (x.getProduct().getId() == productId) {
                    productTotalPrice += (x.getProduct().getPrice() * proportion) * cnt;
                } else {
                    productTotalPrice += x.getProduct().getPrice() * x.getCount();
                }
            }
            return productTotalPrice;
        } else
            return -1;
    }
}

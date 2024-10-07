package com.cadastrodeeventos.api.controller;

import com.cadastrodeeventos.api.domain.coupon.Coupon;
import com.cadastrodeeventos.api.domain.coupon.CouponRequestDTO;
import com.cadastrodeeventos.api.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private CouponService couponService;
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> addCouponsToEvents(@PathVariable UUID eventId, @RequestBody CouponRequestDTO couponRequestDTO) {
        return ResponseEntity.ok(couponService.addCouponToEvent(eventId, couponRequestDTO));
    }
}

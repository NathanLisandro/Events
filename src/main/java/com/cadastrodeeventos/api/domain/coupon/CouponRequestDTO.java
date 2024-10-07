package com.cadastrodeeventos.api.domain.coupon;

import com.cadastrodeeventos.api.domain.event.Event;

public record CouponRequestDTO(Integer discount, String valid, Event event) {
}

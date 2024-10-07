package com.cadastrodeeventos.api.service;

import com.cadastrodeeventos.api.domain.coupon.Coupon;
import com.cadastrodeeventos.api.domain.coupon.CouponRequestDTO;
import com.cadastrodeeventos.api.domain.event.Event;
import com.cadastrodeeventos.api.exceptions.EventoNaoEncontradoException;
import com.cadastrodeeventos.api.repositories.CouponRepository;
import com.cadastrodeeventos.api.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {

    private EventRepository eventRepository;

    private CouponRepository couponRepository;
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO data){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventoNaoEncontradoException("Erro evento não foi encontrado!"));
        Coupon coupon = new Coupon();
        coupon.setDiscount(data.discount());
        coupon.setValid(new Date(data.valid()));
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }
}

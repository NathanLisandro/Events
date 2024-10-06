package com.cadastrodeeventos.api.service;

import com.cadastrodeeventos.api.domain.address.Address;
import com.cadastrodeeventos.api.domain.event.Event;
import com.cadastrodeeventos.api.domain.event.EventRequestDTO;
import com.cadastrodeeventos.api.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private AddressRepository addressRepository;
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(EventRequestDTO eventRequest, Event event) {
        Address newAddress = new Address();
        newAddress.setUf(eventRequest.state());
        newAddress.setCity(eventRequest.city());
        newAddress.setEvent(event);
        return this.addressRepository.save(newAddress);
    }
}

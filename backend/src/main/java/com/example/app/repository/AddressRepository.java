package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
package com.app.todo.repository.jpa;

import com.app.todo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByPersonId(long id);

}

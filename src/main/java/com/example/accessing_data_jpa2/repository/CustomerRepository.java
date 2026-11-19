package com.example.accessing_data_jpa2.repository;

import java.util.List;

import com.example.accessing_data_jpa2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    //List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    Customer findById(long id);
    //Customer save(Customer customer);
}
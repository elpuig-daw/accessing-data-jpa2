package com.example.accessing_data_jpa2.controller.rest;

import com.example.accessing_data_jpa2.dto.CustomerDto;
import com.example.accessing_data_jpa2.model.Customer;
import com.example.accessing_data_jpa2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CustomerResource.CUSTOMERS)
public class CustomerResource {
    public static final String CUSTOMERS = "/customers";

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(@RequestParam(name = "lastName", required = false) String lastName) {
        if(lastName != null) {
            return ResponseEntity.ok(customerService.getCustomersByLastName(lastName));
        } else {
            return ResponseEntity.ok(customerService.getAllCustomers());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        CustomerDto dto = customerService.getCustomerById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto cdto = customerService.addCustomer(customerDto);
        return ResponseEntity.ok(cdto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto cdto = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(cdto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(null);
    }

}

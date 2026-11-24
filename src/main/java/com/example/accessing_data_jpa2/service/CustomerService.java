package com.example.accessing_data_jpa2.service;

import com.example.accessing_data_jpa2.dto.CustomerDto;
import com.example.accessing_data_jpa2.model.Customer;
import com.example.accessing_data_jpa2.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> lc = customerRepository.findAll();
        List<CustomerDto> lcdto =  lc.stream()
                .map(c -> new CustomerDto(c.getFirstName(), c.getLastName(), c.getEmail()))
                .toList();
        return lcdto;
    }

    public CustomerDto getCustomerById(long id) {
        Customer c = customerRepository.findById(id);
        CustomerDto cdto = new CustomerDto(c.getFirstName(), c.getLastName(),c.getEmail());
        return cdto;
    }

    public List<CustomerDto> getCustomersByLastName(String lastName) {
        List<Customer> lc = customerRepository.findByLastName(lastName);
        List<CustomerDto> lcdto =  lc.stream()
                .map(c -> new CustomerDto(c.getFirstName(), c.getLastName(),c.getEmail()))
                .toList();
        return lcdto;
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer c = new Customer(customerDto.firstName(), customerDto.lastName(), customerDto.email());
        Customer customer = customerRepository.save(c);
        return new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer c = customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found with id " + id)
        );
        c.setFirstName(customerDto.firstName());
        c.setLastName(customerDto.lastName());
        c.setEmail(customerDto.email());
        customerRepository.save(c);
        return new CustomerDto(c.getFirstName(), c.getLastName(), c.getEmail());
    }

    public void deleteCustomer(Long id) {
        Customer c = customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found with id " + id)
        );
        log.info("Deleting customer with id {}", id);
        customerRepository.deleteById(id);
    }
}

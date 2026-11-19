package com.example.accessing_data_jpa2.service;

import com.example.accessing_data_jpa2.dto.CustomerDto;
import com.example.accessing_data_jpa2.model.Customer;
import com.example.accessing_data_jpa2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> lc = customerRepository.findAll();
        List<CustomerDto> lcdto =  lc.stream()
                .map(c -> new CustomerDto(c.getFirstName(), c.getLastName()))
                .toList();
        return lcdto;
    }

    public CustomerDto getCustomerById(long id) {
        Customer c = customerRepository.findById(id);
        CustomerDto cdto = new CustomerDto(c.getFirstName(), c.getLastName());
        return cdto;
    }

    public List<CustomerDto> getCustomersByLastName(String lastName) {
        List<Customer> lc = customerRepository.findByLastName(lastName);
        List<CustomerDto> lcdto =  lc.stream()
                .map(c -> new CustomerDto(c.getFirstName(), c.getLastName()))
                .toList();
        return lcdto;
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer c = new Customer(customerDto.firstName(), customerDto.lastName());
        Customer customer = customerRepository.save(c);
        return new CustomerDto(customer.getFirstName(), customer.getLastName());
    }
}

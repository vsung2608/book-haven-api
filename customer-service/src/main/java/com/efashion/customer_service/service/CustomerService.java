package com.efashion.customer_service.service;

import com.efashion.customer_service.dto.request.CustomerRequest;
import com.efashion.customer_service.dto.response.CustomerResponse;
import com.efashion.customer_service.exception.CustomerNotFoundException;
import com.efashion.customer_service.mapper.CustomerMapper;
import com.efashion.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        var customer = customerMapper.toCustomer(customerRequest);
        var response = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(response);
    }

    public CustomerResponse updateCustomer(CustomerRequest customerRequest, String id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with the provided ID:: %s", id)
                ));

        var customer = customerMapper.toCustomer(customerRequest);
        customer.setId(id);
        var response = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(response);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    public CustomerResponse findById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot find customer:: No customer found with the provided ID:: %s", id)
                ));
    }

    public void deleteCustomer(String id) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot delete customer:: No customer found with the provided ID:: %s", id)
                ));
        customerRepository.delete(customer);
    }
}

package com.smartinsurance.customerservice.service;

import com.smartinsurance.customerservice.dto.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


public interface ICustomerService {
    void createCustomer(CustomerDto customerDto);
    CustomerDto getCustomer(String mobileNumber);
    boolean updateCustomer(CustomerDto customerDto);
    boolean deleteCustomer(String mobileNumber);
}

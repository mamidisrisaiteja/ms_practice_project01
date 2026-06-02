package com.smartinsurance.customerservice.mapper;

import com.smartinsurance.customerservice.dto.CustomerDto;
import com.smartinsurance.customerservice.entity.Customers;

public class CustomerMapper {

    public static Customers mapCustomerDtoToCustomer(CustomerDto customerDto, Customers customers){
        customers.setFirstName(customerDto.getFirstName());
        customers.setLastName(customerDto.getLastName());
        customers.setEmail(customerDto.getEmail());
        customers.setMobileNumber(customerDto.getMobileNumber());
        customers.setAddress(customerDto.getAddress());
       return customers;
    }

    public static CustomerDto mapCustomerToCustomerDto(Customers customers,CustomerDto customerDto){
        customerDto.setFirstName(customers.getFirstName());
        customerDto.setLastName(customers.getLastName());
        customerDto.setEmail(customers.getEmail());
        customerDto.setMobileNumber(customers.getMobileNumber());
        customerDto.setAddress(customers.getAddress());
        return customerDto;
    }
}

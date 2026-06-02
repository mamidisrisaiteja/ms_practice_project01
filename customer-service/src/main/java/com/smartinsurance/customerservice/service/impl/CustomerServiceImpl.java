package com.smartinsurance.customerservice.service.impl;

import com.smartinsurance.customerservice.dto.CustomerDto;
import com.smartinsurance.customerservice.entity.Customers;
import com.smartinsurance.customerservice.exception.CustomerAlreadyExistsException;
import com.smartinsurance.customerservice.exception.ResourceNotFoundException;
import com.smartinsurance.customerservice.mapper.CustomerMapper;
import com.smartinsurance.customerservice.repository.CustomersRepository;
import com.smartinsurance.customerservice.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

    @Service
    @AllArgsConstructor
    public class CustomerServiceImpl implements ICustomerService {

        private CustomersRepository customersRepository;
        @Override
        public void createCustomer(CustomerDto customerDto) {
            Customers customers=CustomerMapper.mapCustomerDtoToCustomer(customerDto,new Customers());
            Optional<Customers> optionalCustomer=customersRepository.findByMobileNumber(customerDto.getMobileNumber());
            if(optionalCustomer.isPresent()){
                throw new CustomerAlreadyExistsException("Customer Already Exists with given mobile number"+customerDto.getMobileNumber());
            }
            customersRepository.save(createNewCustomer(customers));
        }

        public Customers createNewCustomer(Customers customers){
            long randomCusNum=1000000000L+ new Random().nextInt(900000000);
            customers.setCustomerId(randomCusNum);
            return customers;
        }

        public CustomerDto getCustomer(String mobileNumber){
            Customers customers=customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
            return CustomerMapper.mapCustomerToCustomerDto(customers,new CustomerDto());
        }

        public boolean updateCustomer(CustomerDto customerDto){
            boolean isUpdated=false;
                    String mobileNumber=customerDto.getMobileNumber();

                    if(mobileNumber!=null){
                       Customers customers =customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)

                       );
                        customers.setFirstName(customerDto.getFirstName());
                        customers.setLastName(customerDto.getLastName());
                        customers.setEmail(customerDto.getEmail());
                        customers.setAddress(customerDto.getAddress());
                        customers.setMobileNumber(customerDto.getMobileNumber());


                        customersRepository.save(customers);
                        isUpdated=true;
                    }
return isUpdated;
                }
                public boolean deleteCustomer(String mobileNumber){

            Customers customers=customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
            );
            customersRepository.delete(customers);
            //return CustomerMapper.mapCustomerToCustomerDto(customers,new CustomerDto());
                    return true;
        }

    }

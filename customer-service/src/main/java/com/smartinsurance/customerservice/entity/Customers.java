package com.smartinsurance.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customers extends BaseEntity {
    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="first_name")
    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private String address;


}

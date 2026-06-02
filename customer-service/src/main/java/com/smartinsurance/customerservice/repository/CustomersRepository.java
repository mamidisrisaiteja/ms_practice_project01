    package com.smartinsurance.customerservice.repository;

    import com.smartinsurance.customerservice.entity.Customers;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;

    @Repository
    public interface CustomersRepository extends JpaRepository<Customers,Long> {

        Optional<Customers> findByMobileNumber(String mobileNumber);
    }

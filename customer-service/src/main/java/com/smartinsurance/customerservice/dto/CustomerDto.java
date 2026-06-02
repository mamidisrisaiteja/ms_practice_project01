        package com.smartinsurance.customerservice.dto;

        import io.swagger.v3.oas.annotations.media.Schema;
        import jakarta.validation.constraints.Email;
        import jakarta.validation.constraints.NotEmpty;
        import jakarta.validation.constraints.Pattern;
        import jakarta.validation.constraints.Size;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.Getter;
        import lombok.Setter;

        @Data
        @Schema(name="Customer Dto",
        description="Schema for Accounts Information")
        public class CustomerDto {

            @NotEmpty(message="First Name Cannot be empty")
            @Schema(description="Customers First Name",example="Jayansh")
            @Size(min=5,max=20,message="The length of the customers first name should be between 5 and 20")
            private String firstName;

            @NotEmpty(message="Field Cannot be empty")
            @Schema(description="Customers Last Name",example="Mamidi")
            private String lastName;

            @NotEmpty(message="Field Cannot be empty")
            @Schema(description="Customers Email",example="jayanshmamidi@gmail.com")
            @Email(message="Email address must be a valid value")
            private String email;

            @NotEmpty(message="Field Cannot be empty")
            @Pattern(regexp="^[0-9]{10}$",message="Mobile number must be 10 ")
            private String mobileNumber;

            private String address;
        }

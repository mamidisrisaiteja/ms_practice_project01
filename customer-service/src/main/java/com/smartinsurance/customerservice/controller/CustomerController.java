package com.smartinsurance.customerservice.controller;


import com.smartinsurance.customerservice.constants.CustomerConstants;
import com.smartinsurance.customerservice.dto.CustomerDto;
import com.smartinsurance.customerservice.dto.ErrorResponseDto;
import com.smartinsurance.customerservice.dto.ResponseDto;
import com.smartinsurance.customerservice.entity.Customers;
import com.smartinsurance.customerservice.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(
        name="CRUD rest apis for Customer Service of smart insurance system",
        description="CRUD rest apis in Smart Insurance Customer Service to Create, Update, Get and Delete  Customer Details"
)
@RestController
@RequestMapping(path="/api",produces={MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {


    private final ICustomerService iCustomerService;

    @Autowired
    public CustomerController(ICustomerService iCustomerService){
        this.iCustomerService=iCustomerService;
    }
    @Operation(
            summary="Get Customer Rest Api",
            description = "Rest Api to Get Customer"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode="200",description = "Https status ok"),
                    @ApiResponse(responseCode="500",description = "Https status Internal Server Error",
                    content=@Content(schema=@Schema(implementation= ErrorResponseDto.class))),
            }
    )
    @GetMapping("/customers/{mobileNumber}")
    public ResponseEntity<CustomerDto> getCustomerDetails(@PathVariable @Pattern(regexp="^$|[0-9]{10}",message="Mobile Number must be 10 digits") @Valid String mobileNumber){
        CustomerDto customerDto=iCustomerService.getCustomer(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }
    @Operation(summary="Post Api", description="create api for customer service")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description="Https Status Created"),
            @ApiResponse(responseCode="500",description="Https Status Internal Server Errror",
                    content = @Content(schema=@Schema(implementation=ErrorResponseDto.class)))
    })
    @PostMapping("/customers")
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody @Valid CustomerDto customerDto){
        iCustomerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CustomerConstants.STATUS_201,CustomerConstants.MESSAGE_201));
    }

    @Operation(summary="Put Api", description="update api for customer service")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description="Https Status ok"),
            @ApiResponse(responseCode = "417",description="Expectation Failed"),
            @ApiResponse(responseCode="500",description="Https Status Internal Server Errror",
                    content = @Content(schema=@Schema(implementation=ErrorResponseDto.class)))
    })
    @PutMapping("/customers")
    public ResponseEntity<ResponseDto> updateCustomer(@RequestBody @Valid CustomerDto customerDto){
       boolean isUpdated = iCustomerService.updateCustomer(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstants.STATUS_201,CustomerConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CustomerConstants.STATUS_417,CustomerConstants.MESSAGE_417_UPDATE));
        }
    }


    @Operation(summary="Delete Api", description="delete api for customer service")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description="Https Status ok"),
            @ApiResponse(responseCode = "417",description="Expectation Failed"),
            @ApiResponse(responseCode="500",description="Https Status Internal Server Error",
                    content = @Content(schema=@Schema(implementation=ErrorResponseDto.class)))
    })
    @DeleteMapping("/customers/{mobileNumber}")
    public ResponseEntity<ResponseDto> deleteCustomer(@PathVariable @Pattern(regexp="^$|[0-9]{10}",message="Mobile number must be 10 digits") String mobileNumber){
        boolean isDeleted=iCustomerService.deleteCustomer(mobileNumber);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CustomerConstants.STATUS_417, CustomerConstants.MESSAGE_417_DELETE));
        }
    }
}

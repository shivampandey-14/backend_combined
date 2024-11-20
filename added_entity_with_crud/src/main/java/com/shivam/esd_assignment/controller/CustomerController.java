package com.shivam.esd_assignment.controller;

import com.shivam.esd_assignment.dto.CustomerRequest;
import com.shivam.esd_assignment.dto.CustomerResponse;
import com.shivam.esd_assignment.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {
        boolean isDeleted = customerService.deleteCustomerByEmail(email);
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request)
    {
        boolean isUpdated = customerService.updateCustomer(request);
        if(isUpdated) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.encore.mijung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encore.mijung.domain.Customer;
import com.encore.mijung.service.CustomerService;

@RestController
@RequestMapping("mijung")
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody Customer customer) throws Exception{
		boolean result = customerService.register(customer);
		if(!result) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Customer customer) throws Exception{
		Customer loginCustomer = customerService.login(customer);
		String msg = "";
		if(loginCustomer!=null) {
			return new ResponseEntity(loginCustomer, HttpStatus.OK);
		}else return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/dropCustomer/{custId}")
	public ResponseEntity dropCustomer(@PathVariable String custId) throws Exception{
		boolean result = customerService.dropCustomer(custId);
		if(!result) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/idCheck/{custId}")
	public ResponseEntity idCheck(@PathVariable String custId) throws Exception{
		System.out.println("idcheck");
		return new ResponseEntity(customerService.idCheck(custId), HttpStatus.OK);
	}
	
	@GetMapping("/findId/{custName}/{custPhone}")
	public ResponseEntity findId(@PathVariable String custName, @PathVariable String custPhone) throws Exception {
		Customer customer = new Customer (custName, custPhone);
		Customer findCustomer = customerService.findId(customer);
		if(findCustomer==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(findCustomer, HttpStatus.OK);
	}
	
	@GetMapping("/findPassword/{custId}/{custName}/{custPhone}")
	public ResponseEntity findPassword(@PathVariable String custId, @PathVariable String custName, @PathVariable String custPhone) throws Exception{
		Customer customer = new Customer (custId, custName, custPhone);
		Customer findCustomer = customerService.findId(customer);
		if(findCustomer==null) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(findCustomer, HttpStatus.OK);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity changePassword(@RequestBody Customer customer) throws Exception{
		boolean result = customerService.changePassword(customer);
		if(!result) return new ResponseEntity(HttpStatus.NO_CONTENT);
		else return new ResponseEntity(HttpStatus.OK);
	}
}

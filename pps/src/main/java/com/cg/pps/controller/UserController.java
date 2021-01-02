package com.cg.pps.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pps.config.PpsUserDetailsService;
import com.cg.pps.dataaccess.UserEntity;
import com.cg.pps.dataaccess.repo.UserRepository;
import com.cg.pps.models.AuthenticationRequest;
import com.cg.pps.models.AuthenticationResponse;
import com.cg.pps.util.JwtUtil;

@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PpsUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	
	@GetMapping("/welcome")
	public String welcomeservice() {
		return "Welcome to Personality Prediction System !!";
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect password or username",e);
		}
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt=jwtTokenUtil.generateToken(userDetails);
		
		
		 
		 HttpHeaders header=new HttpHeaders();
		 header.add("Authorization", jwt);
		 header.add("Access-Control-Expose-Headers", "Authorization");
		 return new ResponseEntity<String>(header,HttpStatus.CREATED);
		 
}
}

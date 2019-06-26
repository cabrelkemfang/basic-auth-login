package com.example.storemanagement.controller;

import com.example.storemanagement.model.Auth;
import com.example.storemanagement.model.LoginForm;
import com.example.storemanagement.repository.RoleRepository;
import com.example.storemanagement.repository.UserRepository;
import com.sun.jersey.core.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("")
@RestController
public class LoginController {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  @Autowired
  public LoginController(final UserRepository userRepository,
                         final RoleRepository roleRepository,
                         final BCryptPasswordEncoder passwordEncoder,
                         final AuthenticationManager authenticationManager) {
    super();
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
  public ResponseEntity<Auth> authenticateUser(@RequestBody LoginForm loginForm) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final byte[] base64EncodedAuthenticationKey = Base64.encode(loginForm.getUserName() + ":" + loginForm.getPassword());

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    System.out.println(userDetails.getAuthorities().toString());


    return new ResponseEntity<>(new Auth(userDetails.isEnabled(), base64EncodedAuthenticationKey, userDetails.getAuthorities(), userDetails.getUsername()), HttpStatus.OK);

  }


}

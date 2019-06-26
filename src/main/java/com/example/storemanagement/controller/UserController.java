package com.example.storemanagement.controller;

import com.example.storemanagement.exception.ServiceException;
import com.example.storemanagement.model.Role;
import com.example.storemanagement.model.User;
import com.example.storemanagement.model.UserModel;
import com.example.storemanagement.repository.RoleRepository;
import com.example.storemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class UserController {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public UserController(final UserRepository userRepository,
                        final BCryptPasswordEncoder passwordEncoder,
                        final RoleRepository roleRepository) {
    super();
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public void createUser(@RequestBody UserModel user) {

    if (user.getUserName() != null && this.userRepository.existsByUserName(user.getUserName())) {
      throw ServiceException.conflict("User {0} already exists.", user.getUserName());
    } else {
      User newUser = new User();

      newUser.setFirstName(user.getFirstName());
      newUser.setLastName(user.getLastName());
      newUser.setUserName(user.getUserName());
      newUser.setId(user.getId());
      newUser.setPhoneNumber(user.getPhoneNumber());
      Role role = this.roleRepository.findByName(user.getRoles()).get();
      newUser.setRoles((Collection<Role>) role);
      newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));

      this.userRepository.save(newUser);
    }
  }

  @RequestMapping(value = "/user", method = RequestMethod.PUT)
  public void updateUser(@RequestBody User user) {

    Optional<User> newUser = this.userRepository.findByUserName(user.getUserName());

    if (newUser.isPresent()) {

      newUser.get().setFirstName(user.getFirstName());
      newUser.get().setLastName(user.getLastName());
      newUser.get().setUserName(user.getUserName());
      newUser.get().setId(user.getId());
      newUser.get().setPhoneNumber(user.getPhoneNumber());
      newUser.get().setRoles(user.getRoles());
      newUser.get().setPassword(user.getPassword());

      this.userRepository.save(newUser.get());
    } else {
      throw ServiceException.conflict("User {0} dont exists.", user.getUserName());
    }
  }

  @RequestMapping(value = "user", method = RequestMethod.GET)
  public ResponseEntity<List<User>> getAllUser() {
    List<User> userList;
    userList = this.userRepository.findAll();
    return new ResponseEntity<>(userList, HttpStatus.OK);

  }


  @RequestMapping(value = "/user/{userName}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable("userName") String userName) {
    Optional<User> user = this.userRepository.findByUserName(userName);
    if (user.isPresent()) {
      this.userRepository.delete(user.get());
    } else {
      throw ServiceException.conflict("User {0} dont exists.", user.get().getUserName());
    }
  }


}

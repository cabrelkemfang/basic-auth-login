package com.example.storemanagement.controller;

import com.example.storemanagement.exception.ServiceException;
import com.example.storemanagement.model.Role;
import com.example.storemanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")

public class RoleController {

  private final RoleRepository roleRepository;

  @Autowired
  public RoleController(final RoleRepository roleRepository) {
    super();
    this.roleRepository = roleRepository;
  }

//  @PreAuthorize("hasAuthority('READ_ALL_ROLE')")
  @RequestMapping(value = "/role", method = RequestMethod.GET)
  public ResponseEntity<List<Role>> getAllRole() {
    List<Role> roleList;
    roleList = this.roleRepository.findAll();
    return new ResponseEntity<>(roleList, HttpStatus.OK);
  }

  @PreAuthorize("hasAuthority('CREATE_ROLE')")
  @RequestMapping(value = "/role", method = RequestMethod.POST)
  public ResponseEntity<String> createRole(@RequestBody Role role) {

    if (role.getName() != null && this.roleRepository.existsByName(role.getName())) {
      throw ServiceException.conflict("Role {0} already exists.", role.getName());
    }
    this.roleRepository.save(role);
    return new ResponseEntity<>("Role have been created successfully", HttpStatus.CREATED);
  }

  @PreAuthorize("hasAuthority('UPDATE_ROLE')")
  @RequestMapping(value = "/role", method = RequestMethod.PUT)
  public ResponseEntity<String> updateRole(@RequestBody Role role) {

    if (this.roleRepository.findById(role.getId()).isPresent()) {
      this.roleRepository.save(role);
      return new ResponseEntity<>("Role have been updated successfully", HttpStatus.CREATED);
    } else {

      throw ServiceException.conflict("Role {0} dont exists.", role.getName());
    }

  }

  @PreAuthorize("hasAuthority('DELETE_ROLE')")
  @RequestMapping(value = "/role/{name}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteRole(@PathVariable("name") String name) {

    Optional<Role> optionalRole=this.roleRepository.findByName(name);
    if (optionalRole.isPresent()){
      this.roleRepository.delete(optionalRole.get());
      return new ResponseEntity<>("Role have been deleted successfully", HttpStatus.OK);
    }
    else {
      throw ServiceException.conflict("Role {0} dont exists.", optionalRole.get().getName());
    }

  }


}

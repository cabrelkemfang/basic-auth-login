package com.example.storemanagement.service;



import com.example.storemanagement.model.Privilege;
import com.example.storemanagement.model.Role;
import com.example.storemanagement.model.User;
import com.example.storemanagement.repository.RoleRepository;
import com.example.storemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustumUserDetails implements UserDetailsService {


  private final UserRepository userRepository;
  private final RoleRepository rolesRepository;

  @Autowired
  public CustumUserDetails(final UserRepository userRepository,
                           final RoleRepository rolesRepository) {
    super();
    this.userRepository = userRepository;
    this.rolesRepository = rolesRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = this.userRepository.findByUserName(username);

    return new org.springframework.security.core.userdetails.User(
            user.get().getUserName(), user.get().getPassword(), true, true, true,
            true, getAuthorities(user.get().getRoles()));

  }

  private Collection<? extends GrantedAuthority> getAuthorities(
          Collection<Role> roles) {

    return getGrantedAuthorities(getPrivileges(roles));
  }

  private List<String> getPrivileges(Collection<Role> roles) {

    List<String> privileges = new ArrayList<>();
    List<Privilege> collection = new ArrayList<>();
    for (Role role : roles) {
      collection.addAll(role.getPrivileges());
    }
    for (Privilege item : collection) {
      privileges.add(item.getName());
    }
    return privileges;
  }


  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }

    return authorities;

  }
}

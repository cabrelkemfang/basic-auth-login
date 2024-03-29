package com.example.storemanagement.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long id;

  @Column
  private String name;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {
          CascadeType.MERGE
  })
  @JoinTable(
          name = "roles_privileges",
          joinColumns = @JoinColumn(
                  name = "role_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "privilege_id", referencedColumnName = "id"))
  private Collection<Privilege> privileges;

  public Role() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<Privilege> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(Collection<Privilege> privileges) {
    this.privileges = privileges;
  }
}

package com.complex.server.persistence.domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Users")
public class User {

  @Id
  @Column(name = "USER_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "Password")
  private String password;

  @Column(name = "Login")
  private String login;

  @Column(name = "Name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  private List<Invoice> invoices;

  public List<Invoice> getInvoices() {
    return invoices;
  }

  public void setInvoices(List<Invoice> invoices) {
    this.invoices = invoices;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return this.id == user.id && Objects.equals(this.password, user.password) && Objects
        .equals(this.login, user.login) && Objects.equals(this.name, user.name) && Objects
        .equals(this.invoices, user.invoices);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, password, login, name, invoices);
  }
}

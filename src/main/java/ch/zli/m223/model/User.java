package ch.zli.m223.model;

import java.util.Set;

import org.mindrot.jbcrypt.BCrypt;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "user_entity")
@NamedQueries({
  @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM user_entity u WHERE u.email = :email")
})
public class User {

  

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;
  
  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String vorname;

  @Column(nullable = false)
  private String nachname;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private boolean role;

  @Column(nullable = false)
  private String salt;

  @OneToMany(mappedBy = "user")
  @JsonIgnoreProperties("user")
  @Fetch(FetchMode.JOIN)
  private Set<Booking> booking;



  public String getVorname() {
    return this.vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getNachname() {
    return this.nachname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public Set<Booking> getBooking() {
    return this.booking;
  }

  public void setBooking(Set<Booking> booking) {
    this.booking = booking;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    String salt = BCrypt.gensalt();
    this.salt = salt;
    this.password = BCrypt.hashpw(password, salt);
  }

  public boolean getRole() {
    return this.role;
  }

  public void setRole(boolean role) {
    this.role = role;
  }


  public boolean isRole() {
    return this.role;
  }

  public String getSalt() {
    return this.salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }


}

package ch.zli.m223.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@NamedQueries({
  @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;
  
  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String vorname;

  @Column(nullable = false)
  private String nachname;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private Set<Buchung> buchung;



  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

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

  public Set<Buchung> getBuchung() {
    return this.buchung;
  }

  public void setBuchung(Set<Buchung> buchung) {
    this.buchung = buchung;
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
    this.password = password;
  }
}

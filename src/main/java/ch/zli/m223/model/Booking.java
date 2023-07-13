package ch.zli.m223.model;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private boolean vormittag;

  @Column(nullable = false)
  private boolean nachmittag;

  @Column(nullable = false)
  private String status;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  private User user;

  private String notiz;



  public LocalDate getDate() {
    return this.date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public boolean isVormittag() {
    return this.vormittag;
  }

  public boolean getVormittag() {
    return this.vormittag;
  }

  public void setVormittag(boolean vormittag) {
    this.vormittag = vormittag;
  }

  public boolean isNachmittag() {
    return this.nachmittag;
  }

  public boolean getNachmittag() {
    return this.nachmittag;
  }

  public void setNachmittag(boolean nachmittag) {
    this.nachmittag = nachmittag;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getNotiz() {
    return this.notiz;
  }

  public void setNotiz(String notiz) {
    this.notiz = notiz;
  }

}
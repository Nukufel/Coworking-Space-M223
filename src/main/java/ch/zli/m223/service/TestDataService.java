
package ch.zli.m223.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Booking;
import ch.zli.m223.model.User;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {

  @Inject
  EntityManager entityManager;

  @Transactional
  void generateTestData(@Observes StartupEvent event) {

    //Set<Booking> booking1 = new HashSet<>();

    // Booking
    var firstBooking = new Booking();
    firstBooking.setDate(LocalDate.of(2024, 1, 1));
    firstBooking.setNachmittag(false);
    firstBooking.setVormittag(true);
    firstBooking.setStatus("in bearbeitung");
    entityManager.persist(firstBooking);
    
    var secondBooking = new Booking();
    secondBooking.setDate(LocalDate.of(2024, 6, 11));
    secondBooking.setNachmittag(true);
    secondBooking.setVormittag(true);
    secondBooking.setStatus("bestätigt");
    entityManager.persist(secondBooking);
    
    var thirdBooking = new Booking();
    thirdBooking.setDate(LocalDate.of(2020, 6, 11));
    thirdBooking.setNachmittag(true);
    thirdBooking.setVormittag(false);
    thirdBooking.setStatus("abgelent");
    entityManager.persist(thirdBooking);
    
    var fourthBooking = new Booking();
    fourthBooking.setDate(LocalDate.of(2026, 6, 11));
    fourthBooking.setNachmittag(false);
    fourthBooking.setVormittag(false);
    fourthBooking.setStatus("abgelent");
    entityManager.persist(fourthBooking);
    
    
    //User
    /*
    var firstUser= new User();
    firstUser.setEmail("vogeln@bzz.ch");
    firstUser.setVorname("Niki");
    firstUser.setNachname("Vogel");
    firstUser.setPassword("12345");
    firstUser.setRole(true);
    
    booking1.add(firstBooking);
    booking1.add(secondBooking);

    firstUser.setBooking(booking1);
    entityManager.persist(firstUser);

    var secondUser= new User();
    secondUser.setEmail("vogeln@bzz.ch");
    secondUser.setVorname("Niki");
    secondUser.setNachname("Vogel");
    secondUser.setPassword("12345");
    secondUser.setRole(false);
    entityManager.persist(secondUser);
    */
  }
}

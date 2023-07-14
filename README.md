# Projekt Coworking Space

Coworking Space ist ein Organisationssystem, welches mit Quarkus entwickelt wird.
Es ist ein prototy der dafür genutzt wird um zu testen ob man mit so einer App einen Coworkingspace organisieren kann.

## Aufsetzen des Projekts

1. Erstelle eine Kopie (fork) von diesem Projekt.
2. Stelle sicher, dass Docker installiert ist und läuft.
3. Stelle sicher, dass Visual Studio Code und die Erweiterung Remote Container installiert ist.
4. Klone (clone) das Projekt lokal, um damit arbeiten zu können.
5. Öffne das Projekt mit Visual Studio Code.
6. Öffne das Projekt im Entwicklungscontainer.

## Starten des Projekts

7. Starte das Projekt mit dem Kommando `Quarkus: Debug current Quarkus Project`
8. Probiere die Client-Applikation unter http://localhost:8080 aus.
9. Schaue die API auf http://localhost:8080/q/swagger-ui/ an.

## Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

## Automatische Tests

Die automatischen Tests können mit `./mvnw quarkus:test` ausgeführt werden. Für die automatischen Tests wird nicht die PostgreSQL-Datenbank verwendet, sondern eine H2-Datenbank, welche sich im Arbeitsspeicher während der Ausführung befindet.
Die Testdaten befinden sich in src/main/java/ch/zli/m223/service/TestDataService.java

## Thunderbird

Um das Projekt mit Thunderbird zu testen müssen die ersten 3 Testfälle (create, loginUser und loginAdmin) ausgefürtwerden. von diesen kann man dann die Tokens nehmen um die restlichen auszuführen.
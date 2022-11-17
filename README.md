# PAP22L-Z01

### Członkowie zespołu:
- Marcin Wawrzyniak 318741
- Mateusz Kiełbus 318668
- Janek Hapunik 318659
- Nel Kułakowska 318687

## Treść zadania:
Chcemy stworzyć aplikację **desktopową** pozwalającą na **zarządzanie biblioteką** z użyciem języka **Java**.
  

**Wymagania:**
1. Dostęp do bazy książek i zajerestrowanych klientów (w tym wyszukiwanie, wyświetlanie jako lista)
2. Możliwość edycji baz książek i klientów (nowe książki/nowi klienci/zmiana danych elementu)
3. Możliwość edycji stanu książek (wypożyczenia, zamówienia)
4. Możliwość naliczenia opłat (za przetrzymanie książek itd.)
5. Konto administratora (login, hasło)

**Dodatkowe funkcjonalności:**
1. Przypomnienia o zbliżaniu się terminu zwrotu
2. Możliwość automatycznego wysyłania maili
3. Utworzenie kont dla klientów (login i hasło, wypożyczenia, zamówienia, naliczone opłaty, zbliżające się terminy zwrotu, oceny książek itd.)

**Implementacja interfejsu graficznego:**  
Do implementacji interfejsu graficznego wykorzystamy narzędzia środowiska IntelliJ IDEA pozwalająca na zautomatyzowanie projektowania interfejsu graficznego(np. mechanizm pozwalający wygenerować formularze).
Przewidujemy także wprowadzanie zmian do GUI ręcznie, pisząc kod klas zarządzających interfejsem graficznym. 

**Klasy:**
Klasa Biblioteka_GUI - klasa zawierająca definicje elementów graficznych, obiekty klas kontrolek i zarządzająca nimi, aktualizująca je.
Klasy utworzone do zarządzania kontrolkami.
Klasa Biblioteka - klasa sterująca. Zawiera obiekty klas administrator, zwykły użytkownik na zasadzie kompozycji. Implementuje metody, które służą do komunikacji między obiektami zawartymi w jej atrybutach. Steruje całą aplikacją biblioteki łącznie z korzystaniem z bazy danych. 
Klasa książka - reprezentuje książkę, ma atrybuty takie jak nazwa, kategoria, data zwrotu, etc., gettery, settery.
Klasa Użytkownik - klasa reprezentująca użytkownika, ma metody reprezentujące czynności: wypożyczanie, oddawanie, przedłużanie, opłacanie kar za nieoddane książki, ma atrybuty takie jak imię, nazwisko, id, liczba wypożyczonych książek, etc.
Klasa Administrator - ma uprawnienia zwykłego użytkownika, ale dodatkowo rozszerza jego możliwości dzięki zastosowaniu dziedziczenia. Może wykonywać czynności administracyjne na systemie zarządzania biblioteką.
Klasa Baza danych - klasa reprezentująca bazę danych.

**Testy:**  
Do implementacji testów jednostkowych planujemy używać JUnit. Poszczególne metody i funkcjonalności będą sprawdzane oddzielnie.

### Wybrane technologie:
- Biblioteki Javy:
    - javax.swing - główne narzędzie do tworzenia okienka aplikacji
    - java.sql - wykorzystywana do operacji na bazach danych
    - java.awt - bilbioteka graficzna, używana obok biblioteki swing, m.in. do obsługi zdarzeń (np. zalogowanie) i kolorów 
    - javax.mail - wykorzystywana do automatycznego wysyłania maili 
- Maven
- Sqldeveloper

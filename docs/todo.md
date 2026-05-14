Projekt: Bankowy Silnik Walidacji (BankDataGuard)
1. Cel projektu
Stworzenie silnika, który przetwarza surowe dane transakcyjne z zewnętrznego pliku, poddaje je surowym regułom walidacji i rozdziela na transakcje „Zatwierdzone” oraz „Odrzucone” wraz z podaniem przyczyny błędu.

2. Format danych wejściowych (Input)
Silnik musi obsługiwać plik tekstowy (np. transactions.csv), gdzie każda linia to:
ID_TRANSAKCJI;NUMER_KONTA;KWOTA;WALUTA;DATA_TRANSAKCJI

Przykład: TX1002;1234567890;150.50;PLN;2026-05-14

3. Wymagane Funkcjonalności (Logika Biznesowa)
A. Walidacja Techniczna (Strukturalna)
Formatowanie: Czy linia zawiera wszystkie 5 pól rozdzielonych średnikiem?

Typy danych: Czy kwota jest liczbą? Czy data pasuje do formatu YYYY-MM-DD?

Unikalność: Czy ID transakcji nie powtórzyło się wcześniej w tym samym pliku?

B. Walidacja Biznesowa (Finansowa)
Rygor Kwoty: Kwota musi być większa od 0. System musi używać klasy BigDecimal dla precyzji finansowej.

Weryfikacja Konta: Numer konta musi mieć dokładnie 10 cyfr (uproszczony standard).

Logika Czasu: Data transakcji nie może być z przyszłości.

Dozwolone Waluty: System akceptuje tylko PLN, EUR, USD. Każda inna waluta powoduje odrzucenie.

C. Obsługa Błędów (Custom Exceptions)
Zamiast używać ogólnych błędów, musisz stworzyć własne klasy wyjątków, np.:

InvalidCurrencyException

FutureDateException

InsufficientDataException

4. Wynik Działania (Output)
Po zakończeniu pracy program powinien wygenerować w konsoli (lub nowym pliku) raport:

Statystyki: Suma przetworzonych transakcji, liczba zaakceptowanych, liczba odrzuconych.

Lista Odrzuconych: ID transakcji + czytelny powód (np. TX1002 - BŁĄD: Nieobsługiwana waluta: CHF).

Suma Wartości: Całkowita kwota wszystkich poprawnych transakcji w PLN (z prostym przelicznikiem, np. 1 EUR = 4.30 PLN).

5. Architektura Plików (Struktura projektu)
Aby zachować porządek, którego wymagasz, podziel projekt na klasy:

Transaction – prosta klasa (POJO) przechowująca dane.

TransactionParser – klasa odpowiedzialna tylko za czytanie pliku i zamianę tekstu na obiekty.

Validator – „mózg” operacji, zawierający metody sprawdzające reguły biznesowe.

ReportGenerator – klasa formatująca wynik końcowy.

Main – punkt startowy aplikacji.

6. Rozszerzenie (Dla ambitnych)
Tryb bezpieczny: Jeśli plik jest pusty lub nie istnieje, program nie może się "wywalić" (crash), musi wyświetlić elegancki komunikat.

Logowanie: Użycie prostego loggera zamiast System.out.println do rejestrowania błędów krytycznych.
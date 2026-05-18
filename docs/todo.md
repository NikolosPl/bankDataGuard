# Dokumentacja Projektu: Bankowy Silnik Walidacji (BankDataGuard)

## 1. Cel projektu
Stworzenie silnika backendowego, który przetwarza surowe dane transakcyjne z zewnętrznego pliku, poddaje je rygorystycznym regułom walidacji biznesowej i technicznej, a następnie rozdziela na transakcje „Zatwierdzone” oraz „Odrzucone” wraz z podaniem konkretnej przyczyny błędu.

## 2. Format danych wejściowych (Input)
Silnik musi obsługiwać plik tekstowy (np. `transactions.csv`), w którym każda linia reprezentuje jedną operację według schematu:
`ID_TRANSAKCJI;NUMER_KONTA;KWOTA;WALUTA;DATA_TRANSAKCJI`

**Przykład poprawnej linii:**
`TX1002;1234567890;150.50;PLN;2026-05-14`

---

## 3. Wymagane Funkcjonalności (Logika Biznesowa)

### A. Walidacja Techniczna (Strukturalna)
*   **Formatowanie:** Weryfikacja, czy linia zawiera dokładnie 5 pól rozdzielonych średnikiem.
*   **Typy danych:** Sprawdzenie, czy kwota jest poprawną liczbą, a data pasuje do formatu `YYYY-MM-DD`.
*   **Unikalność:** Blokowanie transakcji, których `ID_TRANSAKCJI` powtórzyło się wcześniej w tym samym pliku.

### B. Walidacja Biznesowa (Finansowa)
*   **Rygor Kwoty:** Kwota musi być większa od 0. **Wymagane użycie klasy `BigDecimal`** dla zachowania precyzji finansowej.
*   **Weryfikacja Konta:** Numer konta musi składać się z dokładnie 10 cyfr.
*   **Logika Czasu:** Data transakcji nie może być datą z przyszłości względem czasu systemowego.
*   **Dozwolone Waluty:** Akceptacja wyłącznie walut: `PLN`, `EUR`, `USD`. Każda inna (np. CHF, GBP) powoduje odrzucenie.

---

## 4. Wynik Działania (Output)
Program powinien wygenerować czytelny raport końcowy w konsoli:

1.  **Statystyki Ogólne:** Łączna liczba przetworzonych linii, liczba zaakceptowanych transakcji, liczba odrzuconych rekordów.
2.  **Lista Odrzuconych:** Wykaz wszystkich błędnych transakcji w formacie: `[ID] - BŁĄD: [Przyczyna]`.
3.  **Suma Wartości:** Całkowita kwota wszystkich poprawnych transakcji przeliczona na PLN (przyjęte przeliczniki: 1 EUR = 4.30 PLN, 1 USD = 4.00 PLN).

---

## 5. Architektura Plików (Struktura projektu)
Podział na klasy zgodnie z zasadą Single Responsibility (Jednej Odpowiedzialności):

*   **`Transaction`** – Klasa typu POJO przechowująca dane pojedynczej transakcji.
*   **`TransactionParser`** – Odpowiedzialna za odczyt pliku (I/O) i mapowanie tekstu na obiekty.
*   **`Validator`** – Logika sprawdzająca reguły techniczne i finansowe.
*   **`ReportGenerator`** – Moduł odpowiedzialny za formatowanie i wyświetlanie statystyk.
*   **`Main`** – Punkt wejścia do aplikacji, koordynujący przepływ danych.~~

~~---

## 6. Rozszerzenia (Opcjonalne / Dla ambitnych)
*   **Safe Mode:** Obsługa sytuacji, w której plik wejściowy nie istnieje lub jest pusty (bez przerywania działania programu błędem krytycznym).
*   **File Export:** Zapisywanie raportu końcowego do nowego pliku `.txt`.
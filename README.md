# BankDataGuard - Banking Transaction Validation Engine

## 📌 O projekcie
BankDataGuard to silnik backendowy napisany w czystej Javie, służący do procesowania i rygorystycznej walidacji danych transakcyjnych. Projekt został stworzony z myślą o symulacji systemów bankowych, gdzie integralność danych i precyzja finansowa są priorytetem.

## 🚀 Główne Funkcjonalności
*   **Parsowanie danych:** Automatyczne wczytywanie transakcji z plików CSV/tekstowych.
*   **Rygorystyczna Walidacja Biznesowa:**
    *   Weryfikacja formatu numerów kont (dokładnie 10 cyfr).
    *   Walidacja walut (obsługa tylko PLN, EUR, USD).
    *   Blokowanie transakcji z datą przyszłą.
*   **Bezpieczeństwo finansowe:** Wykorzystanie klasy `BigDecimal` do wszystkich obliczeń pieniężnych, aby uniknąć błędów zaokrągleń typu `float/double`.
*   **System Raportowania:** Generowanie podsumowania zawierającego statystyki zaakceptowanych i odrzuconych operacji wraz z powodami błędów.

## 🛠 Technologia
*   **Język:** Java 26
*   **Paradygmaty:** Programowanie Obiektowe (OOP), Clean Code.
*   **Narzędzia:** Brak bibliotek zewnętrznych (Pure Java Core) – pełna kontrola nad logiką.

## 📁 Struktura Projektu
*   `Transaction.java` - Model danych (record).
*   `TransactionParser.java` - Odczyt i interpretacja plików wejściowych.
*   `Validator.java` - Serce systemu; zawiera reguły walidacyjne i rzuca niestandardowe wyjątki.
*   `ReportGenerator.java` - Moduł odpowiedzialny za formatowanie wyjścia konsolowego.

## 📋 Przykład danych wejściowych (transactions.csv)
```text
TX1001;1234567890;1500.00;PLN;2026-05-14
TX1002;09876;50.00;USD;2026-05-10 (BŁĄD: Zły format konta)
TX1003;1122334455;-10.00;EUR;2026-05-01 (BŁĄD: Kwota ujemna)

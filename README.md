# BankDataGuard - Banking Transaction Validation Engine

## 📌 Project Overview
BankDataGuard is a lightweight, pure Java backend engine designed for high-precision parsing and strict validation of banking transaction data. Built with an emphasis on data integrity and financial accuracy, it simulates core banking validation workflows where zero-tolerance for precision loss is critical.

## 🚀 Key Features
* **Data Parsing:** Automated ingestion and parsing of structured transaction records from input streams (CSV/Text).
* **Strict Business Validation:**
  * Account number format verification (exactly 10 digits).
  * Currency compliance (strict enforcement of PLN, EUR, USD).
  * Future-date protection (blocking transactions post-dating the current system time).
* **Financial Safety:** Exclusive utilization of `BigDecimal` for all monetary calculations to eliminate floating-point rounding errors inherent to `float` or `double`.
* **Report Generation:** Comprehensive console-based summary generator compiling accepted/rejected transaction metrics along with precise error reason tracking.

## 🛠️ Tech Stack & Paradigms
* **Language:** Java 21 (or your specific version)
* **Paradigms:** Object-Oriented Programming (OOP), Clean Code, Single Responsibility Principle.
* **Dependencies:** None (Pure Java SE Core) for full control over logic and execution speed.

## 📂 Project Structure
* `Transaction.java` – Data model representing a immutable transaction record.
* `TransactionParser.java` – Input stream handling and parsing logic.
* `Validator.java` – Core validation engine containing strict business rules and custom exception handling.
* `ReportGenerator.java` – Formatting module responsible for generating execution summaries.

## 💻 Input Data Example (`transactions.csv`)
```csv
TX1001;1234567890;1500.00;PLN;2026-05-14
TX1002;09876;50.00;USD;2026-05-10 (ERROR: Bad account format)
TX1003;1122334455;-10.00;EUR;2026-05-01 (ERROR: Negative amount)

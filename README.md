# Kotlin for Java Developers – Week 4 Assignments

This repository contains solutions to two assignments from **Week 4** of the [Kotlin for Java Developers](https://www.coursera.org/learn/kotlin-for-java-developers) course offered on Coursera. These assignments focus on mastering Kotlin conventions, operator overloading, interface implementation, and idiomatic Kotlin practices.

## 📘 Assignment 1: Rational Class

In this task, I implemented a custom `Rational` class to model rational numbers with full support for Kotlin conventions and operator overloading.

### ✅ Key Features:
- Creation of rational numbers using:
  - Constructors
  - `infix` functions
  - `String` parsing
- Custom logic for:
  - `toString()` – returns rational numbers in reduced form
  - `equals()` – compares normalized rational values
- Full operator overloading for:
  - Arithmetic: `+`, `-`, `*`, `/`
  - Comparison: `<`, `<=`, `>`, `>=`, `==`
- Automatic fraction reduction and GCD computation

This exercise helped reinforce Kotlin's expressive syntax, class modeling, and how operator conventions can make user-defined types feel native.

---

## 📗 Assignment 2: Game Board Interfaces

This task involved designing and implementing two interfaces to model a board-based game system.

### 🧩 Interfaces Implemented:
- `SquareBoard`  
- `GameBoard<T>` (extends `SquareBoard`)

### 🛠 Implementation Details:
- **`SquareBoardImpl`**:
  - Implemented core board utilities:
    - `getCell`, `getCellOrNull`, `getAllCells`
    - `getRow`, `getColumn`, `getNeighbour`
- **`GameBoardImpl<T>`**:
  - Stored values in a `Map<Cell, T?>`
  - Implemented:
    - `get`, `put` for cell value access
    - Query functions:
      - `filter(predicate)`
      - `find(predicate)`
      - `any(predicate)`
      - `all(predicate)`

This assignment focused on understanding generics, interface-driven design, and working with Kotlin's standard library functional utilities in a real-world inspired board game scenario.

---

## 🧪 Technologies Used
- **Language**: Kotlin
- **Tools**: Android Studio, Kotlin Standard Library

## 🎓 Course
[Kotlin for Java Developers – Coursera](https://www.coursera.org/learn/kotlin-for-java-developers)

## 👤 Author
Muhammad Shoaib Khalid

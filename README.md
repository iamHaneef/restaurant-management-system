# Restaurant Management System

## 📌 Overview

A Java-based backend system designed to manage restaurant operations including menu handling, table reservations, order processing, billing, and payment flow.

This project focuses on implementing structured business logic using Object-Oriented Programming principles.

---

## ⚡ Features

- Menu management (item selection and pricing)
- Table reservation and availability tracking
- Order creation and status tracking
- Billing system with total calculation
- Payment processing and order completion

---

## 🧠 System Flow

- Customer selects menu items
- Order is created and stored
- Table is assigned or reserved
- System calculates total bill
- Payment is processed
- Order is finalized and recorded

---

## 🛠️ Tech Stack

- Java (Standard Edition)
- Object-Oriented Programming (OOP)
- Java Collections Framework

---

⚙️ How to Run
# Compile
> javac Main.java

# Run
> java Main

---

## 📂 Project Structure

```text
restaurant-management-system/
│
├── menu/
│   └── Menu.java           # Menu item entity
│
├── order/
│   └── Order.java          # Order management entity
│
├── service/
│   └── OrderService.java   # Business logic, Customer and Table management
│
├── Main.java               # Application entry point and interactive CLI
└── README.md               # Project documentation

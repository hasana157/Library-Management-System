# Library Management System (LMS)

A robust, enterprise-ready desktop application developed in Java to automate the core operations of a library. This system leverages **Object-Oriented Programming (OOP)** principles and a modular **Swing-based GUI** to provide a seamless experience for both administrators and library patrons.

---

## 🚀 Key Features

### 1. Multi-Role Access Control

The application features a dual-interface architecture partitioned into **Admin** and **User** modules via a central `CardLayout` manager.

### 2. Administrator Suite (Control Panel)

* **Inventory Management:** Full CRUD (Create, Read, Delete) capabilities for the book catalog.
* **Issuance Tracking:** Mark books as "Issued" directly through the admin dashboard.
* **Data Integrity:** Automated file handling ensures no data loss during book deletions or status updates.

### 3. User Experience (Patron Panel)

* **Catalog Browsing:** Real-time view of all books, their authors, and current availability status.
* **Borrowing Logic:** Self-service borrowing feature that updates book status to "Borrowed."
* **Return Management:** Easy return process to restock the library inventory.

### 4. Technical Excellence

* **Persistence:** Uses flat-file storage (`books.txt`) for lightweight data management without the overhead of a SQL database.
* **Dynamic UI:** Custom-styled buttons, tooltips, and background image rendering for a professional aesthetic.
* **Interactive Tables:** Data is presented using `JTable` within scrollable panes for high readability.

---

## 🖼️ Graphical User Interface (GUI)

### 🏠 Home Dashboard

The entry point of the application, allowing users to select their role.

> ---
> 
> 

### 🔐 Admin Control Panel

The management hub featuring tools for adding, deleting, and issuing books.

> ---
> 
> 

### 📖 User Control Panel

A patron-facing interface focused on discovery and borrowing activities.

> ---
> 
> 

### 📊 Catalog View

An interactive table displaying the book ID, Title, Author, and Status.

> ---
> 
> 

---

## 🛠️ Technical Stack & Architecture

* **Language:** Java (JDK 8+)
* **GUI Framework:** Java Swing & AWT
* **Design Patterns:**
* **Modular Architecture:** Each panel (`homePanel`, `adminPanel`, `userPanel`) is encapsulated for high maintainability.
* **Layout Management:** Uses `CardLayout` for single-window navigation and `BoxLayout`/`GridLayout` for component alignment.


* **Persistence Layer:** Custom File I/O handlers using `BufferedReader` and `BufferedWriter` for transactional updates to the local database.

---

## 📥 Installation & Setup

1. **Clone the Repository:**
```bash
git clone https://github.com/yourusername/library-management-system.git

```


2. **Asset Preparation:**
Ensure a file named `library.jpg` is present in the root directory to serve as the application background.
3. **Compilation:**
```bash
javac LibraryManagementSystem.java

```


4. **Execution:**
```bash
java LibraryManagementSystem

```



---

## 📂 Project Structure

```text
├── LibraryManagementSystem.java # Main Application Logic & GUI
├── books.txt                    # Local Database (Auto-generated)
├── library.jpg                  # UI Background Asset
└── temp.txt                     # Buffer file for data transactions

```

---

## 🤝 Contributing

 Feel free to fork the project, create a feature branch, and submit a pull request for any enhancements.

**Developed by Hasana Zahid** 

<p align="center"> 
  <a href="README.en.md">
    <img src="https://img.shields.io/badge/English%20Version-Click%20Here-blue?style=for-the-badge&logo=readme&logoColor=white" alt="English Version"/>
  </a> 
  <a href="README.md">
    <img src="https://img.shields.io/badge/Spanish%20Version-Click%20Here-orange?style=for-the-badge&logo=readme&logoColor=white" alt="Spanish Version"/>
  </a> 
</p>

# 📚 Library System - Java Desktop Application

![Library System](https://capsule-render.vercel.app/api?type=waving&color=0:0f9b0f,100:38ef7d&height=200&section=header&text=Library%20System&fontSize=50&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=Intelligent%20Library%20Management&descAlignY=58&descAlign=50)

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Swing-GUI-orange?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Reports-PDF-red?style=for-the-badge&logo=adobe&logoColor=white" />
</p>

<p align="center">
  <strong>Comprehensive system for managing loans, returns, users, and books</strong><br/>
  Developed with layered architecture, database connectivity, and professional report generation
</p>

---

## 🚀 Key Features

<table>
<tr>
<td width="50%">

### 📖 **Library Management**
- ✅ Book registration and administration  
- ✅ Loan and return control  
- ✅ User management (readers and administrators)  
- ✅ Advanced search by titles, authors, or categories  

</td>
<td width="50%">

### 🏗️ **System Architecture**
- ✅ MVC pattern implementation  
- ✅ Database connection with JDBC  
- ✅ Swing GUI interface  
- ✅ Reports and statistics exportable to PDF  

</td>
</tr>
</table>

---

## 🖼️ System Screenshots

<div align="center">

### 🔑 Login Screen
<img src="src/main/resources/img/loginVista.png" width="700" alt="Login Screen"/>

### 📌 Main Interface
<img src="src/main/resources/img/panelPrincipalBiblioteca.png" width="700" alt="Main Interface"/>

### 📕 Loan Management
<img src="src/main/resources/img/prestamoVista.png" width="700" alt="Loan Module"/>

### 📗 Return Management
<img src="src/main/resources/img/VistaDevoluciones.png" width="700" alt="Returns View"/>

### 📊 Reports
<img src="src/main/resources/img/reporteVista.png" width="700" alt="Report Generation"/>

</div>

---

## 🛠️ Technology Stack

<div align="center">

| Technology | Version | Purpose |
|:----------:|:-------:|:---------:|
| ![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=java&logoColor=white) | 17 LTS | Business logic and GUI |
| ![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=flat&logo=apache-maven&logoColor=white) | 3.8+ | Build automation and dependency management |
| ![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white) | 8.0 | Relational database |
| ![JDBC](https://img.shields.io/badge/JDBC-Connector-blue?style=flat) | Latest | Database connectivity |
| ![Swing](https://img.shields.io/badge/Swing-GUI-orange?style=flat&logo=java) | Built-in | Graphical interface |
| ![JasperReports](https://img.shields.io/badge/JasperReports-PDF-red?style=flat) | 6.x | Report generation |

</div>

---

## 📁 Project Architecture

```
📦 SistemaBibliotecaJava/
├── 📂 src/
│   ├── 📂 main/
│   │   ├── 📂 java/
│   │   │   ├── 📂 principal/          # Main menu
│   │   │   ├── 📂 prestamos/          # Loans module
│   │   │   ├── 📂 devoluciones/       # Returns module
│   │   │   ├── 📂 usuarios/           # User management
│   │   │   ├── 📂 libros/             # Book management
│   │   │   ├── 📂 reportes/           # PDF reports
│   │   │   ├── 📂 busqueda/           # Search module
│   │   │   ├── 📂 conexion/           # Database connection
│   │   │   └── 📂 salir/              # Logout and exit
│   │   └── 📂 resources/
│   │       ├── 📂 img/                # Application images
│   │       └── 📂 reports/            # JasperReports templates
├── 📂 target/                         # Compiled classes
└── 📄 pom.xml                         # Maven configuration
```

---

## ⚡ Implemented Features

### 👥 **User Management**
- Student and administrator registration  
- Access control with login  
- User loan history  

### 📚 **Book Management**
- Book registration, editing, and deletion  
- Stock and availability control  
- Classification by categories  

### 🔄 **Loans and Returns**
- Active loan registration  
- Due date control  
- Returns with automatic stock updates  

### 📊 **Reports**
- Reports of borrowed and returned books  
- Library usage statistics  
- **PDF** export  

---

## 🚀 Installation and Setup

### Prerequisites
```bash
☕ Java 17 or higher
🛢️ MySQL 8.0+
```

### Installation Steps

1. **Clone the repository**
```bash
git clone https://github.com/Reverse07/SistemaBibliotecaJava.git
cd SistemaBibliotecaJava
```

2. **Configure database**
```sql
CREATE DATABASE biblioteca;
USE biblioteca;
-- Import script with tables and initial data
```

3. **Configure connection**
Edit `conexion/CConexion.java`:
```java
String url = "jdbc:mysql://localhost:3306/biblioteca";
String user = "your_username";
String password = "your_password";
```

4. **Run the project**
   - From your IDE (NetBeans/IntelliJ)
   
   Or with console:
```bash
javac -cp "lib/*:src" src/principal/Main.java
java -cp "lib/*:src" principal.Main
```

---

## 📈 Project Metrics

<div align="center">

![Lines of Code](https://img.shields.io/badge/Lines%20of%20Code-5000+-brightgreen?style=for-the-badge)
![Files](https://img.shields.io/badge/Files-50+-blue?style=for-the-badge)
![Version](https://img.shields.io/badge/Version-1.0-success?style=for-the-badge)

</div>

---

## 🤝 Contributing

Contributions are welcome. To contribute:

1. Fork the project
2. Create a branch (`git checkout -b feature/NewFeature`)
3. Commit your changes (`git commit -m 'Added new feature'`)
4. Push to the branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

---

## 📝 License

This project is under the MIT License. See `LICENSE` for more details.

---

## 📞 Contact and Support

<div align="center">

**Diego Arroyo**  
*Java Developer | Systems Engineering Student*

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Reverse07)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/your-profile)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:your.email@gmail.com)

</div>

---

## 🌟 Did you like the project?

If this project was useful to you, consider giving it a ⭐ on GitHub!

<div align="center">

**Developed with ❤️ for efficient library management**

![Footer](https://capsule-render.vercel.app/api?type=waving&color=0:0f9b0f,100:38ef7d&height=120&section=footer)

</div>

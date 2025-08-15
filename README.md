<p align="center"> 
  <a href="README.en.md">
    <img src="https://img.shields.io/badge/English%20Version-Click%20Here-blue?style=for-the-badge&logo=readme&logoColor=white" alt="English Version"/>
  </a> 
  <a href="README.md">
    <img src="https://img.shields.io/badge/Versión%20en%20Español-Haz%20Click-orange?style=for-the-badge&logo=readme&logoColor=white" alt="Versión en Español"/>
  </a> 
</p>

# 📚 Sistema de Biblioteca - Java Desktop Application

![Sistema de Biblioteca](https://capsule-render.vercel.app/api?type=waving&color=0:0f9b0f,100:38ef7d&height=200&section=header&text=Sistema%20de%20Biblioteca&fontSize=50&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=Gestión%20Inteligente%20de%20Bibliotecas&descAlignY=58&descAlign=50)

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Swing-GUI-orange?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Reports-PDF-red?style=for-the-badge&logo=adobe&logoColor=white" />
</p>

<p align="center">
  <strong>Sistema integral para la gestión de préstamos, devoluciones, usuarios y libros</strong><br/>
  Desarrollado con arquitectura en capas, conexión a base de datos y generación de reportes profesionales
</p>

---

## 🚀 Características Principales

<table>
<tr>
<td width="50%">

### 📖 **Gestión de Biblioteca**
- ✅ Registro y administración de libros  
- ✅ Control de préstamos y devoluciones  
- ✅ Gestión de usuarios (lectores y administradores)  
- ✅ Búsqueda avanzada por títulos, autores o categorías  

</td>
<td width="50%">

### 🏗️ **Arquitectura del Sistema**
- ✅ Patrón MVC implementado  
- ✅ Conexión a base de datos con JDBC  
- ✅ Interfaz gráfica con Swing  
- ✅ Reportes y estadísticas exportables a PDF  

</td>
</tr>
</table>

---

## 🖼️ Capturas del Sistema

<div align="center">

### 🔑 Pantalla de Login
<img src="src/img/LoginVista.png" width="700" alt="Pantalla de Login"/>

### 📌 Interfaz Principal
<img src="src/img/MenuVista.png" width="700" alt="Interfaz Principal"/>

### 📕 Gestión de Préstamos
<img src="src/img/PrestamosVista.png" width="700" alt="Módulo de Préstamos"/>

### 📗 Gestión de Devoluciones
<img src="src/img/DevolucionesVista.png" width="700" alt="Módulo de Devoluciones"/>

### 📊 Reportes
<img src="src/img/ReportesVista.png" width="700" alt="Generación de Reportes"/>

</div>

---

## 🛠️ Stack Tecnológico

<div align="center">

| Tecnología | Versión | Propósito |
|:----------:|:-------:|:---------:|
| ![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=java&logoColor=white) | 17 LTS | Lógica de negocio y GUI |
| ![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white) | 8.0 | Base de datos relacional |
| ![JDBC](https://img.shields.io/badge/JDBC-Connector-blue?style=flat) | Latest | Conectividad BD |
| ![Swing](https://img.shields.io/badge/Swing-GUI-orange?style=flat&logo=java) | Built-in | Interfaz gráfica |
| ![JasperReports](https://img.shields.io/badge/JasperReports-PDF-red?style=flat) | 6.x | Generación de reportes |

</div>

---

## 📁 Arquitectura del Proyecto

```
📦 SistemaBibliotecaJava/
├── 📂 principal/          # Menú principal
├── 📂 prestamos/          # Módulo de préstamos
├── 📂 devoluciones/       # Módulo de devoluciones
├── 📂 usuarios/           # Gestión de usuarios
├── 📂 libros/             # Gestión de libros
├── 📂 reportes/           # Reportes PDF
├── 📂 busqueda/           # Módulo de búsqueda
├── 📂 conexion/           # Conexión a la BD
└── 📂 salir/              # Cerrar sesión y salida
```

---

## ⚡ Funcionalidades Implementadas

### 👥 **Gestión de Usuarios**
- Registro de estudiantes y administradores  
- Control de accesos con login  
- Historial de préstamos por usuario  

### 📚 **Gestión de Libros**
- Registro, edición y eliminación de libros  
- Control de stock y disponibilidad  
- Clasificación por categorías  

### 🔄 **Préstamos y Devoluciones**
- Registro de préstamos activos  
- Control de fechas de entrega  
- Devoluciones con actualización automática de stock  

### 📊 **Reportes**
- Reportes de libros prestados y devueltos  
- Estadísticas de uso de la biblioteca  
- Exportación a **PDF**  

---

## 🚀 Instalación y Configuración

### Prerrequisitos
```bash
☕ Java 17 o superior
🛢️ MySQL 8.0+
```

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/Reverse07/SistemaBibliotecaJava.git
cd SistemaBibliotecaJava
```

2. **Configurar base de datos**
```sql
CREATE DATABASE biblioteca;
USE biblioteca;
-- Importar script con tablas y datos iniciales
```

3. **Configurar conexión**
Editar `conexion/CConexion.java`:
```java
String url = "jdbc:mysql://localhost:3306/biblioteca";
String user = "tu_usuario";
String password = "tu_contraseña";
```

4. **Ejecutar el proyecto**
   - Desde tu IDE (NetBeans/IntelliJ)
   
   O con consola:
```bash
javac -cp "lib/*:src" src/principal/Main.java
java -cp "lib/*:src" principal.Main
```

---

## 📈 Métricas del Proyecto

<div align="center">

![Lines of Code](https://img.shields.io/badge/Lines%20of%20Code-5000+-brightgreen?style=for-the-badge)
![Files](https://img.shields.io/badge/Files-50+-blue?style=for-the-badge)
![Version](https://img.shields.io/badge/Version-1.0-success?style=for-the-badge)

</div>

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Fork el proyecto
2. Crear una rama (`git checkout -b feature/NuevaFuncionalidad`)
3. Commit de los cambios (`git commit -m 'Agregada nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/NuevaFuncionalidad`)
5. Abrir un Pull Request

---

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver `LICENSE` para más detalles.

---

## 📞 Contacto y Soporte

<div align="center">

**Diego Arroyo**  
*Desarrollador Java | Estudiante de Ingeniería de Sistemas*

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Reverse07)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/tu-perfil)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:tu.email@gmail.com)

</div>

---

## 🌟 ¿Te gustó el proyecto?

Si este proyecto te fue útil, ¡considera darle una ⭐ en GitHub!

<div align="center">

**Desarrollado con ❤️ para la gestión eficiente de bibliotecas**

![Footer](https://capsule-render.vercel.app/api?type=waving&color=0:0f9b0f,100:38ef7d&height=120&section=footer)

</div>

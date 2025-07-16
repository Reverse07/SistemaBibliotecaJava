
package Modelo;

public class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private String correo;
    private int telefono;
    private RolesBiblioteca rol;

    public Usuario() {
    }
   
    public Usuario(int id, String nombre, String apellido, int dni, String correo, int telefono, RolesBiblioteca rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public RolesBiblioteca getRol() {
        return rol;
    }

    public void setRol(RolesBiblioteca rol) {
        this.rol = rol;
    }

    @Override
public String toString() {
    return nombre + " " + apellido;  // O lo que quieras mostrar
}
}
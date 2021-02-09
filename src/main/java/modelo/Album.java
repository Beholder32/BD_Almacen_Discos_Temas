package modelo;

public class Album {
    
    int id;
    String nom;
    String grup;
    String fecha;
    
    public Album(){}
    
    public Album(int id, String nom, String grup, String fecha){
        this.id=id;
        this.nom=nom;
        this.grup=grup;
        this.fecha=fecha;
    }

    // Getters & Setters de los atributos
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
     
}

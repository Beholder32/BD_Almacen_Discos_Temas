package modelo;

public class Cancion {
    int idC;
    String nomC;
    String grupC;
    int albumC;
    
    public Cancion(){
    }

    public Cancion(int idC, String nomC, String grupC, int albumC) {
        this.idC = idC;
        this.nomC = nomC;
        this.grupC = grupC;
        this.albumC = albumC;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getGrupC() {
        return grupC;
    }

    public void setGrupC(String grupC) {
        this.grupC = grupC;
    }

    public int getAlbumC() {
        return albumC;
    }

    public void setAlbumC(int albumC) {
        this.albumC = albumC;
    }
    
    
    
}

package modelo;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CancionDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Cancion>datos = new ArrayList<>();
        String sql = "SELECT * FROM canciones";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Cancion c = new Cancion();
                c.setIdC(rs.getInt(1));
                c.setNomC(rs.getString(2));
                c.setGrupC(rs.getString(3));
                c.setAlbumC(rs.getInt(4));
                datos.add(c);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al ejecutar el listado");
        }
        return datos;
    }
    
    public List filtrarCancionAlbum(String nom){
        List<Cancion>datos = new ArrayList<>();
        String sql = "SELECT idCancion, nombreCancion, Grupo, album FROM canciones INNER JOIN album ON canciones.album = album.idDisco WHERE album.nombreDisco = ?;";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            while (rs.next()){
                Cancion c = new Cancion();
                Album a = new Album();
                c.setIdC(rs.getInt(1));
                c.setNomC(rs.getString(2));
                c.setGrupC(rs.getString(3));
                c.setAlbumC(rs.getInt(4));
                datos.add(c);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Error al realizar listado");
            System.out.println(e.getMessage());
        }
        return datos;
    }
    
    public List filtrarCancionGrupo(String nom){
        List<Cancion>datos = new ArrayList<>();
        String sql = "SELECT idCancion, nombreCancion, Grupo, album FROM canciones INNER JOIN album ON canciones.album = album.idDisco WHERE album.nombreGrupo = ?;";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            while (rs.next()){
                Cancion c = new Cancion();
                Album a = new Album();
                c.setIdC(rs.getInt(1));
                c.setNomC(rs.getString(2));
                c.setGrupC(rs.getString(3));
                c.setAlbumC(rs.getInt(4));
                datos.add(c);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Error al realizar listado");
            System.out.println(e.getMessage());
        }
        return datos;
    }
    
    public int agregar(Cancion c){
        String sql="INSERT INTO canciones (nombreCancion, Grupo, album) VALUES (?,?,?)";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNomC());
            ps.setString(2, c.getGrupC());
            ps.setInt(3, c.getAlbumC());
            ps.executeUpdate();
            ps.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al agregar alemento");
        }
        return 1; 
    }
    
    public int actualizar(Cancion c){
        String sql = "UPDATE canciones SET nombreCancion=?, Grupo=?, album=? WHERE idCancion=?";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNomC());
            ps.setString(2, c.getGrupC());
            ps.setInt(3, c.getAlbumC());
            ps.setInt(4, c.getIdC());
            ps.executeUpdate();
            ps.close();
            con.close();
        }catch (Exception e){
            System.out.println("Error al agregar actualizar los datos");
        }
        return 1;
    }
    
    public void eliminar(int id){
        String sql = "DELETE FROM canciones WHERE idCancion="+id;
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al borrar un registro");
        }
    } 
}


package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Album;
import modelo.AlbumDAO;
import modelo.Cancion;
import modelo.CancionDAO;
import vista.Vista;

public class Controlador implements ActionListener{
    
    //Importacion del Objeto y los metodos Album
    AlbumDAO dao = new AlbumDAO();
    Album a = new Album();
    
    //Importacion del Objeto y los metodos Cancion
    Cancion c = new Cancion();
    CancionDAO dao2 = new CancionDAO();
    
    Vista vista = new Vista();
   
    //Variables para las tablas
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    
    public Controlador(Vista v){
        //Aquí se activa la vista
        this.vista = v;
        //Aqui se activan los elementos de la vista que tienen interaccion con los albumes
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnAceptar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        //Botones canciones
        this.vista.btnListar2.addActionListener(this);
        this.vista.btnGuardar2.addActionListener(this);
        this.vista.btnEditar2.addActionListener(this);
        this.vista.btnAceptar2.addActionListener(this);
        this.vista.btnEliminar2.addActionListener(this);
        this.vista.btnBuscar1.addActionListener(this);
        this.vista.btnBuscar2.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Aquí se decide cuando se ejecutan los métodos asociados a los botones activados anteriormente
        if(e.getSource()==vista.btnListar){
            limpiarTabla();
            listar(vista.tablaDiscos);
        }
        if(e.getSource()==vista.btnGuardar){
            agregar();
            limpiarTabla();
            listar(vista.tablaDiscos);
        }
        if(e.getSource()==vista.btnEditar){
            int fila = vista.tablaDiscos.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "No hay disco seleccionado");
            }else{
                int id=Integer.parseInt((String)vista.tablaDiscos.getValueAt(fila, 0).toString());
                String nom = (String)vista.tablaDiscos.getValueAt(fila, 1);
                String grup = (String)vista.tablaDiscos.getValueAt(fila, 2);
                String fecha = (String)vista.tablaDiscos.getValueAt(fila, 3);
                vista.cajaTxtID.setText(""+id);
                vista.cajaTxtNombre.setText(nom);
                vista.cajaTxtGrupo.setText(grup);
                vista.cajaTxtFecha.setText(fecha);
            }
        }
        if(e.getSource() == vista.btnAceptar){
            actualizar();
            limpiarTabla();
            listar(vista.tablaDiscos);
        }
        if(e.getSource() == vista.btnEliminar){
            borrar();
            limpiarTabla();
            listar(vista.tablaDiscos);
        }
        
        // --------- Activadores de los botones para las canciones ---------
        
        if(e.getSource()==vista.btnListar2){
            limpiarTabla2();
            listarCancion(vista.tablaCanciones);
        }
        if(e.getSource()==vista.btnGuardar2){
            agregarCancion();
            limpiarTabla2();
            listarCancion(vista.tablaCanciones);
        }
        if(e.getSource()==vista.btnEditar2){
            int fila = vista.tablaCanciones.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "No hay cancion seleccionada");
            }else{
                int idC=Integer.parseInt((String)vista.tablaCanciones.getValueAt(fila, 0).toString());
                String nomC = (String)vista.tablaCanciones.getValueAt(fila, 1);
                String grupC = (String)vista.tablaCanciones.getValueAt(fila, 2);
                int albumC = Integer.parseInt((String)vista.tablaCanciones.getValueAt(fila, 3).toString());
                vista.cajaTxtIdCancion.setText(""+idC);
                vista.cajaTxtNombreCancion.setText(nomC);
                vista.cajaTxtNombreGrupo.setText(grupC);
                vista.cajaTxtFKalbum.setText(""+albumC);
            }
        }
        if(e.getSource() == vista.btnAceptar2){
            actualizarCancion();
            limpiarTabla2();
            listarCancion(vista.tablaCanciones);
        }
        if(e.getSource() == vista.btnEliminar2){
            borrarCancion();
            limpiarTabla2();
            listarCancion(vista.tablaCanciones);
        }
        if(e.getSource() == vista.btnBuscar1){
            limpiarTabla2();
            buscarCancionAlbum(vista.tablaCanciones);
        }
        if(e.getSource() == vista.btnBuscar2){
            limpiarTabla2();
            buscarCancionGrupo(vista.tablaCanciones);
        }
    }
    
    // ------------- Metodos para los albumes ----------------
    
    public void borrar(){
        int fila = vista.tablaDiscos.getSelectedRow();
            
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "No hay disco seleccionado");
        }else{
            int id=Integer.parseInt((String)vista.tablaDiscos.getValueAt(fila, 0).toString());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(vista, "Registro eliminado");
        }
    }
    
    void limpiarTabla(){
        for(int i = 0;i<vista.tablaDiscos.getRowCount();i++){
            modelo.removeRow(i);
            i=i-1;
        }
    }
    
       
    public void actualizar(){
        int id=Integer.parseInt(vista.cajaTxtID.getText());
        String nom = vista.cajaTxtNombre.getText();
        String grup = vista.cajaTxtGrupo.getText();
        String fecha = vista.cajaTxtFecha.getText();
        a.setId(id);
        a.setNom(nom);
        a.setGrup(grup);
        a.setFecha(fecha);
        int respuesta = dao.actualizar(a);
        if(respuesta==1){
            JOptionPane.showMessageDialog(vista, "Disco actualizado con éxito");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al actualizar un disco");
        }
    }
    
    public void agregar(){
        //recibimos la informacion de las cajas de texto
        String nom = vista.cajaTxtNombre.getText();
        String grup = vista.cajaTxtGrupo.getText();
        String fecha = vista.cajaTxtFecha.getText();
        a.setNom(nom);
        a.setGrup(grup);
        a.setFecha(fecha);
        int respuesta = dao.agregar(a);
        if(respuesta==1){
            JOptionPane.showMessageDialog(vista, "Disco agregado a la colección");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al introducir disco a la colección");
        }
    }
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        List<Album>lista = dao.listar();
        Object[]object = new Object[4];
        for (int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNom();
            object[2] = lista.get(i).getGrup();
            object[3] = lista.get(i).getFecha();
            modelo.addRow(object);
        }
        vista.tablaDiscos.setModel(modelo);
    }
    
// ------------ Metodos para las Canciones --------------------
    
    void limpiarTabla2(){
        for(int i = 0;i<vista.tablaCanciones.getRowCount();i++){
            modelo2.removeRow(i);
            i=i-1;
        }
    }
    
    public void listarCancion(JTable tabla2){
        modelo2 = (DefaultTableModel)tabla2.getModel();
        List<Cancion>lista = dao2.listar();
        Object[]object = new Object[4];
        for (int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getIdC();
            object[1] = lista.get(i).getNomC();
            object[2] = lista.get(i).getGrupC();
            object[3] = lista.get(i).getAlbumC();
            modelo2.addRow(object);
        }
        vista.tablaCanciones.setModel(modelo2);
    }
    
    public void agregarCancion(){
        //recibimos la informacion de las cajas de texto
        String nomC = vista.cajaTxtNombreCancion.getText();
        String grupC = vista.cajaTxtNombreGrupo.getText();
        int albumC = Integer.parseInt(vista.cajaTxtFKalbum.getText());
        c.setNomC(nomC);
        c.setGrupC(grupC);
        c.setAlbumC(albumC);
        int respuesta = dao2.agregar(c);
        if(respuesta==1){
            JOptionPane.showMessageDialog(vista, "Canción agregada a la colección");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al introducir disco a la colección");
        }
    }
    
    public void actualizarCancion(){
        int idC=Integer.parseInt(vista.cajaTxtIdCancion.getText());
        String nomC = vista.cajaTxtNombreCancion.getText();
        String grupC = vista.cajaTxtNombreGrupo.getText();
        int albumC = Integer.parseInt(vista.cajaTxtFKalbum.getText());
        c.setIdC(idC);
        c.setNomC(nomC);
        c.setGrupC(grupC);
        c.setAlbumC(albumC);
        int respuesta = dao2.actualizar(c);
        if(respuesta==1){
            JOptionPane.showMessageDialog(vista, "Canción actualizada con éxito");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al actualizar una canción");
        }
    }
    
    public void borrarCancion(){
        int fila = vista.tablaCanciones.getSelectedRow();
            
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "No hay cancion seleccionada");
        }else{
            int idC=Integer.parseInt((String)vista.tablaCanciones.getValueAt(fila, 0).toString());
            dao2.eliminar(idC);
            JOptionPane.showMessageDialog(vista, "Registro eliminado");
        }
    }
    
    public void buscarCancionAlbum(JTable tabla2){
        String nom = (String)vista.cajaTxtBuscarCancion.getText();
        List<Cancion>lista = dao2.filtrarCancionAlbum(nom);
        modelo2 = (DefaultTableModel)tabla2.getModel();
        Object[]object = new Object[4];
        for (int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getIdC();
            object[1] = lista.get(i).getNomC();
            object[2] = lista.get(i).getGrupC();
            object[3] = lista.get(i).getAlbumC();
            modelo2.addRow(object);
        }
        vista.tablaCanciones.setModel(modelo2);
    }
    
    public void buscarCancionGrupo(JTable tabla2){
        String nom = (String)vista.cajaTxtBuscarCancion2.getText();
        List<Cancion>lista = dao2.filtrarCancionGrupo(nom);
        modelo2 = (DefaultTableModel)tabla2.getModel();
        Object[]object = new Object[4];
        for (int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getIdC();
            object[1] = lista.get(i).getNomC();
            object[2] = lista.get(i).getGrupC();
            object[3] = lista.get(i).getAlbumC();
            modelo2.addRow(object);
        }
        vista.tablaCanciones.setModel(modelo2);
    }
    
}

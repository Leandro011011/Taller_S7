import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {

    private JPanel ventana;
    private JTabbedPane tabbedPane1;
    private JTextField txtId;
    private JTextField txtNombre;
    private JComboBox cmbMision;
    private JSpinner spnPeligrosidad;
    private JTextField txtPagoMensual;
    private JButton agregarAvengerButton;
    private JButton limpiarCamposButton;
    private JList listAvengers;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JTextArea txtInfoBuscado;
    private JButton btnEditar;
    private JTextField txtIdEdit;
    private JTextField txtNombreEdit;
    private JComboBox cmbMisionEdit;
    private JSpinner spnPeligrosidadEdit;
    private JTextField txtPagoMensualEdit;
    private JButton btnEditarDatos;
    private JTextArea txtInfoEstTotales;
    private JButton btnCalcularEstTotales;
    private JTextArea txtInfoEstAvenger;
    private JButton btnCalcularEstAvenger;
    private JList listEstadisticaAvengers1;
    private JButton btnActualizarL;
    private JButton btnEstAvenger;

    ControlAvengers control = new ControlAvengers();
    int codigo = 0;
    int indice;

    public void llenarList(){
        DefaultListModel lsm = new DefaultListModel();
        for (Avenger av: control.getListaAvengers()){
            lsm.addElement(av);
        }
        listAvengers.setModel(lsm);
        listEstadisticaAvengers1.setModel(lsm);

    }

    public Ventana(){
        llenarList();
        SpinnerNumberModel spnModelR = new SpinnerNumberModel(1, 1, 10, 1);
        SpinnerNumberModel spnModelE = new SpinnerNumberModel(1, 1, 10, 1);
        spnPeligrosidad.setModel(spnModelR);
        spnPeligrosidadEdit.setModel(spnModelE);
        txtInfoBuscado.setEditable(false);
        txtInfoEstTotales.setEditable(false);
        txtInfoEstAvenger.setEditable(false);
        txtIdEdit.setEditable(false);

        ///  PRIMER SECCION DE 'REGISTRAR AVENGER'
        // Primer Boton de agregar avenger
        agregarAvengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = txtId.getText();
                String textoNombre = txtNombre.getText();
                String textoPagoMensual = txtPagoMensual.getText();
                if (textoId.isEmpty() || textoNombre.isEmpty() || textoPagoMensual.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, datos faltantes", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    int id = Integer.parseInt(txtId.getText());
                    String nombre = txtNombre.getText();
                    String mision = cmbMision.getSelectedItem().toString();
                    int peligrosidad = Integer.parseInt(spnPeligrosidad.getValue().toString());
                    double pagoMensual = Double.parseDouble(txtPagoMensual.getText());
                    if (mision.isEmpty() || pagoMensual < 0){
                        JOptionPane.showMessageDialog(null, "Error, datos faltantes", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Avenger av = new Avenger(id, nombre, mision, peligrosidad, pagoMensual);
                        control.agregarAvenger(av);
                        JOptionPane.showMessageDialog(null, "Avenger Registrado :D");
                    }
                }
            }
        });
        //Segundo Boton de Limpiar Campos
        limpiarCamposButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Limpiando campos....");
                txtId.setText("");
                txtNombre.setText("");
                cmbMision.setSelectedIndex(0);
                spnPeligrosidad.setValue(1);
                txtPagoMensual.setText("");
            }
        });

        /// SEGUNDA SECCION DE GESTION
        //Primer Boton de Colocar los datos en Editar datos de un Avenger ya registrado
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control.getListaAvengers().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, aun no hay Avenger registrados", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else {
                    if (listAvengers.getSelectedIndex() != -1) {
                        indice = listAvengers.getSelectedIndex();
                        Avenger AvEdit = control.getListaAvengers().get(indice);
                        txtIdEdit.setText("" + AvEdit.getId());
                        txtNombreEdit.setText(AvEdit.getNombre());
                        cmbMisionEdit.setSelectedItem(AvEdit.getMision());
                        spnPeligrosidadEdit.setValue(AvEdit.getPeligrosidad());
                        txtPagoMensualEdit.setText("" + AvEdit.getPagomensual());

                        JOptionPane.showMessageDialog(null, "Exito, mira la seccion de editar");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, debe seleccionar un Avenger de la lista", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        //SEGUNDO BOTON DE ELIMINAR
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control.getListaAvengers().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, aun no hay Avenger registrados", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    if (listAvengers.getSelectedIndex()!= -1){
                        indice = listAvengers.getSelectedIndex();
                        Avenger AvEliminar = control.getListaAvengers().get(indice);
                        int id = AvEliminar.getId();

                        int opcion = JOptionPane.showConfirmDialog(null, "Esta Seguro de Eliminarlo?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION){
                            if(control.eliminarAvenger(id)){
                                JOptionPane.showMessageDialog(null, "Eliminado con Exito :D");
                            }
                            llenarList();
                        } else if (opcion == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "Cancelando....");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Error, debe seleccionar un Avenger de la lista", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //TERCER BOTON DE BUSCAR A UN AVENGR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control.getListaAvengers().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, aun no hay Avenger registrados", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else {
                    if (listAvengers.getSelectedIndex() != -1) {
                        indice = listAvengers.getSelectedIndex();
                        Avenger AvBuscar = control.getListaAvengers().get(indice);
                        JOptionPane.showMessageDialog(null, "Buscando.....");
                        txtInfoBuscado.setText("Informacion Detallada del Avenger\n ID: "+AvBuscar.getId()+"\nNombre: "+AvBuscar.getNombre()+"\nMision: "+AvBuscar.getMision()+"\nPeligrosidad: "+AvBuscar.getPeligrosidad()+"\nPago Mensual: "+AvBuscar.getPagomensual());
                    }else{
                        JOptionPane.showMessageDialog(null, "Error, debe seleccionar un Avenger de la lista", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //QUINTO BOTON DE ACTUALIZAR LA LISTA
        btnActualizarL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control.getListaAvengers().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, aun no hay avengers registrados", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Actualizando Avengers....");
                    llenarList();
                }
            }
        });

        /// TERCERA SECCION DE EDITAR DATOS DE UN AVENGER YA EXISTENTE
        btnEditarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = txtIdEdit.getText();
                String textoNombre = txtNombreEdit.getText();
                String textoPagoMensual = txtPagoMensualEdit.getText();
                if (textoId.isEmpty() || textoNombre.isEmpty() || textoPagoMensual.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, datos faltantes", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else {
                    int id = Integer.parseInt(txtIdEdit.getText());
                    String nombre = txtNombreEdit.getText();
                    String mision = cmbMisionEdit.getSelectedItem().toString();
                    int peligrosidad = Integer.parseInt(spnPeligrosidadEdit.getValue().toString());
                    double pagoMensual = Double.parseDouble(txtPagoMensualEdit.getText());
                    if (mision.isEmpty() || pagoMensual < 0) {
                        JOptionPane.showMessageDialog(null, "Error, datos faltantes", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro de editar los datos?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION){
                            Avenger avE = new Avenger(id, nombre, mision, peligrosidad, pagoMensual);
                            control.getListaAvengers().set(indice, avE);
                            llenarList();

                            JOptionPane.showMessageDialog(null, "Edicion de datos con exito :D");
                        } else if (opcion == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "Cancelando.....");
                        }
                    }
                }
            }
        });

        /// CURATA SECCION DE ESTADISTICAS
        //PRIMER BOTON DE CALCULAR LAS ESTADISTICAS TOTALES
        btnCalcularEstTotales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Calculando.....");
                String totalPNetos = String.format("%.1f", control.totalPagosNetos());
                txtInfoEstTotales.setText("====Estadisticas Totales===="+"\n"+"\nTotal Aportes: "+control.totalAportes()+"$"+"\nTotal de Impuestos: "+control.totalImpuestos()+"$"+"\nTotal Pagos Netos: "+totalPNetos+"$");
            }
        });
        //Segundo Boton de Calcular Estadisticas por Avenger
        btnCalcularEstAvenger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control.getListaAvengers().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, no hay Avengers ingresados", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    txtInfoEstAvenger.setText("");
                    if (listEstadisticaAvengers1.getSelectedIndex()!= -1){
                        indice = listEstadisticaAvengers1.getSelectedIndex();
                        Avenger avEst = control.getListaAvengers().get(indice);
                        String totalPNeto = String.format("%.1f", avEst.calcularPagoNeto());
                        JOptionPane.showMessageDialog(null, "Calculando....");
                        txtInfoEstAvenger.append("====Estadisticas del Avenger: "+avEst.getNombre()+"====");
                        txtInfoEstAvenger.append("\nPago Mensual: "+avEst.getPagomensual());
                        txtInfoEstAvenger.append("\nTotal Aportes: "+avEst.calcularAporteFondo()+"$");
                        txtInfoEstAvenger.append("\nImpuesto anual del Avenger: "+avEst.calcularImpuesto()+"$");
                        txtInfoEstAvenger.append("\nTotal Pago Neto a Recibir por Mes: "+totalPNeto+"$");
                    }else{
                        JOptionPane.showMessageDialog(null, "Error, debe seleccionar un avenger de la lista", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        //BOTON DE MOTRAR LA LISTA DE AVENGERS
        btnEstAvenger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control.getListaAvengers().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Error, aun no hay avengers registrados", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Mostrando Avengers....");
                    llenarList();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestion Avenger");
        frame.setContentPane(new Ventana().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Aline Exojo González y María Ortiz Riera
 */
public class Cesion extends Moto {

    // Declaraciones
    private int id_cesion;
    private Date fecha;
    private int idmoto;
    private int socio_poseeM;
    private int socio_cedeM;

    private static ArrayList<Cesion> array_cesion = new ArrayList<>();

    /**
     * Constructor de la clase Cesion sin parametros.
     */
    public Cesion()
    {
        this.id_cesion = 0;
        this.idmoto=0;
        this.socio_poseeM=0;
        this.socio_cedeM=0;
    }

    /**
     * Método que guarda el socio que posee la moto actualmente.
     *
     * @param socio1 le pasamos el numero de socio que posee dicha moto.
     */
    public void setSocioPoseeMoto(int socio1)
    {
        this.socio_poseeM = socio1;
    }

    /**
     * Método que obtiene el socio que posee la moto actualmente.
     *
     * @return devolvemos el numero del socio que posee la moto.
     */
    public int getSocioPoseeMoto()
    {
        return this.socio_poseeM;
    }

    /**
     * Método que guarda el socio al que se le va a ceder la moto.
     *
     * @param socio2  le pasamos el numero de socio al que le cederemos
     *                la moto.
     */
    public void setSocioCedeMoto(int socio2)
    {
        this.socio_cedeM = socio2;
    }

    /**
     * Método que devuelve el socio al que se le va a ceder la moto.
     *
     * @return devolvemos el socio al que se le cede la moto.
     */
    public int getSocioCedeMoto()
    {
        return this.socio_cedeM;
    }

    /**
     * Getter del atributo fecha
     *
     * @return  devolvemos la fecha en la que se inicia la cesión.
     */

    public Date getFecha() {
        return fecha;
    }

    /**
     * Setter del atributo fecha
     *
     * @param fecha le pasamos la fecha en que se inicia la cesión.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Getter del id de la cesión
     *
     * @return devuelve el id de la cesión.
     */
    public int getId_cesion() {
        return id_cesion;
    }

    /**
     * Setter del id de la cesión.
     *
     * @param id_cesion le pasamos el id de la cesión.
     */
    public void setId_cesion(int id_cesion) {
        this.id_cesion = id_cesion;
    }

    /**
     * Método que añade TODOS los datos al array cesion.
     *
     * @param ces Pasamos la cesión completa para añadirla al array.
     */
    public void introducirCesion(Cesion ces)
    {
        array_cesion.add(ces);
    }

    /**
     * Método que devuelve el array cesiones.
     *
     * @return devolvemos el array de cesiones.
     */
    public ArrayList<Cesion> getArrayCesion()
    {
       return array_cesion;
    }

    /**
     * Función toString, convierte todos los atributos de la clase Cesion en
     * String.
     *
     * @return devuelve los atributos convertidos en String.
     */

    @Override
    public String toString()
    {
        return " "+String.format("%03d", this.getId_cesion())+"   Moto "+String.format("%03d", this.getId_moto())+"    "+this.getFecha()+ "   Socio "
                +(String.format("%03d",this.getSocioPoseeMoto()))+ " cede a socio "+(String.format("%03d",this.getSocioCedeMoto()));
    }


}

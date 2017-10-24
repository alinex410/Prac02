/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prac02;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Aline Exojo González y María Ortiz Riera
 */
public class Cliente {

    // Declaraciones
    private int num_socio;
    private String nombre_c;
    private float importe;
    private int n_motos;
    private int num_cesiones; // Número de cesiones recibidas

    private static ArrayList<Cliente> array_clientes = new ArrayList<>();

    boolean correcto;

    // Expresiones regulares
    String patron_num = "[0-9]+";
    String patron_nom = "[A-Z]+[A-Za-z ]+";
    String patron_importe = "[1-9][0-9]*(.[0-9]+){0,1}";

    Pattern pnum = Pattern.compile(patron_num);
    Pattern pn = Pattern.compile(patron_nom);
    Pattern pi = Pattern.compile(patron_importe);

    Matcher matcher;


    /**
     * Constructor de la clase Cliente, sin parametros.
     */
    public Cliente(){
        this.num_socio = 0;
        this.nombre_c = "";
        this.importe = 0;
        this.n_motos = 0;
        this.num_cesiones = 0;

    }

    /**
     * Getter del numero del socio del Cliente
     *
     * @return Devolvemos el numero del socio.
     */
    public int getNum_socio() {
        return num_socio;
    }

    /**
     * Incrementamos el valor del numero de socio del Cliente en 1.
     */

    public void incNum_socio()
    {
        this.num_socio = num_socio + 1;
    }

    /**
     * Setter del numero del socio del Cliente.
     *
     * @param num_socio
     */
    public void setNum_socio(int num_socio) {
        this.num_socio = num_socio;
    }

    /**
     * Getter del nombre del cliente
     *
     * @return devolvemos el nombre del cliente.
     */
    public String getNombre() {
        return nombre_c;
    }

    /**
     * Setter del nombre del Cliente
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre_c = nombre;
    }

    /**
     * Getter del importe del Cliente.
     *
     * @return devolvemos el importe que posee el Cliente.
     */
    public float getImporte() {
        return importe;
    }

    /**
     * Setter del importe del Cliente.
     *
     * @param importe
     */
    public void setImporte(float importe) {
        this.importe = importe;
    }

    /**
     * Getter del numero de motos que posee el Cliente.
     *
     * @return Devuelve el numero de motos que tiene el Cliente.
     */
    public int getN_motos() {
        return n_motos;
    }

    /**
    * Método que determina el número de motos que tiene un cliente.
    *
    * @param idmiembro   numero de socio.
    *
    * @return número entero (cantidad de motos que tiene el cliente)
    */
    public int getN_motosMiembro(int idmiembro)
    {
        int nmotos=0;

         for(int i=0; i< array_clientes.size(); i++)
        {
             if(array_clientes.get(i).getNum_socio() == idmiembro)
             {
                 nmotos = array_clientes.get(i).getN_motos();

             }
        }
         return nmotos;
    }


    /**
     * Setter del numero de motos.
     *
     * @param n_motos
     */
     public void setN_motos(int n_motos)
     {
        this.n_motos = n_motos;
     }

    /**
      * Método que añade el cliente al arrayList de Cliente.
      *
      * @param c    Cliente que es añadido al array.
      */
    public void introducirCliente(Cliente c)
    {
        array_clientes.add(c);
    }

    /**
     * Método que devuelve el array clientes.
     *
     * @return  El arrayList de clientes.
     */
    public ArrayList<Cliente> getArrayClientes()
    {
       return array_clientes;
    }

     //
    /**
     * Método que incrementa el número de motos que posee un cliente.
     *
     * @param n_mot indica el numero de motos.
     *
     * @return      El numero de motos incrementado en 1.
     */
    public int incrementaMotosPosee(int n_mot)
    {
        return this.n_motos = n_mot +1;
    }

    /**
     * Método que decrementa el número de motos que posee un cliente.
     *
     * @param n_mot indica el numero de motos.
     * @return      El numero de motos decrementado en 1.
     */
    public int decrementaMotosPosee(int n_mot)
    {
        return this.n_motos = n_mot -1;
    }


    /**
     * Método que actualiza el número de motos que posee un cliente.
     * Incrementa o decrementa el número de motos según la situación.
     * Inc -> incrementa (añade al importe), dec -> decrementa (quita del importe)
     *
     * @param idsocio   Numero del socio que tiene el Cliente.
     * @param opcion    Selecciona si se debe incrementar o decrementar el numero de motos.
     * @return          El numero de motos que posee el Cliente.
     */
    public int actualizaMotosPosee(int idsocio, String opcion)
    {
       int inc, dec;

        for(int i=0; i< array_clientes.size(); i++)
        {
             if(array_clientes.get(i).getNum_socio() == idsocio)
             {
                 if(opcion.equals("inc"))
                 {
                    inc = array_clientes.get(i).incrementaMotosPosee(array_clientes.get(i).getN_motos());
                    array_clientes.get(i).setN_motos(inc);
                 }

                 else if (opcion.equals("dec"))
                 {
                    dec = array_clientes.get(i).decrementaMotosPosee(array_clientes.get(i).getN_motos());
                    array_clientes.get(i).setN_motos(dec);
                 }

              }
         }

      return getN_motos();
    }

    /**
     * Método general que determina si el nombre introducido por teclado
     * es válido (se comprueba la expresión regular)
     *
     * @param nombre    El nombre del Cliente
     * @return          true si el nombre es correcto
     *                  false si el nombre es incorrecto y por tanto pide introducirlo de nuevo
     */
    public boolean comprobarNombre(String nombre)
    {
        matcher = pn.matcher(nombre);

           if(!matcher.matches())
           {
               System.out.println("Nombre no válido, por favor vuelva a intentarlo.");
               correcto = false;
           }
           else
           {
               correcto = true;
               this.nombre_c = nombre;
           }

        return correcto;
    }

    /**
     * Función toString, convierte todos los atributos de la clase Cliente en
     * String.
     *
     * @return devuelve los atributos convertidos en String.
     */

    @Override
    public String toString()
    {
        return " "+(String.format("%03d", this.getNum_socio()))+"   "+(String.format("%8s",this.getNombre()))+"   "+(String.format("%1$08.03f",this.getImporte()))+ "€   "
                +(String.format("%02d",this.getN_motos()));
    }

    /**
     * Método general que determina si existe un Id (idmoto, idcliente, idcesion)
     * Es decir, que el Id seleccionado exista dentro del array que se le pasa.
     *
     * @param array     ArrayList de moto, cliente o cesion
     * @param id        id de la moto, cliente o cesion
     *
     * @return          true si el id existe y el tamaño del array es mayor que el
     *                  id introducido
     *                  false si el id no existe.
     */
    public boolean existeId(ArrayList<?> array, int id)
    {
        // Si el tamaño del array es > que el id introducido, existe
        if((array.size() >= id) && (id != 0))
            correcto=true;
        else //si no, no existe
        {
            correcto=false;
            System.out.println("El Id introducido no existe, por favor vuelva a intentarlo.");
        }

        return correcto;
    }

    /**
    * Método que determina si el miembro que se le pasa por parametro existe o no
    * en el array clientes.
    *
    * @param idcliente   número del socio que se le pasa para saber si existe.
    *
    * @return true si el id del cliente existe en el array clientes, false si no.
    */
    public boolean existeIdCliente(int idcliente)
    {
        correcto=false;

         for(int i=0; i< array_clientes.size(); i++)
         {
             if(array_clientes.get(i).getNum_socio() == idcliente)
             {
                 correcto = true;
                 break;
             }

         }

         if(!correcto)
              System.out.println("El Id introducido no existe, por favor vuelva a intentarlo.");

        return correcto;
    }

    /**
     * Método que actualiza el importe del cliente.
     *
     * @param idsocio   Le pasamos el id del socio para saber que importe hay
     *                  que actualizar
     * @param importe   Importe que tiene el socio en ese momento.
     */

    public void actualizaImporte(int idsocio, float importe)
    {

         for(int i=0; i< array_clientes.size(); i++)
         {
             if(array_clientes.get(i).getNum_socio() == idsocio)
             {
                 array_clientes.get(i).setImporte(importe);
             }
         }
    }

    /**
     * Método que devuelve el importe total de un cliente.
     *
     * @param id_cliente    id del cliente que queremos saber cual es el importe actual.
     * @return              Devolvemos el importe total del cliente.
     */
    public float devuelveImporteCliente(int id_cliente)
    {
        float importe_cliente=0;

         for(int i=0; i< array_clientes.size(); i++)
         {
             if(array_clientes.get(i).getNum_socio() == id_cliente)
             {
                 importe_cliente = array_clientes.get(i).getImporte();
             }
         }

         return importe_cliente;
    }

    /**
     * Método que actualiza el importe del socio que cede la moto.
     * (Se resta el precio total del importe a el precio de la moto que se va a ceder)
     *
     * @param id_cliente        id del cliente al que queremos actualizar el importe cuando cede
     *                          una moto.
     * @param importe_total     importe total que tiene el cliente al ceder la moto.
     * @param precio_moto       precio que tiene la moto.
     */
    public void actualizaImporteSocioCede(int id_cliente, float importe_total, float precio_moto)
    {
         for(int i=0; i< array_clientes.size(); i++)
         {
             if(array_clientes.get(i).getNum_socio() == id_cliente)
             {
                array_clientes.get(i).setImporte(importe_total-precio_moto);
             }
         }
    }

     /**
     * Método que comprueba si el importe pasa por parámetro es correcto.
     *
     * @param importe
     *
     * @return boolean : true si el importe es válido, false si no.
     */
    public boolean comprobarImporte(String importe)
    {

         matcher = pi.matcher(importe);

           if(!matcher.matches())
           {
               System.out.println("Importe no válido, por favor vuelva a intentarlo.");
               correcto = false;
           }
           else
           {
               correcto = true;
           }

        return correcto;

    }

    /**
     * Método que comprueba que la opción introducida por teclado sea un número
     * (Mira si la expresión regular se cumple)
     *
     * @param numero    Numero de la moto
     * @return          true si el numero es valido.
     *                  false si no introducimos un numero y por tanto se pide
     *                  volver a introducir un numero.
     */
    public boolean comprobarNumero(String numero)
    {
        matcher = pnum.matcher(numero);

            if(!matcher.matches())
            {
               correcto = false;
               System.out.println("Opción invalida. Debe introducir un número.");
            }
            else
               correcto = true;

        return correcto;
    }

    /**
    * Método que elimina el miembro que se le pasa por parametro.
    *
    * @param idmiembro   número de miembro que se quiere eliminar
    */
    public void EliminarMiembro(int idmiembro)
    {
         for(int i=0; i< array_clientes.size(); i++)
         {
             if(array_clientes.get(i).getNum_socio() == idmiembro)
             {
                // Borramos al miembro
                 array_clientes.remove(i);
             }
         }

    }

     /**
    * Método que obtiene el número de cesiones del cliente.
    *
    * @return num_cesiones
    */
    public int getNumCesionesCliente()
    {
        return this.num_cesiones;
    }

     /**
    * Método que obtiene el número de cesiones de un cliente determinado.
    *
    * @param idcliente
    */
    public int getNumCesionesCliente(int idcliente)
    {
        int num_cesiones=0;

         for(int i=0; i< array_clientes.size(); i++)
         {
             if(array_clientes.get(i).getNum_socio() == idcliente)
             {
                num_cesiones = array_clientes.get(i).getNumCesionesCliente();
             }
         }

         return num_cesiones;
    }

     /**
    * Método que introduce el número de cesiones recibidas de un cliente;
    * se hace un incremento en 1.
    */
     public void setNumCesionesRecCliente()
    {
       this.num_cesiones = this.num_cesiones + 1;
    }

     /**
    * Método que introduce el número de cesiones recibidas de un cliente determinado
    * (el que se le pasa por parámetro).
    *
    * @param idcliente
    */
    public void setNumCesionesRecCliente(int idcliente)
    {

         for(int i=0; i< array_clientes.size(); i++)
         {
             if(array_clientes.get(i).getNum_socio() == idcliente)
             {
                 array_clientes.get(i).setNumCesionesRecCliente();
             }
         }
    }

    /**
    * Método que devuelve el valor del máximo número de cesiones recibidas
    * que tiene un cliente.
    */
    public int clienteConMasCesiones()
    {
        int mas_cesiones=0;

        for(int i=0; i< array_clientes.size(); i++)
        {
             if(array_clientes.get(i).getNumCesionesCliente() > mas_cesiones)
             {
                mas_cesiones = array_clientes.get(i).getNumCesionesCliente();
             }
        }

        return mas_cesiones;
    }

}

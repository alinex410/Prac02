/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prac02;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Aline Exojo González y María Ortiz Riera
 */
public class Moto extends Cliente {

    // Declaraciones
    private int id_moto;
    private String matricula;
    private static int cantidad=0;
    private String nombre_m;
    private String cent_cub;
    private float coste;
    private float importe_adicional;
    private float importe_total;
    boolean correcto;

    private static ArrayList<Moto> array_motos = new ArrayList<>();

    // Expresiones regulares
    String patron_num = "[0-9]+";
    String patron_cc = "[1-9][0-9]|[1-9]|[1-5][0-9]{2,2}";
    String patron_coste = "[1-9][0-9]*(.[0-9]+){0,1}";
    String patron_mat = "[0-9]{5}[A-Z]{3}";

    Pattern pnum = Pattern.compile(patron_num);
    Pattern pcc = Pattern.compile(patron_cc);
    Pattern pc = Pattern.compile(patron_coste);
    Pattern pm = Pattern.compile(patron_mat);

    Matcher matcher;


    /**
     * Constructor de la clase Moto, sin parametros.
     */

    public Moto()
    {
        this.id_moto = 0;
        this.cent_cub = "";
        this.coste = 0;
        this.nombre_m = "";
        this.matricula = "";
    }

    /**
     * Getter del id de la moto
     *
     * @return el id de la moto.
     */
    public int getId_moto() {
        return id_moto;
    }

    /**
     * Setter del id de la moto.
     *
     * @param id_moto Le pasamos el id de la moto como parametro.
     */
    public void setId_moto(int id_moto) {
        this.id_moto = id_moto;
    }

    /**
     * Método que actualiza el idcliente de la moto que se le pasa por parametro.
     *
     * @param idmoto        le pasamos el id de la moto para saber a que cliente pertenece.
     * @param idcliente     le pasamos el id del cliente para saber que id de cliente actualizar.
     */
    public void setIdClienteMoto(int idmoto, int idcliente)
    {
       for(int i=0; i< array_motos.size(); i++)
         {
            if(array_motos.get(i).getId_moto() == idmoto)
            {
                array_motos.get(i).setNum_socio(idcliente);
            }
         }

    }

    /**
     * Getter de la matricula de la moto.
     *
     * @return devuelve la matricula de la moto.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Setter de la matricula de la moto.
     *
     * @param matricula le pasamos la matricula de la moto
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Getter de la cantidad de motos que hay en la aplicacion.
     *
     * @return el numero de motos.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Setter de la cantidad de motos, incrementamos en 1 cada vez que se añade una.
     */
    public void setCantidad() {
        this.cantidad = cantidad + 1;
    }

    /**
     * Getter del nombre de la moto.
     *
     * @return devolvemos el nombre de la moto.
     */
    public String getNombre_m() {
        return nombre_m;
    }

    /**
     * Setter del nombre de la moto.
     *
     * @param nombre_m nombre de la moto.
     */

    public void setNombre_m(String nombre_m) {
        this.nombre_m = nombre_m;
    }

    /**
     * Getter de los centrimetros cúbicos de la moto.
     *
     * @return devolvemos los centimetros cubicos (CC).
     */

    public String getCent_cub() {
        return cent_cub;
    }

    /**
     * Setter de los centimetros cúbicos
     *
     * @param cent_cub los centimetros cubicos de la moto.
     */
    public void setCent_cub(String cent_cub) {
        this.cent_cub = cent_cub;
    }

    /**
     * Getter del coste de la moto.
     *
     * @return devolvemos el coste de la moto.
     */
    public float getCoste() {
        return coste;
    }

    /**
     * Setter del coste de la moto.
     *
     * @param coste el coste de la moto.
     */
    public void setCoste(float coste) {
        this.coste = coste;
    }

    /**
     * Método que devuelve el coste de la moto que se le pasa por parametro.
     *
     * @param idmoto    Le pasamos el id de la moto para saber cual es su coste.
     * @return          El precio de cada una moto que hay en el array.
     */
    public float getCosteMoto(int idmoto)
    {
        float precioMoto=0;

        for(int i=0; i< array_motos.size(); i++)
         {
             if(array_motos.get(i).getId_moto() == idmoto)
                    precioMoto = array_motos.get(i).getCoste();
         }

        return precioMoto;

    }

    /**
     * Método que introduce todos los datos en el array motos.
     *
     * @param m    Las distintas motos que vamos introduciendo en el arrayList.
     */
    public void introducirMoto(Moto m)
    {
        array_motos.add(m);
    }

    /**
     * Método que devuelve el array motos.
     *
     * @return array donde estan las distintas motos.
     */
    public ArrayList<Moto> getArrayMotos()
    {
        return array_motos;
    }

    /**
     * Función toString, convierte todos los atributos de la clase Moto en
     * String.
     *
     * @return devuelve los atributos convertidos en String.
     */

    @Override
    public String toString() {
        return " "+(String.format("%03d", this.getId_moto()))+" "+(String.format("%3s",this.getMatricula()))+" "
                +(String.format("%18s",this.getNombre_m()))+"  "+(String.format("%3s",this.getCent_cub()))+ "cc   "
                + (String.format("%1$08.03f",getCoste()))+"€COST  "+(String.format("%1$08.03f",getImporteAdicional()))+ "€ADIC  "+
                (String.format("%1$08.03f",getImporteTotal()))+"€TOTAL  "+String.format("%03d",getNum_socio());

    }

    /**
     * Método que comprueba si los centimetros cúbicos introducidos
     * son válidos. (Se mira la expresión regular)
     * Tenemos en cuenta que una moto con centimetros cúbicos que sobrepase
     * los 60cc no sería válido ya que es una cifra muy elevada.
     *
     * @param cc    Los centimetros cúbicos de la moto
     * @return      true en caso de que los centimetros cúbicos sean correctos.
     *              false en caso de que no sean correctos y no se cumpla la expresión
     *              regular.
     */
    public boolean comprobarCC(String cc)
    {
        matcher = pcc.matcher(cc);

            if(!matcher.matches())
            {
               System.out.println("Valor inválido o demasiado elevado para una moto.");
               System.out.println("Introduzca de nuevo un valor.");
               correcto = false;
            }
            else
            {
               correcto = true;
               this.cent_cub = cc;
            }

        return correcto;
    }

    /**
     * Método que comprueba si el coste introducido es válido.
     * Es decir, que no sobrepase los 6000 euros.
     * (Mira se la expresión regular se cumple)
     *
     * @param scoste    le pasamos el coste de la moto.
     * @return          true en caso de que el coste sea correcto y no supere los 6000€
     *                  false en caso de que el coste sea un valor incorrecto o supera
     *                  los 6000 €.
     */
    public boolean comprobarCoste(String scoste,float importe)
    {


        matcher = pc.matcher(scoste);

            if(!matcher.matches())
            {
               System.out.println("Valor inválido, vuelva a introducir un coste.");
               correcto = false;
            }
            else
            {
               this.coste = Float.parseFloat(scoste);

               if(this.coste <= importe)
               {
                  correcto = true;
               }
               else
               {
                  correcto = false;
                  System.out.println("El coste de la moto sobrepasa los "+importe +" euros.");

               }
             }

        return correcto;
    }

    // Método que comprueba si la matricula que se le pasa es válida
    // (Mira si la expresión se cumple)
    /**
     * Método que comprueba si la matricula que se le pasa es válida.
     * (Mira si la expresión se cumple).
     *
     * @param matricula     Matricula de la moto.
     * @return              true si la matricula es correcta, es decir tiene 5 numeros
     *                      y 3 letras (en mayuscula).
     *                      false si la matricula es incorrecta, ya que puede no acepta
     *                      un caracter como una minuscula o un guion (-),etc.
     */
    public boolean comprobarMatricula(String matricula)
    {

         matcher = pm.matcher(matricula);

            if(!matcher.matches())
            {
               correcto = false;
               System.out.println("Matricula inválida, vuelva a intentarlo.");

            }
            else
            {
               correcto = true;
               this.matricula = matricula;
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
     * Método que comprueba que en la cesión que se va a producir, el cliente no
     * sobrepasalos 6000 euros.
     *
     * @param id_cliente        id del cliente
     * @param costeM_actual     coste actual de la moto.
     * @return                  true si al buscar en el array de motos el cliente
     *                          existe, asi obtenemos el precio de las motos.
     *                          false, si al buscar en el array de motos el cliente
     *                          no existe, no podremos obtener el precio de las motos
     *                          y por tanto el precio supera los 6000.
     */
   public boolean comprobarCosteClienteMoto(int id_cliente, float costeM_actual, float importe)
   {
       float precioMotos=0;

       // Buscamos en el array de motos el cliente introducido (id_cliente)
       for(int i=0; i< array_motos.size(); i++)
         {
             // Si lo encontramos, obtenemos el precio de las motos
             // que tiene en su poder:
             if(array_motos.get(i).getNum_socio() == id_cliente)
                    precioMotos = array_motos.get(i).getCoste() + precioMotos;
         }

            // Sumamos el coste de la moto actual a las motos que ya posee el cliente.
            precioMotos = costeM_actual + precioMotos;
            // Si el precio sobrepasa el importe se devuelve una advertencia.
            if(precioMotos > importe)
            {
                correcto = false;
                System.out.println("Este cliente no puede tener la moto en su posesión");
                System.out.println("(sobrepasa los "+importe+" euros)");
            }
            // Si el precio no sobrepasa los 1000 euros
            else
            {
                correcto = true;

               // actualizar el precio de las motos en posesión
                actualizaImporte(id_cliente, precioMotos);

            }

       return correcto;
   }

   // Método que determina que cliente posee la moto seleccionada.
   /**
    * Método que determina que cliente posee la moto seleccionada.
    *
    * @param idmoto     id de la moto.
    * @return           devuelve el numero de socio que posee la moto que tiene
    *                   dicho id de la moto.
    */
    public int MotoPoseeCliente(int idmoto)
    {
       int num_socio=0;

           // Buscamos en el array motos el Id de la moto seleccionada.
           // Cuando la encontramos, guardamos el resultado de la variable idNum_socio
           for(int i=0; i< array_motos.size(); i++)
           {
                if(array_motos.get(i).getId_moto() == idmoto)
                    num_socio = array_motos.get(i).getNum_socio();
           }

       return num_socio;

    }

    /**
     * Método que comprueba si la matricula introducida ya existe en el array motos
     *
     * @param matriculaB    Matricula de la moto.
     *
     * @return              true en caso de que la matricula de la moto no existe.
     *                      false en caso de que la matricula ya exista y por tanto
     *                      no pueda haber dos motos con la misma matricula.
     */
   public boolean matriculaRepetida(String matriculaB)
   {
        String matriculaA;

           for(int i=0; i< array_motos.size(); i++)
           {
                matriculaA = array_motos.get(i).getMatricula();

                if(matriculaA.equals(matriculaB))
                {
                    correcto=false;
                    System.out.println("La matricula introducida ya existe. Vuelva a intentarlo.");
                }
                else
                    correcto=true;
            }

        return correcto;
   }

   /**
    * Método que muestra TODAS las motos que posee un cliente.
    *
    * @param numsocio   numero de socio para saber que motos tiene.
    */
    public void mostrarMotosCliente(int numsocio)
    {
        for(int i=0; i< array_motos.size(); i++)
         {
             if(array_motos.get(i).getNum_socio() == numsocio)
                 System.out.println("\t"+array_motos.get(i));
         }
   }

     /**
    * Setter del importe adicional.
    *
    * @param importe_adicional el precio adicional.
    */
    public void setImporteAdicional(float importe_adicional)
    {
        this.importe_adicional = importe_adicional;
    }

    /**
    * Getter del importe adicional.
    *
    * @return el importe adicional.
    */
    public float getImporteAdicional()
    {
        return this.importe_adicional;
    }

    /**
    * Método que devuelve el importe adicional que posee una determinada moto.
    *
    * @param idmoto  : número id de la moto.
    *
    * @return el importe adicional.
    */
    public float getImporteAdicionalMoto(int idmoto)
    {
        float importe_adicional=0;

        for(int i=0; i< array_motos.size(); i++)
         {
             if(array_motos.get(i).getId_moto()== idmoto)
             {
                 importe_adicional = array_motos.get(i).getImporteAdicional();
             }
         }

        return importe_adicional;
    }

    /**
    * Método actualiza el importe adicional de una determinada moto.
    *
    * @param idmoto   : número id de la moto.
    * @param importe_adicional : importe adicional que se quiere añadir.
    */
    public void ActualizarImporteAdicional(int idmoto, float importe_adicional)
    {
        float importe_total, importe_actual;

        for(int i=0; i< array_motos.size(); i++)
         {
             if(array_motos.get(i).getId_moto()== idmoto)
             {
                 // Obtenemos el "importe adicional" que tiene actualmente la moto
                 importe_actual =  array_motos.get(i).getImporteAdicional();

                 // Calculamos el "importe adicional" total (importe actual + introducido)
                 importe_total = importe_actual + importe_adicional;

                 // Guardamos los datos
                 array_motos.get(i).setImporteAdicional(importe_total);
             }
         }
    }

     /**
    * Getter del importe total que posee una moto.
    *
    * @return el importe total.
    */
    public float getImporteTotal()
    {
        return this.importe_total;
    }

    /**
    * Setter del importe total de una moto.
    *
    * @param importetotal   : importe total que posee la moto.
    */
    public void setImporteTotal(float importetotal)
    {
        this.importe_total = importetotal;
    }

    /**
    * Método que actualiza el importe total de una moto.
    *
    * @param idmoto   : id de la moto.
    * @param coste_moto : el coste que tiene la moto.
    * @param importe_adicional : importe adicional que se le quiere añadir a la moto.
    */
    public void ActualizarImporteTotal(int idmoto, float coste_moto, float importe_adicional)
    {
        float total=0;

        for(int i=0; i< array_motos.size(); i++)
         {
             if(array_motos.get(i).getId_moto()== idmoto)
             {
                 total =  coste_moto + importe_adicional;
                 array_motos.get(i).setImporteTotal(total);
             }
         }
    }




}

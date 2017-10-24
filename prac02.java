/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prac02;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Aline Exojo González y María Ortiz Riera
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    private static final Scanner teclado = new Scanner(System.in);
    private static int n_socio;
    private static String nombre_c;
    private static int id_moto;
    private static String matricula;
    private static String nombre_m;
    private static String cent_cub;
    private static String coste;
    private static int id_cesion = 0;
    private static boolean correcto;
    private static float importeF;
    private static int num_motos_reg;
    private static int num_socios;
    private static int num_cesiones;

    private static PrintWriter pw = null;
    private static ArrayList<?> array = new ArrayList<>();
      // Array con el id de los clientes que tienen más cesiones
    private static ArrayList<Integer> array_mas_cesiones = new ArrayList<Integer>();

    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        menu();

    }


    /**
     * Funcion menu, donde se muestran las distintas opciones de la aplicación
     *
     * @throws ParseException
     */
    public static void menu() throws ParseException{

        int opcion=0;
        Moto m = new Moto();
        Cliente c = new Cliente();
        Cesion ces = new Cesion();
        String importeS;

        do
        {
            System.out.println("Introduzca el importe: ");
            importeS = teclado.next();

            correcto = c.comprobarImporte(importeS);

        }while(!correcto);

        importeF = Float.parseFloat(importeS);

        System.out.println("------------------- MENU -------------------------------");
        System.out.println("1. Registrar un nuevo miembro");
        System.out.println("2. Registrar una nueva motocicleta");
        System.out.println("3. Registrar una cesion ");
        System.out.println("4. Eliminar un miembro ");
        System.out.println("5. Incrementar otros gastos a una moto");
        System.out.println("6. Miembros con más cesiones");
        System.out.println("7. Listar en pantalla los miembros con motos en posesión ");
        System.out.println("8. Listar todas las motos");
        System.out.println("9. Listar todas las cesiones realizadas");
        System.out.println("10. Salir");

       do{

         try{
            System.out.println("\n----------------------------------------------------");
            System.out.println("Introduzca la opción seleccionada: ");

            opcion = teclado.nextInt();

            switch(opcion){
                case 1:
                  c = insertar_cliente();
                break;
                case 2:
                    if(num_socios == 0)
                    {
                        System.out.println("Para poder registrar una moto es necesario que");
                        System.out.println("haya al menos un socio registrado. ");
                    }
                    else
                        m = insertar_moto();
                break;
                case 3:
                        if((num_socios < 2) && (num_motos_reg >= 1))
                        {
                             System.out.println("Para poder realizar una cesión es necesario que");
                             System.out.println("haya por lo menos dos socios registrados. ");
                        }
                        else if((num_socios >= 2) && (num_motos_reg < 1))
                        {
                            System.out.println("Para poder realizar una cesión es necesario que");
                            System.out.println("haya por lo menos una moto registrada. ");
                        }
                        else if((num_socios < 2) && (num_motos_reg < 1))
                        {
                            System.out.println("Para poder realizar una cesión es necesario que");
                            System.out.println("haya por lo menos dos socios y una moto registrada. ");
                        }
                       else
                          ces = insertar_cesion();
                break;
                case 4:
                       // Si no hay socios registrados
                       if(num_socios < 1)
                       {
                           System.out.println("Acción invalida. No existe ningún miembro.");
                       }
                       //* Si solo hay un miembro registrado y hay varias motos registradas *
                       else if((num_socios == 1) && (num_motos_reg >= 1))
                       {
                               System.out.println("Acción invalida. Las motos no pueden quedarse");
                               System.out.println("sin asignación.");
                       }
                       else // Hay suficientes miembros y motos para eliminar un miembro.
                       {
                          eliminar_miembro();
                       }

                break;
                case 5:
                       if(num_motos_reg >= 1)
                       {
                         m = insertar_importe_moto();
                       }
                       else
                       {
                           System.out.println("No se puede incrementar el importe porque actualmente ");
                           System.out.println("no hay ninguna moto registrada.");
                       }

                break;
                case 6:
                    if(num_cesiones > 0)
                    {
                        System.out.println("---Clientes con más cesiones---");
                        clientes_max_cesiones();
                    }
                    else
                        System.out.println("No hay cesiones registradas.");
                break;
                case 7:
                    System.out.println("---Clientes registrados---\n");
                    mostrar_clientes(c.getArrayClientes());
                break;
                case 8:
                    System.out.println("---Motos registradas---\n");
                    mostrar_Array(m.getArrayMotos());
                break;
                case 9:
                    System.out.println("---Cesiones registradas---\n");
                    mostrar_Array(ces.getArrayCesion());
                break;
                case 10:
                   salir(c,m,ces);
                break;
                default: //Si se introduce cualquier otro número que no esté entre el 1 y el 7:
                    System.out.println("Número fuera de rango.");
                break;
            }

         // Si se introduce por teclado un valor que no es un número salta un error.
         }catch(InputMismatchException err)
         {

         System.out.println("Opción invalida. Introduzca un número del 1 al 7.");
         teclado.next();
         continue;

         }

        }
        while(opcion != 10);

    }

     /**
     * void clientes_max_cesiones()
     * Función que llama a las funciones clienteConMasCesiones() para poder
     * obtener el máximo número de cesiones recibidas realizadas a un cliente y
     * ClientesMasCesiones() para poder visualizar todos los clientes con ese
     * mismo número de cesiones recibidas (por si existiera más de un cliente
     * con el mismo número de cesiones recibidas máximo).
     *
     */
    public static void clientes_max_cesiones()
    {
        Cliente c = new Cliente();
        ArrayList<Integer> array_max_cesiones = new ArrayList<>();
        int mas_cesiones=0;

        // Obtenemos el valor de cliente que más cesiones tiene
        mas_cesiones = c.clienteConMasCesiones();

        System.out.println("Número máximo de cesiones recibidas realizadas: "+mas_cesiones+"\n");

        // Segun el valor mostramos todos los cientes con más cesiones
        ClientesMasCesiones(mas_cesiones);


    }

     /**
     * void ClientesMasCesiones()
     * Función que selecciona el cliente con más cesiones y luego
     * llama a las funciones correspondientes para poder mostrar el
     * cliente por pantalla y todas las cesiones de motos recibidas.
     *
     * @param mas_cesiones número máximo de cesiones recibidas
     *
     */
    static public void ClientesMasCesiones(int mas_cesiones)
    {
        Cliente c = new Cliente();
        Cesion ces = new Cesion();
        ArrayList<Cliente> array_clientes = new ArrayList<>();
        ArrayList<Cesion> array_cesiones = new ArrayList<>();
        array_clientes = c.getArrayClientes();
        array_cesiones = ces.getArrayCesion();
        int id_cliente=0;

        for(int i=0; i< array_clientes.size(); i++)
         {
             if(array_clientes.get(i).getNumCesionesCliente() == mas_cesiones)
             {
                 mostrar_cliente(array_clientes.get(i).getNum_socio(),array_clientes);
                 mostrar_cesiones_cliente(array_clientes.get(i).getNum_socio(),array_cesiones);
                 System.out.println("\n");
             }
         }


    }

     /**
     * Cliente eliminar_miembro()
     * Función que nos permite eliminar un miembro que se encuentra actualmente registrado.
     */
    public static Cliente eliminar_miembro()
    {
        Cliente c = new Cliente();
        Moto m = new Moto();
        //Cesion ces = new Cesion();
        String n_miembroS;
        int n_miembroI, miembro_selecI, idmotoc;
        String miembro_selecS;
        boolean existe, opcorrecta;
        // Motos que posee el cliente que se va a borrar:
        ArrayList<Integer> motos_cliente = new ArrayList<Integer>();

        System.out.println("---Miembros registrados---");
        mostrar_clientes(c.getArrayClientes());

        do
        {
            do
            {
                System.out.println("Seleccione el miembro (id) que desea eliminar: ");
                n_miembroS = teclado.next();

                correcto = c.comprobarNumero(n_miembroS);

            }while(!correcto); // Repetimos mientras no se introduzca un número

            n_miembroI = Integer.parseInt(n_miembroS);

            existe = c.existeIdCliente(n_miembroI);

        }while(!existe); //Repetimos el bucle mientras no exista el cliente

        // Si el miembro tiene motos:
        if(c.getN_motosMiembro(n_miembroI) > 0)
        {
            System.out.println("---Motos que posee el miembro---");
            mostrar_clientes(c.getArrayClientes()); // Mostramos el cliente con sus motos.

            System.out.println("El miembro posee "+ c.getN_motosMiembro(n_miembroI)+ " moto/s.");
            System.out.println("Por lo tanto, esta/s moto/s debe/n asignarse a otro/s miembro/s.");

            // Introducimos en el array motos_cliente las motos que posee el cliente.
            motos_cliente = m.MotosPoseeCliente(n_miembroI);

         for(int i=0; i< motos_cliente.size(); i++)
         {
             // Creamos una nueva cesión:
             Cesion ces = new Cesion();

             idmotoc = motos_cliente.get(i); // cogemos la moto que posee el cliente que se va borrar
             System.out.println("Cesión de la moto con id "+idmotoc+": \n");

             System.out.println("---Miembros registrados---");
             mostrar_Array(c.getArrayClientes());
          do
          {
            do
            {
               do //Seleccionamos el miembro al que deseamos ceder la moto
               {
                  System.out.println("Introduzca el id del miembro al que desea ceder la moto: ");
                  miembro_selecS = teclado.next();

                  correcto = c.comprobarNumero(miembro_selecS);

               }while(!correcto);

               miembro_selecI = Integer.parseInt(miembro_selecS);

               // Comprobamos que el miembro seleccionado existe
               existe = c.existeIdCliente(miembro_selecI);

             }while(!existe); // Repetimos el bucle mientras no exista el miembro.

             if(n_miembroI == miembro_selecI)
             {
                 System.out.println("Acción inválida: el socio al que se quiere ceder la moto es ");
                 System.out.println("el mismo que el que cede la moto. ");
                 opcorrecta=false;
             }
            else
            {
                // Guardamos el id del miembro al que se le va a ceder la moto
                ces.setSocioCedeMoto(miembro_selecI);

                // comprobamos que la posesión no sobrepasa el importe máximo marcado.
                opcorrecta = ces.comprobarCosteClienteMoto(miembro_selecI,ces.getCosteMoto(idmotoc),importeF);
            }


          }while(!opcorrecta); // Si la posesión de motos de dicho cliente sobrepasa el coste repetimos
                               // el bucle para seleccionar otro cliente

            // Guardamos el miembro que ha cedido la moto (el que se va a ir)
            ces.setSocioPoseeMoto(n_miembroI);

            // Incrementamos en 1 el número de motos que posee el cliente al que se le cede la moto
            ces.actualizaMotosPosee(miembro_selecI,"inc");

            // Guardamos el id de la moto que se va a ceder
            ces.setId_moto(idmotoc);

            // En el id de la moto que se va a ceder se debe modificar el idcliente al selecionado
            ces.setIdClienteMoto(idmotoc, miembro_selecI);

            // Introducimos la fecha en la que se va a realizar la sesión
            // (fecha actual en la que se realiza la cesión)
            Date fecha = new Date();
            System.out.println("Fecha y hora de la cesión: \n"+fecha);
            ces.setFecha(fecha);

            // Incrementamos idcesión
            id_cesion++;
            ces.setId_cesion(id_cesion);

            // Incrementamos el número de cesiones que "recibe" el cliente
            c.setNumCesionesRecCliente(miembro_selecI);

            // Añadimos los datos de la cesión al array Cesión:
            ces.introducirCesion(ces);

            num_cesiones++;

            System.out.println("\nLa cesión de la moto con id " + idmotoc +  " se ha realizado correctamente.");

         } // Repetimos el for hasta que se hayan asignado todas las motos.

        }//fin del if (si tiene motos)


        // En este punto, las motos que poseía el miembro han sido cedidas correctamente
        // a otros miembros; ahora podemos borrar correctamente el socio del array clientes.

        c.EliminarMiembro(n_miembroI);
        num_socios--;

        System.out.println("El miembro se ha eliminado correctamente.");

        return c;
    }

     /**
     * void insertar_importe_moto()
     * Función que permite insertar un importe adicional a la moto.
     *
     */
    public static Moto insertar_importe_moto()
    {
        String idmotoS, importeS;
        int idmotoI;
        float coste_act_m, importeF;

        Moto m = new Moto();
        System.out.println("---Motos registradas---");
        mostrar_Array(m.getArrayMotos());

        do
        {
            System.out.println("Seleccione el id de una moto: ");
            idmotoS = teclado.next();

            correcto = m.comprobarNumero(idmotoS);

        }while(!correcto);

         idmotoI = Integer.parseInt(idmotoS);

         // Obtenemos el coste actual de la moto
        coste_act_m = m.getCosteMoto(idmotoI);

        do
        {
            System.out.println("Importe adicional: ");
            importeS = teclado.next();

            // Comprueba que el importe introducido por teclado es correcto
            correcto = m.comprobarImporte(importeS);

        }while(!correcto);

        // Actualizamos el importe Adicional que posee la moto
        importeF = Float.parseFloat(importeS);

        m.ActualizarImporteAdicional(idmotoI,importeF);

        // Actualizamos el importe total que tiene la moto (coste + importeAdicional)
         m.ActualizarImporteTotal(idmotoI,m.getImporteAdicionalMoto(idmotoI),coste_act_m);

        return m;
    }


    /**
     * void insertar_cliente()
     * Esta función realiza la operacion de introducir los clientes en la base de
     * datos de la empresa. Se pedirá el nombre del cliente, se comprobara si el
     * nombre del cliente es correcto o no, si es correcto se introduce el cliente
     * en el arrayList de Cliente.
     * El importe del cliente será 0 puesto que inicialmente no posee ninguna moto, por
     * tanto el atributo n_motos tambien sera 0.
     */

    public static Cliente insertar_cliente()
    {
        Cliente c = new Cliente();

         System.out.println("'Insertar Incliente'");
         System.out.println("Introduzca ':s' para abortar la operación.\n");

        // Número de socio (se asignará por orden en el que se ha dado de alta):
        n_socio++;
        c.setNum_socio(n_socio);



     try{
         // Nombre del socio:
         do{
             System.out.print("Introduzca el nombre del socio: ");
             nombre_c = teclado.next();

             /***********************/
             if(nombre_c.equals(":s"))
               throw new Exception();
             /***********************/

             correcto = c.comprobarNombre(nombre_c);

           }while(!correcto);

         // Importe del socio:
         // importe = 0, ya que el cliente no posee ninguna moto.

         // Número de motos que posee el cliente:
         // Como el cliente no tiene ninguna moto en su poder, n_motos = 0

         // Añadimos los datos del cliente en el array Clientes.
         c.introducirCliente(c);
         num_socios++;

         System.out.print("\nEl socio se ha dado de alta correctamente.");

        }catch(Exception e)
        {
        //n_socio--;
         System.out.println("Se ha salido de 'Insertar Cliente'.");
        };

        return c;
    }

    /**
     * void insertar_moto()
     * Esta función realiza la operación de introducir las motos en la base de datos
     * de la empresa. Se pedirá la matricula de la moto, el nombre, los centimetros
     * cubicos, el coste y finalmente se añade al array.
     */

    public static Moto insertar_moto()
    {
        Moto m = new Moto();
        Cliente c = new Cliente();
        int socio_selec;
        float coste_adicionalF, costeF, importe_total;
        String socio_selecS, coste_adicionalS;

        System.out.println("'Insertar Moto'");
        System.out.println("Introduzca ':s' para abortar la operación.\n");

        // Número de identificación de la moto dentro de la empresa:
        id_moto++;
        m.setId_moto(id_moto);

    try{
         // Nombre de la moto:
         do{

             System.out.println("Introduzca nombre de la moto: ");
             if(!correcto)
                nombre_m = teclado.nextLine();
             else
             {
                teclado.nextLine();
                nombre_m = teclado.nextLine();
             }

             // Se ha tenido que poner una opción con dos nextline debido a
             // que la primera vez que se entra a 'Insertar moto'
             // se leia una linea sin haber introducido antes el nombre.
             // En el momento en el que se produce el primer error (repite el bucle)
             // ya no se produce una lectura erronea.


             /***********************/
             if(nombre_m.equals(":s"))
               throw new Exception();
             /***********************/

             correcto = m.comprobarNombre(nombre_m);

             m.setNombre_m(nombre_m);

            }while(!correcto);

         // Centímetros cúbicos:
         do{
             System.out.println("\nIntroduzca los centímetros cúbicos: ");
             cent_cub = teclado.next();

             /***********************/
             if(cent_cub.equals(":s"))
                throw new Exception();
             /***********************/

             correcto = m.comprobarCC(cent_cub);

            }while(!correcto);


         // Coste:
         do{
             System.out.println("\nIntroduzca el coste de la moto: ");
             coste = teclado.next();

             /***********************/
             if(coste.equals(":s"))
                throw new Exception();
             /***********************/

             correcto = m.comprobarCoste(coste, importeF);

            }while(!correcto);

         costeF = Float.parseFloat(coste);

         //Costes adicionales
         do
         {
           System.out.println("\nCostes adicionales de la moto: ");
           coste_adicionalS = teclado.next();

           correcto = m.comprobarImporte(coste_adicionalS);

         }while(!correcto);

         // Pasamos el coste a float
         coste_adicionalF = Float.parseFloat(coste_adicionalS);

         // Actualizamos los costes adicionales de la moto:
         m.setImporteAdicional(coste_adicionalF);

         //Actualizamos el coste total de la moto (Coste + costes adicionales)
         importe_total = costeF + coste_adicionalF;
         m.setImporteTotal(importe_total);

        // Matricula:
         do
         {
             do
             {
                System.out.println("\nUtilizando el formato XXXXXLLL, siendo X un número y L una letra en mayúscula. ");
                System.out.println("Matricula: ");
                matricula = teclado.next();

               /***********************/
                if(matricula.equals(":s"))
                    throw new Exception();
               /***********************/

                correcto = m.comprobarMatricula(matricula);

              }while(!correcto); // Repetir hasta que se introduzca el formato correcto de la matricula

              correcto = m.matriculaRepetida(matricula);

         }while(!correcto); // Repetir si la matricula que se quiere registrar ya existe (esta registrada)

         // Cantidad de motos registradas:
         //m.setCantidad();
         num_motos_reg++;

         // Suponemos que al introducirse la moto debe estar asignada a alguien:
         System.out.println("---Socios registrados---");
         mostrar_Array(m.getArrayClientes()); // --

         do{
             do{
                 do{
                     System.out.println("Asigne la moto a un socio (id): ");
                     socio_selecS = teclado.next();

                     /***********************/
                     if(socio_selecS.equals(":s"))
                         throw new Exception();
                     /***********************/

                     correcto = m.comprobarNumero(socio_selecS);

                    }while(!correcto); // Repetir mientras no se introduzca un número

                   // Pasamos la opción introducida (String) a int
                   socio_selec = Integer.parseInt(socio_selecS);

                   correcto = m.existeIdCliente(socio_selec);

                }while(!correcto);

              // Comprobamos que el socio no sobrepasa los 1000 euros en posesiones de motos
              correcto = m.comprobarCosteClienteMoto(socio_selec, m.getCoste(),importeF);

            }while(!correcto);

         m.setNum_socio(socio_selec);

         // Incrementamos en 1 el número de motos que posee dicho cliente
         m.actualizaMotosPosee(socio_selec,"inc");

         // Añadimos todos los datos de la moto en el array Motos.
         m.introducirMoto(m);

         System.out.println("\nLa moto se ha añadido correctamente.");

     }catch(Exception e)
     {
         id_moto--;
         System.out.println("Se ha salido de 'Insertar Moto'.");
     };

    return m;

    }

    /**
     * void insertar_cesion()
     * Esta función se introducen los datos de la cesión, el id vendrá determinado
     * por la variable id_cesion y se incrementara conforme se vayan añadiendo
     * cesiones al array. La fecha se tendra en cuenta que tenga que ser valida
     * ademas de que la conversión se haga correctamente. Se introducirá también
     * la moto con la que se realizará la cesión, así como los ids de los socios
     * que intervienen en dicha cesión (el que posee actualmente la moto y al
     * que se le va a ceder la moto).
     */

    public static Cesion insertar_cesion()
    {
        Cesion ces = new Cesion();
        Cliente c = new Cliente();
        boolean invalido, existe, opcorrecta, invalid_f;
        String id_motoS, id_clienteS;
        int n_socio1, n_socio2, n_motos;

        System.out.println("'Insertar Cesión'");
        System.out.println("Introduzca ':s' para abortar la operación.\n");

        // Número de cesión:
        id_cesion++;
        ces.setId_cesion(id_cesion);

        // Mostramos las motos que posee la empresa:
        System.out.println("---Motos registradas---");
        mostrar_Array(ces.getArrayMotos());

     try{
         do{
             do{
                 System.out.println("Seleccione la moto con la que desea realizar la cesión: ");
                 id_motoS = teclado.next();

                 /***********************/
                 if(id_motoS.equals(":s"))
                      throw new Exception();
                 /***********************/

                 invalid_f = ces.comprobarNumero(id_motoS);

                }while(!invalid_f); // Se repite mientras no se introduzca un número

             id_moto = Integer.parseInt(id_motoS);

             // Comprobamos que la moto existe
             existe = ces.existeId(ces.getArrayMotos(), id_moto);

            }while(!existe);

          ces.setId_moto(id_moto); // *

          // Buscamos el socio que posee actualmente esta moto
          n_socio1 = ces.MotoPoseeCliente(id_moto);

          ces.setSocioPoseeMoto(n_socio1); // *

         do{
             do{
                  System.out.println("---Socios registrados---");
                  mostrar_Array(ces.getArrayClientes()); // --

                  do{

                       System.out.println("Seleccione el cliente al que desea ceder la moto: ");
                       id_clienteS = teclado.next();

                       /***********************/
                       if(id_clienteS.equals(":s"))
                            throw new Exception();
                       /***********************/

                       invalid_f = ces.comprobarNumero(id_clienteS);


                     }while(!invalid_f);

                  n_socio2 = Integer.parseInt(id_clienteS);

                  existe = ces.existeIdCliente(n_socio2);

                 }while(!existe);

              if(n_socio1 == n_socio2)
              {
                 System.out.println("Acción inválida: el socio al que se quiere ceder la moto es ");
                 System.out.println("el mismo que el que cede la moto. ");
                 opcorrecta=false;
              }
              else
              {
                ces.setSocioCedeMoto(n_socio2); // *

                opcorrecta = ces.comprobarCosteClienteMoto(n_socio2,ces.getCosteMoto(id_moto),importeF);
              }

            }while(!opcorrecta);

            // Actualizamos el importe para el socio_1 (debemos restarle al importe el precio de la moto)
            ces.actualizaImporteSocioCede(n_socio1, ces.devuelveImporteCliente(n_socio1), ces.getCosteMoto(id_moto));

            // Decrementamos en 1 el número de motos que posee n_socio1
            ces.actualizaMotosPosee(n_socio1,"dec");


            // Incrementamos en 1 el número de motos que posee n_socio2
            ces.actualizaMotosPosee(n_socio2,"inc");

            // En el id de la moto que se va a ceder se debe modificar el idcliente a n_socio2
            ces.setIdClienteMoto(id_moto, n_socio2);

            // Introducimos la fecha en la que se va a realizar la sesión
            // (fecha actual en la que se realiza la cesión)
            Date fecha = new Date();
            System.out.println("fecha: "+fecha);
            ces.setFecha(fecha);

            // Incrementamos el número de cesiones que ha hecho dicho cliente (n_socio1)
            c.setNumCesionesRecCliente(n_socio2);
            System.out.println("Numero de cesiones: "+c.getNumCesionesCliente(n_socio1));

            // Añadimos los datos de la cesión al array Cesión:
            ces.introducirCesion(ces);

            num_cesiones++;

            System.out.println("\nLa cesión se ha realizado correctamente.");

        }catch(Exception e)
        {
            id_cesion--;
            System.out.println("Se ha salido de 'Insertar cesión'.");
        };

         return ces;
    }

    /**
     * void mostrar_Array()
     * Esta función nos permite mostrar la información que se encuentra almacenada
     * en cualquier array.
     */
    public static void mostrar_Array(ArrayList<?> array)
    {

         for(int i=0; i< array.size(); i++)
         {
             System.out.println(array.get(i));
         }
    }

    /**
     * void mostrar_clientes()
     * Esta función nos permite mostrar la información que se encuentra almacenada
     * en el array clientes, así como la moto que posee cada cliente.
     *
     * @param array_cliente
     */
    public static void mostrar_clientes(ArrayList<Cliente> array_cliente)
    {
        Moto m = new Moto();

        for(int i=0; i< array_cliente.size(); i++)
         {
             System.out.println(array_cliente.get(i));
             m.mostrarMotosCliente(array_cliente.get(i).getNum_socio());
         }
    }

     /**
     * Esta función nos permite mostrar todas las cesiones que ha recibido el cliente
     * que se le pasa por parámetro.
     *
     * @param idcliente
     * @param array_cesiones
     *
     */
    public static void mostrar_cesiones_cliente(int idcliente,ArrayList<Cesion> array_cesiones)
    {

        for(int i=0; i< array_cesiones.size(); i++)
         {
             if(array_cesiones.get(i).getSocioCedeMoto() == idcliente)
             {
                  System.out.println(array_cesiones.get(i));

             }
         }


    }

     /**
     * Esta función nos muestra la información de un solo cliente (el que
     * se le pasa por parámetro).
     *
     * @param idcliente
     * @param array_cliente
     *
     */
    public static void mostrar_cliente(int idcliente, ArrayList<Cliente> array_cliente)
    {
        Moto m = new Moto();

        for(int i=0; i< array_cliente.size(); i++)
         {
             if(array_cliente.get(i).getNum_socio() == idcliente)
             {
                System.out.println(array_cliente.get(i));

                  // m.mostrarMotosCliente(array_cliente.get(i).getNum_socio());
             }
         }

    }

    /**
     * En esta funcion habrimos el fichero y guardamos todos los clientes que se
     * han añadido y las cesiones realizadas por los distintos clientes.
     *
     * @param c_array       array de la clase Cliente
     * @param m_array       array de la clase Moto
     * @param ces_array     array de la clase Cesion
     */

    public static void Fichero(ArrayList<Cliente> c_array, ArrayList<Moto> m_array, ArrayList<Cesion> ces_array)
    {
        FileWriter fichero = null;

        try
        {
            System.out.println("\nABRIENDO EL FICHERO...");
            fichero = new FileWriter("informacion.txt");

            System.out.println("GUARDANDO INFORMACION EN EL FICHERO...");
            pw = new PrintWriter (fichero);

            pw.println("---Clientes registrados---");

            for(int i=0; i< c_array.size(); i++)
            {
              pw.println(c_array.get(i));

               for(int j=0; j< m_array.size(); j++)
               {
                  if(m_array.get(j).getNum_socio() == c_array.get(i).getNum_socio())
                  pw.println("\t"+m_array.get(j));
               }

            }

          pw.println("\n---Cesiones registradas---");

          for(int i=0; i< ces_array.size(); i++)
          {
             pw.println(ces_array.get(i));
          }


            System.out.println("INFORMACION GUARDADA");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("CIERRE DEL FICHERO");
                fichero.close();
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
    }


    /**
     * Esta funcion realiza la opcion 7 del menu, donde el usuario indica que quiere
     * terminar el programa y debe mostrar los clientes con todos sus datos ademas
     * de las motos y las cesiones
     *
     * @param c     Clientes insertados en la aplicación
     * @param m     Motos insertadas en la aplicación
     * @param ces   Cesiones realizadas e insertadas en la aplicacion
     */

    public static void salir(Cliente c, Moto m, Cesion ces)
    {
        System.out.println("\nClientes registrados: ");
        mostrar_Array(c.getArrayClientes());
        System.out.println("\n--------------------------------------------\n");
        System.out.println("\nMotos registradas: ");
        mostrar_Array(m.getArrayMotos());
        System.out.println("\n--------------------------------------------\n");
        System.out.println("\nCesiones registradas: \n");
        mostrar_Array(ces.getArrayCesion());

        Fichero(c.getArrayClientes(),m.getArrayMotos(),ces.getArrayCesion());

    }
}

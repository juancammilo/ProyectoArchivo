/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.archivos;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
/**
 *
 * @author Jkammilo
 */
public class Logica {
    
    String cadena;
    
    Map<Integer, Persona>personas = new HashMap<>();
    
    int cedula;
    
    public Logica() throws IOException {
        leerArchivoPersonas("archivos/personas.txt");
        leerArchivoReportes("archivos/reportes.txt");
        pedirDatos();
        
        for (Map.Entry<Integer, Persona> entry : personas.entrySet()) {
            Integer key = entry.getKey();
            Persona value = entry.getValue();
            System.out.println("c:  "+key+"- nombre:  "+value.getNombre());
        }
    }
    
    public void pedirDatos(){
        int opcion;
        do{
            System.out.println("\n       USUARIOS");
            for (Map.Entry<Integer, Persona> entry : personas.entrySet()) {
                Integer cedulaPersona = entry.getKey();
                Persona persona = entry.getValue();
                System.out.println("Cedula: "+cedulaPersona+" -  nombre: "+persona.getNombre());
                
            }
            Scanner recibeCedula = new Scanner(System.in);
            System.out.print("Ingrese la cedula: ");
            cedula = recibeCedula.nextInt();
            
            if(personas.containsKey(cedula)){
               Persona persona = personas.get(cedula);
               System.out.println("Cedula: "+persona.getCedula()+",  Nombre: "+persona.getNombre()+",  Apellido: "+persona.getApellido()+",  Correo: "+persona.getCorreo());
            }else{
                System.out.println("No existe usuario con esta cedula, se va a crear uno");
                Scanner recibeInfo = new Scanner(System.in);
                System.out.print("Ingresa el nombre: ");
                String nombrePersona = recibeInfo.nextLine();
                System.out.print("Ingresa el apellido: ");
                String apellidoPersona = recibeInfo.nextLine();
                System.out.print("Ingresa el correo: ");
                String correoPersona = recibeInfo.nextLine();
                
                personas.put(cedula, new Persona(cedula, nombrePersona, apellidoPersona, correoPersona));
                                
                File file = new File("archivos/personas.txt");
                try{
                    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                    try (BufferedWriter bw = new BufferedWriter(fw)) {
                        cadena = cedula+","+nombrePersona+","+apellidoPersona+","+correoPersona;
                        bw.newLine();
                        bw.write(cadena);
                        
                    }
                }catch(IOException e){
                    System.out.println("\nNo se encontro el archivo de datos para escribir en él!!!");
                }
            }
            do{
                Scanner leeOpcion = new Scanner(System.in);
                System.out.println("\n     Menu de "+personas.get(cedula).getNombre());
                System.out.println("1. Ver reportes");
                System.out.println("2. Agregar reporte");
                System.out.println("3. Eliminar reporte");
                System.out.println("4. Salir de este menu");
                System.out.println("5. Salir");
                System.out.print("Ingrese una opcion: ");
                opcion = leeOpcion.nextInt();
                
                switch(opcion){
                    case 1:
                            if(personas.get(cedula).getReportes().isEmpty()){
                                System.out.println("Este usuario no tiene reportes");
                            }
                            else{
                                for (Map.Entry<Integer, Reporte> entry : personas.get(cedula).getReportes().entrySet()) {
                                    Integer key = entry.getKey();
                                    Reporte value = entry.getValue();
                                    System.out.println("Codigo reporte: "+key+",  nombre: "+value.getNombre()+",  descripcion: "+value.getDescripcion()+",  estado: "+value.getEstado()+",  valor: "+value.getValor());
                                }
                            }
                        break;
                    case 2: 
                            Scanner entradaReporte = new Scanner(System.in);
                            System.out.print("Ingrese el codigo: ");
                            int codigoReporte = Integer.parseInt(entradaReporte.nextLine());
                            System.out.print("Ingrese el nombre: ");
                            String nombreReporte = entradaReporte.nextLine();
                            System.out.print("Ingrese la descripcion: ");
                            String descripcionReporte = entradaReporte.nextLine();
                            System.out.print("Ingrese el estado: ");
                            String estadoReporte = entradaReporte.nextLine();
                            System.out.print("Ingrese el valor: ");
                            String valorReporte = entradaReporte.nextLine();
                            
                            if(personas.get(cedula).getReportes().containsKey(codigoReporte)){
                                System.out.println("No se puede crear este reporte, porque ese codigo ya existe");
                            }else{
                                personas.get(cedula).getReportes().put(codigoReporte, new Reporte(codigoReporte, nombreReporte, descripcionReporte, estadoReporte, valorReporte));
                                ///Guarda en  Fichero
                                BufferedReader br;  
                                try {
                                    br = new BufferedReader(new FileReader("archivos/reportes.txt"));
                                    if(br.readLine() == null) { 
                                        //System.out.println("ESTA VACIO"); 
                                        File file = new File("archivos/reportes.txt");
                                        try{
                                            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                                            try (BufferedWriter bw = new BufferedWriter(fw)) {
                                                cadena = ""+cedula+","+codigoReporte+","+nombreReporte+","+descripcionReporte+","+estadoReporte+","+valorReporte;
                                                bw.write(cadena);
                                                bw.newLine();
                                            }
                                        }catch(IOException e){
                                            System.out.println("\nNo se encontro el archivo de datos para escribir en él!!!");
                                        }
                                    }else{
                                        File file = new File("archivos/reportes.txt");
                                        try{
                                            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                                            try (BufferedWriter bw = new BufferedWriter(fw)) {
                                                cadena = ""+cedula+","+codigoReporte+","+nombreReporte+","+descripcionReporte+","+estadoReporte+","+valorReporte;
                                                bw.write(cadena);
                                                bw.newLine();
                                            }
                                        }catch(IOException e){
                                            System.out.println("\nNo se encontro el archivo de datos para escribir en él!!!");
                                        }
                                    }
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        break;
                    case 3:
                            if(personas.get(cedula).getReportes().isEmpty()){
                                System.out.println("Este usuario no tiene reportes");
                            }
                            else{
                                for (Map.Entry<Integer, Reporte> entry : personas.get(cedula).getReportes().entrySet()) {
                                    Integer key = entry.getKey();
                                    Reporte value = entry.getValue();
                                    System.out.println("Codigo reporte: "+key+",  nombre: "+value.getNombre()+",  descripcion: "+value.getDescripcion()+",  estado: "+value.getEstado()+",  valor: "+value.getValor());
                                }
                                Scanner recibeCodigo = new Scanner(System.in);
                                System.out.print("Ingrese el codigo del reporte que desea eliminar: ");
                                int codigoElimina = recibeCodigo.nextInt();
                                
                                personas.get(cedula).getReportes().remove(codigoElimina);
                                
                                File file = new File("archivos/reportes.txt");
                                try {
                                    FileWriter fr = new FileWriter(file);
                                } catch (IOException ex) {
                                    Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                for (Map.Entry<Integer, Persona> entry : personas.entrySet()) {
                                    Integer cedPersona = entry.getKey();
                                    for (Map.Entry<Integer, Reporte> entry1 : personas.get(cedPersona).getReportes().entrySet()) {
                                        Integer codReporte = entry1.getKey();
                                        Reporte reporte = entry1.getValue();
                                        
                                        try{
                                            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                                            try (BufferedWriter bw = new BufferedWriter(fw)) {
                                                cadena = ""+cedPersona+","+codReporte+","+reporte.getNombre()+","+reporte.getDescripcion()+","+reporte.getEstado()+","+reporte.getValor();
                                                bw.write(cadena);
                                                bw.newLine();
                                            }
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IOException ex) {
                                            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                    }
                                }
                            }
                        break;
                    case 4:
                        break;
                    case 5: 
                        break;
                    default: System.out.println("Valor no valido");
                        break;
                        
                }
            
            }while(opcion <4);
            
        
        }while(opcion != 5);
    }
    
    public String leerArchivoPersonas(String archivo){
        
        FileReader f;
        try {
            f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine())!=null) {
                String[] campo = cadena.split(",");
                personas.put(Integer.parseInt(campo[0]), new Persona(Integer.parseInt(campo[0]), campo[1], campo[2], campo[3]));
            }
            b.close();
            return cadena;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }
    
    public String leerArchivoReportes(String archivo){
        
        FileReader f;
        try {
            f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine())!=null) {
                String[] campo = cadena.split(",");
                personas.get(Integer.parseInt(campo[0])).getReportes().put(Integer.parseInt(campo[1]), new Reporte(Integer.parseInt(campo[1]), campo[2], campo[3], campo[4], campo[5]));
            }
            b.close();
            return cadena;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }
    
}

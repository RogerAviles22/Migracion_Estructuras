/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap; 
import java.util.LinkedList;

/**
 *
 * @author Rogencio
 */
public class Registrador {
    
    
    public HashMap<Migrante,HashMap<String,RegistroMigratorio>> leerArchivo(){
        
        HashMap<Migrante,HashMap<String,RegistroMigratorio>> migrantes= new HashMap<>();
        String fileName = "src/archivos/migrantes.txt";  
        try(BufferedReader inputStream = new BufferedReader(new FileReader(fileName));)
        { 
            String line = null;
            while( (line = inputStream.readLine()) != null ){
                String[] lines=line.split(";");
                Nacionalidad nacionalidad= new Nacionalidad(lines[6],lines[7],lines[8],lines[9]);
                Migrante migrante= new Migrante(lines[1],lines[2],lines[3],lines[4],nacionalidad,lines[5],lines[10]);
                HashMap<String,RegistroMigratorio> mig= new HashMap<>();
                if (lines[0]=="Entrada"){
                    //String medioTrans, LocalDate fecha, String ciudadIngreso, String paisIngreso, String continenteIngreso
                    RegistroMigratorio registro= new Entrada(lines[11],lines[12],lines[13],lines[14],lines[15]);
                    mig.put(lines[0], registro);
                }else if(lines[0]=="Salida"){
                    RegistroMigratorio registro= new Salida(lines[11],lines[12],lines[13],lines[14],lines[15]);
                    mig.put(lines[0], registro);
                }
                migrantes.put(migrante, mig);
            }
        }
        catch(FileNotFoundException e)
        {
          System.out.println("File " + fileName + " not found.");
        }
        catch(IOException e)
        {
           System.out.println("Error reading from file " + fileName);
        }
        return migrantes;
    }
    
    public void EscribirArchivo(String confRegistro, Migrante migrante, RegistroMigratorio registro){
        try(BufferedWriter outputStream =
                 new BufferedWriter(new FileWriter("src/archivos/migrantes.txt",true)))
        {
                if(registro instanceof Entrada){
                    Entrada entrada=(Entrada) registro;
                    outputStream.write(confRegistro+";"+migrante.getCedula()+";"+migrante.getNombre()+";"+migrante.getApellido()+";"+migrante.getSexo()+";"+
                        migrante.getNacionalidad().getPais()+";"+migrante.getNacionalidad().getContinente()+";"+migrante.getNacionalidad().getCiudad()+";"+
                        migrante.getNacionalidad().getCanton()+";"+migrante.getFechaNacimiento()+";"+migrante.getTipo()+";"+registro.getMedioTrans()+";"+registro.getFecha()+";"+
                            entrada.getCiudadIngreso()+";"+entrada.getPaisIngreso()+";"+entrada.getContinenteIngreso());
                    outputStream.newLine();
                }else if(registro instanceof Salida){
                    Salida salida=(Salida) registro;
                    outputStream.write(confRegistro+";"+migrante.getCedula()+";"+migrante.getNombre()+";"+migrante.getApellido()+";"+migrante.getSexo()+";"+
                        migrante.getNacionalidad().getPais()+";"+migrante.getNacionalidad().getContinente()+";"+migrante.getNacionalidad().getCiudad()+";"+
                        migrante.getNacionalidad().getCanton()+";"+migrante.getFechaNacimiento()+";"+migrante.getTipo()+";"+registro.getMedioTrans()+";"+registro.getFecha()+";"+
                        salida.getCiudadDestino()+";"+salida.getPaisDestino()+";"+salida.getContinenteDestino());
                    outputStream.newLine();
                }
            }
        
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening the file out.txt."+ e.getMessage());
        }
        catch(IOException e){
            System.out.println("IOException."+ e.getMessage());
        }
    }
}

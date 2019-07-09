/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.VentanaEmergente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap; 
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Rogencio
 */
public class Registrador {
    
    
    public static HashMap<Migrante,HashMap<String,LinkedList<RegistroMigratorio>>> leerArchivo(){
        HashMap<Migrante,HashMap<String,LinkedList<RegistroMigratorio>>> migrantes= new HashMap<>();
        LinkedList<String> cedulas= new LinkedList<>();
        String fileName = "src/archivos/migrantes.txt";  
        HashMap<String,LinkedList<RegistroMigratorio>> mig;
        LinkedList<RegistroMigratorio> registros;
        try(BufferedReader inputStream = new BufferedReader(new FileReader(fileName));)
        { 
            String line = null;
            
            while( (line = inputStream.readLine()) != null ){
                Migrante migrante;
                String[] lines=line.split(";");
                if (lines[0].equals("Entrada")){
                    RegistroMigratorio registro= new Entrada(lines[11],lines[12],lines[13],lines[14],lines[15]);
                    if(!cedulas.contains(lines[1])){
                        Nacionalidad nacionalidad= new Nacionalidad(lines[5],lines[6],lines[7],lines[8]);
                        migrante= new Migrante(lines[1],lines[2],lines[3],lines[4],nacionalidad,lines[9],lines[10]);
                        mig=new HashMap<>();
                        registros=new LinkedList<>();
                        registros.add(registro);
                        mig.put(lines[0], registros);
                        migrantes.put(migrante, mig);
                    }
                    else if(cedulas.contains(lines[1])){
                        
                        for(Migrante e: migrantes.keySet()){
                            if(e.getCedula().equals(lines[1])){
                                mig=migrantes.get(e);
                                if(mig.containsKey(lines[0])){
                                registros=mig.get(lines[0]);
                                registros.add(registro);
                            }   if(!(mig.containsKey(lines[0]))){
                                 registros=new LinkedList<>();
                                 registros.add(registro);
                                 migrantes.get(e).put(lines[0], registros);
                                }
                            }   
                }
            }
                    }
                else if(lines[0].equals("Salida")){
                        RegistroMigratorio registro= new Salida(lines[11],lines[12],lines[13],lines[14],lines[15]);
                        if(!cedulas.contains(lines[1])){
                            Nacionalidad nacionalidad= new Nacionalidad(lines[5],lines[6],lines[7],lines[8]);
                            migrante= new Migrante(lines[1],lines[2],lines[3],lines[4],nacionalidad,lines[9],lines[10]);
                            mig=new HashMap<>();
                            registros=new LinkedList<>();
                            registros.add(registro);
                            mig.put(lines[0], registros);
                            migrantes.put(migrante, mig);
                            cedulas.add(lines[1]);
                    }
                        else if(cedulas.contains(lines[1])){
                        for(Migrante e: migrantes.keySet()){
                            if(e.getCedula().equals(lines[1])){
                                mig=migrantes.get(e);
                                if(mig.containsKey(lines[0])){
                                registros=mig.get(lines[0]);
                                registros.add(registro);
                        }
                                if(!(mig.containsKey(lines[0]))){
                                 registros=new LinkedList<>();
                                 registros.add(registro);
                                 migrantes.get(e).put(lines[0],registros);
                                }
                            }   
                }
            }
                }    
                        
                        
               
                    
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
        //System.out.println(migrantes.keySet());
        return migrantes;
    }
    
    public static void EscribirArchivo(String confRegistro, Migrante migrante, RegistroMigratorio registro){
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
    
    public static void modificarData(Migrante migrante, RegistroMigratorio registro){
        String fileName = "src/archivos/migrantes.txt"; 
        
       try(BufferedReader inputStream = new BufferedReader(new FileReader(fileName));)
        { 
            String line=null;
          while( (line = inputStream.readLine()) != null ){
              String[] lines=line.split(";");
              
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
    }
    
    public static void EliminarData(Migrante migrante,RegistroMigratorio registro) {
        String fileNameT = "src/archivos/migrantes.txt";
    try {

      File fileName = new File(fileNameT);

      if (!fileName.isFile()) {
        System.out.println("Parameter is not an existing file");
        return;
      }

      //Construir un nuevo archivo que me se va a ir modificando
      File tempFile = new File(fileName.getAbsolutePath() + ".tmp");

      BufferedReader br = new BufferedReader(new FileReader(fileNameT));
      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

      String line = null;
      while ((line = br.readLine()) != null) {
            String lineTo=null;
            if(registro instanceof Entrada){
                Entrada entrada=(Entrada) registro;
                lineTo="Entrada"+";"+migrante.getCedula()+";"+migrante.getNombre()+";"+migrante.getApellido()+";"+migrante.getSexo()+";"+
                        migrante.getNacionalidad().getPais()+";"+migrante.getNacionalidad().getContinente()+";"+migrante.getNacionalidad().getCiudad()+";"+
                        migrante.getNacionalidad().getCanton()+";"+migrante.getFechaNacimiento()+";"+migrante.getTipo()+";"+entrada.getMedioTrans()+";"+entrada.getFecha()+
                        ";"+entrada.getCiudadIngreso()+";"+entrada.getPaisIngreso()+";"+entrada.getContinenteIngreso();
                    
                }else if(registro instanceof Salida){
                    Salida salida=(Salida) registro;
                    lineTo="Salida"+";"+migrante.getCedula()+";"+migrante.getNombre()+";"+migrante.getApellido()+";"+migrante.getSexo()+";"+
                        migrante.getNacionalidad().getPais()+";"+migrante.getNacionalidad().getContinente()+";"+migrante.getNacionalidad().getCiudad()+";"+
                        migrante.getNacionalidad().getCanton()+";"+migrante.getFechaNacimiento()+";"+migrante.getTipo()+";"+registro.getMedioTrans()+";"+registro.getFecha()+";"+
                        salida.getCiudadDestino()+";"+salida.getPaisDestino()+";"+salida.getContinenteDestino();
                   
                }
        
        if (!line.trim().equals(lineTo)) {

          pw.println(line);
          pw.flush();
        }
      }
      pw.close();
      br.close();

      //Delete the original file
      if (!fileName.delete()) {
        System.out.println("Could not delete file");
        return;
      }

      //Rename the new file to the filename the original file had.
      if (!tempFile.renameTo(fileName))
        System.out.println("Could not rename file");

    }
    catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    
  }
    
    public static void main(String[] args){
        //Salida;1306944461;Celenita;Mendoza;Hombre;Switch;Europa;LuxerNur;LuxerNur;1969-08-12;Normal;Maritimo;2019-07-06;Luxenburge;Chile;America del SurEntradaEntrada
                Nacionalidad nacionalidad= new Nacionalidad("Switch","Europa","LuxerNur","LuxerNur");
                Migrante migrante= new Migrante("1306944461","Celenita","Mendoza","Hombre",nacionalidad,"1969-08-12","Normal");
                RegistroMigratorio registro= new Salida("Maritimo","2019-07-06","Luxenburge","Chile","America del Sur");
                EliminarData(migrante,registro);
            System.out.println(leerArchivo());
        
    }
}

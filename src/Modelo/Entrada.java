/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Francisco
 */
public class Entrada extends RegistroMigratorio{
    
    private String ciudad;
    private String pais;
    private String continente;
    
    public Entrada(String medioTrans, String fecha, String ciudadIngreso, String paisIngreso, String continenteIngreso) {
        super(medioTrans, fecha);
        this.ciudad=ciudadIngreso;
        this.continente=continenteIngreso;
        this.pais=paisIngreso;
    }

    public String getCiudadIngreso() {
        return ciudad;
    }

    public void setCiudadIngreso(String ciudadIngreso) {
        this.ciudad = ciudadIngreso;
    }

    public String getPaisIngreso() {
        return pais;
    }

    public void setPaisIngreso(String paisIngreso) {
        this.pais = paisIngreso;
    }

    public String getContinenteIngreso() {
        return continente;
    }

    public void setContinenteIngreso(String continenteIngreso) {
        this.continente = continenteIngreso;
    }
    
}

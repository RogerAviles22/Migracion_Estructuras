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
    
    private String ciudadIngreso;
    private String paisIngreso;
    private String continenteIngreso;
    
    public Entrada(String medioTrans, String fecha, String ciudadIngreso, String paisIngreso, String continenteIngreso) {
        super(medioTrans, fecha);
        this.ciudadIngreso=ciudadIngreso;
        this.continenteIngreso=continenteIngreso;
        this.paisIngreso=paisIngreso;
    }

    public String getCiudadIngreso() {
        return ciudadIngreso;
    }

    public void setCiudadIngreso(String ciudadIngreso) {
        this.ciudadIngreso = ciudadIngreso;
    }

    public String getPaisIngreso() {
        return paisIngreso;
    }

    public void setPaisIngreso(String paisIngreso) {
        this.paisIngreso = paisIngreso;
    }

    public String getContinenteIngreso() {
        return continenteIngreso;
    }

    public void setContinenteIngreso(String continenteIngreso) {
        this.continenteIngreso = continenteIngreso;
    }
    
}

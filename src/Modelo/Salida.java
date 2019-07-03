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
public class Salida extends RegistroMigratorio{
    private String ciudadDestino;
    private String paisDestino;
    private String continenteDestino;

    public Salida(String medioTrans, String fecha, String ciudadDestino,String paisDestino, String continenteDestino) {
        super(medioTrans, fecha);
        this.paisDestino=paisDestino;
        this.ciudadDestino=ciudadDestino;
        this.continenteDestino=continenteDestino;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getContinenteDestino() {
        return continenteDestino;
    }

    public void setContinenteDestino(String continenteDestino) {
        this.continenteDestino = continenteDestino;
    }
    
}

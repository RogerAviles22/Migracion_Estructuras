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
    private String ciudad;
    private String pais;
    private String continente;

    public Salida(String medioTrans, String fecha, String ciudadDestino,String paisDestino, String continenteDestino) {
        super(medioTrans, fecha);
        this.pais=paisDestino;
        this.ciudad=ciudadDestino;
        this.continente=continenteDestino;
    }

    public String getCiudadDestino() {
        return ciudad;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudad = ciudadDestino;
    }

    public String getPaisDestino() {
        return pais;
    }

    public void setPaisDestino(String paisDestino) {
        this.pais = paisDestino;
    }

    public String getContinenteDestino() {
        return continente;
    }

    public void setContinenteDestino(String continenteDestino) {
        this.continente = continenteDestino;
    }
    
}

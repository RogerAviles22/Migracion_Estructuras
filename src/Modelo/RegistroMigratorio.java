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
public class RegistroMigratorio {
    private String medioTrans;
    private String fecha;

    public RegistroMigratorio(String medioTrans, String fecha) {
        this.medioTrans = medioTrans;
        this.fecha = fecha;
    }

    public String getMedioTrans() {
        return medioTrans;
    }

    public void setMedioTrans(String medioTrans) {
        this.medioTrans = medioTrans;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}

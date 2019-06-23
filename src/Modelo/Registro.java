/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Rogencio
 */
public class Registro {
    private Migrante migrante;
    private Registrador registrador;
    private String lugarDestino; //Donde se va o vive el migrante
    private String tipoMovimiento; // Entrada o Salida
    private String tipoTransporte; //maritimo, aereo, terrestre

    public Registro(Migrante migrante, Registrador registrador, String lugarDestino, String tipoMovimiento, String tipoTransporte) {
        this.migrante = migrante;
        this.registrador = registrador;
        this.lugarDestino = lugarDestino;
        this.tipoMovimiento = tipoMovimiento;
        this.tipoTransporte = tipoTransporte;
    }

    public Migrante getMigrante() {
        return migrante;
    }

    public void setMigrante(Migrante migrante) {
        this.migrante = migrante;
    }

    public Registrador getRegistrador() {
        return registrador;
    }

    public void setRegistrador(Registrador registrador) {
        this.registrador = registrador;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    @Override
    public String toString() {
        return "Registro{" + "migrante=" + migrante + ", registrador=" + registrador + ", lugarDestino=" + lugarDestino + ", tipoMovimiento=" + tipoMovimiento + ", tipoTransporte=" + tipoTransporte + '}';
    }
    
    
}

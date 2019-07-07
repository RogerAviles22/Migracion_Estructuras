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
public class Nacionalidad {
    private String pais;
    private String continente;
    private String ciudad; //
    private String canton;

    public Nacionalidad(String pais, String continente, String ciudad, String canton) {
        this.pais = pais;
        this.continente = continente;
        this.ciudad = ciudad;
        this.canton = canton;
    }

    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    /**
     * Ciudad Origen del Migrante
     * @return Ciudad Origen
     */
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Canton Origen del Migrante
     * @return Canton Origen
     */
    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @Override
    public String toString() {
        return pais;
    }
    
    
    
}

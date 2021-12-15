package com.gmail.arieldeleonhernandez123.paintball.Juego;

import org.bukkit.Location;

import java.util.ArrayList;

public class Equipo {

    private ArrayList<JugadorPaintBall> jugadores;
    private String tipo;
    private Location spawn;

    public Equipo(String tipo) {
        this.jugadores = new ArrayList<JugadorPaintBall>();
        this.tipo = tipo;

    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public boolean contienejugador(String jugador) {
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (jugadores.get(i).getJugador().getName().equals(jugador)) {
                return true;
            }


        }
        return false;
    }

    public boolean agregarJugador(JugadorPaintBall jugador) {
        if (!contienejugador(jugador.getJugador().getName())) {
            this.jugadores.add(jugador);
            return true;
        }else{
            return false;
        }
    }

    public boolean removerJugador(String jugador){
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (jugadores.get(i).getJugador().getName().equals(jugador)) {
                this.jugadores.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<JugadorPaintBall> getJugadores() {
        return jugadores;
    }

    public org.bukkit.Location getSpawn() {
        return spawn;
    }

    public void setSpawn(org.bukkit.Location spawn) {
        this.spawn = spawn;
    }

    public int getCantidadJugadores() {
        return this.jugadores.size();
    }

    public int getAsesinatosTotales(){
        int kills = 0;
        for (JugadorPaintBall j: this.jugadores) {
            kills = kills + j.getAsesinatos();
        }
        return kills;
    }
}



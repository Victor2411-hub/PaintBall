package com.gmail.arieldeleonhernandez123.paintball.Juego;

import org.bukkit.Location;

import java.util.ArrayList;

public class Partida {

    private Equipo equipo1;
    private Equipo equipo2;
    private String nombre;
    private int CantidadMaximaJugadores;
    private int CantidadMinimaJugadores;
    private int CantidadActualJugadores;
    private EstadoPartida estado;
    private Location lobby;
    private int tiempo;
    private int tiempoMaximo;


    public Partida(String nombre) {
        //por defecto
        this.equipo1 = new Equipo("Azul");
        this.equipo2 = new Equipo("Rojo");
        this.nombre = nombre;
        this.CantidadActualJugadores = 0;
        this.CantidadMaximaJugadores = 8;
        this.CantidadMinimaJugadores = 2;
        this.estado = EstadoPartida.DESACTIVADA;
        this.tiempo = 0;
        this.tiempoMaximo = 60;


    }

    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public void disminuirTiempo() {
        this.tiempo--;
    }

    public void aumentarTiempo() {
        this.tiempo++;
    }

    public int setTiempo(int tiempo) {
        this.tiempo = tiempo;

        return tiempo;
    }

    public int getTiempo() {
        return this.tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarJugador(JugadorPaintBall jugador) {
        if (equipo1.agregarJugador(jugador)) {
            this.CantidadActualJugadores++;
        }
    }

    public void RepartirJudadorTeam2(JugadorPaintBall jugador) {
        this.equipo1.removerJugador(jugador.getJugador().getName());
        this.equipo2.agregarJugador(jugador);
    }

    public void removerJugador(JugadorPaintBall jugador) {
        if (equipo1.removerJugador(String.valueOf(jugador)) || equipo2.removerJugador(String.valueOf(jugador))) {
            this.CantidadActualJugadores--;
        }
    }

    public ArrayList<JugadorPaintBall> getJugadores() {
        ArrayList<JugadorPaintBall> jugadores = new ArrayList<JugadorPaintBall>();
        for (JugadorPaintBall j : equipo1.getJugadores()) {
            jugadores.add(j);
        }
        for (JugadorPaintBall j : equipo2.getJugadores()) {
            jugadores.add(j);
        }
        return jugadores;
    }

    public JugadorPaintBall getJugador(String jugador) {
        ArrayList<JugadorPaintBall> jugadores = getJugadores();
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getJugador().getName().equals(jugador)) {
            }
        }
        return null;
    }

    public Equipo getEquipoJugador(String jugador) {
        ArrayList<JugadorPaintBall> jugadoresTeam1 = equipo1.getJugadores();
        for (int i = 0; i < jugadoresTeam1.size(); i++) {
            if (jugadoresTeam1.get(i).getJugador().getName().equals(jugador)) {
                return equipo1;
            }
        }
        ArrayList<JugadorPaintBall> jugadoresTeam2 = equipo2.getJugadores();
        for (int i = 0; i < jugadoresTeam2.size(); i++) {
            if (jugadoresTeam2.get(i).getJugador().getName().equals(jugador)) {
                return equipo2;
            }
        }
        return null;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public int getCantidadMaximaJugadores() {
        return CantidadMaximaJugadores;
    }

    public void setCantidadMaximaJugadores(int max) {
        CantidadMaximaJugadores = max;
    }

    public int getCantidadMinimaJugadores() {
        return CantidadMinimaJugadores;
    }

    public void setCantidadMinimaJugadores(int mim) {
        CantidadMinimaJugadores = mim;
    }

    public int getCantidadActualJugadores() {
        return CantidadActualJugadores;
    }

    public void setEstado(EstadoPartida estado) {
        this.estado = estado;
    }

    public boolean EstaIniciada() {
        if (estado.equals(EstadoPartida.ESPERANDO) || estado.equals(EstadoPartida.DESACTIVADA) || estado.equals(EstadoPartida.COMENZANDO)) {
            return false;
        }else{
            return true;
        }
    }

    public boolean EstaLlena() {
        if (CantidadActualJugadores == CantidadMaximaJugadores) {
            return true;
        } else {
            return false;
        }
    }

    public boolean EstaActivada(){
        if(!estado.equals(EstadoPartida.DESACTIVADA)){
            return true;
        }else{
            return false;
        }
    }

    public void setLobby(org.bukkit.Location l) {
        this.lobby = l;
    }

    public org.bukkit.Location getLobby(){
        return this.lobby;
    }

    public Equipo getGanador(){
        if (equipo1.getJugadores().size() == 0) {
            return equipo2;
        }
        if (equipo2.getJugadores().size() == 0) {
            return equipo1;
        }

        int killsTeam1 = equipo1.getAsesinatosTotales();
        int killsTeam2 = equipo2.getAsesinatosTotales();

        if (killsTeam1 > killsTeam2) {
            return equipo1;
        }
        else if (killsTeam1 < killsTeam2) {
            return equipo2;
        }
        else {
            return null;
        }
    }

    public EstadoPartida getEstado() {
        return estado;
    }

    /*public ArrayList<JugadorPaintBall> getJugadoresKills(){

    }*/



}

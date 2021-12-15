package com.gmail.arieldeleonhernandez123.paintball.Juego;

import org.bukkit.entity.Player;

public class JugadorPaintBall {

    private Player jugador;
    private int asesinatos;
    private int muertes;
    private boolean asesinadoRecientemente;
    private ElementosGuardados guardados;

    public JugadorPaintBall(Player jugador) {
        this.jugador = jugador;
        this.guardados = new ElementosGuardados(jugador.getInventory().getContents(), jugador.getInventory().getArmorContents(), jugador.getGameMode(),
                jugador.getExp(), jugador.getLevel(), jugador.getFoodLevel(), jugador.getHealth(), jugador.getMaxHealth());


    }
    public ElementosGuardados getGuardados() {
        return this.guardados;
    }

    public void aumentarasesinatos() {
        this.asesinatos++;
    }

    public void aumentarmuertes() {
        this.muertes++;
    }

    public int getAsesinatos() {
        return this.asesinatos;
    }

    public int getMuertes() {
        return this.muertes;
    }

    public Player getJugador() {
        return this.jugador;
    }

    public boolean isAsesinadoRecientemente() {
        return this.asesinadoRecientemente;
    }

    public void setAsesinadoRecientemente(boolean asesinadoRecientemente) {
        this.asesinadoRecientemente = asesinadoRecientemente;
    }
}



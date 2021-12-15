package com.gmail.arieldeleonhernandez123.paintball.Juego;

import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;

public class ElementosGuardados {

    private ItemStack[] InventarioGuardado;
    private ItemStack[] EquipamientoGuardado;
    private GameMode GamemodeGuardado;
    private float ExperienciaGuardada;
    private int NivelGuardado;
    private int HambreGuardada;
    private double VidaGuardada;
    private double MaximaVidaGuardada;

    public ElementosGuardados(ItemStack[] inventarioGuardado, ItemStack[] equipamientoGuardado, GameMode gamemodeGuardado, float experienciaGuardada, int nivelGuardado, int hambreGuardada, double vidaGuardada, double maximaVidaGuardada) {
        InventarioGuardado = inventarioGuardado;
        EquipamientoGuardado = equipamientoGuardado;
        GamemodeGuardado = gamemodeGuardado;
        ExperienciaGuardada = experienciaGuardada;
        NivelGuardado = nivelGuardado;
        HambreGuardada = hambreGuardada;
        VidaGuardada = vidaGuardada;
        MaximaVidaGuardada = maximaVidaGuardada;
    }

    public ItemStack[] getInventarioGuardado() {
        return InventarioGuardado;
    }

    public void setInventarioGuardado(ItemStack[] inventarioGuardado) {
        InventarioGuardado = inventarioGuardado;
    }

    public ItemStack[] getEquipamientoGuardado() {
        return EquipamientoGuardado;
    }

    public void setEquipamientoGuardado(ItemStack[] equipamientoGuardado) {
        EquipamientoGuardado = equipamientoGuardado;
    }

    public GameMode getGamemodeGuardado() {
        return GamemodeGuardado;
    }

    public void setGamemodeGuardado(GameMode gamemodeGuardado) {
        GamemodeGuardado = gamemodeGuardado;
    }

    public float getExperienciaGuardada() {
        return ExperienciaGuardada;
    }

    public void setExperienciaGuardada(float experienciaGuardada) {
        ExperienciaGuardada = experienciaGuardada;
    }

    public int getNivelGuardado() {
        return NivelGuardado;
    }

    public void setNivelGuardado(int nivelGuardado) {
        NivelGuardado = nivelGuardado;
    }

    public int getHambreGuardada() {
        return HambreGuardada;
    }

    public void setHambreGuardada(int hambreGuardada) {
        HambreGuardada = hambreGuardada;
    }

    public double getVidaGuardada() {
        return VidaGuardada;
    }

    public void setVidaGuardada(double vidaGuardada) {
        VidaGuardada = vidaGuardada;
    }

    public double getMaximaVidaGuardada() {
        return MaximaVidaGuardada;
    }

    public void setMaximaVidaGuardada(double maximaVidaGuardada) {
        MaximaVidaGuardada = maximaVidaGuardada;
    }


}

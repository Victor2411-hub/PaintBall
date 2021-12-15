package com.gmail.arieldeleonhernandez123.paintball;

import com.gmail.arieldeleonhernandez123.paintball.Juego.EstadoPartida;
import com.gmail.arieldeleonhernandez123.paintball.Juego.JugadorPaintBall;
import com.gmail.arieldeleonhernandez123.paintball.Juego.Partida;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public final class PaintBall extends JavaPlugin {

    PluginDescriptionFile pdfFile = this.getDescription();
    public String version = pdfFile.getVersion();
    public String prefix = "[PaintBall] ";
    private ArrayList<Partida> partidas;
    private FileConfiguration Arenas = null;
    private File ArenasFile = null;

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        registerArenas();
        registerConfig();
        CargarPartidas();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        GuardarPartidas();
    }

    public void registerCommands(){
        getCommand("paintball").setExecutor(new Commands());
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        //pm.registerEvents(new Events(), this);
    }

    public void agregarPartida(Partida partida){
        partidas.add(partida);
    }

    public Partida getPartidas(String nombre){
        for (int i = 0; i < partidas.size(); i++){
            if (partidas.get(i).getNombre().equals(nombre)){
                return partidas.get(i);
            }
        }
        return null;
    }

    public ArrayList<Partida> getPartidas(){
        return partidas;
    }

    public Partida getJugador(String jugador){
            for (int i = 0; i < partidas.size(); i++) {
                ArrayList<JugadorPaintBall> jugadores = partidas.get(i).getJugadores();
                for (int j = 0; j < jugadores.size(); j++) {
                    if (jugadores.get(j).getJugador().getName().equals(jugador)) {
                        return partidas.get(i);
                    }
                }
            }
            return null;
    }

    public void removerPartidas(Partida partida){
        for (int i = 0; i < partidas.size(); i++){
            if (partidas.get(i).getNombre().equals(partida.getNombre())){
                partidas.remove(i);
            }
        }
    }

    public void CargarPartidas(){
        this.partidas = new ArrayList<Partida>();
        FileConfiguration Arenas = this.getArenas();
        if (Arenas.contains("Arenas")) {
            for(String nombre : Arenas.getConfigurationSection("Arenas").getKeys(false)){
                int minPlayers = Integer.valueOf(Arenas.getString("Arenas." + nombre + ".Max_Players"));
                int maxPlayers = Integer.valueOf(Arenas.getString("Arenas." + nombre + ".Max_Players"));
                int maxTime = Integer.valueOf(Arenas.getString("Arenas." + nombre + ".time"));

                org.bukkit.Location lLobby = null;
                if (Arenas.contains("Arenas." + nombre + ".Lobby")) {
                    double xlobby = Arenas.getDouble("Arenas." + nombre + ".Lobby.X");
                    double ylobby = Arenas.getDouble("Arenas." + nombre + ".Lobby.Y");
                    double zlobby = Arenas.getDouble("Arenas." + nombre + ".Lobby.Z");
                    float yawlobby = (float) Arenas.getDouble("Arenas." + nombre + ".Lobby.Yaw");
                    float pitchlobby = (float) Arenas.getDouble("Arenas." + nombre + ".Lobby.Pitch");
                    World worldlobby = Bukkit.getWorld(Arenas.getString("Arenas." + nombre + ".Lobby.World"));
                    lLobby = new Location(worldlobby, xlobby, ylobby, zlobby, yawlobby, pitchlobby);
                }
                String nombreTeam1 = Arenas.getString("Arenas." + nombre + ".Team1.Name");
                Location lSpawnTeam1 = null;
                if (Arenas.contains("Arenas." + nombre + ".Team1.Spawn")) {
                    double xSpawnTeam1 = Double.parseDouble(Arenas.getString("Arenas." + nombre + ".SpawnTeam1.X"));
                    double ySpawnTeam1 = Double.parseDouble(Arenas.getString("Arenas." + nombre + ".SpawnTeam1.Y"));
                    double zSpawnTeam1 = Double.parseDouble(Arenas.getString("Arenas." + nombre + ".SpawnTeam1.Z"));
                    float yawSpawnTeam1 = Float.valueOf(Arenas.getString("Arenas." + nombre + ".SpawnTeam1.Yaw"));
                    float pitchSpawnTeam1 = Float.valueOf (Arenas.getString("Arenas." + nombre + ".SpawnTeam1.Pitch"));
                    World worldSpawnTeam1 = Bukkit.getWorld(Arenas.getString("Arenas." + nombre + ".SpawnTeam1.World"));
                    lSpawnTeam1 = new Location(worldSpawnTeam1, xSpawnTeam1, ySpawnTeam1, zSpawnTeam1, yawSpawnTeam1, pitchSpawnTeam1);

                }
                String nombreTeam2 = Arenas.getString("Arenas." + nombre + ".Team2.Name");
                Location lSpawnTeam2 = null;
                if (Arenas.contains("Arenas." + nombre + ".Team2.Spawn")) {
                    double xSpawnTeam2 = Double.parseDouble(Arenas.getString("Arenas." + nombre + ".SpawnTeam2.X"));
                    double ySpawnTeam2 = Double.parseDouble(Arenas.getString("Arenas." + nombre + ".SpawnTeam2.Y"));
                    double zSpawnTeam2 = Double.parseDouble(Arenas.getString("Arenas." + nombre + ".SpawnTeam2.Z"));
                    float yawSpawnTeam2 = Float.valueOf(Arenas.getString("Arenas." + nombre + ".SpawnTeam2.Yaw"));
                    float pitchSpawnTeam2 = Float.valueOf (Arenas.getString("Arenas." + nombre + ".SpawnTeam2.Pitch"));
                    World worldSpawnTeam2 = Bukkit.getWorld(Arenas.getString("Arenas." + nombre + ".SpawnTeam2.World"));
                    lSpawnTeam2 = new Location(worldSpawnTeam2, xSpawnTeam2, ySpawnTeam2, zSpawnTeam2, yawSpawnTeam2, pitchSpawnTeam2);
                }

                Partida partida = new Partida(nombre);
                partida.setCantidadMaximaJugadores(maxPlayers);
                partida.setCantidadMinimaJugadores(minPlayers);
                partida.setTiempoMaximo(maxTime);
                partida.setLobby(lLobby);
                partida.getEquipo1().setTipo(nombreTeam1);
                partida.getEquipo1().setSpawn(lSpawnTeam1);
                partida.getEquipo2().setTipo(nombreTeam2);
                partida.getEquipo2().setSpawn(lSpawnTeam2);
                String Enabled = Arenas.getString("Arenas." + nombre + ".Enabled");
                if(Enabled.equals("false")){
                    partida.setEstado(EstadoPartida.DESACTIVADA);
                }else {
                    partida.setEstado(EstadoPartida.DESACTIVADA);
                 }
                }
            }
        }


    public void GuardarPartidas(){
        FileConfiguration Arenas = this.getArenas();
        for(Partida p : partidas){
            String nombre = p.getNombre();
            getArenas().set("Arenas." + nombre + ".Min_Players",p.getCantidadMinimaJugadores());
            getArenas().set("Arenas." + nombre + ".Max_Players",p.getCantidadMaximaJugadores());
            getArenas().set("Arenas." + nombre + ".time",p.getTiempoMaximo());
            Location Llobby = (Location) p.getLobby();
            if (Llobby != null) {
                getArenas().set("Arenas." + nombre + ".Lobby.X", Llobby.getX());
                getArenas().set("Arenas." + nombre + ".Lobby.Y", Llobby.getY());
                getArenas().set("Arenas." + nombre + ".Lobby.Z", Llobby.getZ());
                getArenas().set("Arenas." + nombre + ".Lobby.World", Llobby.getWorld().getName());
                getArenas().set("Arenas." + nombre + ".Lobby.Yaw", Llobby.getYaw());
                getArenas().set("Arenas." + nombre + ".Lobby.Pitch", Llobby.getPitch());
            }
            Location LSpawnTeam1 = (Location) p.getEquipo1().getSpawn();
            if (LSpawnTeam1 != null) {
                getArenas().set("Arenas." + nombre + ".SpawnTeam1.X", LSpawnTeam1.getX());
                getArenas().set("Arenas." + nombre + ".SpawnTeam1.Y", LSpawnTeam1.getY());
                getArenas().set("Arenas." + nombre + ".SpawnTeam1.Z", LSpawnTeam1.getZ());
                getArenas().set("Arenas." + nombre + ".SpawnTeam1.World", LSpawnTeam1.getWorld().getName());
                getArenas().set("Arenas." + nombre + ".SpawnTeam1.Yaw", LSpawnTeam1.getYaw());
                getArenas().set("Arenas." + nombre + ".SpawnTeam1.Pitch", LSpawnTeam1.getPitch());
            }
              Arenas.set("Arenas." + nombre + ".Team1.Name", p.getEquipo1().getTipo());

            Location LSpawnTeam2 = (Location) p.getEquipo2().getSpawn();
            if (LSpawnTeam2 != null) {
                getArenas().set("Arenas." + nombre + ".SpawnTeam2.X", LSpawnTeam2.getX());
                getArenas().set("Arenas." + nombre + ".SpawnTeam2.Y", LSpawnTeam2.getY());
                getArenas().set("Arenas." + nombre + ".SpawnTeam2.Z", LSpawnTeam2.getZ());
                getArenas().set("Arenas." + nombre + ".SpawnTeam2.World", LSpawnTeam2.getWorld().getName());
                getArenas().set("Arenas." + nombre + ".SpawnTeam2.Yaw", LSpawnTeam2.getYaw());
                getArenas().set("Arenas." + nombre + ".SpawnTeam2.Pitch", LSpawnTeam2.getPitch());

            }
            Arenas.set("Arenas." + nombre + ".Team2.Name", p.getEquipo2().getTipo());

            if (p.getEstado() == EstadoPartida.DESACTIVADA) {
                getArenas().set("Arenas." + nombre + ".Enabled", false);
            }else{
                getArenas().set("Arenas." + nombre + ".Enabled", true);
            }
        }
    }

    public void registerConfig(){
        File config = new File(this.getDataFolder(), "config.yml");
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public void registerArenas(){
        ArenasFile = new File(this.getDataFolder(), "arenas.yml");
        Arenas = this.getConfig();
        if(!ArenasFile.exists()){
            this.getArenas().options().copyDefaults(true);
            this.saveArenas();
        }
    }

    public void saveArenas(){
        try {
            Arenas.save(ArenasFile);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileConfiguration getArenas() {
        if(Arenas == null){
            reloadArenas();
        }
        return Arenas;
    }

    public void reloadArenas(){
        if (ArenasFile == null){
            ArenasFile = new File(this.getDataFolder(), "arenas.yml");
        }
        Arenas = YamlConfiguration.loadConfiguration(ArenasFile);

        Reader defConfigStream = null;
        try {
            defConfigStream = new java.io.InputStreamReader(this.getResource("arenas.yml"), "UTF8");

            if (defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                Arenas.setDefaults(defConfig);
            }

            }catch (UnsupportedEncodingException e){
            e.printStackTrace();

        }

    }



}

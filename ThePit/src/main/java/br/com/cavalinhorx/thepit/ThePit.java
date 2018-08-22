package br.com.cavalinhorx.thepit;

import br.com.cavalinhorx.thepit.database.Database;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class ThePit extends JavaPlugin {

    @Getter private static Database database;

    @Override
    public void onEnable() {
        database = new Database("localhost", "3306", "motocrack", "root", "");
        database.openConnection();
        database.createTables();
    }

    @Override
    public void onDisable() {
        database.closeConnection();
    }
}
package br.com.cavalinhorx.thepit.player;

import br.com.cavalinhorx.thepit.ThePit;
import br.com.cavalinhorx.thepit.database.crud.ICRUD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TPlayerCRUD implements ICRUD<TPlayer, UUID> {

    /**
     * Adiciona o jogador no banco de dados.
     *
     * @param object = recebe um objeto TPlayer
     * @return {@link TPlayer}
     */
    @Override
    public void create(TPlayer object) {
        try {
            ThePit.getDatabase().query("INSERT INTO ThePit(uuid, level, xp, gold) VALUES('" + object.getUuid().toString() + "', '" + object.getLevel() + "','" + object.getXp() + "', '" + object.getGold() + "');").executeUpdate();
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[ThePit] " + object.getUuid() + " registrado com sucesso!");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Recupera o jogador no banco de dados.
     *
     * @param object = recebe um jogador
     * @return {@link TPlayer}
     */
    @Override
    public TPlayer read(UUID object) {
        try {
            ResultSet resultSet = ThePit.getDatabase().query("SELECT * FROM ThePit WHERE uuid='" + object.toString() + "';").executeQuery();
            if (resultSet.next()) {
                TPlayer tPlayer = new TPlayer();
                tPlayer.setUuid(object);
                tPlayer.setId(resultSet.getInt("id"));
                tPlayer.setLevel(resultSet.getInt("level"));
                tPlayer.setXp(resultSet.getInt("xp"));
                tPlayer.setGold(resultSet.getDouble("gold"));
                TPlayerCache.getInstance().add(object, tPlayer);
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[ThePit] " + object + " carregado com sucesso!");
                return tPlayer;
            } else {
                new SQLException("Nenhum jogador encontrado.");
                return null;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Atualiza o jogador no banco de dados.
     *
     * @param object = recebe um objeto TPlayer
     * @return {@link TPlayer}
     */
    @Override
    public void update(TPlayer object) {
        try {
            ThePit.getDatabase().query("UPDATE ThePit SET level='" + object.getLevel() + "', xp='" + object.getXp() + "', gold='" + object.getGold() + "' WHERE id='" + object.getId() + "';");
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[ThePit] " + object.getUuid() + " atualizado com sucesso!");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Remove o jogador do banco de dados.
     *
     * @param object = recebe um jogador
     * @return {@link TPlayer}
     */
    @Override
    public void delete(UUID object) {
        try {
            ThePit.getDatabase().query("DELETE FROM ThePit WHERE uuid='" +  object.toString() + "';");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
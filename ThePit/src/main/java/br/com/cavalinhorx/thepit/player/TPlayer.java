package br.com.cavalinhorx.thepit.player;

import br.com.cavalinhorx.thepit.sidebar.TPScoreboard;
import lombok.Data;

import java.util.UUID;

@Data
public class TPlayer {

    /**
     * UUID único do jogador.
     * @return {@link UUID}
     */
    private UUID uuid;

    /**
     * Id único do jogador.
     * @return int
     */
    private int id;

    /**
     * Nível do jogador.
     * @return int
     */
    private int level;

    /**
     * Experiência total do jogador.
     * @return int
     */
    private int xp;

    /**
     * Dinheiro total do jogador.
     * @return int
     */
    private double gold;

    /**
     * Sidebar do jogador.
     * @return {@link TPScoreboard}
     */
    private TPScoreboard tpScoreboard;

}
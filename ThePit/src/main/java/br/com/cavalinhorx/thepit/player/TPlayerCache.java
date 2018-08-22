package br.com.cavalinhorx.thepit.player;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.UUID;

/**
 * Cache simples para armazenamento das contas dos jogadores online.
 */
public class TPlayerCache {

    private Map<UUID, TPlayer> pojo;

    private static TPlayerCache instance;

    private TPlayerCache() {
        pojo = Maps.newHashMap();
    }

    /**
     * Adiciona o jogador na lista.
     * @param uuid = UUID único do jogador.
     * @param tPlayer = Objeto do jogador.
     */
    public void add(UUID uuid, TPlayer tPlayer) {
        pojo.put(uuid, tPlayer);
    }

    /**
     * Remove o jogador da lista.
     * @param uuid = UUID único do jogador.
     */
    public void remove(UUID uuid) {
        if (pojo.containsKey(uuid))
            pojo.remove(uuid);
    }

    /**
     * Verifica se o jogador está na lista.
     * @param uuid = UUID único do jogador.
     * @return boolean
     */
    public boolean contains(UUID uuid) {
        return pojo.containsKey(uuid);
    }

    /**
     * Recupera o jogador na lista.
     * @param uuid = UUID único do jogador.
     * @return @{@link TPlayer}
     */
    public TPlayer retriever(UUID uuid) {
        return pojo.get(uuid);
    }

    /**
     * Recupera todos os jogadores da lista.
     * @return {@link Map<UUID, TPlayer>}
     */
    public Map<UUID, TPlayer> retrieverCache() {
        return pojo;
    }

    public static TPlayerCache getInstance() {
        if (instance == null)
            instance = new TPlayerCache();

        return instance;
    }
}
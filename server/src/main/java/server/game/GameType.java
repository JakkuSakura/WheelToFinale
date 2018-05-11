package server.game;

import shared.events.EventType;

import java.util.HashMap;
import java.util.Map;

public class GameType extends EventType
{
    public static final GameType GAME_BEGIN = new GameType("GAME_BEGIN");
    public static final GameType ROUND_END = new GameType("ROUND_END");
    public static final GameType EVENT_BEGIN = new GameType("EVENT_BEGIN");
    public static final GameType ROUND_BEGIN = new GameType("ROUND_BEGIN");

    public static final Map<String, GameType> EVENT_MAP = new HashMap<>();
    protected GameType(String name) {
        super(name);
        EVENT_MAP.put(name, this);
    }
}

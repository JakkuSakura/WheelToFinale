package server.events;

public class GameType extends EventType
{
    public static final GameType GAME_BEGIN = new GameType(1);
    public static final GameType ROUND_END = new GameType(2);
    public static final GameType EVENT_BEGIN = new GameType(3);
    public static final GameType ROUND_BEGIN = new GameType(4);

    protected GameType(int v) {
        super(v);
    }
}

package server.gamecenter;

public class StringCommand extends Command {
    private String cmd;

    public StringCommand(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}

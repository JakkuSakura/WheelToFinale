package client.display;
public abstract class Engine implements IGameEngine {

    protected boolean running = false;
    protected boolean pause = false;

    // 开始游戏
    public void start() {
        if (running)
            return;

        running = true;
        pause = false;

        // 启动主循环
        loop();
    }

    // 暂停游戏
    public void pause(boolean pause) {
        this.pause = pause;
    }

    // 主循环
    private void loop() {
        init(); // 初始化整个体系，框架、图形、声音等等

        while(running) {

            // 暂停游戏
            if (pause) {
                return;
            }

            update();

            render();
        }

        clear(); // 清理资源、关闭各种接口等
    }

    // 停止游戏
    public void stop() {
        running = false;
    }
}
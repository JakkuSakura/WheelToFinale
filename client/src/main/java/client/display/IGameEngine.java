package client.display;

public interface IGameEngine {
    // 初始化
    void init();
    // 主循环
    void update();
    // 渲染
    void render();
    // 清理
    void clear();
}
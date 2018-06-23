package client.display;


import client.display.event.EventMapper;
import client.input.KeyEvent;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import base.events.Event;
import base.reactor.Chain;
import base.reactor.EventHandler;
import base.reactor.Reactor;


public abstract class Picker extends BaseAppState {

    protected SimpleApplication sapp;
    protected Camera camera;
    private Reactor reactor;

    @Override
    protected void initialize(Application app) {
        sapp = (SimpleApplication) app;
        camera = app.getCamera();
    }

    @Override
    protected void cleanup(Application app) {
    }



    /**
     * 使用射线检测，判断离摄像机最近的点。
     */
    public CollisionResults pick() {
        CollisionResults collisionResults = new CollisionResults();
        Ray ray = calcRay();

        sapp.getRootNode().collideWith(ray, collisionResults);


        return collisionResults;

    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }

    public abstract Ray calcRay();

    public void print(CollisionResults results) {
        System.out.println("碰撞结果：" + results.size());

        if (results.size() > 0) {

            // 从近到远，打印出射线途径的所有交点。
            for (int i = 0; i < results.size(); i++) {
                CollisionResult result = results.getCollision(i);

                float dist = result.getDistance();
                Vector3f point = result.getContactPoint();
                Vector3f normal = result.getContactNormal();
                Geometry geom = result.getGeometry();

                System.out.printf("序号：%d, 距离：%.2f, 物体名称：%s, 交点：%s, 交点法线：%s\n", i, dist, geom.getName(), point, normal);
            }

            // 离射线原点最近的交点
            Vector3f closest = results.getClosestCollision().getContactPoint();
            // 离射线原点最远的交点
            Vector3f farthest = results.getFarthestCollision().getContactPoint();

            System.out.printf("最近点：%s, 最远点：%s\n", closest, farthest);
        }
    }

    public void setReactor(Reactor reactor, EventMapper eventMapper) {
        this.reactor = reactor;
        reactor.addHandler(KeyEvent.class, new EventHandler() {
            @Override
            public void handler(Chain chain, Event event) {
                CollisionResults pickResult = pick();
                System.out.println(pickResult.size());
                if (pickResult.size() > 0) {
                    Geometry geom = pickResult.getClosestCollision().getGeometry();
                    eventMapper.trigger(EventMapper.Type.SELECT, geom);
                }
            }

            @Override
            public boolean check(Event event) {
                return event.convert(KeyEvent.class).map((a) -> a.getKey() == KeyEvent.Keys.LMB).orElse(false);
            }
        });
    }
}

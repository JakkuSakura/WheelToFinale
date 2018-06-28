import client.core.DependencesSolver;
import client.core.GameModuleInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDefencesSolver {
    @Test
    public void test1() {
        GameModuleInfo modA = new GameModuleInfo();
        modA.setName("Ma");
        modA.setLoadLevel(1000);

        GameModuleInfo modB = new GameModuleInfo();
        modB.setName("Mb");
        modB.addDep("Ma");
        modB.setLoadLevel(10000);

        List<GameModuleInfo> list = new ArrayList<>();
        list.add(modA);
        list.add(modB);
        DependencesSolver dependicesSolver = new DependencesSolver(list);
        List<GameModuleInfo> sorted = dependicesSolver.getSorted();
    }
}

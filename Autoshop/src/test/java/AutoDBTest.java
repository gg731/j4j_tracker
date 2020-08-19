import data.AutoDB;
import model.Engine;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AutoDBTest {

    @Test
    public void add() {
        Engine engine = new Engine("Engine");
        AutoDB.getInst().add(engine);

        assertThat(
                engine.getName(),
                is(AutoDB.getInst().findEngineById(engine.getId()).getName()));
    }
}

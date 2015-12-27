package io.senseai.dependencyinjectionapp.test;

import android.test.InstrumentationTestCase;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.senseai.dependencyinjectionapp.Mode;
import io.senseai.dependencyinjectionapp.MySqliteHelper;
import io.senseai.dependencyinjectionapp.OperatingMode;

import static org.mockito.Mockito.*;
/**
 * Created by bigern on 12/26/15.
 */
@RunWith(AndroidJUnit4.class)
public class MySqliteHelperTest extends InstrumentationTestCase{

    MySqliteHelper db;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        db = MySqliteHelper.getInstance(getInstrumentation().getTargetContext());
        assertNotNull(db);
    }

    public void testGetInstance() throws Exception {
        db = MySqliteHelper.getInstance(getInstrumentation().getTargetContext());
        assertNotNull(db);
    }

    public void testOnCreate() throws Exception {

    }

    public void testOnUpgrade() throws Exception {

    }

    public void testGetMode() throws Exception {


    }

    @Test
    public void testSetMode() throws Exception {
        OperatingMode operatingMode = new OperatingMode();
        operatingMode.setMode(Mode.OFF);
        operatingMode.setTimestamp(System.currentTimeMillis());
        operatingMode.setRand(0.2);

        db.setMode(operatingMode);

        OperatingMode outMode = db.getMode();
        assertEquals(outMode.getMode(), operatingMode.getMode());
        assertEquals(outMode.getTimestamp(), operatingMode.getTimestamp());
        assertEquals(outMode.getRand(), operatingMode.getRand());

        db.setMode(null);
        outMode = db.getMode();
        assertEquals(outMode, null);

        OperatingMode othermode = new OperatingMode();
        db.setMode(othermode);
        outMode = db.getMode();
        assertEquals(outMode.getMode(), Mode.OFF);
        assertEquals(outMode.getTimestamp(), 0);
        assertEquals(outMode.getRand(), 0.0);
    }
}
import org.junit.*;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * test class for model
 */
public class WordleModelTest {

    @Test
    public void testInitialStatusMustBeUndecided() {
       WordleModel model = new WordleModel("hello");
        assertEquals(WordleModel.Status.UNDECIDED, model.getStatus());
    }
    @Test
    public void testGetWinningWord(){
        WordleModel model = new WordleModel("hello");
        assertEquals("hello", model.getWordToGuess());
    }

    @Test
    public void testGetGuessedWord(){
        WordleModel model = new WordleModel("hello");
        model.setGuessedWord("power");
        assertEquals("power", model.getGuessedWord());
    }

    @Test
    public void testPowerAsWord(){
        WordleModel model = new WordleModel("hello");
        model.play("power");
        boolean[] y = model.getYellowBoxes();
        assertFalse(y[0]);
        assertTrue(y[1]);
        assertFalse(y[2]);
        assertTrue(y[3]);
        assertFalse(y[4]);
    }

    @Test
    public void testHelpsAsWord(){
        WordleModel model = new WordleModel("hello");
        model.play("helps");
        boolean[] y = model.getGreenBoxes();
        assertTrue(y[0]);
        assertTrue(y[1]);
        assertTrue(y[2]);
        assertFalse(y[3]);
        assertFalse(y[4]);
    }

    @Test
    public void testWin(){
        WordleModel model = new WordleModel("hello");
        model.play("helps");
        model.play("hello");
        assertEquals(model.getStatus(), WordleModel.Status.WIN);
    }

    @Test
    public void testLose(){
        WordleModel model = new WordleModel("hello");
        model.play("helps");
        model.play("power");
        model.play("model");
        model.play("plots");
        model.play("foils");
        assertEquals(model.getStatus(), WordleModel.Status.LOSE);
    }

}

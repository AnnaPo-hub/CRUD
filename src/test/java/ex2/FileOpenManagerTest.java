package ex2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenManagerTest {
    FileOpenManager man = new FileOpenManager();

    @Test
    void getName() {
        man.register("Word", "txt");
        assertEquals("Word", man.getName("txt"));
    }


    @Test
    void removeConnection() {
        man.register("Word", "txt");
        man.removeConnection("Word", "txt");
        assertNull(man.getName("txt"));
    }

    @Test
    void getAllExtensions() {
        man.register("Word", "txt");
        man.register("IDEA", "class");
        List<String> actual = man.getAllExtensions();
        List<String> expected = new ArrayList<>();
        expected.add ("class");
        expected.add ("txt");
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldGetAllAppsWithoutRepeat() {
        man.register("Excel", "xls");
        man.register("Word", "txt");
        man.register("IDEA", "class");
        man.register("Paint", "bmp");
        man.register("Paint", "jpeg");
        Set<String> actual = man.getAllApps();
        assertEquals(4, actual.size());

    }
}

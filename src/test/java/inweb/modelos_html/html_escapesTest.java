package inweb.modelos_html;

import innui.modelos.errores.oks;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emilio
 */
public class html_escapesTest {
    
    public html_escapesTest() {
    }

    /**
     * Test of poner_escape_HTML method, of class html_escapes.
     */
    @Test
    public void testPoner_escape_HTML() throws Exception {
        System.out.println("poner_escape_HTML");
        String texto = "Esta es una prueba: ª!\"·$%&/()=?¿;\\|@#~½¬<>,.-;:_'ḉÇü^`+*{}[]";
        oks ok = new oks();
        Object[] extras_array = null;
        String expResult = "Esta&nbsp;es&nbsp;una&nbsp;prueba:&nbsp;&ordf;!&quot;&dot;$%&amp;/()=?&iquest;;\\|@#~&frac12;&not;&lt;&gt;,.-;:_'ḉ&Ccedil;&uuml;^`+*{}[]";
        String result = html_escapes.poner_escape_HTML(texto, ok, extras_array);
        assertEquals(expResult, result);
    }

    /**
     * Test of quitar_escape_HTML method, of class html_escapes.
     */
    @Test
    public void testQuitar_escape_HTML() throws Exception {
        System.out.println("quitar_escape_HTML");
        String texto = "Esta&nbsp;es&nbsp;una&nbsp;prueba:&nbsp;&ordf;!&quot;&dot;$%&amp;/()=?&iquest;;\\|@#~&frac12;&not;&lt;&gt;,.-;:_'ḉ&Ccedil;&uuml;^`+*{}[]";
        oks ok = null;
        Object[] extras_array = null;
        String expResult = "Esta es una prueba: ª!\"·$%&/()=?¿;\\|@#~½¬<>,.-;:_'ḉÇü^`+*{}[]";
        String result = html_escapes.quitar_escape_HTML(texto, ok, extras_array);
        assertEquals(expResult, result);
    }
    
}

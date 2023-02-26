package inweb.modelos_html.formularios;

import innui.modelos.errores.oks;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author emilio
 */
public class procesar_plantillasTest {
    
    public procesar_plantillasTest() {
    }

    /**
     * Test of iniciar method, of class procesamiento_plantillas.
     */
    @Ignore
    public void testIniciar() throws Exception {
        System.out.println("iniciar");
        String ruta = "/re/templates/formularios/fragmentos/fragmentos_principales.html";
        oks ok = new oks();
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        boolean expResult = true;
        boolean result = instance.iniciar(ruta, ok, extras_array);
        assertEquals(expResult, result);
    }

    /**
     * Test of _leer_plantillas_fragmento method, of class procesamiento_plantillas.
     */
    @Ignore
    public void test_leer_plantillas_fragmento() throws Exception {
        System.out.println("_leer_plantillas_fragmento");
        oks ok = null;
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        boolean expResult = false;
        boolean result = instance._leer_plantillas_fragmento(ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of _encontrar_fin_de_texto_literal method, of class procesamiento_plantillas.
     */
    @Ignore
    public void test_encontrar_fin_de_texto_literal() throws Exception {
        System.out.println("_encontrar_fin_de_texto_literal");
        char fin = ' ';
        oks ok = null;
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        Integer expResult = null;
        Integer result = instance._encontrar_fin_de_texto_literal("'aqui'", fin, 0, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of _procesar_plantillas_fragmento method, of class procesamiento_plantillas.
     */
    @Ignore
    public void test_procesar_plantillas_fragmento() throws Exception {
        System.out.println("_procesar_plantillas_fragmento");
        oks ok = null;
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        boolean expResult = false;
        boolean result = instance._procesar_plantillas_fragmento(ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of _buscar_sin_texto_literal method, of class procesamiento_plantillas.
     */
    @Ignore
    public void test_buscar_sin_texto_literal() throws Exception {
        System.out.println("_buscar_sin_texto_literal");
        boolean ignorar_caso = false;
        String que_buscar = "donde";
        int inicio = 0;
        oks ok = null;
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        int expResult = -1;
        int result = instance._buscar_sin_texto_literal("Texto 'donde' buscar. ", ignorar_caso, que_buscar, inicio, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of _extraer_etiqueta method, of class procesamiento_plantillas.
     */
    @Ignore
    public void test_extraer_etiqueta() throws Exception {
        System.out.println("_extraer_etiqueta");
        String texto = "";
        oks ok = null;
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        String expResult = "";
        String result = instance._extraer_etiqueta(texto, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extraer_fragmento method, of class procesamiento_plantillas.
     */
    @Ignore
    public void testExtraer_fragmento() throws Exception {
        System.out.println("extraer_fragmento");
        String ruta = "/re/templates/formularios/fragmentos/fragmentos_principales.html";
        oks ok = new oks();
        String nombre_fragmento = "web_formularios";
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        boolean iniciar_result = instance.iniciar(ruta, ok, extras_array);
        assertEquals(true, iniciar_result);
        LinkedList<String> result = instance.extraer_fragmento(nombre_fragmento, ok, extras_array);
        assertNotNull(result);
    }

    /**
     * Test of procesar_fragmento method, of class procesamiento_plantillas.
     */
    @Test
    public void testProcesar_fragmento() throws Exception {
        System.out.println("procesar_fragmento");
        String ruta = "/re/templates/formularios/fragmentos/fragmentos_principales.html";
        oks ok = new oks();
        String nombre_fragmento = "control_textareas";
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        boolean iniciar_result = instance.iniciar(ruta, ok, extras_array);
        assertEquals(true, iniciar_result);
        LinkedList<String> extraer_fragmento_result = instance.extraer_fragmento(nombre_fragmento, ok, extras_array);
        assertNotNull(extraer_fragmento_result);
        Map<String, String> datos_mapa = new HashMap<>();
        datos_mapa.put("url_destino_tex", "/");
        datos_mapa.put("contenido_formulario_tex", "Contenido del formulario");
        datos_mapa.put("metodo_tex", "GET");
        datos_mapa.put("mensaje_de_captura_tex", "Introduzca los datos. ");
        datos_mapa.put("clave_tex", "clave");
        datos_mapa.put("valor_tex", "valor");
        datos_mapa.put("filas_tex", "");
        datos_mapa.put("columnas_tex", "");
        String result = instance.procesar_fragmento(extraer_fragmento_result, datos_mapa, ok, extras_array);
        assertNotNull(result);
    }

    /**
     * Test of _poner_escape_HTML method, of class procesamiento_plantillas.
     */
    @Ignore
    public void testPoner_escape_HTML() throws Exception {
        System.out.println("poner_escape_HTML");
        String texto = "";
        oks ok = null;
        Object[] extras_array = null;
        procesamiento_plantillas instance = new procesamiento_plantillas();
        String expResult = "";
        String result = instance._poner_escape_HTML(texto, ok, extras_array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

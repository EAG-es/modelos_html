package inweb.modelos_html.formularios;

import static inclui.formularios.control_entradas.k_entradas_tipo_texto;
import innui.modelos.errores.oks;
import static inweb.modelos_html.formularios.control_entradas.k_valores_mapa_style_texto_tex;
import static inweb.modelos_html.formularios.web_formularios.k_fragmento_control_textareas;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_valor_tex;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class control_textareas extends control_entradas {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    
    public control_textareas() {
        _control_tipo = k_entradas_tipo_texto;
        fragmento_nombre = k_fragmento_control_textareas;
    }
    /**
     * Inicia una textarea.
     * @param tipo_entrada Se ignora este parámetro
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    @Override
    public boolean iniciar(String tipo_entrada, oks ok, Object ... extras_array) throws Exception {
        _control_tipo = k_entradas_tipo_texto;
        fragmento_nombre = k_fragmento_control_textareas;
        return ok.es;
    }
    /**
     * Llena datos_mapa con las opciones posibles de ser empleadas en el archivo html con los fragmentos de plantilla
     * @param nuevos_datos_mapa
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    @Override
    public Map<String, String> _crear_valores_mapa(Map<String, String> nuevos_datos_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return null; }
            Map<String, String> nuevo_mapa = super._crear_valores_mapa(nuevos_datos_mapa, ok, extras_array);
            if (ok.es == false) { return null; }
            nuevo_mapa.put(k_valores_mapa_style_texto_tex, "width:95%;height:140px;margin-top:5px;margin-bottom:5px;margin-left:5px;margin-right:5px;");
            return nuevo_mapa;
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public boolean importar_captura(Object valor_del_objeto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            getValor().put(k_valores_mapa_valor_tex, valor_del_objeto.toString().replace("\r", ""));
        } catch (Exception e) {
            throw e;
        }
        return ok.es;        
    }

}

package inweb.modelos_html.formularios;

import innui.formularios.formularios;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import static inweb.modelos_html.formularios.control_entradas.k_valores_mapa_style_fragmento_reset_tex;
import static inweb.modelos_html.formularios.control_entradas.k_valores_mapa_style_reset_tex;
import static inweb.modelos_html.formularios.web_formularios.k_fragmento_control_redirecciones;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_style_fragmento_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_style_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_url_destino_tex;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class control_redirecciones extends control_entradas {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    
    public control_redirecciones() {
        _control_tipo = k_entradas_tipo_submit;
        fragmento_nombre = k_fragmento_control_redirecciones;
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
        _control_tipo = k_entradas_tipo_boton;
        fragmento_nombre = k_fragmento_control_redirecciones;
        return ok.es;
    }
    @Override
    public boolean _poner_atributos(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            super._poner_atributos(ok, extras_array);
            if (ok.es == false) { return false; }
            Map<String, String> valores_mapa = null;
            valores_mapa = getValor();
            valores_mapa.put(k_valores_mapa_style_tex, valores_mapa.get(k_valores_mapa_style_reset_tex));
            valores_mapa.put(k_valores_mapa_style_fragmento_tex, valores_mapa.get(k_valores_mapa_style_fragmento_reset_tex));
            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

    @Override
    public boolean poner_en_formulario(formularios formulario, String clave, Object valor, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            super.poner_en_formulario(formulario, clave, valor, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es) {
                if (this.getValor().get(k_valores_mapa_url_destino_tex) == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_valores_mapa_url_destino_tex);
                }
            }
            if (ok.es) {
                this.valor = _crear_valores_mapa(getValor(), ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

}

package inweb.modelos_html.formularios;

import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import static inweb.modelos_html.formularios.control_bucle_clave_valor.k_in_ruta;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_clave_tex;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class control_bucle_filas extends control_bucle_clave_valor {
    
    @Override
    @SuppressWarnings("unchecked")
    public Object _iniciar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return null; }
            if (bucle_datos_lista.isEmpty()) {
                return valor;
            }
            Object object = bucle_datos_lista.get(pos).getValue();
            if (object instanceof LinkedHashMap) {
                clave = bucle_datos_lista.get(pos).getKey();
                getValor().put(k_valores_mapa_clave_tex, clave);
                LinkedHashMap<String, Object> claves_valores_mapa = (LinkedHashMap<String, Object>) object;
                for (Entry<String, Object> entry : claves_valores_mapa.entrySet()) {
                    if (entry.getValue() != null) {
                        getValor().put(entry.getKey(), entry.getValue().toString());
                    } else {
                        getValor().put(entry.getKey(), null);
                    }
                }
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Se esperaba un mapa de claves-valor. "));
            }
        } catch (Exception e) {
            throw e;
        }
        return valor;
    }
    
}

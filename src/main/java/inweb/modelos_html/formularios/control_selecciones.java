package inweb.modelos_html.formularios;

import innui.formularios.formularios;
import static innui.formularios.formularios.k_fase_captura;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import static inweb.modelos_html.formularios.web_formularios.k_fragmento_control_opciones_selecciones;
import static inweb.modelos_html.formularios.web_formularios.k_fragmento_control_selecciones;
import static inweb.modelos_html.formularios.web_formularios.k_nombre_fragmento;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_contenido_tex;

/**
 *
 * @author emilio
 */
public class control_selecciones extends control_entradas {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    public static String k_control_selecciones_opciones_mapa = "opciones_mapa_control_selecciones";
    public Map<String, Object> control_selecciones_mapa;
    public control_bucle_clave_valor _control_bucle_texto = new control_bucle_clave_valor();
    public web_formularios _web_formulario = new web_formularios();
    
    public control_selecciones() {
        fragmento_nombre = k_fragmento_control_selecciones;
    }    
    /**
     * Ignorar el parámetro tipo_control
     * @param tipo_control
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    @Override
    public boolean iniciar(String tipo_control, oks ok, Object ... extras_array) throws Exception {
        return ok.es; 
    }
    /**
     * Carga el control con el contenido de un archivo de propiedades
     * @param ruta
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean cargar_control_con_propiedades(String ruta, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            control_selecciones_mapa = inclui.formularios.control_selecciones.cargar_propiedades(ruta, ok, extras_array);
            _procesar_selecciones_mapa(ok, extras_array);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Carga el control con el contenido de un archivo de propiedades
     * @param selecciones_mapa
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean cargar_selecciones(Map<String, Object> selecciones_mapa, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            control_selecciones_mapa = selecciones_mapa;
            _procesar_selecciones_mapa(ok, extras_array);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Prepara el mapa de selecciones para ser presentado entrada a entrada
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean _procesar_selecciones_mapa(oks ok, Object ... extras_array) throws Exception {
        try {
            List<Entry<String, Object>> bucle_datos_lista = new ArrayList<>();
            Entry<String, Object> entrada_lista;
            Object object;
            for(Entry<String, Object> entry: control_selecciones_mapa.entrySet()) {
                object = entry.getValue();
                if (object == null) {
                    object = "";
                }
                entrada_lista = new SimpleEntry<>(entry.getKey(), object.toString());
                bucle_datos_lista.add(entrada_lista);
            }
            _control_bucle_texto.poner_bucle_lista(bucle_datos_lista, ok, extras_array);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

    @Override
    public Object _iniciar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            Object object;
            if (modo_operacion.equals(k_fase_captura)) {
                _web_formulario.setProcesa_plantilla(_procesamiento_plantilla);
                Map<String, Object> bucle_opciones_mapa = new HashMap<>();
                bucle_opciones_mapa.put(k_nombre_fragmento, k_fragmento_control_opciones_selecciones);
                _control_bucle_texto.poner_en_formulario(_web_formulario, null, null, null, bucle_opciones_mapa, ok, extras_array);
                if (ok.es == false) { return null; }
                object = _control_bucle_texto.procesar(modo_operacion, valor, ok, extras_array);
                if (ok.es == false) { return null; }
                String texto_html = object.toString();
                getValor().put(k_valores_mapa_contenido_tex, texto_html);
            }
        } catch (Exception e) {
            throw e;
        }
        return valor;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean poner_en_formulario(formularios formulario, String clave, Object valor, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            super.poner_en_formulario(formulario, clave, valor, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es) {
                if (control_selecciones_mapa == null) {
                    control_selecciones_mapa = (Map<String, Object>) this.opciones_mapa.get(k_control_selecciones_opciones_mapa);
                    if (control_selecciones_mapa == null) {
                        in = ResourceBundles.getBundle(k_in_ruta);
                        ok.setTxt(tr.in(in, "No se ha cargado el control selecciones con la opcion: ") + k_control_selecciones_opciones_mapa);
                    } else {
                        _procesar_selecciones_mapa(ok, extras_array);
                        this.opciones_mapa.remove(k_control_selecciones_opciones_mapa);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

    @Override
    public boolean importar_captura(Object valor_del_objeto, oks ok, Object ... extras_array) throws Exception {
        return _control_bucle_texto.importar_captura(valor_del_objeto, ok, extras_array);        
    }
    
    @Override
    public boolean _ser_valor_vacio(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return false;
    } 
}

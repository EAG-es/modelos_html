package inweb.modelos_html.formularios;

import innui.formularios.formularios;
import static innui.formularios.formularios.k_fase_captura;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import static inweb.modelos_html.formularios.web_formularios.k_nombre_fragmento;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_contenido_tex;

/**
 *
 * @author emilio
 */
public class control_tablas extends control_entradas {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    public static String k_control_tablas_opciones_mapa_lista = "control_tablas_opciones_mapas_lista";
    public static String k_control_tablas_opciones_mapa_tabla_fragmento = "control_tablas_opciones_mapa_tabla_fragmento";
    public static String k_control_tablas_opciones_mapa_tabla_fila_fragmento = "control_tablas_opciones_mapa_tabla_fila_fragmento";
    public LinkedList<LinkedHashMap<String, Object>> control_filas_lista;
    public control_bucle_filas _control_bucle_filas = new control_bucle_filas();
    public web_formularios _web_formulario = new web_formularios();
    public String fragmento_fila_nombre = null;
    
    public control_tablas() {
        _control_tipo = k_entradas_tipo_texto;
    }    
    /**
     * Entrega una representación de tabla, vacía, válida para el control.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public LinkedList<LinkedHashMap<String, Object>> crear_tabla_vacia(oks ok, Object ... extras_array) throws Exception {
        return new LinkedList<>();
    }
    /**
     * Carga el control con el contenido de un archivo de propiedades
     * @param filas_lista
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean cargar_tabla(LinkedList<LinkedHashMap<String, Object>> filas_lista, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            control_filas_lista = filas_lista;
            _procesar_filas_lista(ok, extras_array);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    public boolean _procesar_filas_lista(oks ok, Object ... extras_array) throws Exception {
        try {
            List<Entry<String, Object>> bucle_datos_lista = new ArrayList<>();
            Entry<String, Object> entrada_lista;
            int i = 0;
            for(Map<String, Object> datos_mapa: control_filas_lista) {
                entrada_lista = new SimpleEntry<>(String.valueOf(i), datos_mapa);
                bucle_datos_lista.add(entrada_lista);
                i = i + 1;
            }
            _control_bucle_filas.poner_bucle_lista(bucle_datos_lista, ok, extras_array);
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
                bucle_opciones_mapa.put(k_nombre_fragmento, fragmento_fila_nombre);
                _control_bucle_filas.poner_en_formulario(_web_formulario, null, null, null, bucle_opciones_mapa, ok, extras_array);
                if (ok.es == false) { return null; }
                object = _control_bucle_filas.procesar(modo_operacion, valor, ok, extras_array);
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
                if (control_filas_lista == null) {
                    control_filas_lista = (LinkedList<LinkedHashMap<String, Object>>) this.opciones_mapa.get(k_control_tablas_opciones_mapa_lista);
                    if (control_filas_lista == null) {
                        in = ResourceBundles.getBundle(k_in_ruta);
                        ok.setTxt(tr.in(in, "No se ha cargado el control tablas con la opcion: ") + k_control_tablas_opciones_mapa_lista);
                    } else {
                        _procesar_filas_lista(ok, extras_array);
                        this.opciones_mapa.remove(k_control_tablas_opciones_mapa_lista);
                    }
                }
                fragmento_nombre = (String) this.opciones_mapa.get(k_control_tablas_opciones_mapa_tabla_fragmento);
                if (fragmento_nombre == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(ok.getTxt(), tr.in(in, "No se ha cargado el control tablas con la opcion: ") + k_control_tablas_opciones_mapa_tabla_fragmento);
                }
                fragmento_fila_nombre = (String) this.opciones_mapa.get(k_control_tablas_opciones_mapa_tabla_fila_fragmento);
                if (fragmento_fila_nombre == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(ok.getTxt(), tr.in(in, "No se ha cargado el control tablas con la opcion: ") + k_control_tablas_opciones_mapa_tabla_fila_fragmento);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

    @Override
    public boolean importar_captura(Object valor_del_objeto, oks ok, Object ... extras_array) throws Exception {
        return _control_bucle_filas.importar_captura(valor_del_objeto, ok, extras_array);        
    }
    /**
     * Recupera una fila
     * @param fila_num
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public LinkedHashMap<String, Object> leer_fila(int fila_num, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        LinkedHashMap<String, Object> retorno_mapa = null;
        try {
            int i = 0;
            for (LinkedHashMap<String, Object> columnas_mapa : control_filas_lista) {
                if (i == fila_num) {
                    return columnas_mapa;
                }
                i = i + 1;
            }
        } catch (Exception e) {
            throw e;
        }
        return retorno_mapa;
    }
}

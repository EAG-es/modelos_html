package inweb.modelos_html.formularios;

import innui.formularios.formularios;
import innui.formularios.controles;
import innui.modelos.errores.oks;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author emilio
 */
public class web_formularios extends formularios {   
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    public static String k_web_formularios_procesamiento_plantilla = "web_formularios_procesa_plantilla";
    public static String k_fragmento_web_formularios = "web_formularios";
    public static String k_fragmento_control_entradas = "control_entradas";
    public static String k_fragmento_control_textareas = "control_textareas";
    public static String k_fragmento_control_selecciones = "control_selecciones";
    public static String k_fragmento_control_opciones_selecciones = "control_opciones_selecciones";
    public static String k_fragmento_mensaje_linea = "mensaje_linea";
    public static String k_fragmento_mensaje_error_linea = "mensaje_error_linea";
    public static String k_datos_mapa_url_destino_tex = "url_destino_tex";
    public static String k_datos_mapa_metodo_tex = "metodo_tex";
    public static String k_datos_mapa_contenido_formulario_tex = "contenido_formulario_tex";
    public static String k_datos_mapa_atributos_tex = "atributos_tex";
    public static String k_datos_mapa_control_entradas = "control_entradas";
    public static String k_datos_mapa_mensaje_de_captura_tex = "mensaje_de_captura_tex";
    public static String k_datos_mapa_control_tipo_tex = "control_tipo_tex";
    public static String k_datos_mapa_clave_tex = "clave_tex";
    public static String k_datos_mapa_valor_tex = "valor_tex";
    public static String k_datos_mapa_mensaje_error_tex = "mensaje_error_tex";
    public static String k_datos_mapa_mensaje_tex = "mensaje_tex";
    public procesamiento_plantillas procesamiento_plantilla = new procesamiento_plantillas();
    public String contenido_formulario_html = "";

    public procesamiento_plantillas getProcesa_plantilla() {
        return procesamiento_plantilla;
    }

    public void setProcesa_plantilla(procesamiento_plantillas procesa_plantilla) {
        this.procesamiento_plantilla = procesa_plantilla;
    }

    public String getFormulario_html() {
        return contenido_formulario_html;
    }
    
    public boolean iniciar(String ruta_plantilla, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            procesamiento_plantilla.iniciar(ruta_plantilla, ok);
            if (ok.es == false) { return false; }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }    
    
    @Override
    public boolean _poner_control(controles control, oks ok, Object ... extras_array) throws Exception {
        super._poner_control(control, ok, extras_array);
        if (ok.es) {
            if (control.opciones_mapa == null) {
                control.opciones_mapa = new HashMap<>();
            }
            control.opciones_mapa.put(k_web_formularios_procesamiento_plantilla, procesamiento_plantilla);
        }
        return ok.es;
    }    
    /**
     * Inicia el proceso de captura y procesamiento de la información
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    @Override
    public boolean _iniciar_formulario(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_captura)) {
                super._iniciar_formulario(modo_operacion, ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Inicia el proceso de captura y procesamiento de la información
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    @Override
    public boolean _terminar_formulario(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Map<String, String> datos_mapa = null;
            List<String> fragmentos_lista = null;
            if (modo_operacion.equals(k_fase_captura)) {
                super._terminar_formulario(modo_operacion, ok, extras_array);
                if (ok.es == false) { return false; }
                fragmentos_lista = procesamiento_plantilla.extraer_fragmento(k_fragmento_web_formularios, ok, extras_array);
                if (ok.es == false) { return false; }
                if (datos_mapa == null) {
                    datos_mapa = new HashMap<>();
                }
                iniciar_datos_mapa(datos_mapa, ok);
                datos_mapa.put(k_datos_mapa_contenido_formulario_tex, contenido_formulario_html);
                contenido_formulario_html = procesamiento_plantilla.procesar_fragmento(fragmentos_lista, datos_mapa, ok, extras_array);
                if (ok.es == false) { return false; }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Llena datos_mapa con las opciones posibles de ser empleadas en el archivo htm con los fragmentos de plantilla
     * @param datos_mapa
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean iniciar_datos_mapa(Map<String, String> datos_mapa, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            datos_mapa.put(k_datos_mapa_url_destino_tex, "");
            datos_mapa.put(k_datos_mapa_metodo_tex, "GET");
            datos_mapa.put(k_datos_mapa_contenido_formulario_tex, "");
            datos_mapa.put(k_datos_mapa_atributos_tex, "");
            datos_mapa.put(k_datos_mapa_control_entradas, "");
            datos_mapa.put(k_datos_mapa_mensaje_de_captura_tex, "");
            datos_mapa.put(k_datos_mapa_control_tipo_tex, "");
            datos_mapa.put(k_datos_mapa_clave_tex, "");
            datos_mapa.put(k_datos_mapa_valor_tex, "");
            datos_mapa.put(k_datos_mapa_mensaje_error_tex, "");
            datos_mapa.put(k_datos_mapa_mensaje_tex, "");
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Realiza los procesos posteriores a la captura de los datos
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    @Override
    public boolean procesar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            while (true) {
                ok.iniciar();
                super.procesar(modo_operacion, ok, extras_array);
                if (ok.es) {
                    if (_es_repetir) {
                        continue;
                    }
                }
                break;
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }    
        
}

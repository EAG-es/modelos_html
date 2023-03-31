package inweb.modelos_html.formularios;

import innui.formularios.formularios;
import innui.formularios.controles;
import static innui.formularios.controles.k_opciones_mapa_no_requerido;
import innui.modelos.errores.oks;
import static inweb.modelos_html.formularios.control_entradas.k_atributo_required;
import static inweb.modelos_html.formularios.procesamiento_plantillas.k_error_fragmento_no_encontrado;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author emilio
 */
public class web_formularios extends formularios {   
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    public static String k_web_formularios_procesamiento_plantilla = "web_formularios_procesa_plantilla";
    public static String k_fragmento_web_formularios = "web_formularios";
    public static String k_fragmento_web_formularios_sin_form = "web_formularios_sin_form";
    public static String k_fragmento_control_entradas = "control_entradas";
    public static String k_fragmento_control_redirecciones = "control_redirecciones";
    public static String k_fragmento_control_textareas = "control_textareas";
    public static String k_fragmento_control_selecciones = "control_selecciones";
    public static String k_fragmento_control_opciones_selecciones = "control_opciones_selecciones";
    public static String k_fragmento_mensaje_linea = "mensaje_linea";
    public static String k_fragmento_mensaje_error_linea = "mensaje_error_linea";
    public static String k_valores_mapa_url_destino_tex = "url_destino_tex";
    public static String k_valores_mapa_metodo_tex = "metodo_tex";
    public static String k_valores_mapa_contenido_formulario_tex = "contenido_formulario_tex";
    public static String k_valores_mapa_style_tex = "style_tex";
    public static String k_valores_mapa_style_mensaje_error_tex = "style_mensaje_error_tex";
    public static String k_valores_mapa_style_fragmento_tex = "style_fragmento_tex";
    public static String k_valores_mapa_style_mensaje_de_captura_tex = "style_mensaje_de_captura_tex";
    public static String k_valores_mapa_atributos_tex = "atributos_tex";
    public static String k_valores_mapa_atributos_error_tex = "atributos_error_tex";
    public static String k_valores_mapa_control_entradas = "control_entradas";
    public static String k_valores_mapa_mensaje_de_captura_tex = "mensaje_de_captura_tex";
    public static String k_valores_mapa_control_tipo_tex = "control_tipo_tex";
    public static String k_valores_mapa_id_tex = "id_tex";
    public static String k_valores_mapa_clave_tex = "clave_tex";
    public static String k_valores_mapa_valor_tex = "valor_tex";
    public static String k_valores_mapa_mensaje_error_tex = "mensaje_error_tex";
    public static String k_valores_mapa_mensaje_tex = "mensaje_tex";
    public static String k_valores_mapa_contenido_input_tex = "contenido_input_tex";
    public static String k_valores_mapa_contenido_tex = "contenido_tex";
    public static String k_valores_mapa_lenguaje_tex = "lenguaje_tex";
    public static String k_atributo_checked = " checked ";
    public static String k_atributo_selected = " selected ";
    public static String k_nombre_fragmento = "nombre_fragmento";
    public static String k_nueva_linea_html = "<br>";
    public procesamiento_plantillas procesamiento_plantilla = new procesamiento_plantillas();
    public String contenido_formulario_html = "";
    public String fragmento_nombre = k_fragmento_web_formularios;
    public Map<String, String> valores_mapa = null;
    public Map<String, Object> opciones_mapa = null;

    public web_formularios() {
        fragmento_nombre = k_fragmento_web_formularios;
    }
    
    public web_formularios(String fragmento_nombre) {
        this.fragmento_nombre = fragmento_nombre;
    }
    
    public String getFragmento_nombre() {
        return fragmento_nombre;
    }

    public void setFragmento_nombre(String fragmento_nombre) {
        this.fragmento_nombre = fragmento_nombre;
    }
    
    public procesamiento_plantillas getProcesa_plantilla() {
        return procesamiento_plantilla;
    }

    public void setProcesa_plantilla(procesamiento_plantillas procesa_plantilla) {
        this.procesamiento_plantilla = procesa_plantilla;
    }

    public String getContenido_formulario_html() {
        return contenido_formulario_html;
    }

    public Map<String, String> getValores_mapa() {
        return valores_mapa;
    }

    @Override
    public boolean escribir_linea_error(String mensaje, oks ok, Object ... extras_array) {
        try {
            if (ok.es == false) { return false; }
            String texto;
            texto = valores_mapa.get(k_valores_mapa_mensaje_error_tex);
            if (texto.isBlank() == false) {
                texto = texto + k_nueva_linea_html + mensaje;
            } else {
                texto = mensaje;
            }
            valores_mapa.put(k_valores_mapa_mensaje_error_tex, texto);
            super.escribir_linea_error(mensaje, ok, extras_array);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    @Override
    public boolean escribir_error(String mensaje, oks ok, Object ... extras_array) {
        return escribir_linea_error(mensaje, ok, extras_array);
    }
    /**
     * Cambia el fragmento por defecto para que sea uno sin la etiqueta form.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean configurar_sin_form(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            fragmento_nombre = k_fragmento_web_formularios_sin_form;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Prepara las plantillas y los valores potenciales que poner en ellas
     * @param ruta_plantilla
     * @param valores_mapa
     * @param opciones_mapa
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean iniciar(String ruta_plantilla, Map<String, String> valores_mapa, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            contenido_formulario_html = "";
            procesamiento_plantilla.iniciar(ruta_plantilla, ok);
            if (ok.es == false) { return false; }
            _crear_valores_mapa(valores_mapa, ok, extras_array);
            if (ok.es == false) { return false; }
            if (opciones_mapa != null) {
                if (this.opciones_mapa == null) {
                    this.opciones_mapa = new LinkedHashMap<>();
                }
                this.opciones_mapa.putAll(opciones_mapa);
            }
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
     * Inicia el proceso de valor_de_captura y procesamiento de la información
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
            contenido_formulario_html = "";
            // Limpiar el error
            if (valores_mapa != null) {
                if (modo_operacion.equals(k_fase_procesamiento)) {
                    valores_mapa.put(k_valores_mapa_mensaje_error_tex ,"");
                }
            }
            super._iniciar_formulario(modo_operacion, ok, extras_array);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Inicia el proceso de valor_de_captura y procesamiento de la información
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
                fragmentos_lista = procesamiento_plantilla.extraer_fragmento(fragmento_nombre, ok, extras_array);
                if (ok.id.equals(k_error_fragmento_no_encontrado) == false) {
                    if (ok.es == false) { return false; }
                    String clave_entry;
                    Object valor_entry;
                    String atributos_tex = "";
                    if (opciones_mapa != null) {
                        for (Map.Entry<String, Object> entry: opciones_mapa.entrySet()) {
                            clave_entry = entry.getKey();
                            valor_entry = entry.getValue();
                            if (clave_entry.equals(k_opciones_mapa_no_requerido)) {
                                continue;
                            }
                            if (valor_entry == null) {
                                valor_entry = "";
                            }
                            if (valor_entry instanceof String) {
                                if (datos_mapa.containsKey(clave_entry)) {
                                    datos_mapa.put(clave_entry, valor_entry.toString());
                                } else {
                                    atributos_tex = atributos_tex 
                                    + " " + clave_entry 
                                    + "=\"" + valor_entry.toString() + "\"";
                                }
                            }
                        }
                        if (this.opciones_mapa.containsKey(k_opciones_mapa_no_requerido) == false) {
                            atributos_tex = atributos_tex 
                            + " " + k_atributo_required 
                            + "=\"true\"";
                        }
                    }
                    if (valores_mapa != null) {
                        valores_mapa.put(k_valores_mapa_atributos_tex, atributos_tex);
                        valores_mapa.put(k_valores_mapa_contenido_formulario_tex, contenido_formulario_html);
                    }
                    contenido_formulario_html = procesamiento_plantilla.procesar_fragmento(fragmentos_lista, valores_mapa, ok, extras_array);
                    if (ok.es == false) { return false; }
                } else {
                    ok.iniciar();
                }
            }
        } catch (Exception e) {
            throw e;
        }
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
    public boolean _crear_valores_mapa(Map<String, String> nuevos_datos_mapa, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (valores_mapa == null) {
                valores_mapa = new HashMap<>();
                valores_mapa.put(k_valores_mapa_url_destino_tex, "");
                valores_mapa.put(k_valores_mapa_metodo_tex, "GET");
                valores_mapa.put(k_valores_mapa_contenido_formulario_tex, "");
                valores_mapa.put(k_valores_mapa_atributos_tex, "");
                valores_mapa.put(k_valores_mapa_atributos_error_tex, "");
                valores_mapa.put(k_valores_mapa_control_entradas, "");
                valores_mapa.put(k_valores_mapa_mensaje_de_captura_tex, "");
                valores_mapa.put(k_valores_mapa_control_tipo_tex, "");
                valores_mapa.put(k_valores_mapa_id_tex, "");
                valores_mapa.put(k_valores_mapa_clave_tex, "");
                valores_mapa.put(k_valores_mapa_valor_tex, "");
                valores_mapa.put(k_valores_mapa_mensaje_error_tex, "");
                valores_mapa.put(k_valores_mapa_mensaje_tex, "");
                valores_mapa.put(k_valores_mapa_style_tex, "");
                valores_mapa.put(k_valores_mapa_style_mensaje_error_tex, "color:red;");
                valores_mapa.put(k_valores_mapa_style_fragmento_tex, "");
                valores_mapa.put(k_valores_mapa_style_mensaje_de_captura_tex, "");
                valores_mapa.put(k_valores_mapa_contenido_input_tex, "");
                valores_mapa.put(k_valores_mapa_contenido_tex, "");
                valores_mapa.put(k_valores_mapa_lenguaje_tex, "es");
            }
            if (nuevos_datos_mapa != null) {
                valores_mapa.putAll(nuevos_datos_mapa);
            }
            return ok.es;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Realiza los procesos posteriores a la valor_de_captura de los datos
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
            super.procesar(modo_operacion, ok, extras_array);
            if (ok.es == false) {
                if (modo_operacion.equals(k_fase_procesamiento)) {
                    valores_mapa.put(k_valores_mapa_mensaje_error_tex ,ok.getTxt());
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }    
    /**
     * Añade o modifica un valor dentro de uno (o más de un control) identificado por su nombre
     * @param nombre_control
     * @param clave
     * @param valor
     * @param ok
     * @param extras_array
     * @return El mapa de valores de texto que representan los valores del control que ha sido modificado
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> importar_valor_en_controles(String nombre_control, String clave, String valor, oks ok, Object ... extras_array) throws Exception {
        Map<String, String> retorno_mapa = null;
        try {
            if (ok.es == false) { return null; }
            List<Map.Entry<String, Object>> controles_lista = exportar_valores(nombre_control, ok, extras_array);
            for (Entry<String, Object> entry : controles_lista) {
                retorno_mapa = (Map<String, String>) entry.getValue();
                retorno_mapa.put(clave, valor);
            }
        } catch (Exception e) {
            throw e;
        }
        return retorno_mapa;
    }    
}

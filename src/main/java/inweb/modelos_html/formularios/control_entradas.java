package inweb.modelos_html.formularios;

import static inclui.formularios.control_entradas.k_entradas_clui_lectura;
import static inclui.formularios.control_entradas.k_entradas_tipo_boton;
import static inclui.formularios.control_entradas.k_entradas_tipo_checkbox;
import static inclui.formularios.control_entradas.k_entradas_tipo_con_imagen;
import static inclui.formularios.control_entradas.k_entradas_tipo_hidden;
import static inclui.formularios.control_entradas.k_entradas_tipo_radio;
import static inclui.formularios.control_entradas.k_entradas_tipo_reset;
import static inclui.formularios.control_entradas.k_entradas_tipo_submit;
import static inclui.formularios.control_entradas.k_entradas_tipo_texto;
import innui.formularios.formularios;
import static innui.formularios.formularios.k_fase_captura;
import static innui.formularios.formularios.k_fase_procesamiento;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import static inweb.modelos_html.formularios.web_formularios.k_atributo_checked;
import static inweb.modelos_html.formularios.web_formularios.k_fragmento_control_entradas;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_atributos_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_clave_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_contenido_input_tex;
import java.util.Map;
import java.util.ResourceBundle;
import static inweb.modelos_html.formularios.web_formularios.k_web_formularios_procesamiento_plantilla;
import java.util.List;
import java.util.Map.Entry;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_control_tipo_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_id_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_mensaje_de_captura_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_mensaje_error_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_style_fragmento_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_style_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_valor_tex;
import java.util.HashMap;

/**
 *
 * @author emilio
 */
public class control_entradas extends inclui.formularios.control_entradas {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    public static String k_valores_mapa_style_texto_tex = "style_texto_tex";
    public static String k_valores_mapa_style_submit_tex = "style_submit_tex";
    public static String k_valores_mapa_style_reset_tex = "style_reset_tex";
    public static String k_valores_mapa_style_fragmento_texto_tex = "style_fragmento_texto_tex";
    public static String k_valores_mapa_style_fragmento_submit_tex = "style_fragmento_submit_tex";
    public static String k_valores_mapa_style_fragmento_reset_tex = "style_fragmento_reset_tex";
    public static String k_valores_mapa_style_fragmento_cancelar_tex = "style_fragmento_reset_tex";
    public procesamiento_plantillas _procesamiento_plantilla = null;
    public static String k_atributo_required = " required = \"true\"";
    public String fragmento_nombre = k_fragmento_control_entradas;

    public String getFragmento_nombre() {
        return fragmento_nombre;
    }

    public void setFragmento_nombre(String fragmento_nombre) {
        this.fragmento_nombre = fragmento_nombre;
    }        
    /**
     * Procesa la información capturada
     * @param modo_operacion
     * @param objeto_a_capturar
     * @param ok
     * @param extras_array
     * @return El objeto captuado, o null si hay error.
     * @throws Exception 
     */
    @Override
    public Object _capturar(String modo_operacion, Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        String control_html = null;
        try {
            if (ok.es == false) { return null; }
            List<String> fragmentos_lista;
            if (modo_operacion.equals(k_fase_captura)) {
                fragmentos_lista = _procesamiento_plantilla.extraer_fragmento(fragmento_nombre, ok, extras_array);
                if (ok.es == false) { return false; }
                _poner_atributos(ok, extras_array);
                if (ok.es == false) { return false; }
                control_html = _procesamiento_plantilla.procesar_fragmento(fragmentos_lista, getValor(), ok, extras_array);
            } else {
                control_html = getValor().get(k_valores_mapa_valor_tex);
            }
        } catch (Exception e) {
            throw e;
        }
        return control_html;
    }
    /**
     * Establece los atributos de la etiqueta del control para capturar
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean _poner_atributos(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Map<String, String> valores_mapa = null;
            valores_mapa = getValor();
            String clave_entry;
            Object valor_entry;
            String atributos_tex = valores_mapa.get(k_valores_mapa_atributos_tex);
            if (atributos_tex == null) {
                atributos_tex = "";
            }
            if (opciones_mapa != null) {
                for (Entry<String, Object> entry: opciones_mapa.entrySet()) {
                    clave_entry = entry.getKey();
                    valor_entry = entry.getValue();
                    if (clave_entry.equals(k_opciones_mapa_no_requerido)
                     || clave_entry.equals(k_web_formularios_procesamiento_plantilla)) {
                        continue;
                    }
                    if (valor_entry == null) {
                        valor_entry = "";
                    }
                    atributos_tex = atributos_tex 
                    + " " + clave_entry 
                    + "=\"" + valor_entry.toString() + "\"";
                }
                if (this.opciones_mapa.containsKey(k_opciones_mapa_no_requerido) == false) {
                    if (atributos_tex.contains(k_atributo_required) == false) {
                        atributos_tex = atributos_tex 
                        + k_atributo_required;
                    }
                } else {
                    if (atributos_tex.contains(k_atributo_required)) {
                        atributos_tex = atributos_tex.replace(k_atributo_required, "");
                    }
                }
            }
            valores_mapa.put(k_valores_mapa_atributos_tex, atributos_tex);
            if(_control_tipo.equals(k_entradas_tipo_radio)) {
                String valor_tex = valores_mapa.get(k_valores_mapa_valor_tex);
                valores_mapa.put(k_valores_mapa_id_tex, clave + "-" + valor_tex);
            } else {
                valores_mapa.put(k_valores_mapa_id_tex, clave);
            }
            valores_mapa.put(k_valores_mapa_clave_tex, clave);
            if (_control_tipo == null) {
                _control_tipo = "";
            }
            if (_control_tipo.equals(k_entradas_tipo_hidden)) {
                valores_mapa.put(k_valores_mapa_mensaje_de_captura_tex, "");                                                
            } else if (_control_tipo.equals(k_entradas_tipo_submit)
             || _control_tipo.equals(k_entradas_tipo_reset)
             || _control_tipo.equals(k_entradas_tipo_boton)
             || _control_tipo.equals(k_entradas_tipo_con_imagen)) {
                valores_mapa.put(k_valores_mapa_valor_tex, mensaje_de_captura);                        
            } else if (_control_tipo.equals(k_entradas_tipo_checkbox)
             || _control_tipo.equals(k_entradas_tipo_radio)) {
                valores_mapa.put(k_valores_mapa_contenido_input_tex, mensaje_de_captura);
            } else {
                valores_mapa.put(k_valores_mapa_mensaje_de_captura_tex, mensaje_de_captura);                        
            }
            if (_control_tipo.equals(k_entradas_tipo_reset)) {
                valores_mapa.put(k_valores_mapa_style_tex, valores_mapa.get(k_valores_mapa_style_reset_tex));
                valores_mapa.put(k_valores_mapa_style_fragmento_tex, valores_mapa.get(k_valores_mapa_style_fragmento_reset_tex));
            } else if (_control_tipo.equals(k_entradas_tipo_submit)) {
                valores_mapa.put(k_valores_mapa_style_tex, valores_mapa.get(k_valores_mapa_style_submit_tex));
                valores_mapa.put(k_valores_mapa_style_fragmento_tex, valores_mapa.get(k_valores_mapa_style_fragmento_submit_tex));
            } else if (_control_tipo.equals(k_entradas_tipo_texto)) {
                valores_mapa.put(k_valores_mapa_style_tex, valores_mapa.get(k_valores_mapa_style_texto_tex)); 
                valores_mapa.put(k_valores_mapa_style_fragmento_tex, valores_mapa.get(k_valores_mapa_style_fragmento_texto_tex));
            }
            valores_mapa.put(k_valores_mapa_control_tipo_tex, _control_tipo);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Presenta información antes de capturar información
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    @Override
    public boolean _presentar(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

    @Override
    public Object _terminar(String modo_operacion, Object objeto_a_terminar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            if (modo_operacion.equals(k_fase_captura)) {
                if (_formulario instanceof web_formularios web_formulario) {
                    if (objeto_a_terminar != null) {
                        web_formulario.contenido_formulario_html = web_formulario.contenido_formulario_html
                        + objeto_a_terminar.toString();
                    }
                }
            } else {
                objeto_a_terminar = valor;
            }
        } catch (Exception e) {
            throw e;
        }
        return objeto_a_terminar;
    }    
    public boolean modificar_valor(Map<String, String> valores_mapa, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            getValor().putAll(valores_mapa);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Conecta un control con un formulario
     * @param formulario Formulario donde incorporar el control (por orden de inclusión)
     * @param clave Nombre identificador del formulario (en un grupo exclusivo el clave puede repetirse)
     * @param valores_mapa Mapa con los valores iniciales para los atributos HTML (puede ser null)
     * @param mensaje_de_captura Mensaje que presentar en la valor_de_captura
     * @param opciones_mapa Opciones que va a utilizar el control en algunas de sus fases (puede ser null)
     * @param ok
     * @param extras_array
     * @return true si no hay errores
     * @throws Exception 
     */
    public boolean poner_en_formulario(formularios formulario, String clave, Map<String, String> valores_mapa, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        return poner_en_formulario(formulario, clave, (Object) valores_mapa, mensaje_de_captura, opciones_mapa, ok, extras_array);
    }
    /**
     * Conecta un control con un formulario
     * @param formulario Formulario donde incorporar el control (por orden de inclusión)
     * @param clave Nombre identificador del formulario (en un grupo exclusivo el clave puede repetirse)
     * @param valor Valor inicial (puede ser null)
     * @param mensaje_de_captura Mensaje que presentar en la valor_de_captura
     * @param opciones_mapa Opciones que va a utilizar el control en algunas de sus fases (puede ser null)
     * @param ok
     * @param extras_array
     * @return true si no hay errores
     * @throws Exception 
     */
    @Override
    public boolean poner_en_formulario(formularios formulario, String clave, Object valor, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            if (valor != null) {
                if ((valor instanceof Map) == false) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Se espera que el valor del control sea: Map<String, String> "));
                }
            } else {
                if (this.valor == null) {
                    this.valor = new HashMap<>();
                }
            }
            if (ok.es == false) { return false; }
            super.poner_en_formulario(formulario, clave, valor, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.id.equals(k_entradas_clui_lectura)) {
                ok.iniciar();
            }
            if (ok.es) {
                _procesamiento_plantilla = (procesamiento_plantillas) this.opciones_mapa.get(k_web_formularios_procesamiento_plantilla);
                if (_procesamiento_plantilla == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_web_formularios_procesamiento_plantilla);
                } else {
                    this.opciones_mapa.remove(k_web_formularios_procesamiento_plantilla);
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
    /**
     * Llena datos_mapa con las opciones posibles de ser empleadas en el archivo html con los fragmentos de plantilla
     * @param nuevos_datos_mapa
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public Map<String, String> _crear_valores_mapa(Map<String, String> nuevos_datos_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return null; }
            Map<String, String> nuevo_mapa = null;
            if (_formulario instanceof web_formularios web_formulario) {
                web_formulario._crear_valores_mapa(null, ok);
                if (ok.es == false) { return null; }
                nuevo_mapa = new HashMap<>();
                nuevo_mapa.putAll(web_formulario.valores_mapa);
                nuevo_mapa.put(k_valores_mapa_style_texto_tex, "width:100%;height:28px;margin-top:5px;margin-bottom:5px;");
                nuevo_mapa.put(k_valores_mapa_style_submit_tex, "width:100%;height:28px;margin-top:5px;margin-bottom:5px;background-color: darkseagreen;");
                nuevo_mapa.put(k_valores_mapa_style_reset_tex, "width:100%;height:28px;margin-top:5px;margin-bottom:5px;background-color: darksalmon;");
                nuevo_mapa.put(k_valores_mapa_style_tex, "height:28px;margin-top:5px;margin-bottom:5px;");
                if (nuevos_datos_mapa != null) {
                    nuevo_mapa.putAll(nuevos_datos_mapa);
                }
                if (valor == null) {
                    valor = nuevo_mapa;
                } else {
                    getValor().putAll(nuevo_mapa);
                }
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "El parámetro: formulario, no deriva o es de la clase web_formularios "));
            }            
            return nuevo_mapa;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean importar_captura(Object valor_del_objeto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (_control_tipo.equals(k_entradas_tipo_checkbox)
             || _control_tipo.equals(k_entradas_tipo_radio)) {
                if (valor_del_objeto == null) {
                    valor_del_objeto = "";
                }
                String atributos = getValor().get(k_valores_mapa_atributos_tex);
                if (valor != null) {
                    String valor_tex = getValor().get(k_valores_mapa_valor_tex);
                    if (valor_tex.equals(valor_del_objeto.toString())) {                                    
                        atributos = atributos + k_atributo_checked;
                    } else {
                        atributos = atributos.replace(k_atributo_checked, "");
                    }
                    getValor().put(k_valores_mapa_atributos_tex, atributos);
                }
            } else if (valor != null) {
                getValor().put(k_valores_mapa_valor_tex, valor_del_objeto.toString());
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;        
    }
    
    /**
     * Indica si el valor es vacío
     * @param objeto_a_validar
     * @param ok
     * @param extras_array
     * @return true si hay que utilizar el valor previo, por defecto.
     * @throws Exception 
     */
    @Override
    public boolean _ser_valor_vacio(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return false;
    }    
    /**
     * Valida un valor vacío, si hay valor por defecto
     * @param objeto_a_validar
     * @param ok
     * @param extras_array
     * @return true si hay que utilizar el valor previo, por defecto.
     * @throws Exception 
     */
    @Override
    public boolean _utilizar_valor_por_defecto(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return false;
    }
    /**
     * Hay que borrar la entrada
     * @param objeto_a_validar
     * @param ok
     * @param extras_array
     * @return true si hay que utilizar el valor previo, por defecto.
     * @throws Exception 
     */
    @Override
    public boolean _ser_borrar_entrada(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return false;
    }
    
    @Override
    public boolean _validar_boton(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return true;
    }
    
    @Override
    public boolean _validar_color(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return true;
    }
    /**
     * Evita repetir el procesamiento, y limpia el error.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    @Override
    public boolean hacer_repetir_procesar(oks ok, Object ... extras_array) throws Exception {
        ok.iniciar();
        return false;
    }
    
    @Override
    public boolean post_procesar_submit(Object objeto_a_procesar, oks ok, Object ... extras_array) throws Exception {
        return ok.es;
    }

    @Override
    public boolean post_procesar_reset(Object objeto_a_procesar, oks ok, Object ... extras_array) throws Exception {
        return ok.es;
    }
        
    @Override
    public boolean escribir_error(String texto, oks ok, Object... extras_array) {
        getValor().put(k_valores_mapa_mensaje_error_tex, texto);
        _formulario.escribir_error(texto, ok, extras_array);
        return true;
    }
    
    @Override
    public boolean escribir_linea_error(String texto, oks ok, Object... extras_array) {
        getValor().put(k_valores_mapa_mensaje_error_tex, texto);
        _formulario.escribir_linea_error(texto, ok, extras_array);
        return true;
    }
    
    @Override
    public Object _iniciar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        if (valor != null) {
            if (modo_operacion.equals(k_fase_procesamiento)) {
                getValor().put(k_valores_mapa_mensaje_error_tex ,"");
            }
        }
        return valor;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> getValor() {
        return (Map<String, String>) valor;
    }
    
}

package inweb.modelos_html.formularios;

import innui.formularios.controles;
import static innui.formularios.controles.k_opciones_mapa_no_requerido;
import innui.formularios.formularios;
import static innui.formularios.formularios.k_fase_captura;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import static inweb.modelos_html.formularios.web_formularios.k_datos_mapa_atributos_tex;
import static inweb.modelos_html.formularios.web_formularios.k_datos_mapa_mensaje_de_captura_tex;
import static inweb.modelos_html.formularios.web_formularios.k_datos_mapa_valor_tex;
import java.util.Map;
import java.util.ResourceBundle;
import static inweb.modelos_html.formularios.web_formularios.k_web_formularios_procesamiento_plantilla;
import java.util.List;
import java.util.Map.Entry;
import static inweb.modelos_html.formularios.web_formularios.k_datos_mapa_control_tipo_tex;

/**
 *
 * @author emilio
 */
public class control_textos extends controles {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    public static String k_nombre_fragmento = "nombre_fragmento";
    public procesamiento_plantillas _procesamiento_plantilla = null;
    public String fragmento_nombre = null;

    public String getFragmento_nombre() {
        return fragmento_nombre;
    }

    public void setFragmento_nombre(String fragmento_nombre) {
        this.fragmento_nombre = fragmento_nombre;
    }
    
    /**
     * Convierte el objeto_a_convertir a otro objeto (puede ser otra clase)
     * @param modo_operacion
     * @param objeto_a_convertir
     * @param ok
     * @param extras_array
     * @return El objeto resultado de convertir, o null si hay error.
     * @throws Exception 
     */
    @Override
    public Object _convertir(String modo_operacion, Object objeto_a_convertir, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            
        } catch (Exception e) {
            throw e;
        }
        return objeto_a_convertir;
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
    @SuppressWarnings("unchecked")
    public Object _capturar(String modo_operacion, Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        String control_html = null;
        try {
            if (ok.es == false) { return null; }
            List<String> fragmentos_lista;
            Map<String, String> valores_mapa = null;
            if (modo_operacion.equals(k_fase_captura)) {
                fragmentos_lista = _procesamiento_plantilla.extraer_fragmento(fragmento_nombre, ok, extras_array);
                if (ok.es == false) { return false; }
                valores_mapa = (Map<String, String>) valor;
                String clave_entry;
                Object valor_entry;
                String atributos_tex = "";
                if (opciones_mapa != null) {
                    for (Entry<String, Object> entry: opciones_mapa.entrySet()) {
                        clave_entry = entry.getKey();
                        valor_entry = entry.getValue();
                        if (clave_entry.equals(k_opciones_mapa_no_requerido)
                         || clave_entry.equals(k_nombre_fragmento)) {
                            continue;
                        }
                        if (valor_entry == null) {
                            valor_entry = "";
                        }
                        if (valor_entry instanceof String) {
                            if (valores_mapa.containsKey(clave_entry)) {
                                valores_mapa.put(clave_entry, valor_entry.toString());
                            } else {
                                atributos_tex = atributos_tex 
                                + " " + clave_entry 
                                + "=\"" + valor_entry.toString() + "\"";
                            }
                        }
                    }
                }
                if (valores_mapa != null) {
                    String texto;
                    if (mensaje_de_captura == null) {
                        texto = "";
                    } else {
                        texto = mensaje_de_captura;
                    }
                    valores_mapa.put(k_datos_mapa_mensaje_de_captura_tex, texto);
                    valores_mapa.put(k_datos_mapa_valor_tex, texto);
                    if (_control_tipo == null) {
                        texto = "";
                    } else {
                        texto = _control_tipo;
                    }
                    valores_mapa.put(k_datos_mapa_control_tipo_tex, texto);
                    valores_mapa.put(k_datos_mapa_atributos_tex, atributos_tex);
                }
                control_html = _procesamiento_plantilla.procesar_fragmento(fragmentos_lista, valores_mapa, ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
        return control_html;
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
    /**
     * Procesa la información capturada
     * @param modo_operacion
     * @param objeto_a_procesar puede ser nulo (si no es solo_procesa_control)
     * @param ok
     * @param extras_array
     * @return Un objeto resultante de procesar, o null si hay error.
     * @throws Exception 
     */
    @Override
    public Object procesar(String modo_operacion, Object objeto_a_procesar, oks ok, Object ... extras_array) throws Exception {
        Object object = null;
        try {
            if (modo_operacion.equals(k_fase_captura)) {
                object = super.procesar(modo_operacion, objeto_a_procesar, ok, extras_array);
                if (ok.es) {
                    if (_formulario instanceof web_formularios web_formulario) {
                        if (object != null) {
                            web_formulario.contenido_formulario_html = web_formulario.contenido_formulario_html
                            + object.toString();
                        }
                    }

                }
            }
        } catch (Exception e) {
            throw e;
        }
        return object;
    }
    /**
     * Conecta un control con un formulario
     * @param formulario Formulario donde incorporar el control (por orden de inclusión)
     * @param clave Nombre identificador del formulario (en un grupo exclusivo el clave puede repetirse)
     * @param valor Valor inicial (puede ser null)
     * @param mensaje_de_captura Mensaje que presentar en la captura
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
            if (valor != null 
             && (valor instanceof Map) == false) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Se espera que el valor del control sea: Map<String, String> "));
            }
            if (ok.es == false) { return false; }
            super.poner_en_formulario(formulario, clave, valor, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es) {
                _procesamiento_plantilla = (procesamiento_plantillas) this.opciones_mapa.get(k_web_formularios_procesamiento_plantilla);
                if (_procesamiento_plantilla == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_web_formularios_procesamiento_plantilla);
                }
                fragmento_nombre = (String) this.opciones_mapa.get(k_nombre_fragmento);
                if (fragmento_nombre == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_nombre_fragmento);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

}

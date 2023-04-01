package inweb.modelos_html.formularios;

import innui.formularios.formularios;
import static innui.formularios.formularios.k_fase_captura;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import static inweb.modelos_html.formularios.web_formularios.k_atributo_selected;
import static inweb.modelos_html.formularios.web_formularios.k_nueva_linea_html;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_atributos_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_clave_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_mensaje_error_tex;
import static inweb.modelos_html.formularios.web_formularios.k_valores_mapa_valor_tex;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class control_bucle_clave_valor extends control_textos {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    public List<Entry<String, Object>> bucle_datos_lista = null;
    public String bucle_datos_seleccion = null;
    public int pos = 0;
    public int tam = 0;
    
    public Object poner_bucle_lista(List<Entry<String, Object>> bucle_datos_lista, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            this.bucle_datos_lista = bucle_datos_lista;
        } catch (Exception e) {
            throw e;
        }
        return valor;
    }
    
    @Override
    public boolean hacer_repetir_procesar(oks ok, Object ... extras_array) throws Exception {
        return (pos < tam);
    }    

    public List<Entry<String, Object>> getBucle_datos_mapa() {
        return bucle_datos_lista;
    }

    @Override
    public boolean poner_en_formulario(formularios formulario, String clave, Object valor, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            super.poner_en_formulario(formulario, clave, valor, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es) {
                getValor().put(k_valores_mapa_mensaje_error_tex, "");
                if (bucle_datos_lista == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "No se ha iniciado el control bucle textos con datos. "));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

    @Override
    public Object _iniciar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            clave = bucle_datos_lista.get(pos).getKey();
            getValor().put(k_valores_mapa_clave_tex, clave);
            String texto = bucle_datos_lista.get(pos).getValue().toString();
            if (texto == null) {
                texto = "";
            }
            getValor().put(k_valores_mapa_valor_tex, texto);
        } catch (Exception e) {
            throw e;
        }
        return valor;
    }
    
    @Override
    public Object _terminar(String modo_operacion, Object objeto_a_terminar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            if (modo_operacion.equals(k_fase_captura)) {
                if (objeto_a_terminar != null) {
                    valor_de_captura = valor_de_captura
                    + objeto_a_terminar.toString();
                }
                pos = pos + 1;
                objeto_a_terminar = valor_de_captura; // Retorno si no se repite el bucle
            }
        } catch (Exception e) {
            throw e;
        }
        return objeto_a_terminar;
    }

    @Override
    public boolean escribir_error(String texto, oks ok, Object... extras_array) {
        String texto_previo;
        if (texto != null && texto.isBlank() == false) {
            texto_previo = getValor().get(k_valores_mapa_mensaje_error_tex);
            if (texto_previo == null || texto_previo.isBlank()) {
                texto_previo = "";
            } else {
                texto_previo = texto_previo + k_nueva_linea_html;
            }
            getValor().put(k_valores_mapa_mensaje_error_tex, texto_previo + texto);
            _formulario.escribir_error(texto, ok, extras_array);
        }
        return true;
    }
    
    @Override
    public boolean escribir_linea_error(String texto, oks ok, Object... extras_array) {
        String texto_previo;
        if (texto != null && texto.isBlank() == false) {
            texto_previo = getValor().get(k_valores_mapa_mensaje_error_tex);
            if (texto_previo == null || texto_previo.isBlank()) {
                texto_previo = "";
            } else {
                texto_previo = texto_previo + k_nueva_linea_html;
            }
            getValor().put(k_valores_mapa_mensaje_error_tex, texto_previo + texto);
            _formulario.escribir_linea_error(texto, ok, extras_array);
        }
        return true;
    }

    @Override
    public boolean importar_captura(Object valor_del_objeto, oks ok, Object ... extras_array) throws Exception {
        try {
            String texto;
            if (valor_del_objeto == null) {
                texto = "";
            } else {
                texto = valor_del_objeto.toString();
            }
            bucle_datos_seleccion = texto;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;        
    }

    @Override
    public Object procesar(String modo_operacion, Object objeto_a_procesar, oks ok, Object ... extras_array) throws Exception {
        Object object = null;
        try {
            pos = 0;
            tam = bucle_datos_lista.size();
            if (modo_operacion.equals(k_fase_captura)) {
                valor_de_captura = "";
            }
            object = super.procesar(modo_operacion, objeto_a_procesar, ok, extras_array);
        } catch (Exception e) {
            throw e;
        }
        return object;
    }

    @Override
    public boolean _poner_atributos(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            super._poner_atributos(ok, extras_array);
            if (ok.es == false) { return false; }
            String clave_tex;
            clave_tex = getValor().get(k_valores_mapa_clave_tex);
            String atributos = getValor().get(k_valores_mapa_atributos_tex);
            if (clave_tex.equals(bucle_datos_seleccion)) {
                atributos = atributos + k_atributo_selected;
            } else {
                atributos = atributos.replace(k_atributo_selected, "");
            }
            getValor().put(k_valores_mapa_atributos_tex, atributos);
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
}

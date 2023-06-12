package inweb.modelos_html.formularios;

import innui.formularios.formularios;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import static inweb.modelos_html.formularios.web_formularios.k_nombre_fragmento;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class control_textos extends control_entradas {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";

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

    @Override
    public boolean poner_en_formulario(formularios formulario, String clave, Object valor, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            super.poner_en_formulario(formulario, clave, valor, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es) {
                fragmento_nombre = (String) this.opciones_mapa.get(k_nombre_fragmento);
                if (fragmento_nombre == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_nombre_fragmento);
                } else {
                    this.opciones_mapa.remove(k_nombre_fragmento);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }

    @Override
    public boolean importar_captura(Object valor_del_objeto, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            valor = valor_del_objeto;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    @Override
    public boolean _ser_valor_vacio(Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return false;
    } 
}

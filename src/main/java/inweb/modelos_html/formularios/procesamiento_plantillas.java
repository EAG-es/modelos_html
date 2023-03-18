package inweb.modelos_html.formularios;

import innui.bases;
import innui.modelos.configuraciones.ResourceBundles;
import innui.modelos.configuraciones.Resources;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.utiles.strings.literales;
import inweb.modelos_html.html_escapes;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class procesamiento_plantillas extends bases {
    public static String k_in_ruta = "in/inweb/modelos_html/formularios/in";
    public static String k_th_fragment="th:fragment";
    public static String k_error_fragmento_no_encontrado = "Fragmento no encontrado. ";
    public String ruta_plantillas_fragmento;
    public InputStream plantillas_fragmento_inputstream;
    public List<String> etiquetas_y_texto_lista = new LinkedList<>();
    public String plantillas_fragmento_tex = "";

    public boolean iniciar(String ruta, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in = null;
        try {
            if (ok.es == false) { return false; }
            plantillas_fragmento_inputstream = Resources.getResourceAsStream(ruta);
            if (plantillas_fragmento_inputstream == null) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in( in, "Error abriendo la plantilla con fragmentos: ") + ruta);
            }
            _leer_plantillas_fragmento(ok);
            if (ok.es == false) { return false; }
            _procesar_plantillas_fragmento(ok);
            if (ok.es == false) { return false; }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    public boolean _leer_plantillas_fragmento(oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in = null;
        try {
            if (ok.es == false) { return false; }
            BufferedReader reader = new BufferedReader(new InputStreamReader(plantillas_fragmento_inputstream, StandardCharsets.UTF_8));
            char [] char_array = new char[100];
            int leidos_num;
            while (true) {
                leidos_num = reader.read(char_array, 0, char_array.length);
                if (leidos_num == -1) {
                    break;
                } else {
                    plantillas_fragmento_tex = plantillas_fragmento_tex + new String(char_array, 0, leidos_num);
                }
            }
            plantillas_fragmento_tex = plantillas_fragmento_tex.replaceAll("\\s+", " ");
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error leyendo el texto de la plantilla con los fragmentos. "), e);
        }
        return ok.es;
    }
    /**
     * Encuentra el final de un texto literal, terminando en el caracter fin.
     * @param donde_buscar
     * @param fin
     * @param i
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */        
    public Integer _encontrar_fin_de_texto_literal(String donde_buscar, char fin, int i, oks ok, Object ... extras_array) throws Exception {
        return literales._encontrar_fin_de_texto_literal(donde_buscar, fin, i, ok, extras_array);
    }
    
    public boolean _procesar_plantillas_fragmento(oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in = null;
        try {
            if (ok.es == false) { return false; }
            int i = 0;
            int tam = plantillas_fragmento_tex.length();
            int inicio = 0;
            int fin = 0;
            boolean es_etiqueta = false;
            String etiqueta;
            while (true) {
                if (i >= tam) {
                    break;
                }
                if (plantillas_fragmento_tex.charAt(i) == '<') {
                    es_etiqueta = true;
                    fin = i;
                    etiqueta = plantillas_fragmento_tex.substring(inicio, fin);
                    if (etiqueta.isEmpty() == false) {
                        etiquetas_y_texto_lista.add(etiqueta);
                    }
                    inicio = i;
                } else if (es_etiqueta) {
                    if (plantillas_fragmento_tex.charAt(i) == '"') {
                        i = _encontrar_fin_de_texto_literal(plantillas_fragmento_tex, '"', i, ok);
                        if (i == -1 || ok.es == false) { 
                            break; 
                        }
                    } else if (plantillas_fragmento_tex.charAt(i) == '\'') {
                        i = _encontrar_fin_de_texto_literal(plantillas_fragmento_tex, '\'', i, ok);
                        if (i == -1 || ok.es == false) { 
                            break; 
                        }
                    } else if (plantillas_fragmento_tex.charAt(i) == '>') {
                        fin = i;
                        etiqueta = plantillas_fragmento_tex.substring(inicio, fin + 1);
                        if (etiqueta.isEmpty() == false) {
                            etiquetas_y_texto_lista.add(etiqueta);
                        }
                        inicio = i + 1;
                        es_etiqueta = false;
                    }
                }
                i = i + 1;
            }
            fin = tam;
            etiqueta = plantillas_fragmento_tex.substring(inicio, fin);
            if (etiqueta.isEmpty() == false) {
                etiquetas_y_texto_lista.add(etiqueta);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    /**
     * Busca un texto saltándo las cadenas entrecomillas dobles y simples
     * @param donde_buscar
     * @param ignorar_caso
     * @param que_buscar
     * @param inicio
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public int _buscar_sin_texto_literal(String donde_buscar, boolean ignorar_caso, String que_buscar, int inicio, oks ok, Object ... extras_array) throws Exception {
        return literales.buscar_sin_texto_literal(donde_buscar, ignorar_caso, que_buscar, inicio, ok, extras_array);
    }
    /**
     * Busca un texto a continuación de la posición de inicio
     * @param donde_buscar
     * @param ignorar_caso
     * @param que_buscar
     * @param inicio
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public int _buscar_seguido(String donde_buscar, boolean ignorar_caso, String que_buscar, int inicio, oks ok, Object ... extras_array) throws Exception {
        return literales.buscar_seguido(donde_buscar, ignorar_caso, que_buscar, inicio, ok, extras_array);
    }
    
    public String _extraer_etiqueta(String texto, oks ok, Object ... extras_array) throws Exception {
        String etiqueta = "";
        ResourceBundle in = null;
        try {
        if (ok.es == false) { return null; }
            int i = 1;
            int tam = texto.length();
            String letra_tex;
            while (true) {
                if (i >= tam) {
                    break;
                }
                letra_tex = "" + texto.charAt(i);
                if (letra_tex.isBlank() == false) {
                    break;
                }
                i = i +1;
            }
            while (true) {
                if (i >= tam) {
                    break;
                }
                letra_tex = "" + texto.charAt(i);
                if (letra_tex.isBlank() == false) {
                    if (letra_tex.equals(">")) {                        
                        break;
                    }
                    etiqueta = etiqueta + letra_tex;                    
                } else {
                    break;
                }
                i = i +1;
            }
        } catch (Exception e) {
            throw e;
        }
        return etiqueta;
    }

    public LinkedList<String> extraer_fragmento(String nombre_fragmento, oks ok, Object ... extras_array) throws Exception {
        String k_modo_buscar_fragmento = "modo_buscar_fragmento";
        String k_modo_poner_texto_en_lista = "modo_concatenar_texto";
        ResourceBundle in = null;
        LinkedList<String> fragmento_lista = null;
        try {
            if (ok.es == false) { return null; }
            int i = 0;
            int encontrado_pos;
            int anidamientos_num = 0;
            String etiqueta = "";
            String nuevo_texto;
            String modo = k_modo_buscar_fragmento;
            nombre_fragmento = "\"" + nombre_fragmento + "\"";
            for (String texto: etiquetas_y_texto_lista) {
                i = 0;
                if (modo.equals(k_modo_buscar_fragmento)) {
                    if (texto.startsWith("<")) {
                        encontrado_pos = _buscar_sin_texto_literal(texto, true, k_th_fragment, i, ok);
                        if (ok.es == false) { break; }
                        if (encontrado_pos >= 0) {
                            nuevo_texto = texto.substring(0, encontrado_pos);
                            i = encontrado_pos + k_th_fragment.length();
                            encontrado_pos = _buscar_seguido(texto, true, "=", i, ok);
                            if (ok.es == false) { break; }
                            if (encontrado_pos == -1 ) { continue; }
                            i = encontrado_pos + "=".length();
                            encontrado_pos = _buscar_seguido(texto, false, nombre_fragmento, i, ok);
                            if (ok.es == false) { break; }
                            if (encontrado_pos == -1 ) { continue; }
                            nuevo_texto = nuevo_texto + texto.substring(encontrado_pos + nombre_fragmento.length());
                            etiqueta = _extraer_etiqueta(texto, ok);
                            if (ok.es == false) { break; }
                            fragmento_lista = new LinkedList<>();
                            fragmento_lista.add(nuevo_texto);
                            modo = k_modo_poner_texto_en_lista;
                        }
                    }
                } else if (modo.equals(k_modo_poner_texto_en_lista)) {
                    fragmento_lista.add(texto);
                    if (texto.matches("(?i)<\\s*" + etiqueta + ".+")) {
                        anidamientos_num = anidamientos_num + 1;
                    }
                    if (texto.matches("(?i)<\\s*/\\s*" + etiqueta + ".+")) {
                        anidamientos_num = anidamientos_num - 1;
                        if (anidamientos_num < 0) {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            if (fragmento_lista == null || fragmento_lista.isEmpty()) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.id = k_error_fragmento_no_encontrado;
                ok.setTxt(tr.in(in, k_error_fragmento_no_encontrado));
            }
        } catch (Exception e) {
            throw e;
        }
        return fragmento_lista;
    }
    
    public String _sustituir_contenido(String contenido, Map<String, String> datos_mapa, oks ok, Object ... extras_array) throws Exception {
        String k_reemplazo_artificial = "--$--{--";
        String k_th_attr = " th:attr";
        String k_reemplazo_th = " th:";
        try {
            if (ok.es == false) { return null; }
            String valor;
            String reemplazo;
            int i = 0;
            int i_doble_comilla = 0;
            int i_comilla = 0;
            int inicio;
            int fin;
            i = _buscar_sin_texto_literal(contenido, true, k_th_attr, 0, ok, extras_array);
            if (i >= 0) {
                inicio = i;
                i = _buscar_seguido(contenido, true, "=", i +  + k_th_attr.length(), ok, extras_array);
                if (i >= 0) {
                    i_doble_comilla = _buscar_seguido(contenido, true, "\"", i + "=".length(), ok, extras_array);
                    i_comilla = _buscar_seguido(contenido, true, "'", i + "=".length(), ok, extras_array);
                    if (i_doble_comilla > 0) {
                        if (i_comilla > 0) {
                            i = Math.min(i_comilla, i_doble_comilla);
                        } else {
                            i = i_doble_comilla;
                        }
                    } else {
                        i = i_comilla;
                    }
                    if (i >= 0) {
                        fin = i + 1;
                        i = _buscar_sin_texto_literal(contenido, true, " ", i, ok, extras_array);
                        if (i < 0) {
                            i = _buscar_sin_texto_literal(contenido, true, ">", fin - 1, ok, extras_array);
                        }
                        if (i > 0) {
                            String entre_comillas = contenido.substring(fin, i - 1);
                            entre_comillas = entre_comillas.replace(",", " ");
                            contenido = contenido.substring(0, inicio + 1)
                            + entre_comillas
                            + contenido.substring(i);
                        }
                    }
                }
            }
            if (datos_mapa != null) {
                for (Entry<String, String> entry: datos_mapa.entrySet()) {
                    reemplazo = "${" + entry.getKey() + "}";
                    if (contenido.contains(reemplazo)) {
                        valor = entry.getValue();
                        if (valor == null) {
                            valor = "";
                        } else {
                            valor = valor.replace("${", k_reemplazo_artificial);
                        }
                        contenido = contenido.replace(reemplazo, valor);
                    }
                }
                contenido = contenido.replace(k_reemplazo_artificial, "${");
                contenido = contenido.replace(k_reemplazo_th, " ");
            }
        } catch (Exception e) {
            throw e;
        }
        return contenido;
    }
    
    public String procesar_fragmento(List<String> fragmento_lista, Map<String, String> datos_mapa, oks ok, Object ... extras_array) throws Exception {
        String k_th_utext = "th:utext";
        String k_th_text = "th:text";
        String k_modo_buscar_igual = "modo_buscar_igual";
        String k_modo_buscar_texto_literal = "modo_buscar_texto_literal";
        ResourceBundle in = null;
        String texto = "";
        try {
            in = ResourceBundles.getBundle(k_in_ruta);
            if (ok.es == false) { return null; }
            int i;
            int tam;
            int inicio = 0;
            int fin = 0;
            String letra_tex;
            String etiqueta = null;
            String modo = k_modo_buscar_igual;
            String contenido = null;
            String atributo_tex = null;
            String reemplazo = null;
            String texto_previo;
            for (String parte: fragmento_lista) {
                if (contenido == null) {
                    i = _buscar_sin_texto_literal(parte, true, k_th_utext, 0, ok);
                    if (ok.es == false) { break; }
                    if (i >= 0) {
                        atributo_tex = k_th_utext;
                    } else {
                        i = _buscar_sin_texto_literal(parte, true, k_th_text, 0, ok);
                        if (i >= 0) {
                            atributo_tex = k_th_text;
                        }
                    }
                    if (i >= 0) {
                        modo = k_modo_buscar_igual;
                        texto_previo = parte.substring(0, i);
                        i = i + atributo_tex.length();
                        tam = parte.length();
                        while (true) {
                            if (i >= tam) {
                                break;
                            }
                            letra_tex = "" + parte.charAt(i);
                            if (modo.equals(k_modo_buscar_igual)) {
                                if (letra_tex.equals("=")) {
                                    modo = k_modo_buscar_texto_literal;
                                } else if (letra_tex.isBlank() == false) {
                                    ok.setTxt(tr.in(in, "Formato incorrecto: ") + atributo_tex);
                                    break;
                                }
                            } else if (modo.equals(k_modo_buscar_texto_literal)) {
                                if (letra_tex.equals("\"")) {
                                    inicio = i;
                                    fin = _encontrar_fin_de_texto_literal(parte, '\"', i, ok);
                                    if (fin == -1 || ok.es == false) { break; }
                                    contenido = parte.substring(inicio + 1, fin);
                                    break;
                                } else if (letra_tex.equals("\'")) {
                                    inicio = i;
                                    fin = _encontrar_fin_de_texto_literal(parte, '\'', i, ok);
                                    if (fin == -1 || ok.es == false) { break; }
                                    contenido = parte.substring(inicio + 1, fin);
                                    break;
                                } else if (letra_tex.isBlank() == false) {
                                    ok.setTxt(tr.in(in, "Formato incorrecto: ") + atributo_tex);
                                    break;
                                }
                            }                    
                            i = i + 1;
                        }
                        if (ok.es == false) { break; }
                        texto_previo = texto_previo + parte.substring(fin + 1);
                        texto_previo = _sustituir_contenido(texto_previo, datos_mapa, ok);
                        if (ok.es == false) { break; }
                        contenido = _sustituir_contenido(contenido, datos_mapa, ok);
                        if (ok.es == false) { break; }
                        if (atributo_tex.equals(k_th_text)) {
                            contenido = _poner_escape_HTML(contenido, ok);
                            if (ok.es == false) { break; }
                        }
                        texto = texto + texto_previo + contenido;
                        if (parte.endsWith("/>") == false) {
                            etiqueta = _extraer_etiqueta(texto_previo, ok);
                            etiqueta = "<\\s*/\\s*" + etiqueta + ".+";
                        } else {
                            contenido = null;
                        }
                    } else {
                        if (parte.startsWith("<")
                         && parte.endsWith(">")) {
                            reemplazo = _sustituir_contenido(parte, datos_mapa, ok);
                            texto = texto + reemplazo;
                        } else {
                            texto = texto + parte;                            
                        }
                    }
                } else {
                    if (parte.matches(etiqueta)) {
                        reemplazo = _sustituir_contenido(parte, datos_mapa, ok);
                        texto = texto + reemplazo;
                        contenido = null;
                    }
                }
            }
            if (ok.es == false) { return null; }
        } catch (Exception e) {
            throw e;
        }
        return texto;
    }
    /**
     * Convierte los caracteres a su equivalente formato de escape en HTML
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public String _poner_escape_HTML(String texto, oks ok, Object ... extras_array) throws Exception {
        return html_escapes.poner_escape_HTML(texto, ok, extras_array);
    }    
}

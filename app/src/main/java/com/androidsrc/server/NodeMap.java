package com.androidsrc.server;

/**
 * Created by antonyarce on 10/9/16.
 */
public class NodeMap {

    public NodoDoble inicio,fin;

    public NodeMap(){
        inicio=fin=null;
    }


    public boolean estaVacia(){
        return inicio==null;
    }

    public MeshNode actualizar(MeshNode meshNode, NodeMap lista){
        if (lista.inicio == null) {
            lista.agregarInicio(meshNode);
            return null;
        } else if (meshNode.ip == lista.inicio.dato.ip){
            return lista.inicio.dato;
        } else {
            NodoDoble nodoTemp = lista.inicio;
            lista.borrarInicio();
            lista.fin.siguiente = nodoTemp;
            return actualizar(meshNode, lista);
        }
    }

    public MeshNode buscarEspacio(int bytes, NodeMap lista){
        if (lista.inicio == null) {
            return null;
        } else if ((lista.inicio.dato.bytesTot-lista.inicio.dato.bytesUso) >= bytes){
            return lista.inicio.dato;
        } else {
            NodoDoble nodoTemp = lista.inicio;
            lista.borrarInicio();
            lista.fin.siguiente = nodoTemp;
            return buscarEspacio(bytes, lista);
        }
    }


    public void agregarFinal(MeshNode ele){
        if (!estaVacia()){
            fin= new NodoDoble(ele, null, fin);
            fin.anterior.siguiente=fin;
        }else{
            inicio=fin=new NodoDoble(ele);
        }
    }

    public void agregarInicio(MeshNode ele){
        if (!estaVacia()){
            inicio=new NodoDoble(ele, inicio, null);
            inicio.siguiente.anterior= inicio;
        }else{
            inicio=fin=new NodoDoble(ele);
        }
    }


    public String mostrarInicioFin(){
        String datos="<=>";
        if(!estaVacia()){
            NodoDoble auxiliar = inicio;
            while (auxiliar!=null){
                datos = datos + "{"+auxiliar.dato+"}"+"<=>";
                auxiliar=auxiliar.siguiente;

            }
        }
        return datos;
    }


    public String mostrarFinInicio(){
        String datos="<=>";
        if(!estaVacia()){
            NodoDoble auxiliar = fin;
            while (auxiliar!=null){
                datos = datos + "{"+auxiliar.dato+"}"+"<=>";
                auxiliar=auxiliar.anterior;

            }
        }
        return datos;
    }

    public Object buscar(Object elemento){
        NodoDoble auxiliar=inicio;
        for ( ; auxiliar != null && !elemento.equals(auxiliar.dato); auxiliar = auxiliar.siguiente);
        if(auxiliar==null){
            return null;
        }else{
            return auxiliar.dato;
        }

    }


    public void borrarInicio(){
        if(inicio==fin){
            inicio=fin=null;
        }else{
            inicio=inicio.siguiente;
            inicio.anterior=null;
        }
    }


    public void borrarFinal(){
        if(inicio==fin){
            inicio=fin=null;
        }else{
            fin=fin.anterior;
            fin.siguiente=null;
        }
    }


    public void borrar(Object dato){
        NodoDoble buscado = null;
        NodoDoble iterador = inicio;
        if(inicio==fin){
            inicio=fin=null;
        }else if (dato.equals(inicio.dato)){
            inicio=inicio.siguiente;
            inicio.anterior=null;
        }else if (dato.equals(fin.dato)){
            fin=fin.anterior;
            fin.siguiente=null;
        }else{
            while ( buscado == null && iterador != null ) {
                if ( dato.equals(iterador.dato)) {
                    buscado = iterador;
                }
                iterador = iterador.siguiente;
            }
            buscado.anterior.siguiente=buscado.siguiente;
            buscado.siguiente.anterior=buscado.anterior;
        }
    }
}
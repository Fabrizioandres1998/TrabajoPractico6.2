package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Comparator;

public class ProductManager {

    private static ProductManager instancia;
    private TreeSet<Producto> productos;

    private ProductManager() {
        // Ordena los productos por código
        productos = new TreeSet<>(new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return Integer.compare(p1.getCodigo(), p2.getCodigo());
            }
        });
    }

    public static ProductManager getInstancia() {
        if (instancia == null) {
            instancia = new ProductManager();
        }
        return instancia;
    }

    public boolean agregarProducto(Producto p) {
        return productos.add(p); 
    }

    public boolean eliminarProducto(int codigo) {
        Producto p = buscarPorCodigo(codigo);
        if (p != null) {
            productos.remove(p);
            return true;
        }
        return false;
    }

    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public boolean actualizarProducto(Producto nuevo) {
        Producto viejo = buscarPorCodigo(nuevo.getCodigo());
        if (viejo != null) {
            productos.remove(viejo);
            productos.add(nuevo);
            return true;
        }
        return false;
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getDescripcion().toLowerCase().contains(nombre.toLowerCase())) {
                lista.add(p);
            }
        }
        return lista;
    }

    public List<Producto> buscarPorRubro(String rubro) {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getRubro().equalsIgnoreCase(rubro)) {
                lista.add(p);
            }
        }
        return lista;
    }

    public List<Producto> buscarPorRangoPrecio(double minimo, double maximo) {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getPrecio() >= minimo && p.getPrecio() <= maximo) {
                lista.add(p);
            }
        }
        return lista;
    }
}

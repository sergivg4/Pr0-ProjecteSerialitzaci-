/**
 * 
 */
package projecteEx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


/**@author Sergi Valenzuela García
 * M03-UF4 
 * 10 mar 2023
 */
public class ProductesDao implements Persistable<ProducteAbstract>{

	private static HashMap<Integer, ProducteAbstract> productes = new HashMap<>();
	
	public void guardarFitxer() {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("res/productes.txt"))){
			oos.writeObject(productes);
			
		}catch (Exception e) {
			System.out.println("ha petado, pero no!" + e.getMessage());
		}
	}
	
	public void obrirFitxer() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("res/productes.txt"))){
			
			HashMap<Integer, ProducteAbstract> productes2 = (HashMap<Integer, ProducteAbstract>)ois.readObject();
	        
			productes = productes2;
			
//			for (ProducteAbstract p : productes2.values()) {
//				System.out.println(p);
//			}

		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("ha petado, pero sigue vivo, aunque el archivo no existe!");
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println("ha petado, pero no se porque, cosas...!"+e.getMessage());
		}
	}

	@Override
	public void guardar(ProducteAbstract prod) {
		productes.put(prod.getId(), prod);
	}

	@Override
	public void eliminar(int id) {
			productes.remove(id);
	}

	@Override
	public ProducteAbstract buscar(int id) {
		return productes.get(id);
	}

	@Override
	public HashMap<Integer, ProducteAbstract> getMap() {
		return productes;
	}
	
}

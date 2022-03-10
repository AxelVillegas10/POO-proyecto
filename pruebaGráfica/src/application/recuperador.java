package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class recuperador {
	
	//Atributos
	
		String linea, dirección, palabra;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listDos = new ArrayList<String>();
		ArrayList<String> textoPlano = new ArrayList<String>();
		ArrayList<String> listTres = new ArrayList<String>();
		ArrayList<Integer> listInt = new ArrayList<Integer>();
		
	//Métodos
	
	//Método que recibe la dirección del archivo csv y regresa un ArrayList con el contenido	
	public ArrayList<String> regresaArrayList(String dirección){
		try {
			//Abrimos el archivo
			Scanner csvData = new Scanner(new File(dirección));
			while(csvData.hasNext()) {
				list.add(csvData.nextLine());
			}
		}catch(FileNotFoundException ex) {
			System.out.println(ex.toString());
		}
		return list;
	}
	
	//Método que recibe el ArrayList y elimina los caracteres especiales, devuelve el ArrayList ya limpio como listDos
	public ArrayList<String> limpiaTexto(ArrayList<String> list){
		//Se obtiene cada linea con 'get', se limpia según las restricciones con 'replace all' y por último se agrega a un ArrayList nuevo
		for(int j = 0; j < list.size(); j++) {
			linea = list.get(j);
			linea = linea.replaceAll("\\d", "");
			linea = linea.replaceAll("\\W", " ");
			listDos.add(j,linea);
		}
		return listDos; //Se regresa el ArrayList nuevo, con el contenido ya filtrado
	}
	
	//Método que recibe las dos listas, cuenta el número de veces que se repite cada país y asigna el valor en el mismo orden a un ArrayList de enteros, lo devuelve como listInt
	public ArrayList<Integer> cuentaPalabras(ArrayList<String> listTres, ArrayList<String> textoPlano){
		for(int k = 0; k < textoPlano.size(); k++) {
			palabra = textoPlano.get(k);
			int contador = 0;
			for(int i = 0; i < listTres.size(); i++) { //Ciclo que recorre cada linea de listDos
				linea = listTres.get(i); //Se obtiene la linea número 'i' y se asigna a una variable tipo String
				if(linea.contains(palabra) == true) {
					contador++;
				}
			}
			listInt.add(k,contador);
		}
		return listInt; //Se regresa el ArrayList a la función main
	}
	
	//Método que recibe las dos listas, para eliminar los países repetidos en los nombres, lo asigna en un nuevo ArrayList y lo devuelve como listTres
	public ArrayList<String> nombresRepetidos(ArrayList<String> listDos, ArrayList<String> textoPlano){
		for(int i = 0; i < listDos.size(); i++) { //Ciclo que recorre cada linea de listDos
			linea = listDos.get(i);
			//Se suma la combinación de caracteres junto con la variable palabra que se desean reemplazar con el método 'replace all'
			for(int k = 0; k < textoPlano.size(); k++) {
				palabra = textoPlano.get(k);
				linea = linea.replaceAll(palabra + "\\t", "");
				linea = linea.replaceAll("\\t" + palabra, "");
			}
			listTres.add(i,linea);
		}
		return listTres; //Se regresa el ArrayList nuevo, sin paises repetidos
	}
}

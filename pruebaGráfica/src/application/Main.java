package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	
	//M�todo que prepara la parte gr�fica
	public void start(Stage primaryStage) {
		
		CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Pa�s");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("N�mero de Empresas");

       
		BarChart<Number, String> chart = new BarChart<Number, String>(xAxis, yAxis);
        chart.setTitle("N�mero de unicornios por pa�s");
        // agregamos datos 
        chart.setData(obtenerDatos());
        
        // Paneles
        StackPane root = new StackPane();
        root.getChildren().add(chart);
        
        // Tama�o del Frame
        Scene scene = new Scene(root, 640, 427);

        primaryStage.setTitle("Proyecto Unicornio");
        primaryStage.setScene(scene);
        primaryStage.show();
        
	}
	@SuppressWarnings("unchecked")
	
	//M�todo que asigna los valores a la gr�fica, devuelve la gr�fica
	public static ObservableList<XYChart.Series<Number, String>> obtenerDatos() {
		
		//Se declaran los ArrayList necesarios para guardar la informaci�n, compararla y mostrarla gr�ficamente
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<String> textoPlano = new ArrayList<String>();
		ArrayList<Integer> numPalabras = new ArrayList<Integer>();
		
		//Instancias del objeto recuperdador, le objeto1 se utiliza para guardar la informaci�n, el objeto2 para compararla 
		recuperador objeto1 = new recuperador();
		recuperador objeto2 = new recuperador();
		
		//Se llama al m�todo regresaArrayList para obtener la informaci�n, se env�a la direcci�n del archivo
		lista = objeto1.regresaArrayList("C:\\Users\\axjvb\\Documents\\IBERO\\Primavera2022\\POO\\Proyecto\\Unicorn_Companies.csv");
		
		//Se env�a el ArrayList obtenido por el m�todo regresaArrayList, regresa ArrayList sin caract�res especiales
		lista = objeto1.limpiaTexto(lista); 
		
		//Se env�an dos ArrayList para comparar linea por linea los 30 paises y eliminar los paises que corresponden al nombre de la empresa
		lista = objeto1.nombresRepetidos(lista, textoPlano); 
		
		//imprime la lista
		for(String txt : lista){ //imprime la lista
			System.out.println(txt);
		}
		
		//Direcci�n del archivo como argumento
		textoPlano = objeto2.regresaArrayList("C:\\Users\\axjvb\\Documents\\IBERO\\Primavera2022\\POO\\Proyecto\\textoPlanoProyecto.txt");
		
		//Se env�an dos ArrayList para comparar linea por linea los 30 paises y contar el n�mero de empresas que hay en cada uno,
		//regresa un ArrayList de tipo Integer
		numPalabras = objeto1.cuentaPalabras(lista,textoPlano);
		
        XYChart.Series<Number, String> frecuenciasPalabras = new XYChart.Series<>();
        frecuenciasPalabras.setName("N�mero de Empresas"); 
        
        //Ciclo para agregar los 30 paises y el n�mero de empresas en cada uno
        for(int i = 0; i < numPalabras.size(); i++) {
        	frecuenciasPalabras.getData().addAll(
                    new XYChart.Data<>(numPalabras.get(i), textoPlano.get(i)));
        }
        
        ObservableList<XYChart.Series<Number, String>> data = FXCollections.observableArrayList();
        data.addAll(frecuenciasPalabras);

        return data;
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

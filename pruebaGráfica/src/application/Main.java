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
	
	//Método que prepara la parte gráfica
	public void start(Stage primaryStage) {
		
		CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("País");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Número de Empresas");

       
		BarChart<Number, String> chart = new BarChart<Number, String>(xAxis, yAxis);
        chart.setTitle("Número de unicornios por país");
        // agregamos datos 
        chart.setData(obtenerDatos());
        
        // Paneles
        StackPane root = new StackPane();
        root.getChildren().add(chart);
        
        // Tamaño del Frame
        Scene scene = new Scene(root, 640, 427);

        primaryStage.setTitle("Proyecto Unicornio");
        primaryStage.setScene(scene);
        primaryStage.show();
        
	}
	@SuppressWarnings("unchecked")
	
	//Método que asigna los valores a la gráfica, devuelve la gráfica
	public static ObservableList<XYChart.Series<Number, String>> obtenerDatos() {
		
		//Se declaran los ArrayList necesarios para guardar la información, compararla y mostrarla gráficamente
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<String> textoPlano = new ArrayList<String>();
		ArrayList<Integer> numPalabras = new ArrayList<Integer>();
		
		//Instancias del objeto recuperdador, le objeto1 se utiliza para guardar la información, el objeto2 para compararla 
		recuperador objeto1 = new recuperador();
		recuperador objeto2 = new recuperador();
		
		//Se llama al método regresaArrayList para obtener la información, se envía la dirección del archivo
		lista = objeto1.regresaArrayList("C:\\Users\\axjvb\\Documents\\IBERO\\Primavera2022\\POO\\Proyecto\\Unicorn_Companies.csv");
		
		//Se envía el ArrayList obtenido por el método regresaArrayList, regresa ArrayList sin caractéres especiales
		lista = objeto1.limpiaTexto(lista); 
		
		//Se envían dos ArrayList para comparar linea por linea los 30 paises y eliminar los paises que corresponden al nombre de la empresa
		lista = objeto1.nombresRepetidos(lista, textoPlano); 
		
		//imprime la lista
		for(String txt : lista){ //imprime la lista
			System.out.println(txt);
		}
		
		//Dirección del archivo como argumento
		textoPlano = objeto2.regresaArrayList("C:\\Users\\axjvb\\Documents\\IBERO\\Primavera2022\\POO\\Proyecto\\textoPlanoProyecto.txt");
		
		//Se envían dos ArrayList para comparar linea por linea los 30 paises y contar el número de empresas que hay en cada uno,
		//regresa un ArrayList de tipo Integer
		numPalabras = objeto1.cuentaPalabras(lista,textoPlano);
		
        XYChart.Series<Number, String> frecuenciasPalabras = new XYChart.Series<>();
        frecuenciasPalabras.setName("Número de Empresas"); 
        
        //Ciclo para agregar los 30 paises y el número de empresas en cada uno
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

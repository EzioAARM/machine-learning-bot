package com.ia.url.grupo6;

import java.util.Arrays;
import java.util.Scanner;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Classifier<String, String> bayes = new BayesClassifier<String, String>();
        Scanner in = new Scanner(System.in); 
        while(true){
            try{
                System.out.println("Ingrese una oracion:");
                String oracion = in.nextLine(); 
                System.out.println("Ingrese una etiqueta, e.g.(positivo o negativo):");
                String etiqueta = in.nextLine();
                String[] texto = oracion.split("\\s");
                bayes.learn(etiqueta, Arrays.asList(texto));
                System.out.println("¿Desea terminar el entrenamiento?");
                String respuesta = in.nextLine();
                if(respuesta.equals("si")){
                    break;
                }
            }
            catch(Exception e){
            }
        }
        while(true){
            try{
                System.out.println("Ingrese una oracion para clasificar:");
                String oracion = in.nextLine();
                String[] unknownText1 = oracion.split("\\s");
                System.out.println("La oracion es: "+bayes.classify(Arrays.asList(unknownText1)).getCategory());
                System.out.println("¿desea continuar?");
                String respuesta = in.nextLine();
                if(respuesta.equals("si")){
                }
                else{
                    break;
                }
            }
            catch(Exception e){
            }
        }
    }

    public static String escaparOracion(String oracion) {
        return oracion;
    }
}

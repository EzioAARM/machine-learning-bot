package com.ia.url.grupo6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

/**
 * Hello world!
 *
 */
public class App 
{
    static List<String> palabras = new ArrayList<>();
    public static void main( String[] args )
    {
        try {
            File archivoPalabras = new File("palabras.txt");
            FileReader lector = new FileReader(archivoPalabras);
            BufferedReader bLector = new BufferedReader(lector);
            String lineaActual = "";
            while ((lineaActual = bLector.readLine()) != null) {
                palabras.add(lineaActual.toLowerCase());
            }
            bLector.close();
            lector.close();

        } catch (Exception ex) {

        }
        Classifier<String, String> bayes = new BayesClassifier<String, String>();
        Scanner in = new Scanner(System.in); 
        while(true){
            try{
                System.out.println("Ingrese una oracion:");
                String oracion = in.nextLine(); 
                System.out.println("Ingrese una etiqueta, e.g.(positivo o negativo):");
                String etiqueta = in.nextLine();
                String[] texto = escaparOracion(oracion).split("\\s");
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
        in.close();
    }

    public static String escaparOracion(String oracion) {
        oracion = " " + oracion.toLowerCase();
        for (String palabraActual : palabras) {
            if (oracion.contains(palabraActual)) {
                oracion = oracion.replace(palabraActual, " ");
            }
        }
        return oracion.trim();
    }
}

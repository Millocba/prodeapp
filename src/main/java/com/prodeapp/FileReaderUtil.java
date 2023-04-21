package com.prodeapp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<String> readLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de resultados");
        }

        return lines;
    }

    public static Object[] leerConfig(String archivo) throws FileNotFoundException {
        String database, ipadress;
        int port, puntosxacierto, puntosxbonus;
        database = "";
        ipadress = "";
        port = 0;
        puntosxacierto = 0;
        puntosxbonus = 0;
        //System.out.println(new FileInputStream(archivo));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))){
            
            String line;

            while ((line = br.readLine()) != null) {
                

                if (line.contains("database:")) {
                    database = line.split(":")[1].trim();
                } else if (line.contains("ipadress:")) {
                    ipadress = line.split(":")[1].trim();
                } else if (line.contains("port:")) {
                    port = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.contains("puntosxacierto:")) {
                    puntosxacierto = Integer.parseInt(line.split(":")[1].trim());
                } else if (line.contains("puntosxbonus:")) {
                    puntosxbonus = Integer.parseInt(line.split(":")[1].trim());
                }
            }

            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración");
        }

        return new Object[]{database, ipadress, port, puntosxacierto, puntosxbonus};
    }
}


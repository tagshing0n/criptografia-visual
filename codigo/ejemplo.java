import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class visualpng_3 {

    public static void main(String[] args) {
        int tamanoMatriz = 100; //200 original Tamaño de la matriz
        int tamanoCuadrado = 50; //100 original Tamaño del cuadrado

        // Crear la matriz para el cuadrado original
        int[][] matriz = new int[tamanoMatriz][tamanoMatriz];

        // Dibujar el cuadrado original en la matriz
        int inicioCuadrado = tamanoMatriz / 2 - tamanoCuadrado / 2;
        int finCuadrado = inicioCuadrado + tamanoCuadrado;
        for (int i = 0; i < tamanoMatriz; i++) {
            for (int j = 0; j < tamanoMatriz; j++) {
                if (i >= inicioCuadrado && i < finCuadrado && j >= inicioCuadrado && j < finCuadrado) {
                    matriz[i][j] = 1; // Pintar de negro (1) dentro del cuadrado
                } else {
                    matriz[i][j] = 0; // Inicializar fuera del cuadrado con blanco (0)
                }
            }
        }

        // Generar y guardar la imagen del cuadrado original
        BufferedImage imagenCuadradoOriginal = generarImagen(matriz);
        guardarImagen(imagenCuadradoOriginal, "cuadrado_original.png");

        // Partir o cifrar el cuadrado en S1 y S2
        Random random = new Random();
        int[][] S1 = new int[tamanoMatriz][tamanoMatriz];
        int[][] S2 = new int[tamanoMatriz][tamanoMatriz];
        for (int i = 0; i < tamanoMatriz; i++) {
            for (int j = 0; j < tamanoMatriz; j++) {
                if (matriz[i][j] == 1) {
                    int bitAleatorio = random.nextInt(2); // Generar 0 o 1 aleatoriamente
                    S1[i][j] = bitAleatorio; // Pintar de acuerdo a S1
                    S2[i][j] = S1[i][j] ^ 1; // Pintar de acuerdo a S2 (XOR con 1 para que la suma binaria sea 1)
                } else {
                    int bitAleatorio = random.nextInt(2); // Generar 0 o 1 aleatoriamente
                    S1[i][j] = bitAleatorio; // Pintar de forma aleatoria en S1
                    S2[i][j] = bitAleatorio; // Pintar de forma aleatoria en S2
                }
            }
        }

        // Generar y guardar la imagen de S1
        BufferedImage imagenS1 = generarImagen(S1);
        guardarImagen(imagenS1, "S1.png");

        // Generar y guardar la imagen de S2
        BufferedImage imagenS2 = generarImagen(S2);
        guardarImagen(imagenS2, "S2.png");

        // Dibujar la suma de S1 y S2
        int[][] suma = new int[tamanoMatriz][tamanoMatriz];
        for (int i = 0; i < tamanoMatriz; i++) {
            for (int j = 0; j < tamanoMatriz; j++) {
                suma[i][j] = S1[i][j] | S2[i][j]; // Realizar la suma binaria
            }
        }

        // Generar y guardar la imagen de la suma de S1 y S2
        BufferedImage imagenSuma = generarImagen(suma);
        guardarImagen(imagenSuma, "suma.png");
    }

    // Método para generar una imagen a partir de una matriz de blancos y negros
    public static BufferedImage generarImagen(int[][] matriz) {
        int tamanoMatriz = matriz.length;

        BufferedImage imagen = new BufferedImage(tamanoMatriz, tamanoMatriz, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = imagen.createGraphics();

        for (int i = 0; i < tamanoMatriz; i++) {
            for (int j = 0; j < tamanoMatriz; j++) {
                if (matriz[i][j] == 1) {
                    graphics.setColor(Color.BLACK);
                } else {
                    graphics.setColor(Color.WHITE);
                }

                graphics.fillRect(j, i, 1, 1);
            }
        }

        graphics.dispose();
        return imagen;
    }

    // Método para guardar una imagen en formato PNG
    public static void guardarImagen(BufferedImage imagen, String nombreArchivo) {
        try {
            ImageIO.write(imagen, "png", new File(nombreArchivo));
            System.out.println("Imagen " + nombreArchivo + " guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen " + nombreArchivo + ": " + e.getMessage());
        }
    }
}

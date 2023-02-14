import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static void sortPixels(BufferedImage image){
        TreeMap<Integer, Integer> allPixels = new TreeMap<>();
        int counter;

        for(int y = 0;y<image.getHeight();y++){
            for(int x = 0; x<image.getWidth();x++){
                int pixel = image.getRGB(x,y);
                if(allPixels.containsKey(pixel)){
                    int value= allPixels.get(pixel)+1;
                    allPixels.put(pixel,value);
                }
                else{
                    allPixels.put(pixel,1);
                }
            }
        }
        for(int yy = 0;yy<image.getHeight();yy++){
            for(int xx = 0; xx<image.getWidth();xx++){
                int key = allPixels.keySet().iterator().next();
                counter=allPixels.get(key);
                if(allPixels.get(key)!=0){
                    Color color = new Color(key,true);
                    image.setRGB(xx,yy,color.getRGB());
                    counter--;
                    allPixels.put(key,counter);
                }
                else{
                    allPixels.remove(key);
                    key = allPixels.keySet().iterator().next();
                    counter=allPixels.get(key);
                    Color color = new Color(key,true);
                    image.setRGB(xx,yy,color.getRGB());
                    counter--;
                    allPixels.put(key,counter);
                }
            }
        }
    }
    static void convertToGrayscale(BufferedImage image){
        for(int y = 0;y< image.getHeight();y++){
            for (int x = 0; x<image.getWidth();x++){
                int pixel = image.getRGB(x,y);
                Color color = new Color(pixel,true);
                int avg = (int)(0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
                color = new Color(avg, avg, avg);
                image.setRGB(x,y,color.getRGB());
            }
        }
    }
    static int averageGrayScale(int gray_values){
        Color color = new Color(gray_values,true);
        int avg = (int)(0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
        return avg;
    }
    static void sobelFilter(BufferedImage image) {
        int maxGradient = -1;
        int[][] edges = new int[image.getWidth()][image.getHeight()];
        System.out.println("#####################");
        convertToGrayscale(image);
        for (int y = 1; y < image.getHeight() - 1; y++) {
            for (int x = 1; x < image.getWidth() - 1; x++) {

                int v00 = averageGrayScale(image.getRGB(x - 1, y - 1));
                int v01 = averageGrayScale(image.getRGB(x - 1, y));
                int v02 = averageGrayScale(image.getRGB(x - 1, y + 1));

                int v10 = averageGrayScale(image.getRGB(x, y - 1));
                int v12 = averageGrayScale(image.getRGB(x, y + 1));

                int v20 = averageGrayScale(image.getRGB(x + 1, y - 1));
                int v21 = averageGrayScale(image.getRGB(x + 1, y));
                int v22 = averageGrayScale(image.getRGB(x + 1, y + 1));

                int Gx = (-1 * v00) + (v02) + (-2 * v10) + (2 * v12) + (-1 * v20) + (v22);
                int Gy = (v00) + (2 * v01) + (v02) + (-1 * v20) + (-2 * v21) + (-1 * v22);
                double G = Math.sqrt((Gx * Gx) + (Gy * Gy));
                int g = (int) G;
                if (maxGradient < g) {
                    maxGradient = g;
                }
                edges[x][y] = g;
            }
        }
        double scale = 255.0 / maxGradient;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color cl;
                int edgeColor = edges[x][y];
                edgeColor = (int) (edgeColor * scale);
                int red = edgeColor;
                int green = edgeColor;
                int blue = edgeColor;
                cl = new Color(red, green, blue);
                image.setRGB(x, y, cl.getRGB());
            }
        }
    }
    public static void main(String[] args) throws IOException{
        while(true) {
            Scanner menu = new Scanner(System.in);
            System.out.println("1: Show all files in directory\n2: Sort pixels\n3: Convert image to grayscale\n4: Apply a sobel-filter\n5: Exit");
            System.out.print("Select choice: ");
            Integer choice = menu.nextInt();
            System.out.println();

            if (choice == 1) {
                File directory = new File("pictures/");
                File[] allFiles = directory.listFiles();
                for (File dir : allFiles) {
                    System.out.println(dir.getName());
                }
                System.out.println();
            }
            else if(choice==5){
                break;
            }
            else {
                Scanner names = new Scanner(System.in);
                System.out.print("Select file: ");
                String name = names.nextLine();
                System.out.print("Name output file: ");
                String new_name = names.nextLine();
                String ext = new_name.substring(new_name.lastIndexOf(".") + 1);

                if (choice == 2) {
                    File img = new File("pictures/" + name);
                    BufferedImage image = ImageIO.read(img);
                    sortPixels(image);
                    img = new File("pictures/" + new_name);
                    ImageIO.write(image, ext, img);
                    System.out.println();
                } else if (choice == 3) {
                    File img = new File("pictures/" + name);
                    BufferedImage image = ImageIO.read(img);
                    convertToGrayscale(image);
                    img = new File("pictures/" + new_name);
                    ImageIO.write(image, ext, img);
                    System.out.println();
                } else if (choice == 4) {
                    File img = new File("pictures/" + name);
                    BufferedImage image = ImageIO.read(img);
                    sobelFilter(image);
                    img = new File("pictures/" + new_name);
                    ImageIO.write(image, ext, img);
                    System.out.println();
                }
            }
        }
    }
}

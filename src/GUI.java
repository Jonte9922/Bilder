import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class GUI {
    public static ImageIcon scaleImage(File image, JButton button) throws IOException {
        Image img = ImageIO.read(image);
        img=img.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon imgs = new ImageIcon(img);
        return imgs;
    }
    public static void main(String[] args) throws IOException {
        JFrame f=new JFrame();
        JPanel panel = new JPanel();
        System.out.println("hej");
        File directory = new File("pictures/");
        File[] allFiles = directory.listFiles();
        panel = new JPanel(new GridLayout(0,5));
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton[] buttons = new JButton[allFiles.length];
        for(int i = 0;i<allFiles.length;i++){
            //buttons[i] = new JButton();
            //buttons[i].setIcon(scaleImage(allFiles[i],buttons[i]));
            panel.add(new JButton());
            f.setVisible(true);
        }

        //f.getContentPane().setLayout(new BorderLayout());
        //f.getContentPane().setLayout(new GridLayout(0,5));
        //f.getContentPane().setLayout(new FlowLayout());
        JScrollPane scrollPane = new JScrollPane(panel);
        f.getContentPane().add(scrollPane,BorderLayout.CENTER);
        //f.setSize(300,300);
        //f.add(panel);
    }
}

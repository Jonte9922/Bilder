import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class GUI {
    public static void main(String[] args) throws IOException {
        JFrame f=new JFrame();
        JPanel panel = new JPanel();
        JPanel imagePanel = new JPanel();

        System.out.println("hej");
        File directory = new File("pictures/");
        File[] allFiles = directory.listFiles();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        imagePanel.setLayout(new GridLayout(0, 7 ));

//        for(int i = 0;i<allFiles.length;i++){
//            JLabel label = new JLabel();
//            ImageIcon imageIcon = new ImageIcon(new ImageIcon("pictures/"+allFiles[i].getName()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
//            label.setIcon(imageIcon);
//            imagePanel.add(label);
//        }
        for(File labels:allFiles){
            JLabel label = new JLabel();
            label.putClientProperty(labels,labels);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("pictures/"+labels.getName()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            label.setIcon(imageIcon);
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    JFrame runMethodFrame=new JFrame();
                    runMethodFrame.setVisible(true);
                    runMethodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JButton submit = new JButton("submit");
                    JTextField te = new JTextField(24);
                    JPanel test = new JPanel();
                    test.add(te);
                    test.add(submit);
                    submit.addActionListener({
                        // The code  to handle the   action  event goes  here
                    });
                    runMethodFrame.add(test);
                    System.out.println(label.getClientProperty(labels));

                }
            });
            imagePanel.add(label);
        }
        f.add(panel, BorderLayout.NORTH);
        f.add(new JScrollPane(imagePanel), BorderLayout.CENTER);
        f.setVisible(true);
    }
}

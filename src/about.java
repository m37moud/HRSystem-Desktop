

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import java.awt.Image;
import java.awt.Rectangle;

import java.awt.Toolkit;

import java.io.File;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class about extends JDialog {
    private JPanel jPanel1 = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();

    public about() {
        this(null, "", false);
    }

    public about(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(490, 306));
        this.getContentPane().setLayout(borderLayout1);
        jPanel1.setLayout(null);
        jPanel1.setBackground(Color.white);
        jLabel1.setText("human resourse program ");
        jLabel1.setBounds(new Rectangle(0, 15, 485, 50));
        jLabel1.setFont(new Font("Tahoma", 1, 24));
        jLabel1.setForeground(Color.red);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel2.setText("best employee manage\r\n");
        jLabel2.setBounds(new Rectangle(0, 75, 485, 45));
        jLabel2.setFont(new Font("Tahoma", 1, 24));
        jLabel2.setForeground(Color.red);
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel3.setText("JAVA  Programmer");
        jLabel3.setBounds(new Rectangle(0, 120, 485, 50));
        jLabel3.setFont(new Font("Tahoma", 1, 24));
        jLabel3.setForeground(new Color(0, 33, 115));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel4.setText("mahmoud aly");
        jLabel4.setBounds(new Rectangle(-3, 170, 490, 35));
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel4.setForeground(new Color(0, 33, 115));
        jLabel4.setFont(new Font("Tahoma", 1, 24));
        jLabel5.setText("Tel : 01067961686");
        jLabel5.setBounds(new Rectangle(0, 220, 485, 35));
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel5.setForeground(new Color(0, 33, 115));
        jLabel5.setFont(new Font("Tahoma", 1, 24));
        jPanel1.add(jLabel4, null);
        jPanel1.add(jLabel3, null);
        jPanel1.add(jLabel2, null);
        jPanel1.add(jLabel1, null);
        jPanel1.add(jLabel5, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        setImage();
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setTitle("Program info");
    }
    
    private void setImage() {
        
        URL url=getClass().getResource("img/ServiceManager.png");
        
        if(url != null){
            this.setIconImage(new ImageIcon(url).getImage());
        }
               
    }
}

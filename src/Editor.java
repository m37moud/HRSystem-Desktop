

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.BevelBorder;

public class Editor extends JDialog {
    private JSpinner timef = new JSpinner();
    private JButton ok = new JButton();
    SimpleDateFormat  formatter=null;
    public boolean agree = false;

    public Editor() {
        this(null, "", false,null);
    }

    public Editor(Frame parent, String title, boolean modal,Date dvalue) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setspmodel();// ------------------------------ important arrange
        this.timef.setValue(dvalue);// ---------------
        
        this.setResizable(false);
        
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(139, 99));
        this.getContentPane().setLayout( null );
        this.setModal(true);
        timef.setBounds(new Rectangle(2, 5, 130, 30));
        timef.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        ok.setText("Change time");
        ok.setBounds(new Rectangle(2, 40, 130, 25));
        ok.setToolTipText("press arrow up or down to change time and press ( Change time ) ");
        ok.setFont(new Font("Tahoma", 1, 11));
        ok.setFocusPainted(false);
        ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });
        this.getContentPane().add(ok, null);
        this.getContentPane().add(timef, null);
        this.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    private void setspmodel(){
        
        Calendar calen=Calendar.getInstance();
        Date initdate=calen.getTime();
        calen.add(Calendar.YEAR, -100);
        Date earlestd=calen.getTime();
        calen.add(Calendar.YEAR, 200);
        Date latestd=calen.getTime();
        
        SpinnerModel smodel=new SpinnerDateModel(initdate,earlestd,latestd,Calendar.YEAR);
        this.timef.setModel(smodel);
        this.timef.setEditor(new JSpinner.DateEditor(timef,"HH:mm"));

        JComponent deditor= timef.getEditor();
        JTextField dfield=((JSpinner.DefaultEditor)deditor).getTextField();
        dfield.setHorizontalAlignment(JTextField.CENTER);
        dfield.setForeground(new Color(198, 0, 0));
        
        this.timef.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.timef.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);        
        
    }

    public String getValue(){
        
        formatter=new SimpleDateFormat("HH:mm",Locale.getDefault());
        
        String value = formatter.format((Date)this.timef.getValue());
                
        return  value;
        
    }

    private void jButton1_actionPerformed(ActionEvent e) {
        this.agree = true;// dialog result
        this.dispose();
    }
}

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    static void main() {
        JFrame ventana = new JFrame("Prueba QDate");

//        QDate fecha = new QDate();

//        try {
//            fecha.setDate(31, 2, 2025);
//        } catch (QDateException e) {
//            JOptionPane.showMessageDialog(null, "This date doesn't even exist!");
//        }

//        JButton check = new JButton("Check it");
        QDate initialdate = new QDate();
        QDate finaldate = new QDate();

        JButton calculate = new JButton("Calculate difference");


        initialdate.setBounds(50, 30, 300, 30);
        finaldate.setBounds(50, 80, 300, 30);
        calculate.setBounds(115, 140, 200, 30);
        ventana.setLayout(null);
        ventana.add(initialdate);
        ventana.add(finaldate);
        ventana.add(calculate);
        ventana.setSize(415, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

//        check.addActionListener(e -> {
//            try {
//                Date selectedDate=fecha.getDate();
//                JOptionPane.showMessageDialog(ventana, "✅Valid date: "+selectedDate);
//            } catch (QDateException ex) {
//                JOptionPane.showMessageDialog(ventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });

        calculate.addActionListener( e -> {
            Date date1=null;
            Date date2=null;
            boolean validate1=false;
            boolean validate2=false;
            try {
                date1 = initialdate.getDate();
                validate1=true;
                JOptionPane.showMessageDialog(ventana, "✅Valid initial date: "+date1);
            } catch (QDateException ex) {
                initialdate.setBackground(Color.red);
                JOptionPane.showMessageDialog(ventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                date2 = finaldate.getDate();
                validate2=true;
                JOptionPane.showMessageDialog(ventana, "✅Valid final date: "+date2);
            } catch (QDateException ex) {
                finaldate.setBackground(Color.red);
                JOptionPane.showMessageDialog(ventana, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            if(validate1&&validate2){
                long msdifference = date2.getTime() - date1.getTime();
                long diffdays = msdifference / (1000 * 60 * 60 * 24);
                if(date1.before(date2)){
                    JOptionPane.showMessageDialog(ventana, "The difference is "+diffdays+" days");
                } else if(date1.after(date2)){
                    JOptionPane.showMessageDialog(ventana, "Error: the initial date must be before the final!","Date order error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ventana, "Both dates are the same, silly!");
                }
            }
        });
    }
}

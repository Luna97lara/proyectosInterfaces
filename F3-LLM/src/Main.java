import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    Fecha fechaMaquina=new Fecha();
    int contador;
    JFrame frame;
    void Simple (){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton start=new JButton("Generate Random date");   //creating instance of JButton
        start.setBounds(80,50,200, 40);
        frame.add(start);//adding button in JFrame
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                fechaMaquina = new Fecha();
            }
        });

        JTextField day, month,year;
        day=new JTextField();
        month=new JTextField();
        year=new JTextField();
        day.setBounds(130, 150, 100, 20);
        frame.add(day);
        month.setBounds(130, 200, 100, 20);
        frame.add(month);
        year.setBounds(130, 250, 100, 20);
        frame.add(year);

        JLabel attempts=new JLabel("Attempts: ");
        attempts.setBounds(270,400,100,40);
        frame.add(attempts);


        JButton check=new JButton("Check");
        check.setBounds(130,350,100,40);
        frame.add(check);

        check.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int d=Integer.parseInt(day.getText());
                int m=Integer.parseInt(month.getText());
                int y=Integer.parseInt(year.getText());


                if(d==fechaMaquina.dia){
                    day.setBackground(Color.green);
                } else if(d<fechaMaquina.dia){
                    day.setBackground(Color.blue);
                } else {
                    day.setBackground(Color.red);
                }
                if(m== fechaMaquina.mes){
                    month.setBackground(Color.green);
                }  else if(m<fechaMaquina.mes){
                    month.setBackground(Color.blue);
                } else {
                    month.setBackground(Color.red);
                }
                if(y==fechaMaquina.ano){
                    year.setBackground(Color.green);
                } else if(y<fechaMaquina.ano){
                    year.setBackground(Color.blue);
                } else  {
                    year.setBackground(Color.red);
                }
                contador++;
                attempts.setText("Attempts: "+contador);

                if (day.getBackground().equals(Color.green)&&month.getBackground().equals(Color.green)&&year.getBackground().equals(Color.green)){
                    JLabel victory=new JLabel("You won!");
                    victory.setBounds(150,400,100,40);
                    frame.add(victory);
                }
            }


        });

        frame.setSize(400,500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }


    void main(){
        new Main().Simple();
    }

}

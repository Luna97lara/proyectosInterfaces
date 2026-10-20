import javax.swing.*;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class QDate extends JPanel {
    private JComboBox<Integer> day;
    private JComboBox<Integer> month;
    private JTextField year;

    public QDate() {
        this.day= new JComboBox<>();
        this.month= new JComboBox<>();
        this.year= new JTextField("1900", 4);

        for (int i = 1; i <= 31; i++) {
            this.day.addItem(i);
        }

        for (int i = 1; i <= 12; i++) {
            this.month.addItem(i);
        }

        this.day.setSelectedItem(1);
        this.month.setSelectedItem(1);

        JLabel dayLabel = new JLabel("Day:");
        JLabel monthLabel = new JLabel("Month:");
        JLabel yearLabel = new JLabel("Year:");

        this.add(dayLabel);
        this.add(day);
        this.add(monthLabel);
        this.add(month);
        this.add(yearLabel);
        this.add(year);
    }
    public Date getDate() {
        int day=(int)this.day.getSelectedItem();
        int month=(int)this.month.getSelectedItem();
        if(this.year.getText().isEmpty()){
            throw new QDateException("Incomplete date: year is missing :(");
        }
        int year;
        try {
            year=Integer.parseInt(this.year.getText());
        } catch (NumberFormatException e) {
            throw new QDateException("Invalid characters: the year must be a number!");
        }
        LocalDate localDate;
        try{
            localDate=LocalDate.of(year,month,day);
        }catch(DateTimeException e){
            throw new QDateException("Incorrect date: this date doesn't even exist in the calendar!");
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void setDate(int day, int month, int year) {
        LocalDate localDate;
        try{
            localDate=LocalDate.of(year,month,day);
        }catch(DateTimeException e){
            throw new QDateException("Incorrect date: this date doesn't even exist in the calendar!");
        }
        this.day.setSelectedItem(day);
        this.month.setSelectedItem(month);
        this.year.setText(String.valueOf(year));
    }


}

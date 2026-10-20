import java.util.Date;
import java.util.Random;

public class Fecha {

    int  dia;
    int mes;
    int ano;



    Random random = new Random();

    public Fecha(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Fecha(){
        this.mes = random.nextInt(1,13);
        this.ano = random.nextInt(1990,2001);
        switch (mes){
            case 1,3,5,7,8,10,12:
                this.dia = random.nextInt(1,32);
                break;
            case 2:
                this.dia = random.nextInt(1,28);
                break;
            case 4,6,9,11:
                this.dia = random.nextInt(1,31);
        }
    }

    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

}

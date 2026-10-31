import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class AdivinaLaFechaFrame extends JFrame {

    private QDate qDateInicio;
    private QDate qDateFin;
    private QDate qDateIntento;

    private Date fechaObjetivo;
    private int contadorIntentos;

    private JLabel lblIntentos;
    private JLabel lblMensaje;
    private JButton btnIniciar;
    private JButton btnAdivinar;

    public AdivinaLaFechaFrame() {
        super("Adivina la Fecha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // inicializar componentes QDate
        qDateInicio = new QDateImplementation();
        qDateFin = new QDateImplementation();
        qDateIntento = new QDateImplementation();

        // inicializar botones
        btnIniciar = new JButton("Iniciar Partida");
        btnAdivinar = new JButton("Adivinar");
        btnAdivinar.setEnabled(false);

        // configuración de rango (Panel Norte)
        JPanel panelRango = new JPanel(new GridLayout(2, 1));

        JPanel panelStart = new JPanel(new FlowLayout());
        panelStart.add(new JLabel("Rango inicio:"));
        panelStart.add((Component) qDateInicio);

        JPanel panelEnd = new JPanel(new FlowLayout());
        panelEnd.add(new JLabel("Rango fin:"));
        panelEnd.add((Component) qDateFin);

        panelRango.add(panelStart);
        panelRango.add(panelEnd);

        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelRango, BorderLayout.CENTER);
        panelNorte.add(btnIniciar, BorderLayout.SOUTH);

        // juego e intentos (Panel Central)
        JPanel panelJuego = new JPanel(new FlowLayout());
        panelJuego.add(new JLabel("Tu intento:"));
        panelJuego.add((Component) qDateIntento);
        panelJuego.add(btnAdivinar);

        // estado e información (Panel Sur)
        lblIntentos = new JLabel("Intentos: 0");
        lblMensaje = new JLabel("Configura el rango y pulsa Iniciar Partida");

        JPanel panelSur = new JPanel(new GridLayout(2, 1));
        panelSur.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panelSur.add(lblIntentos);
        panelSur.add(lblMensaje);

        // listeners de eventos
        btnIniciar.addActionListener(e -> iniciarPartida());
        btnAdivinar.addActionListener(e -> comprobarIntento());

        // ensamblar en el frame principal
        add(panelNorte, BorderLayout.NORTH);
        add(panelJuego, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void iniciarPartida() {
        try {
            Date inicio = qDateInicio.getDate();
            Date fin = qDateFin.getDate();

            if (inicio.after(fin)) {
                JOptionPane.showMessageDialog(this, "La fecha de inicio debe ser anterior a la de fin.",
                        "Error de Rango", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // generación random directa
            long startMillis = inicio.getTime();
            long endMillis = fin.getTime();
            long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis + 1);
            fechaObjetivo = new Date(randomMillis);

            contadorIntentos = 0;
            lblIntentos.setText("Intentos: " + contadorIntentos);
            lblMensaje.setText("Partida iniciada!! Adivina la fecha");
            lblMensaje.setForeground(Color.BLACK);
            btnAdivinar.setEnabled(true);
        } catch (QDateException ex) {
            JOptionPane.showMessageDialog(this, "Error en rango de fechas: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void comprobarIntento() {
        try {
            Date intento = qDateIntento.getDate();
            contadorIntentos++;
            lblIntentos.setText("Intentos: " + contadorIntentos);

            Calendar calObjetivo = Calendar.getInstance();
            calObjetivo.setTime(fechaObjetivo);
            calObjetivo.set(Calendar.HOUR_OF_DAY, 0);
            calObjetivo.set(Calendar.MINUTE, 0);
            calObjetivo.set(Calendar.SECOND, 0);
            calObjetivo.set(Calendar.MILLISECOND, 0);

            Calendar calIntento = Calendar.getInstance();
            calIntento.setTime(intento);
            calIntento.set(Calendar.HOUR_OF_DAY, 0);
            calIntento.set(Calendar.MINUTE, 0);
            calIntento.set(Calendar.SECOND, 0);
            calIntento.set(Calendar.MILLISECOND, 0);

            // comparación mediante getTimeInMillis()
            long timeIntento = calIntento.getTimeInMillis();
            long timeObjetivo = calObjetivo.getTimeInMillis();

            if (timeIntento == timeObjetivo) {
                lblMensaje.setText("¡Has adivinado la fecha en " + contadorIntentos + " intentos!");
                lblMensaje.setForeground(new Color(0, 128, 0));
                btnAdivinar.setEnabled(false);
            } else if (timeIntento < timeObjetivo) {
                lblMensaje.setText("La fecha buscada es posterior a la introducida.");
                lblMensaje.setForeground(Color.BLUE);
            } else {
                lblMensaje.setText("La fecha buscada es anterior a la introducida.");
                lblMensaje.setForeground(Color.RED);
            }
        } catch (QDateException ex) {
            JOptionPane.showMessageDialog(this, "Error en la fecha: " + ex.getMessage()
                    + " (Tipo: " + ex.getTipo() + ")", "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdivinaLaFechaFrame().setVisible(true));
    }
}
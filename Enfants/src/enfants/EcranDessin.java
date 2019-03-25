package enfants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Marine Veyssiere
 */
public class EcranDessin extends JFrame {

    int x = 0, y = 0;
    JPanel container = new JPanel();
    JLabel label = new JLabel();
    JPanel dessin = new JPanel();

    public EcranDessin() {
        dessin.setBackground(Color.cyan);
        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                setLabel(x, y);
            }
        });

        container.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = dessin.getGraphics();
                g.drawLine(x, y, e.getX(), e.getY());
                x = e.getX();
                y = e.getY();
                setLabel(x, y);
            }
        });

        setLabel(x, y);
        container.setLayout(new BorderLayout());
        container.add(label, BorderLayout.SOUTH);
        container.add(dessin, BorderLayout.CENTER);
        this.setTitle("Dessinons");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void setLabel(int x, int y) {
        label.setText("x=" + x + "y=" + y);
    }

}

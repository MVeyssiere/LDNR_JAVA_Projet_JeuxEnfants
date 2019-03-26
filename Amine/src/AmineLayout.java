
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AmineLayout {
    public static void main(String[] args) {
        // la création des objets graphiques est déléguée au thread de
        // diffusion d'événements de Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // nouvelle fenêtre
                JFrame fenetre = new JFrame("NullLayout Demo");
                 
                // pas de layout manager pour cette fenêtre : 
                // on positionnera les composants à la min
                fenetre.setLayout(null);
                 
                // création des boutons
                JButton b1 = new JButton("Dessin"),
                        b2 = new JButton("Calcul"),
                        b3 = new JButton("Questions/Réponse");
                 
                // ajout des boutons à la fenêtre
                fenetre.add(b1);
                fenetre.add(b2);
                fenetre.add(b3);
                 
                // positionnement et dimensionnement manuel des boutons
                b1.setBounds(10, 10, 100, 30);
                b2.setBounds(100, 40, 100, 40);
                b3.setBounds(200, 70, 150, 50);
                 
                // quitter le programme lorsqu'on ferme la fenêtre
                fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 
                // dimensionnement en affichage de la fenêtre
                fenetre.setSize(300, 200);
                fenetre.setVisible(true);
            }
             
        });
    }
}
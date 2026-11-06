// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
// Consegna 4: gli ostacoli. Scadenza: domenica 15 novembre 2026.
package ostacoli;

import geometria.Rettangolo;
import java.awt.Color;

/**
 * L'ostacolo mobile: va su e giù fra un limite alto e un limite basso, sempre alla stessa velocità,
 * e ai limiti torna indietro. Non si rompe mai: colpito, non cambia niente.
 *
 * IL PEZZO MANCANTE: i corpi dei metodi segnati «scrivete qui» e i campi che vi servono.
 * I contratti dei metodi sono quelli dell'interfaccia Ostacolo (aprite Ostacolo.java), più quello
 * che dice questo commento e quelli sopra il costruttore e sopra avanza.
 */
public class OstacoloMobile implements Ostacolo {
    public static final Color COLORE = new Color(80, 200, 120);

    private Rettangolo area;   // non è final: l'ostacolo si muove
    private double velocita;
    private double limiteAlto; 
    private double limiteBasso; 
    private boolean versoBasso = true; //all'inizio scende come da contratto di avanza

    /**
     * Pretende: area non è null; velocita è maggiore di 0; l'area sta fra i due limiti
     * (limiteAlto <= area.y() e area.y() + area.altezza() <= limiteBasso).
     * Promette: un ostacolo che sta in area e che, al primo avanza, comincia a scendere.
     */
    public OstacoloMobile(Rettangolo area, double velocita, double limiteAlto, double limiteBasso) {
        if(area != null && velocita > 0 && (limiteAlto <= area.y()  && area.y() + area.altezza() <= limiteBasso)){
            this.area = area;
            this.velocita = velocita;
            this.limiteAlto = limiteAlto;
            this.limiteBasso = limiteBasso;
        }
    }

    @Override
    public Rettangolo getArea() { return area; }

    /** Non si rompe mai: il colpo non cambia niente (né dove sta, né come si muove). */
    @Override
    public void colpisci(Ostacoli campo) {
        //  non fa niente
    }

    /** Non si rompe mai: il colpo non cambia niente (né dove sta, né come si muove). */
    @Override
    public boolean isRotto() {
        return false; // non si rompe mai
    }

    /** Promette: COLORE. */
    @Override
    public Color getColore() {
        return COLORE; 
    }

    /**
     * Promette: l'ostacolo si sposta in verticale di velocita pixel nel verso in cui sta andando
     * (all'inizio verso il basso). Se così arriverebbe su un limite o lo supererebbe, si ferma
     * esattamente sul limite e cambia verso: al passo dopo va dall'altra parte.
     * Il bordo alto non va mai sopra limiteAlto, il bordo basso mai sotto limiteBasso
     * (area.y() + area.altezza() <= limiteBasso); x, larghezza e altezza non cambiano.
     */
    @Override
    public void avanza() {
        double y = area.y();
        if (versoBasso) {
            y += velocita;
            double massimo = limiteBasso - area.altezza();   // y massima: il bordo basso sta sul limite
            if (y >= massimo) {
                y = massimo;
                versoBasso = false;
            }
        } else {
            y -= velocita;
            if (y <= limiteAlto) {
                y = limiteAlto;
                versoBasso = true;
            }
        }
        // il Rettangolo non cambia mai: se ne crea uno nuovo con la y nuova
        area = new Rettangolo(area.x(), y, area.larghezza(), area.altezza());
    }
}

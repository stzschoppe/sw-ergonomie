package mensch;  

import java.util.*;

/**
 * Ein Mitspieler des Mensch-Ärgere-Dich-Nicht-Spiels.
 * 
 * @author (Lothar Schmitz) 
 * @version (20.06.04)
 */
public class Spieler {
    
    private Parser parser;      // für Benutzereingaben
    private String farbe;       // klar
    private Feld ansetzFeld;    // zwei wesentliche ...
    private Feld hausEingang;   // ... Felder eines Spielers
    private Figur[] figuren;    // alle seine Figuren
    private Spieler naechster;  // im Kreis der nächste Spieler
    private Wuerfeln wuerfler;  // bestimmt, wie oft man würfeln darf
    private Turnier turnier;    // für Abbrüche etc
    private boolean aktiv;      // damit Aufgeben gleich wirkt
    
    // Der Voreingestellte Radius aus Turnier
    private int radius;

    /**
     * Konstruktor für Spieler-Objekte
     */
    public Spieler(Turnier turnier, String farbe, Feld anfang, Koord[] nestKoord, Koord[] hausKoord, int rad) {
        parser = new Parser();
        radius = rad;
        this.turnier = turnier;
        this.farbe   = farbe;
        ansetzFeld   = anfang;
        // Figuren und ihre Nestfelder anlegen
        Feld[] nest  = new Feld[4];
        figuren      = new Figur[4];
        for (int i = 0; i < 4; i++) {
           nest[i]    = new Feld();
           nest[i].scheibe = new Scheibe(nestKoord[i], "white", rad);
           figuren[i] = new Figur(this,nest[i],i+1);
           figuren[i].insNest();
           figuren[i].scheibe = new Scheibe(nestKoord[i],farbe,rad-1);
           
        };
        Koord hilf = new Koord(nestKoord[0].getX()+(3*rad),nestKoord[0].getY()+(3*rad));
        Scheibe spot = new Scheibe(hilf, farbe, rad);
        // Das Haus des Spielers und seine Verkettung
        Feld [] haus = new Feld[4];
        for (int k = 0; k < 4; k++) {
           haus[k] = new Feld();
           haus[k].scheibe = new Scheibe(hausKoord[k],"white",rad);
        };
        haus[0].setVorgaenger(anfang.getVorgaenger());
        for (int j = 0; j < 3; j++) {
           haus[j].setNachfolger(haus[j+1]);
           haus[j+1].setVorgaenger(haus[j]);
        };
        hausEingang = haus[0];
        // Die restlichen Attribute
        wuerfler    = new Dreimal();
        aktiv       = true;
    }

    /**
     * Feld, auf dem der Spieler seine Figuren einsetzt
     */
    public Feld ansetzFeld() {
        return ansetzFeld;
    }

    /**
     * Erstes Feld im Haus des Spielers
     */
    public Feld hausEingang() {
        return hausEingang;
    }

    /**
     * getter zu Farbe
     */
    public String getFarbe() {
        return farbe;
    }

    /**
     * Farb-Anfangsbuchstabe
     */
    public String farbAnf() {
        return getFarbe().substring(0,1);
    }

    /**
     * getter zu naechster
     */
    public Spieler getNaechster() {
        return naechster;
    }

    /**
     * setter zu naechster
     */
    public void setNaechster(Spieler s) {
        naechster = s;
    }

    /**
     *  Spieler ist dran.
     */
    public void spiele()  {            
        // Solange Befehle lesen, analysieren und ausfuehren,
        // bis "true" zurueckgegeben wird
        if (!aktiv) return; // uneleganter Zwangsabbruch
        System.out.println("Spieler " + farbe + " am Zug");
        pruefeBeweglichkeit();
        int geworfen = wuerfler.wuerfle();
        if (geworfen == 0) 
           return;
        LinkedList ziehbar = ziehbareFiguren(geworfen);
        if (ziehbar.size() > 0) {
            Iterator zit;
            Figur akt;
            boolean zugAusgefuehrt = false;
            while (! zugAusgefuehrt) {
               zit = ziehbar.iterator();
               System.out.println("Ziehen koennen: ");
               while (zit.hasNext()) {
                   akt = (Figur)zit.next();
                   System.out.println(akt);
               };
               System.out.println("Ihre Wahl? ('?' zeigt Eingabemoeglichkeiten)");
               Command command = parser.getCommand();
               zugAusgefuehrt = verarbeite(command, geworfen, ziehbar);
           };
           if (geworfen == 6) spiele();
       };
    }

    /**
     *  Figuren, die der Spieler regelgerecht ziehen darf.
     *  Hier sind die Spielregeln umgesetzt.
     */
    public LinkedList ziehbareFiguren(int geworfen)  {   
        LinkedList ziehbar = new LinkedList();
        if (figurImNest() && // muss freimachen oder einsetzen
            (ansetzFeld.getFigur() != null &&
             ansetzFeld.getFigur().getSpieler().getFarbe() == getFarbe())) {
                 // Ansetzfeld besetzt: Freimachhindernis (letztes) beseitigen
                 Figur akt = ansetzFeld.getFigur();
                 Figur zFigur = akt.weiter(geworfen).getFigur();
                 while (zFigur != null && zFigur.getSpieler().getFarbe() == getFarbe()) {
                    akt = zFigur;
                    zFigur = akt.weiter(geworfen).getFigur();
                 };
                 ziehbar.add(akt);
        }
        else if (geworfen == 6 && figurImNest()) {
            // muss einsetzen
            if (ansetzFeld.getFigur() == null ||
                ansetzFeld.getFigur().getSpieler().getFarbe() != getFarbe()) {
                // direkt auf Ansetzfeld
                for (int i = 0; i < 4; i++) {
                   if (figuren[i].imNest())
                      ziehbar.add(figuren[i]);
                };
            };
        }
        else {
            // Ziehen oder werfen möglich?
            LinkedList kannWerfen = new LinkedList();
            for (int k = 0; k < 4; k++) {
                Feld z = figuren[k].weiter(geworfen);
                if (z != null) {
                    // Ziehen oder werfen erfordert ein Zielfeld
                    if (z.getFigur() != null &&
                        z.getFigur().getSpieler().getFarbe() != getFarbe()) {
                        // Werfmöglichkeit gefunden
                        kannWerfen.add(figuren[k]);
                    }
                    else if (z.getFigur() == null) {
                    // normaler Zug auf Zielfeld möglich
                    ziehbar.add(figuren[k]);
                    };
                }
            };
            // vergiss normale Züge, wenn Du werfen kannst
            if (kannWerfen.size() > 0)
               ziehbar = kannWerfen;
        };
        return ziehbar;
    }
    

    /**
     * Hier werden die Befehle interpretiert.
     */
    private boolean verarbeite(Command command, int geworfen, LinkedList ziehbar) {
        if (command.isUnknown()) {
            System.out.println("Ich verstehe Sie nicht!");
            hilfe();
            return false;
        };
        String commandWord = command.getCommandWord();
        if (commandWord.equals("?")) {
            hilfe();
            return false;
        }
        else if (commandWord.equals("1")) {
            return zieheFigur(1, geworfen, ziehbar);
        }
        else if (commandWord.equals("2")) {
            return zieheFigur(2, geworfen, ziehbar);
        }
        else if (commandWord.equals("3")) {
            return zieheFigur(3, geworfen, ziehbar);
        }
        else if (commandWord.equals("4")) {
            return zieheFigur(4, geworfen, ziehbar);
        }
        else if (commandWord.equals("aufgeben")) {
            brichAb();
            return true;
        }
        else if (commandWord.equals("stop")) {
            turnier.beenden();
            aktiv = false;
            return true;
        }
        else {
            System.out.println("Ich verstehe Sie nicht!");
            hilfe();
            return false;
        }
    }

    // Implementierung der Befehle:

    /**
     * Die Befehlsliste anzeigen
     */
    private void hilfe() {
        System.out.println("\nSie haben folgende Eingabemoeglichkeiten:");
        parser.showCommands();
    }

    /** 
     * Die gewählte Figur des Spielers ziehen, der dran ist.
     */
    private boolean zieheFigur(int nr, int geworfen, LinkedList ziehbar)  {
        if (nr < 1 || nr > 4) return false;
        Figur akt = figuren[nr-1];
        if (! ziehbar.contains(akt)) return false;
        Feld ziel = akt.weiter(geworfen);
        Figur zielFigur = ziel.getFigur();
        if (zielFigur != null) zielFigur.insNest();
        // Der Pfeil wird dargestellt und danach wieder entfernt.
        akt.pfeil = new Pfeil(akt.getOrt(),ziel,radius);
        akt.pfeil = null;
        System.gc(); // Den Garbage leeren
        // Ende der Pfeildarstellung
        akt.setOrt(ziel);
        // Die Scheibe wird auf das neue Feld bewegt
        akt.scheibe.move(ziel);
        akt.erhoeheFortschritt(geworfen);
        if (hausEnde() == 4) {
            System.out.println("Spieler " + farbe + " hat GEWONNEN!!");
            turnier.beenden();
        };
        wuerfler = new Einmal();
        return true;
    }

    /** 
     * Der Spieler, der dran ist, mag nicht weiterspielen und
     * scheidet aus.
     */
    private void brichAb()  {
        for (int i = 0; i < 4; i++) {
           figuren[i].insNest();
        };
        wuerfler = new Nicht();
        Spieler akt = this;
        while (akt.getNaechster() != this) {
           akt = akt.getNaechster();
        };
        akt.setNaechster(this.getNaechster());
        aktiv = false;
        turnier.ausscheiden();
    }

    /** 
     * Anzahl der unbeweglichen Figuren 
     * im Haus des Spielers (wichtig für Sieg
     * bzw. wie oft man würfeln darf).
     */
    private int hausEnde()  {
        Feld akt = hausEingang;
        for (int i = 0; i < 3; i++) {
           akt = akt.getNachfolger();
        };
        int ergebnis = 0;
        while (ergebnis < 4 && akt.getFigur() != null) {
           ergebnis++;
           akt = akt.getVorgaenger();
        };
        return ergebnis;    
    }

    /** 
     * Wenn alle Figuren des Spielers unbeweglich sind,
     * dann würfle als nächstes dreimal.
     */
    public void pruefeBeweglichkeit()  {
        int unbewegliche = 0;
        for (int i = 0; i < 4; i++) {
           if (figuren[i].imNest())
              unbewegliche++;
        };
        unbewegliche += hausEnde();
        if (unbewegliche == 4)
            wuerfler = new Dreimal();
    }

    /** 
     * Hat der Spieler eine Figur im Nest?
     */
    private boolean figurImNest()  {
        for (int i = 0; i < 4; i++) {
           if (figuren[i].imNest())
              return true;
        };
        return false;
    }

}

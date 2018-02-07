package ca.mcgill.ecse223.tileo.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class SquareButton extends JButton {

    SquareButton(String s) {
        super(s);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        int s = (int)(d.getWidth()<d.getHeight() ? d.getHeight() : d.getWidth());
        return new Dimension (s,s);
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                JComponent gui = new JPanel(new GridLayout(12,12));
                for (int y=0; y<144; y++) {
                    
                    //for(int x = 0; x < 12; x++){
                    	gui.add(new SquareButton(""));
                    //}
                    	
                }

                gui.setBorder(new EmptyBorder(4, 8, 4, 8));

                JFrame f = new JFrame("Square Buttons");
                f.add(gui);
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See http://stackoverflow.com/a/7143398/418556 for demo.
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // should be done last, to avoid flickering, moving,
                // resizing artifacts.
                f.setVisible(true);
            }
        };

        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        SwingUtilities.invokeLater(r);
    }
}
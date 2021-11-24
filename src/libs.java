import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class libs extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.red);

        DirectedWeightedGraph_ t = null;
        try {
            t = new DirectedWeightedGraph_("data/G1.json");
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < t.nodeSize() ; i++) {
           double x = t.getNode(i).getLocation().x()*10000;
           double y = t.getNode(i).getLocation().y()*10000;
           x=x%500;
           y=y%600;

            g2d.fillOval((int)x, (int)y, 5, 5);
        }



    }
}

package lk.tchk.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JLabel;


public class Label extends JLabel{
    
    private final int arc = 20;

    public Label() {
        init();
    }

    private void init() {
        setOpaque(false);
        putClientProperty(FlatClientProperties.STYLE, "arc: " + arc);
    }
    
}

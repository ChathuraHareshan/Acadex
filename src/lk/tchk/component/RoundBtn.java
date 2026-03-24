
package lk.tchk.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;


public class RoundBtn extends JButton {
    
    private final int arc = 20;

    public RoundBtn() {
        init();
    }

    private void init() {
        setOpaque(false);
        putClientProperty(FlatClientProperties.STYLE, "arc: " + arc);
    }
    
}

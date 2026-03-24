
package lk.tchk.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPasswordField;


public class inputPasswordField extends JPasswordField {
    
    private final int arc = 10;

    public inputPasswordField() {
        init();
    }

    private void init() {
        setOpaque(false);
        putClientProperty(FlatClientProperties.STYLE, "arc: " + arc);
    }

    
    
}

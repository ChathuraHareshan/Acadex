package lk.tchk.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JComboBox;

public class ComboBox extends JComboBox {

    private final int arc = 20;

    public ComboBox() {  
        init();
    }

    private void init() {
        setOpaque(false);
        putClientProperty(FlatClientProperties.STYLE, "arc: " + arc);
    }
}

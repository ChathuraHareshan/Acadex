package lk.tchk.component;


import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JFormattedTextField;

public class FormattedTextField extends JFormattedTextField {
    private final int arc = 10;

    public FormattedTextField() {
        init();
    }

    private void init() {
        setOpaque(false);
        putClientProperty(FlatClientProperties.STYLE, "arc: " + arc);
    }
}

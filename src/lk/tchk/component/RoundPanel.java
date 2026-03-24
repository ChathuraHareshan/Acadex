
package lk.tchk.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;

public class RoundPanel extends JPanel {

    private final int arc = 30;

    public RoundPanel() {
        init();
    }

    private void init() {
        setOpaque(false);
        putClientProperty(FlatClientProperties.STYLE, "arc: " + arc);
    }

}

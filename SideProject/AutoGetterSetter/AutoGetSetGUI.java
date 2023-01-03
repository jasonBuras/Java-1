import javax.swing.*;

public class AutoGetSetGUI extends javax.swing.JDialog {
    private javax.swing.JPanel theGUI;
    private javax.swing.JButton buttonOK;
    private javax.swing.JButton buttonCancel;
    private JTextField tfVariableName;
    private JRadioButton isInt;
    private JRadioButton isDouble;
    private JTextPane tpOutput;
    private JCheckBox staticCheckBox;
    private ButtonGroup dataType;

    public AutoGetSetGUI() {
        setContentPane(theGUI);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onOK();
            }
                String variableName=tfVariableName.getText();

        });

        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        theGUI.registerKeyboardAction(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onCancel();
            }
        }, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }



    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        AutoGetSetGUI dialog = new AutoGetSetGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}

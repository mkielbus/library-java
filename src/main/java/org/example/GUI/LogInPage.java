package org.example.GUI;

import lombok.Getter;
import org.example.LibraryContextPackage.LibraryContext;
import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;


class LogInPage extends JFrame implements ActionListener {
    private static final int width = 700;
    private static final int height = 600;
    // private variables
    @Getter
    private JTextField login_field;
    @Getter
    private JPasswordField password_field;
    private ComponentDesigner.OptionButton login_button;
    private ComponentDesigner.OptionButton exit_button;
    @Getter
    private JCheckBox userbox;
    private ButtonGroup checksGroup;
    @Getter
    private JCheckBox adminbox;
    private JLabel box_prompt;
    private SpringLayout layout;

    private JPanel box_panel;

    public LogInPage() {
        int x_pos = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int y_pos = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        this.setBounds(x_pos, y_pos, width, height);

        this.setResizable(false);
        this.layout = new SpringLayout();
        this.setLayout(this.layout);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initContent();
        this.setVisible(true);
    }

    private void initContent() {
        this.getContentPane().setBackground(Color.ORANGE);
        this.initPrompt();
        this.initLogging();
        this.initButtons();
        this.initCheckBocks();
    }

    private void initLogging() {
        this.login_field = (JTextField) ComponentDesigner.makeBorderedComponent(new JTextField(), 3, 3, 3,3);

        var login_label = new JLabel("Login:");
        login_label.setFont(ComponentDesigner.getDefaultFont(25));
        login_label.setHorizontalAlignment(SwingConstants.LEFT);
        login_label.setOpaque(false);

        this.password_field = (JPasswordField) ComponentDesigner.makeBorderedComponent(new JPasswordField(), 3, 3, 3, 3);

        var password_label = new JLabel("Password:");
        password_label.setFont(ComponentDesigner.getDefaultFont(25));
        password_label.setHorizontalAlignment(SwingConstants.LEFT);
        password_label.setOpaque(false);

        this.layout.putConstraint(SpringLayout.WEST, login_label, 80, SpringLayout.WEST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.EAST, login_label, 230, SpringLayout.WEST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, login_label, 40, SpringLayout.SOUTH, LibraryGUI.main_prompt);
        this.layout.putConstraint(SpringLayout.SOUTH, login_label, 80, SpringLayout.SOUTH, LibraryGUI.main_prompt);

        this.layout.putConstraint(SpringLayout.WEST, this.login_field, 20, SpringLayout.EAST, login_label);
        this.layout.putConstraint(SpringLayout.EAST, this.login_field, -80, SpringLayout.EAST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, this.login_field, 40, SpringLayout.SOUTH, LibraryGUI.main_prompt);
        this.layout.putConstraint(SpringLayout.SOUTH, this.login_field, 80, SpringLayout.SOUTH, LibraryGUI.main_prompt);

        this.layout.putConstraint(SpringLayout.WEST, password_label, 80, SpringLayout.WEST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.EAST, password_label, 230, SpringLayout.WEST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, password_label, 20, SpringLayout.SOUTH, login_label);
        this.layout.putConstraint(SpringLayout.SOUTH, password_label, 60, SpringLayout.SOUTH, login_label);

        this.layout.putConstraint(SpringLayout.WEST, this.password_field, 20, SpringLayout.EAST, password_label);
        this.layout.putConstraint(SpringLayout.EAST, this.password_field, -80, SpringLayout.EAST, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, this.password_field, 20, SpringLayout.SOUTH, this.login_field);
        this.layout.putConstraint(SpringLayout.SOUTH, this.password_field, 60, SpringLayout.SOUTH, this.login_field);

        this.add(login_label);
        this.add(this.login_field);
        this.add(password_label);
        this.add(this.password_field);
    }

    private void initButtons() {
        this.login_button = ComponentDesigner.makeOptionButton("Log in");
        this.login_button.addActionListener(this);
        this.login_button.setAction_manager(new AppLogger());
        this.exit_button = ComponentDesigner.makeOptionButton("EXIT");
        this.exit_button.addActionListener(this);
        this.exit_button.setAction_manager(new Exiter());

        this.layout.putConstraint(SpringLayout.SOUTH, this.exit_button, -20, SpringLayout.SOUTH, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, this.exit_button, -80, SpringLayout.SOUTH, this.getContentPane());
        this.layout.putConstraint(SpringLayout.WEST, this.exit_button, -100, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        this.layout.putConstraint(SpringLayout.EAST, this.exit_button, 100, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());

        this.layout.putConstraint(SpringLayout.SOUTH, this.login_button, -20, SpringLayout.NORTH, this.exit_button);
        this.layout.putConstraint(SpringLayout.NORTH, this.login_button, -80, SpringLayout.NORTH, this.exit_button);
        this.layout.putConstraint(SpringLayout.WEST, this.login_button, -100, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        this.layout.putConstraint(SpringLayout.EAST, this.login_button, 100, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());

        this.add(this.exit_button);
        this.add(this.login_button);
    }

    private void initCheckBocks() {
        this.box_panel = new JPanel();

        this.userbox = new JCheckBox("User");
        this.userbox.setFont(ComponentDesigner.getDefaultFont(20));
        this.userbox.setFocusPainted(false);
        this.userbox.setOpaque(false);

        this.adminbox = new JCheckBox("Admin");
        this.adminbox.setFont(ComponentDesigner.getDefaultFont(20));
        this.userbox.setFocusPainted(false);
        this.adminbox.setOpaque(false);

        this.checksGroup = new ButtonGroup();
        this.checksGroup.add(userbox);
        this.checksGroup.add(adminbox);

        this.layout.putConstraint(SpringLayout.WEST, this.userbox, -100, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        this.layout.putConstraint(SpringLayout.EAST, this.userbox, -10, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        this.layout.putConstraint(SpringLayout.SOUTH, this.userbox, -210, SpringLayout.SOUTH, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, this.userbox, -300, SpringLayout.SOUTH, this.getContentPane());

        this.layout.putConstraint(SpringLayout.WEST, this.adminbox, 10, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        this.layout.putConstraint(SpringLayout.EAST, this.adminbox, 100, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        this.layout.putConstraint(SpringLayout.SOUTH, this.adminbox, -210, SpringLayout.SOUTH, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, this.adminbox, -300, SpringLayout.SOUTH, this.getContentPane());

        this.add(this.userbox);
        this.add(this.adminbox);
    }

    private void initPrompt() {
        LibraryGUI.main_prompt.setFont(ComponentDesigner.getDefaultFont(40));
        LibraryGUI.main_prompt.setHorizontalAlignment(SwingConstants.CENTER);
        LibraryGUI.main_prompt.setText("Welcome!");

        this.layout.putConstraint(SpringLayout.WEST, LibraryGUI.main_prompt, -300, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        this.layout.putConstraint(SpringLayout.EAST, LibraryGUI.main_prompt, 300, SpringLayout.HORIZONTAL_CENTER, this.getContentPane());
        this.layout.putConstraint(SpringLayout.SOUTH, LibraryGUI.main_prompt, 80, SpringLayout.NORTH, this.getContentPane());
        this.layout.putConstraint(SpringLayout.NORTH, LibraryGUI.main_prompt, 40, SpringLayout.NORTH, this.getContentPane());

        this.add(LibraryGUI.main_prompt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof ComponentDesigner.OptionButton button) {
            button.getAction_manager().manage(new JPanel());
        }
    }
}
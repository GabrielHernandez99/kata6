/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata6.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import kata6.control.Command;
import kata6.control.DownCommand;
import kata6.control.LeftCommand;
import kata6.control.RightCommand;
import kata6.control.UpCommand;
import kata6.model.Block;
import kata6.view.BlockDisplay;

/**
 *
 * @author gabri
 */
public class Main extends JFrame{

    private Block block;
    private BlockDisplay blockDisplay;
    private Map<String,Command> commands;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main().execute();
        // TODO code application logic here
    }
    public Main(){
    this.setTitle("Block shifter");
    this.setSize(700,720);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().add(blockPanel());
    this.add(toolbar(),BorderLayout.SOUTH);        
    }

    private void execute() {
        this.block=new Block();
        this.blockDisplay.display(block);
        this.block.register(blockDisplay);
        this.commands=createCommands();
        this.setVisible(true);
    
    }

    private BlockPanel blockPanel() {
        BlockDisplay blockPanel=new BlockPanel();
        this.blockDisplay=blockPanel;
        return (BlockPanel) blockPanel;
    }

    private Component toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(button("L"));
        toolbar.add(button("R"));
        toolbar.add(button("U"));
        toolbar.add(button("D"));
        return toolbar;
    }

    private HashMap<String, Command> createCommands() {
        HashMap<String,Command> comms = new HashMap<String,Command>();
        comms.put("U", new UpCommand(this.block));
        comms.put("D", new DownCommand(this.block));
        comms.put("L", new LeftCommand(this.block));
        comms.put("R", new RightCommand(this.block));
        return comms;
    }

    private JButton button(String l) {
        JButton boton=new JButton(l);
        boton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(l).execute();
            }
        
        });
        return boton;
                
    }
    
}

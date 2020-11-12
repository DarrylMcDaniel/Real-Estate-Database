/*

 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public class Project4 {

    private static TreeMap <Integer,Property> propertyRecords = new TreeMap<>();


    public static void main(String[] args) {

        JFrame databaseFrame = new JFrame();
        JPanel databasePanel = new JPanel();
        String[] labelNames = {"Transaction No:", "Address:", "Bedrooms:", "Square Footage:", "Price:"};
        String[] action = {"Insert", "Delete", "Find"};
        Label[] arrayLbls = new Label[5];
        TextField[] arrayTF = new TextField[5];
        Button processBtn = new Button("Process");
        Button changeStatusBtn = new Button("Change Status");
        JComboBox actionCB = new JComboBox(action);
        JComboBox statusCB = new JComboBox(Status.values());



        for(int i = 0; i < arrayLbls.length; i++) {
            arrayLbls[i] = new Label(labelNames[i]);
            arrayTF[i] = new TextField(20);
            databasePanel.add(arrayLbls[i]);
            databasePanel.add(arrayTF[i]);
        }

        processBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (actionCB.getSelectedItem() == action[0]) {
                        Property propertyInput = new Property(arrayTF[1].getText(), Integer.parseInt(arrayTF[2].getText()), Integer.parseInt(arrayTF[3].getText()), Integer.parseInt(arrayTF[4].getText()));
                        if (propertyRecords.containsKey(Integer.parseInt(arrayTF[0].getText()))){
                            JOptionPane.showMessageDialog(databasePanel,"Duplicate Entry");
                            System.out.println(propertyRecords.size());
                            }
                        else {
                            propertyRecords.put(Integer.parseInt(arrayTF[0].getText()), propertyInput);
                            JOptionPane.showMessageDialog(databasePanel, "Transaction Added");
                        }
                    }
                    if (actionCB.getSelectedItem() == action[1]) {
                        if (!propertyRecords.containsKey(Integer.parseInt(arrayTF[0].getText()))){
                            JOptionPane.showMessageDialog(databasePanel,"Entry not found");
                        }
                        else {
                            propertyRecords.remove(Integer.parseInt(arrayTF[0].getText()));
                            JOptionPane.showMessageDialog(databasePanel, "Transaction Removed");
                        }
                    }
                    if (actionCB.getSelectedItem() == action[2]) {
                        if (!propertyRecords.containsKey(Integer.parseInt(arrayTF[0].getText()))){
                            JOptionPane.showMessageDialog(databasePanel,"Entry not found");
                        }
                        else {
                            JOptionPane.showMessageDialog(databasePanel, propertyRecords.get(Integer.parseInt(arrayTF[0].getText())).toString());
                        }

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(databasePanel, "Invalid Input");
                }
            }
            });

        changeStatusBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusCB.getSelectedItem() == Status.FOR_SALE)
                    propertyRecords.get(Integer.parseInt(arrayTF[0].getText())).changeState(Status.FOR_SALE);
                if (statusCB.getSelectedItem() == Status.UNDER_CONTRACT)
                    propertyRecords.get(Integer.parseInt(arrayTF[0].getText())).changeState(Status.UNDER_CONTRACT);
                if (statusCB.getSelectedItem() == Status.SOLD)
                    propertyRecords.get(Integer.parseInt(arrayTF[0].getText())).changeState(Status.SOLD);
            }
        });



        databasePanel.add(processBtn);
        databasePanel.add(actionCB);
        databasePanel.add(changeStatusBtn);
        databasePanel.add(statusCB);

        databasePanel.setLayout(new GridLayout(7,2,10,15));

        databaseFrame.add(databasePanel);

        databaseFrame.setTitle("Real Estate Database");
        databaseFrame.setLocationRelativeTo(null);
        databaseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        databaseFrame.pack();
        databaseFrame.setVisible(true);
    }
}

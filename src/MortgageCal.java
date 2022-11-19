import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class MortgageCal extends JFrame{

    private JPanel panelMain;
    private JTextField valuePrincipal;
    private JButton btnClick;
    private JTextField valueAnnualInterestrate;
    private JTextField valuePeriodYears;

    public MortgageCal() {
        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final byte PERCENT = 100;
                final byte monthsPerYear = 12;
                boolean value1;
                boolean value2;
                boolean value3; // Sri Lanka
                int principal = 0;
                int annualIntRate = 0;
                int periodInYears = 0;

                try {
                principal = Integer.valueOf(valuePrincipal.getText());
                value1 = true;
                if (1000 > principal || principal > 1_000_000) {
                    JOptionPane.showMessageDialog(btnClick, " Invalid input. Enter principal between 1K - 1000K");
                    value1 = false;
                    }
                }
                catch (Exception valuePrincipal){
                    JOptionPane.showMessageDialog(btnClick,"Your input is invalid, please try again");
                }


                try {
                    annualIntRate = Integer.valueOf(valueAnnualInterestrate.getText());
                    value2 = true;
                    if (0>annualIntRate || annualIntRate>30) {
                        JOptionPane.showMessageDialog(btnClick, " Invalid input. Enter rate upto 30%");
                        value2 = false;
                    }
                }
                catch (Exception valuePrincipal){
                    JOptionPane.showMessageDialog(btnClick,"Your input is invalid, please try again");
                }


                try {
                    periodInYears = Integer.valueOf(valuePeriodYears.getText());
                    value3 = true;
                    if (1>periodInYears) {
                        JOptionPane.showMessageDialog(btnClick, " Invalid input. Enter value >=1");
                        value3 = false;
                    }
                }
                catch (Exception valuePrincipal){
                    JOptionPane.showMessageDialog(btnClick,"Your input is invalid, please try again");
                }


                double interestRateMonthly = Float.valueOf(annualIntRate) / PERCENT / monthsPerYear;
                double periodInMonths = periodInYears * monthsPerYear;

                double mortgage = (principal * interestRateMonthly * Math.pow((1+interestRateMonthly),periodInMonths)) / (Math.pow((1+interestRateMonthly),periodInMonths) - 1);

                NumberFormat mortgageInCurrency = NumberFormat.getCurrencyInstance(Locale.US);
                String finalValue = mortgageInCurrency.format(mortgage);

                //JOptionPane.showMessageDialog(btnClick,"Your input is OK");
                JOptionPane.showMessageDialog(btnClick,"Mortgage: " + finalValue);

                }
        });
    }

    public static void main(String[] args) {
        MortgageCal temp = new MortgageCal();
        temp.setContentPane(temp.panelMain);
        temp.setTitle("Mortgage Calculator");
        temp.setBounds(300,200,500,200);
        temp.setVisible(true);
        temp.getContentPane().setBackground(Color.YELLOW);
        temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}


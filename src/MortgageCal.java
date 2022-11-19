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
                boolean value0 = false; //To check if the inputs are valid
                boolean value1 = false; //To check if the principal value is in the acceptable region
                boolean value2 = false; //To check if the Interest rate (Yearly) is in the acceptable region
                boolean value3 = false; //To check if the time period (years) is a positive integer
                int principal = 0;
                int annualIntRate = 0;
                int periodInYears = 0;

                try {
                principal = Integer.valueOf(valuePrincipal.getText());

                if (1000 > principal || principal > 1_000_000) {
                    value1 = true;
                    }
                }
                catch (Exception valuePrincipal){
                    value0 = true;
                }


                try {
                    annualIntRate = Integer.valueOf(valueAnnualInterestrate.getText());

                    if (0>annualIntRate || annualIntRate>30) {
                        value2 = true;
                    }
                }
                catch (Exception valuePrincipal){
                    value0 = true;
                }


                try {
                    periodInYears = Integer.valueOf(valuePeriodYears.getText());

                    if (1>periodInYears) {
                        value3 = true;
                    }
                }
                catch (Exception valuePrincipal){
                    value0 = true;
                }


                if (value0) {
                    JOptionPane.showMessageDialog(btnClick, "Your input is invalid, please try again");
                }
                else if (value1) {
                    JOptionPane.showMessageDialog(btnClick, " Invalid input. Enter principal between 1K - 1000K");
                }
                else if (value2) {
                    JOptionPane.showMessageDialog(btnClick, " Invalid input. Enter rate upto 30%");
                }
                else if (value3) {
                    JOptionPane.showMessageDialog(btnClick, " Invalid input. Enter value >=1");
                }

                else {

                    double interestRateMonthly = Float.valueOf(annualIntRate) / PERCENT / monthsPerYear;
                    double periodInMonths = periodInYears * monthsPerYear;

                    double mortgage = (principal * interestRateMonthly * Math.pow((1 + interestRateMonthly), periodInMonths)) / (Math.pow((1 + interestRateMonthly), periodInMonths) - 1);

                    NumberFormat mortgageInCurrency = NumberFormat.getCurrencyInstance(Locale.US);
                    String finalValue = mortgageInCurrency.format(mortgage);

                    JOptionPane.showMessageDialog(btnClick, "Mortgage: " + finalValue);
                }

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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bank;

import static bank.Cuenta.menu;
import java.util.ArrayList;

/**
 *
 * @author emche
 */
public class Bank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Account> cuenta = new ArrayList();
        int opc = 0;
        Cuenta.crearCuenta(cuenta);
        do {
            opc = menu();
            switch (opc) {
                case 1:Cuenta.consultarSaldo(cuenta);
                    break;
                case 2:Cuenta.ingresarDinero(cuenta);
                    break;
                case 3:Cuenta.sacarDinero(cuenta);
                    break;
                case 4:Cuenta.transferencia(cuenta);
                    break;

            }
        } while (opc < 5);
        
    }
}

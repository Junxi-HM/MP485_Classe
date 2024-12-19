/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author emche
 */
public class Cuenta {

    public static void crearCuenta(ArrayList<Account> cuenta) {
        //Crear 2 cuentas
        int idnum = 0;
        String holder = "";
        double balance = 0;

        for (idnum = 1; idnum < 3; idnum++) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese el nombre del titular de la cuenta " + idnum + " :");
            holder = sc.nextLine();
            System.out.print("Ingrese el saldo inicial de la cuenta " + idnum + " :");
            balance = sc.nextDouble();
            Account acc = new Account(idnum, holder, balance);
            cuenta.add(acc);
        }

    }

    public static void consultarSaldo(ArrayList<Account> cuenta) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        String titular = "";
        System.out.print("Ingrese el nombre del titular: ");
        titular = sc.nextLine();

        for (int i = 1; i < cuenta.size(); i++) {
            if (cuenta.get(i).getHolder().equalsIgnoreCase(titular)) {
                num = i;
                break;
            }
        }
        if (cuenta.get(num).getHolder().equalsIgnoreCase(titular)) {
            System.out.println("El saldo de la cuenta es: " + cuenta.get(num).getBalance());
        } else {
            System.out.println("No existe Titular");
        }
    }

    public static void ingresarDinero(ArrayList<Account> cuenta) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        System.out.print("Ingrese el nombre del titular: ");
        String titular = sc.nextLine();
        System.out.print("Ingresar la cantidad a ingresar: ");
        double dinero = sc.nextDouble();
        for (int i = 1; i < cuenta.size(); i++) {
            if (cuenta.get(i).getHolder().equalsIgnoreCase(titular)) {
                num = i;
                break;
            }
        }
        if (cuenta.get(num).getHolder().equalsIgnoreCase(titular)) {
            cuenta.get(num).setBalance(dinero + cuenta.get(num).getBalance());
            System.out.println("El dinero ha sido ingresado en la cuenta. ");
        } else {
            System.out.println("No existe Titular");
        }
    }

    public static void sacarDinero(ArrayList<Account> cuenta) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        System.out.print("Ingrese el nombre del titular: ");
        String titular = sc.nextLine();
        System.out.print("Ingresar la cantidad a retirar: ");
        double dinero = sc.nextDouble();
        for (int i = 1; i < cuenta.size(); i++) {
            if (cuenta.get(i).getHolder().equalsIgnoreCase(titular)) {
                num = i;
                break;
            }
        }

        if (cuenta.get(num).getHolder().equalsIgnoreCase(titular)) {
            if (cuenta.get(num).getBalance() < dinero) {
                System.out.println("No hay saldo suficiente");
            } else {
                cuenta.get(num).setBalance(cuenta.get(num).getBalance() - dinero);
                System.out.println("El dinero ha sido retirado de la cuenta.");
            }
        } else {
            System.out.println("No existe Titular");
        }

    }

    public static void transferencia(ArrayList<Account> cuenta) {
        Scanner sc = new Scanner(System.in);
        int origNum = 0;
        int destNum = 0;
        System.out.print("Ingrese el nombre del titular origen: ");
        String origTitular = sc.nextLine();
        System.out.print("Ingrese el nombre del titular destinto: ");
        String destTitular = sc.nextLine();
        System.out.print("Ingresar la cantidad a transferir: ");
        double dinero = sc.nextDouble();

        for (int i = 1; i < cuenta.size(); i++) {
            if (cuenta.get(i).getHolder().equalsIgnoreCase(origTitular)) {
                origNum = i;
                break;
            }
            if (cuenta.get(i).getHolder().equalsIgnoreCase(destTitular)) {
                destNum = i;
                break;
            }
        }

        if (!cuenta.get(origNum).getHolder().equalsIgnoreCase(origTitular)) {
            System.out.println("Titular origen no existe");
        } else if (!cuenta.get(destNum).getHolder().equalsIgnoreCase(destTitular)) {
            System.out.println("Titular destinto no existe");
        } else {
            if (cuenta.get(origNum).getBalance() < dinero) {
                System.out.println("No hay saldo suficiente");
            } else {
                cuenta.get(origNum).setBalance(cuenta.get(origNum).getBalance() - dinero);
                cuenta.get(destNum).setBalance(cuenta.get(destNum).getBalance() + dinero);
                System.out.println("La transferencia se realizo con exito");
            }
        }

    }

    public static int menu() {
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        System.out.println("1. Consultar saldo");
        System.out.println("2. Ingresar dinero");
        System.out.println("3. Sacar dinero");
        System.out.println("4. Realizar transferencia");
        System.out.println("5. Salir");
        System.out.print("Ingrese una opcion: ");
        opc = sc.nextInt();
        return opc;
    }

}

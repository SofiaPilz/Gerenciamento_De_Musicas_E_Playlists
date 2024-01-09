package org.example;

import org.example.controllers.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Controller controller = new Controller();

        while (true) {
            System.out.println("---------- MENU ---------");
            System.out.println("  1. cadastrar musica");
            System.out.println("   2. criar playlist");
            System.out.println("  3. juntar playlists");
            System.out.println("    4. reproduzir");
            System.out.println("-------------------------");

            while (true) {
                try {
                int acao = in.nextInt();
                in.nextLine();

                try {
                    switch (acao) {

                        case 1:
                            controller.register();
                            break;

                        case 2:
                            controller.createPlaylist();
                            break;

                        case 3:
                            controller.unirPlaylist();
                            break;

                        case 4:
                            controller.reproduce();
                            break;

                        default:
                            System.out.println("Erro, digite um código válido!");
                            break;

                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro, código inválido. Tente novamente: ");
                    in.nextLine();
                }
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Erro, código inválido. Tente novamente: ");
                    in.nextLine();
                }
            }
        }
    }
}
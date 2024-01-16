package org.example;

import org.example.controllers.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Controller controller = new Controller();

        while (true) {
            System.out.println("------------ MENU -----------");
            System.out.println("    1. cadastrar musica");
            System.out.println("2. ver biblioteca de musicas");
            System.out.println("     3. ver playlist");
            System.out.println("     4. criar playlist");
            System.out.println("    5. juntar playlists");
            System.out.println("      6. reproduzir");
            System.out.println("-----------------------------");

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
                            controller.viewBiblioteca();
                            break;
                        case 3:
                            controller.exibirPlaylists();
                            break;

                        case 4:
                            controller.createPlaylist();
                            break;

                        case 5:
                            controller.unirPlaylist();
                            break;

                        case 6:
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
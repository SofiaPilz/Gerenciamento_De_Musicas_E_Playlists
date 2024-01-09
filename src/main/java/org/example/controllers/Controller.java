package org.example.controllers;

import org.example.models.Music;


import java.util.*;

public class Controller {
    Scanner in = new Scanner(System.in);
    List<Music> biblioteca = new ArrayList<>();
    static TreeMap<String, List<Music>> allPlaylist = new TreeMap<>();


    public void register() {
        int controle = 9;

        while (true) {
            Music music = new Music();

            System.out.println("CADASTRO DE MUSICAS: ");
            System.out.println("Título: ");
            String titulo = in.nextLine();
            music.setTitle(titulo);


            System.out.println("Artista: ");
            String artista = in.nextLine();
            music.setSingle(artista);


            System.out.println("Duração em segundos: ");
            while (true) {
                try {
                    int tempo = in.nextInt();
                    in.nextLine();
                    music.setTime(tempo);
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Digite um tempo válido, tente novamente");
                    in.nextLine();
                }
            }

            biblioteca.add(music);

            System.out.println("Deseja adicionar mais musicas? 0-SIM 1-NAO");

            while (true) {
                int opcao = in.nextInt();
                in.nextLine();
                try {
                    if (opcao == 0) {
                        controle = 0;
                        break;
                    } else if (opcao == 1) {
                        controle = 1;
                        break;
                    } else {
                        System.out.println("Digite um comando válido, tente novamente:");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro, digite um codigo válido!");
                    in.nextLine();
                }
            }
            if (controle == 1) {
                break;
            } else {
            }
        }
    }

    public void createPlaylist() {
        int controle = 9;
        List<Music> playlist = new ArrayList<>();

        System.out.println("Escolha um nome para a playlist: ");
        String nomePlaylist = in.nextLine();

        while (true) {
            exibirBiblioteca();
            System.out.println("Indique o índice da musica que deseja adicionar: ");

            try {
                int opcao = (in.nextInt() - 1);
                in.nextLine();

                // n ta funcionando
                for (Music music : playlist) {
                    if (music != biblioteca.get(opcao)) {
                        playlist.add(biblioteca.get(opcao));
                    } else {
                        System.out.println("Esta música ja esta na playlist! Adicione outras.");
                    }
                }
                System.out.println("-----PLAYLIST-----");
                for (Music music : playlist) {
                    System.out.println(music.getTitle());
                }
                System.out.println("-------------------");

                while (true) {
                    System.out.println("Deseja adicionar mais alguma musica? 0-SIM 1-NAO");
                    try {
                        int acao = in.nextInt();
                        in.nextLine();

                        if (acao == 0) {
                            controle = 0;
                            break;
                        } else if (acao == 1) {
                            allPlaylist.put(nomePlaylist, playlist);
                            controle = 1;
                            break;
                        } else {
                            System.out.println("Digite um comando válido, tente novamente:");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Erro, código inválido. Tente novamente: ");
                        in.nextLine();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro, código inválido. Tente novamente: ");
                in.nextLine();
            }
            if (controle == 1) {
                break;
            } else {
            }
        }

    }

    public void unirPlaylist() {
        List<Music> apoio = new ArrayList<>();
        List<Music> newPlaylist = new ArrayList<>();

        System.out.println("Escolha o nome da nova playlist: ");
        String newNamePlaylist = in.nextLine();

        int numeroPlaylists = (exibirPlaylists() - 1);

        while (true) {
            System.out.println("Indique o indice da primeira playlist: ");
            while (true) {
                try {
                    int index1 = (in.nextInt() - 1);
                    in.nextLine();
                    // tem erro nos inicess n ta puxando o nome da playlist certa
                    // a index2 tb deve ta com problema
                    if (index1 <= numeroPlaylists && index1 > 0) {
                        String chave1 = verificarKey(index1);
                        newPlaylist.addAll(allPlaylist.get(chave1));
                        allPlaylist.remove(chave1);
                        break;

                    } else {
                        System.out.println("Indice inválido, tente novamente:");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro, código inválido. Tente novamente: ");
                    in.nextLine();
                }
            }

            System.out.println("Indique o indice da segunda playlist: ");
            while (true) {
                try {
                    int index2 = in.nextInt();
                    in.nextLine();

                    if (index2 <= numeroPlaylists && index2 > 0) {
                        String chave2 = verificarKey(index2);
                        apoio.addAll(allPlaylist.get(chave2));
                        for (Music music : newPlaylist) {
                            for (Music musica : apoio) {
                                if (musica != music) {
                                    newPlaylist.add(musica);
                                }
                            }
                        }
                        allPlaylist.remove(chave2);
                        break;

                    } else {
                        System.out.println("Indice inválido, tente novamente:");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Erro, código inválido. Tente novamente: ");
                    in.nextLine();
                }
            }
            for (Music music : newPlaylist) {
                System.out.println(music.getTitle());
            }
            allPlaylist.put(newNamePlaylist, newPlaylist);
            break;

        }
    }

    public void reproduce() {

    }


    public void exibirBiblioteca() {
        int i = 0;
        System.out.println("--------- BIBLIOTECA ----------");
        for (Music music : biblioteca) {
            i++;
            System.out.println(i + ") " + music.getTitle() + " - (" + music.getSingle() + ")");
        }
        System.out.println("------------------------------");
    }

    public int exibirPlaylists() {
        int i = 0;
        for (Map.Entry<String, List<Music>> playlist : allPlaylist.entrySet()) {
            i++;
            String chave = playlist.getKey();
            List<Music> conteudoPlaylist = playlist.getValue();
            System.out.println(i + ") " + chave);
        }
        return i ;
    }

    public String verificarKey(int index) {
        while (true) {
            try {
                String chave = "o";
                for (String key : allPlaylist.keySet()) {
                    for (int i = 0; i < index; i++) {
                        chave = key;
                    }
                }
                return chave;
            } catch (InputMismatchException e) {
                System.out.println("Erro, código inválido. Tente novamente: ");
                in.nextLine();
            }
        }
    }
}

package org.example.controllers;

import org.example.models.Music;


import java.util.*;

public class Controller {
    Scanner in = new Scanner(System.in);
    List<Music> biblioteca = new ArrayList<>();
    List<String> namePlaylist = new ArrayList<>();
    static TreeMap<String, List<Music>> allPlaylist = new TreeMap<>();


    public void register() {
        int controle;

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
                try {
                    int opcao = in.nextInt();
                    in.nextLine();

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
            }
        }
    }

    public void viewBiblioteca() {
        int i = 0;
        if (biblioteca.isEmpty()) {
            System.out.println("Biblioteca vazia!");
        } else {
            System.out.println("-------------BIBLIOTECA-------------");
            for (Music music : biblioteca) {
                i++;
                System.out.println(i + ") " + music.getTitle() + " - " + music.getSingle());
                System.out.println("-----------------------------------");
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
                while (true) {
                    int opcao = (in.nextInt() - 1);
                    in.nextLine();

                    boolean verificacao = verificacaoMusic(playlist, biblioteca.get(opcao));

                    if (verificacao) {
                        playlist.add(biblioteca.get(opcao));
                        imprimirPLaylist(playlist, nomePlaylist);
                        break;
                    } else {
                        System.out.println("Esta música ja esta na playlist! Adicione outra: ");
                        exibirBiblioteca();
                    }
                }

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
            }
        }

    }

    // nao pegou musicas iguais
    public boolean verificacaoMusic(List<Music> playlist, Music musicaSelecionada) {
        boolean verificacao = true;
        for (Music music : playlist) {
            if (music.equals(musicaSelecionada)) {
                verificacao = false;
                break;
            }
        }
        return verificacao;
    }


    public void unirPlaylist() {
        List<Music> apoio = new ArrayList<>();
        List<Music> apoio2 = new ArrayList<>();
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
                    if (index1 <= numeroPlaylists && index1 >= 0) {
                        String namePlaylistSelect = namePlaylist.get(index1);
                        newPlaylist.addAll(allPlaylist.get(namePlaylistSelect));
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
                    int index2 = (in.nextInt() - 1);
                    in.nextLine();

                    if (index2 <= numeroPlaylists && index2 >= 0) {
                        String namePlaylistSelect2 = namePlaylist.get(index2);
                        apoio.addAll(allPlaylist.get(namePlaylistSelect2));

                        for (Music musica : apoio) {
                            boolean verificador = verificacaoMusic(newPlaylist, musica);
                            if (verificador) {
                                apoio2.add(musica);
                            }
                        }

                        newPlaylist.addAll(apoio2);
                        break;

                    } else {
                        System.out.println("Indice inválido, tente novamente:");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Erro, código inválido. Tente novamente: ");
                    in.nextLine();
                }
            }
            imprimirPLaylist(newPlaylist, newNamePlaylist);
            allPlaylist.put(newNamePlaylist, newPlaylist);
            break;
        }
    }

    public void reproduce() {
        int contagemSegundos = 0;
        int numeroPlaylists = (exibirPlaylists() - 1);
        System.out.println("Indique o indice da playlist que deseja ouvir: ");

        while (true) {
            try {
                int indice = (in.nextInt() - 1);
                in.nextLine();

                if (indice <= numeroPlaylists && indice >= 0) {
                    String chave = namePlaylist.get(indice);

                    for (Music music : allPlaylist.get(chave)) {
                        contagemSegundos += music.getTime();
                    }
                    System.out.println("Tempo de reprodução: " + contagemSegundos + " segundos");
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro, código inválido. Tente novamente: ");
                in.nextLine();
            }
        }
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


    public void imprimirPLaylist(List<Music> playlist, String name) {
        System.out.println("--------" + name + "--------");
        for (Music music : playlist) {
            System.out.println(music.getTitle());
            System.out.println("----------------------------");
        }
    }


    public int exibirPlaylists() {
        int i = 0;
        if (allPlaylist.isEmpty()) {
            System.out.println("biblioteca de playlists vazia!");
        } else {
            int contagemSegundos = 0;

            namePlaylist.clear();
            for (Map.Entry<String, List<Music>> playlist : allPlaylist.entrySet()) {
                i++;
                String chave = playlist.getKey();
                List<Music> conteudoPlaylist = playlist.getValue();

                for (Music music : conteudoPlaylist) {
                    contagemSegundos += music.getTime();
                }
                namePlaylist.add(chave);
                System.out.println(i + ") " + chave + " (" + contagemSegundos + " segundos de reprodução)");
            }
        }
        return i;
    }

} // CONTROLLER

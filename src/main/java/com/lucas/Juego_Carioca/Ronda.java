package com.lucas.Juego_Carioca;

import java.util.ArrayList;

public class Ronda {
    public static final int[][] rondas = {
            //{Escala, Trio} ; 6,7,8,9,10,11
            {0, 2}, // 0*4+2*3 = 6 (2 trios)
            {1, 1}, //1*4+1*3 = 7 (1 escala y 1 trio)
            {2, 0}, // 2 escalas
            {0, 3}, // 3 trios
            {1, 2}, // 1 escala y 2 trios
            {2, 1} //2 escalas y 1 trio
    };
    private int nivel;
    private ArrayList<Jugador> jugadores;
    private Mazo mazo;

    public Ronda(ArrayList<Jugador> jugadores, int nivel) {
        this.nivel = nivel;
        this.jugadores = jugadores;
        Mazo mazo = new Mazo();
    }

    public void menuJuego() {
        //Se crea un juego
        int nroEscala = rondas[nivel][0];
        int nroTrios = rondas[nivel][1];
        Mazo mazo = new Mazo();

        //Antes de comenzar a jugar, Entregamos a cada jugador doce cartas
        for (int index = 0; index < jugadores.size(); index++) {
            jugadores.get(index).setCartas(mazo.sacarUnNumeroDeCartas(12));
        }
        ArrayList<Carta> pozo = new ArrayList<Carta>(); //Este es el pozo donde los jugadores botan sus cartas

        pozo.add(mazo.sacarCarta()); //Se extrae una carta del mazo para dejarla en la mesa, al principio de la ronda (esta carta nunca sera un JKR)

        int turnoActual = (int) (Math.random() * jugadores.size()); //y el primer turno se hara de forma aleatoria

        //Imprimimos un mensaje que da comienzo el juego y cuantas escalas o/y trios hay que formar
        MainCarioca.imprimirTitulo("Comienza el juego nivel " + (nivel + 1) + "\n"
                + "Escala: " + nroEscala + " Trios: " + nroTrios);
        //Aqui comienza el juego, deberia haber un bucle donde va jugando cada jugador,
        //el juego termina cuando un jugador se queda sin cartas

        //Este Bucle funciona hasta que alguien se quede sin cartas (temporal, lo ideal remplezarlo por un metodo)
        do {
            Carta cartaEnLaMesa = pozo.get(0); //La carta que esta mas arriba en el pozo, este objeto es solo para proposito de imprimir en pantalla
            Jugador jugadorActual = jugadores.get(turnoActual);
            System.out.println("Turno Actual: " + turnoActual + " Nombre: " + jugadorActual.getNombre());
            System.out.println();
            System.out.println("Carta en la mesa: " + cartaEnLaMesa.toStringEC());
            System.out.println();
            System.out.println("Cartas del Jugador: ");
            jugadorActual.imprimirCartas();
            jugadorActual.menu_SacarCarta(pozo, mazo);
            if(jugadorActual.getCartas().size()==0){
                //Si el jugador Actual se quedo sin cartas, quiere decir que gano la partida
                System.out.println("Fin de la ronda, la ronda gano ");
                break;
            }
            //De lo contrario sigue el siguiente turno
            turnoActual++;
            if (turnoActual == jugadores.size()) {
                turnoActual = 0;
            }


        }while(true);
    }
}

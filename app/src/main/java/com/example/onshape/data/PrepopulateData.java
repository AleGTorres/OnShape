package com.example.onshape.data;

import java.util.ArrayList;
import java.util.List;

public class PrepopulateData {

    public static List<ExerciseLibrary> getInitialExercises() {
        List<ExerciseLibrary> exercises = new ArrayList<>();

        // --- PEITORAL ---
        exercises.add(new ExerciseLibrary(
                "Supino Reto com Barra", "Peitoral", "Barra e Banco Reto",
                "Deite-se em um banco reto. Pegue a barra com as mãos um pouco além da largura dos ombros. Desça a barra até tocar o meio do peito e empurre de volta à posição inicial."
        ));
        exercises.add(new ExerciseLibrary(
                "Supino Inclinado com Halteres", "Peitoral", "Halteres e Banco Inclinado",
                "Deite-se em um banco inclinado a 45 graus. Segure os halteres acima do peito com as palmas para a frente. Desça os halteres até a altura dos ombros e empurre para cima."
        ));
        exercises.add(new ExerciseLibrary(
                "Crucifixo com Halteres", "Peitoral", "Halteres e Banco Reto",
                "Deite-se em um banco reto com os halteres acima do peito, palmas viradas uma para a outra. Abra os braços em um arco amplo até sentir o alongamento no peito e retorne."
        ));
        exercises.add(new ExerciseLibrary(
                "Flexão de Braço", "Peitoral", "Peso Corporal",
                "Com as mãos no chão na largura dos ombros, mantenha o corpo reto. Desça o corpo até o peito quase tocar o chão e empurre de volta para a posição inicial."
        ));
        exercises.add(new ExerciseLibrary(
                "Mergulho nas Paralelas", "Peitoral", "Barras Paralelas",
                "Segure nas barras paralelas com os braços estendidos. Incline o corpo para a frente e desça até que os ombros fiquem abaixo dos cotovelos. Empurre para voltar ao início."
        ));

        // --- COSTAS ---
        exercises.add(new ExerciseLibrary(
                "Barra Fixa (Pull-up)", "Costas", "Barra Fixa",
                "Segure a barra com as mãos além da largura dos ombros, palmas para a frente. Puxe o corpo para cima até que o queixo passe da barra. Desça de forma controlada."
        ));
        exercises.add(new ExerciseLibrary(
                "Remada Curvada com Barra", "Costas", "Barra",
                "Com os joelhos levemente flexionados, incline o tronco para a frente mantendo as costas retas. Puxe a barra em direção à parte inferior do abdômen."
        ));
        exercises.add(new ExerciseLibrary(
                "Puxada Frontal (Lat Pulldown)", "Costas", "Máquina de Polia Alta",
                "Sente-se na máquina e segure a barra com uma pegada ampla. Puxe a barra para baixo até a parte superior do peito, contraindo os músculos das costas."
        ));
        exercises.add(new ExerciseLibrary(
                "Remada Unilateral com Halter (Serrote)", "Costas", "Halter e Banco",
                "Apoie um joelho e uma mão em um banco reto. Com o outro braço, puxe o halter para cima, ao lado do tronco, mantendo as costas retas."
        ));
        exercises.add(new ExerciseLibrary(
                "Levantamento Terra", "Costas", "Barra",
                "Agache com as costas retas para pegar a barra no chão. Levante-se estendendo as pernas e o quadril, mantendo a barra próxima ao corpo."
        ));

        // --- PERNAS ---
        exercises.add(new ExerciseLibrary(
                "Agachamento Livre com Barra", "Pernas", "Barra e Suporte",
                "Posicione a barra sobre os ombros (trapézio). Mantenha as costas retas, desça o quadril como se fosse sentar, até as coxas ficarem paralelas ao chão."
        ));
        exercises.add(new ExerciseLibrary(
                "Leg Press 45°", "Pernas", "Máquina de Leg Press",
                "Sente-se na máquina com os pés na plataforma. Empurre a plataforma para cima até estender as pernas (sem travar os joelhos) e retorne de forma controlada."
        ));
        exercises.add(new ExerciseLibrary(
                "Afundo com Halteres", "Pernas", "Halteres",
                "Dê um passo à frente com uma perna. Desça o corpo até que ambos os joelhos formem um ângulo de 90 graus. Retorne e alterne as pernas."
        ));
        exercises.add(new ExerciseLibrary(
                "Cadeira Extensora", "Pernas", "Máquina Extensora",
                "Sente-se na máquina e posicione os tornozelos sob o rolo. Estenda as pernas para cima, contraindo o quadríceps, e desça lentamente."
        ));
        exercises.add(new ExerciseLibrary(
                "Mesa Flexora", "Pernas", "Máquina Flexora",
                "Deite-se de bruços na máquina com os tornozelos sob o rolo. Flexione os joelhos, trazendo o rolo em direção aos glúteos."
        ));

        // --- OMBROS ---
        exercises.add(new ExerciseLibrary(
                "Desenvolvimento Militar com Barra", "Ombros", "Barra",
                "Em pé, segure a barra na altura dos ombros, palmas para a frente. Empurre a barra para cima até estender completamente os braços. Desça de forma controlada."
        ));
        exercises.add(new ExerciseLibrary(
                "Elevação Lateral com Halteres", "Ombros", "Halteres",
                "Em pé, segure um halter em cada mão ao lado do corpo. Levante os braços para os lados até a altura dos ombros, mantendo uma leve flexão nos cotovelos."
        ));
        exercises.add(new ExerciseLibrary(
                "Elevação Frontal com Halteres", "Ombros", "Halteres",
                "Em pé, segure os halteres na frente das coxas. Levante um braço de cada vez para a frente, até a altura dos ombros."
        ));
        exercises.add(new ExerciseLibrary(
                "Remada Alta", "Ombros", "Barra ou Halteres",
                "Segure a barra com as mãos próximas. Puxe a barra verticalmente até a altura do queixo, liderando com os cotovelos."
        ));
        exercises.add(new ExerciseLibrary(
                "Crucifixo Invertido na Máquina", "Ombros", "Máquina Pec Deck",
                "Sente-se virado para a máquina. Segure as alças e abra os braços para trás, contraindo a parte posterior dos ombros."
        ));

        // --- BÍCEPS ---
        exercises.add(new ExerciseLibrary(
                "Rosca Direta com Barra", "Bíceps", "Barra",
                "Em pé, segure a barra com as palmas para cima. Mantendo os cotovelos fixos ao lado do corpo, levante a barra até contrair totalmente o bíceps."
        ));
        exercises.add(new ExerciseLibrary(
                "Rosca Alternada com Halteres", "Bíceps", "Halteres",
                "Sentado ou em pé, segure um halter em cada mão. Levante um halter de cada vez, girando o pulso (supinação) durante o movimento."
        ));
        exercises.add(new ExerciseLibrary(
                "Rosca Martelo", "Bíceps", "Halteres",
                "Segure os halteres com uma pegada neutra (palmas viradas uma para a outra). Levante os pesos sem girar os pulsos."
        ));

        // --- TRÍCEPS ---
        exercises.add(new ExerciseLibrary(
                "Tríceps na Polia Alta (Pulley)", "Tríceps", "Máquina de Polia Alta",
                "Segure a barra ou corda com os cotovelos fixos ao lado do corpo. Empurre para baixo até estender completamente os braços, contraindo o tríceps."
        ));
        exercises.add(new ExerciseLibrary(
                "Tríceps Francês com Halter", "Tríceps", "Halter e Banco",
                "Deitado em um banco, segure um halter com as duas mãos acima da cabeça. Desça o peso para trás da cabeça, flexionando os cotovelos, e retorne."
        ));
        exercises.add(new ExerciseLibrary(
                "Mergulho no Banco", "Tríceps", "Banco",
                "Apoie as mãos em um banco atrás de você, com as pernas estendidas à frente. Desça o corpo flexionando os cotovelos e empurre para cima."
        ));

        // --- ABDÔMEN ---
        exercises.add(new ExerciseLibrary(
                "Prancha Abdominal", "Abdômen", "Peso Corporal",
                "Apoie os antebraços e os pés no chão. Mantenha o corpo reto como uma tábua, contraindo o abdômen e os glúteos. Segure a posição pelo tempo desejado."
        ));
        exercises.add(new ExerciseLibrary(
                "Elevação de Pernas na Barra Fixa", "Abdômen", "Barra Fixa",
                "Pendurado na barra, levante as pernas estendidas (ou joelhos flexionados para iniciantes) até a altura do quadril ou mais alto."
        ));
        exercises.add(new ExerciseLibrary(
                "Abdominal na Roda (Ab Wheel)", "Abdômen", "Roda Abdominal",
                "Ajoelhado, segure a roda e role para a frente o máximo que puder sem arquear as costas. Puxe de volta para a posição inicial usando a força do abdômen."
        ));
        exercises.add(new ExerciseLibrary(
                "Abdominal Russo com Peso", "Abdômen", "Anilha ou Kettlebell",
                "Sente-se no chão com os joelhos flexionados e o tronco inclinado para trás. Segure um peso e gire o tronco de um lado para o outro."
        ));

        return exercises;
    }
}
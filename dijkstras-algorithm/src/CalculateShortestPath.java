import java.util.*;
import java.util.stream.Stream;

public class CalculateShortestPath {

    public void execute(Node source) {

        // colocando a distancia inical como zero
        source.setDistance(0);

        // criando as listas resolvidas e nao resolvidas
        Set<Node> settledNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));

        // enquanto esta fila nao esteja vazia, retorno o no com a menor distancia dela
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();

            currentNode.getAdjacentNodes()
                    // na sequencia faça um loop pelos seus nós adjacentes
                    .entrySet().stream()

                    // filtre pelos nós que não estão resolvidos
                    .filter(entry -> !settledNodes.contains(entry.getKey()))

                    // atualize a distancia desse nó
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);

                        // por fim adicione esse nó na fila dos não resolvidos
                        unsettledNodes.add(entry.getKey());
                    });

            // depois de percorrer todos os nos adjacentes, adicionamos o no com o qual estamos trabalhando no momento ao conjunto de nos resolvidos
            settledNodes.add(currentNode);
        }
    }

    private void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {

        // Precisamos comparar a soma do peso da aresta e a distancia do no de origem que ele conecta com a distancia do destino
        Integer newDistance = sourceNode.getDistance() + edgeWeight;

        // se a distancia calculada for menor que a do no que estamos tentando alcancar
        if (newDistance < adjacentNode.getDistance()) {
            //Então encontramos um caminho menor e mais ideal ligando ao nó de origem
            // Para atualizar o caminho basta adicionar o no em que estamos ao caminho em questão
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(
                    Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                            .toList());
        }
    }
}

/**
 * This is a simple implementation of vertex degree computation.
 */
package ru.simple.giraph.project.org;

import com.google.common.collect.Iterables;
import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class EuclideanDistancesComputation extends
        BasicComputation<IntWritable, PointValue,
                DoubleWritable, Text> {
    public void compute(Vertex<IntWritable, PointValue,
    		DoubleWritable> vertex, Iterable<Text> iterable) throws IOException {
        if (getSuperstep() == 0){
            //sendMessageToAllEdges(vertex, new Text());
        } else if (getSuperstep() == 1){
            Integer degree = Iterables.size(vertex.getEdges());
            System.out.println("x:" + vertex.getValue().getX() + ", y:" + vertex.getValue().getX());
        }else{
            //vertex.voteToHalt();
            //System.out.println("Vertex " + vertex.getId() + " computing after it has voted to halt ");
        }
    }
}

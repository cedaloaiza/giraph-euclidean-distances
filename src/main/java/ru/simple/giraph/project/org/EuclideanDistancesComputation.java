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

import java.awt.geom.Point2D;
import java.io.IOException;

public class EuclideanDistancesComputation extends
        BasicComputation<IntWritable, PointValue,
                DoubleWritable, PointValue> {
    public void compute(Vertex<IntWritable, PointValue,
    		DoubleWritable> vertex, Iterable<PointValue> messages) throws IOException {
    	int numVertices = 13;//75276;//18819;
    	int partitions = 6;
    	int verticesPerPartition = numVertices / partitions;
    	int startingVertex = verticesPerPartition*((int)getSuperstep()-1);
    	if (((int)getSuperstep()-1) == partitions) {
    		verticesPerPartition = numVertices % partitions;
    	}
    	
    	if (getSuperstep() == 0) {
    		System.out.println("Sending messages. Vertex id: " + vertex.getId());
    		for (int i = startingVertex; i < startingVertex + verticesPerPartition; i++) {
    			//System.out.println("sending message to:" + i);
    			sendMessage(new IntWritable(i), vertex.getValue());
    		}
    	} else {
    		
    		System.out.println("Computing messages. Vertex id: " + vertex.getId() + " " + vertex.getValue().getId() );
    		double[] distances = new double[numVertices];
    		for (PointValue point : messages) {
    			distances[point.getId()] = Point2D.distance(vertex.getValue().getX(), vertex.getValue().getY(), point.getX(), point.getY());
    			//System.out.println("THe distance from " + vertex.getId() + " to " + point.getId() + " is " + distances[point.getId()]);

        	}
    		vertex.getValue().setDistances(distances);
    		vertex.voteToHalt();
    	}
    }
}

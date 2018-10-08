package ru.simple.giraph.project.org;

import java.io.IOException;

import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexOutputFormat;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * Simple VertexOutputFormat that supports {@link SimplePageRankComputation}
 */
public class RDCMSTVertexOutputFormat extends
    TextVertexOutputFormat<IntWritable, PointValue, DoubleWritable> {
  @Override
  public TextVertexWriter createVertexWriter(TaskAttemptContext context)
    throws IOException, InterruptedException {
    return new SimplePageRankVertexWriter();
  }
  /**
   * Simple VertexWriter that supports {@link SimplePageRankComputation}
   */
  public class SimplePageRankVertexWriter extends TextVertexWriter {
    @Override
    public void writeVertex(
        Vertex<IntWritable, PointValue, DoubleWritable> vertex)
      throws IOException, InterruptedException {
    	System.out.println("Printing vertex " + vertex.getId() + "...");
    	double[] distances = vertex.getValue().getDistances();
    	StringBuffer line = new StringBuffer();
    	line.append("[" + vertex.getId() + ",[");
    	double maxDistance = 0;
    	for (int i = 0; i < distances.length; i++) {
    		if (distances[i] > maxDistance ) {
    			maxDistance = distances[i];
    		}
    		line.append(Double.toString(distances[i]));
    		if (i != (distances.length - 1)) {
    			line.append(",");
    		} 
    	}
    	line.append("],");
    	if (vertex.getId().get() == 0) {
    		line.append("[");
    		for (int i = 1; i < distances.length; i++) {
    			line.append(i);
				if (i != (distances.length - 1)) {
					line.append(",");
	    		} 
        	}
    		line.append("]," + maxDistance + ",0]");
    	} else {
    		line.append("null," + "0," + maxDistance + "]");
    		System.out.println("after for");
    	}
        getRecordWriter().write(
	          new Text(line.toString()),
	          null);
    	
    }
  }
}

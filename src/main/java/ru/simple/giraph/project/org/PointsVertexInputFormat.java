package ru.simple.giraph.project.org;


import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexInputFormat;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

/**
  * VertexInputFormat that features <code>long</code> vertex ID's,
  * <code>double</code> vertex values and <code>float</code>
  * out-edge weights, and <code>double</code> message types,
  *  specified in JSON format.
  */
public class PointsVertexInputFormat extends
  TextVertexInputFormat<IntWritable, PointValue, DoubleWritable> {

  @Override
  public TextVertexReader createVertexReader(InputSplit split,
      TaskAttemptContext context) {
    return new RDCMSTVertexReader();
  }

 /**
  * VertexReader that features <code>double</code> vertex
  * values and <code>float</code> out-edge weights. The
  * files should be in the following JSON format:
  * JSONArray(<vertex id>, <vertex value>,
  *   JSONArray(JSONArray(<dest vertex id>, <edge value>), ...))
  * Here is an example with vertex id 1, vertex value 4.3, and two edges.
  * First edge has a destination vertex 2, edge value 2.1.
  * Second edge has a destination vertex 3, edge value 0.7.
  * [1,4.3,[[2,2.1],[3,0.7]]]
  */
  class  RDCMSTVertexReader extends
    TextVertexReaderFromEachLineProcessedHandlingExceptions<JSONArray,
    JSONException> {

    @Override
    protected JSONArray preprocessLine(Text line) throws JSONException {
      return new JSONArray(line.toString());
    }

    @Override
    protected IntWritable getId(JSONArray jsonVertex) throws JSONException,
              IOException {
      return new IntWritable(jsonVertex.getInt(0));
    }

    @Override
    protected PointValue getValue(JSONArray jsonVertex) throws
      	JSONException, IOException {
      return new PointValue(jsonVertex.getDouble(1), jsonVertex.getDouble(2));
    }

    @Override
    protected Iterable<Edge<IntWritable, DoubleWritable>> getEdges(
        JSONArray jsonVertex) throws JSONException, IOException {
    	
      List<Edge<IntWritable, DoubleWritable>> edges = Lists.newArrayList();
      /*
      boolean isTheRoot = !jsonVertex.isNull(2);
      if(isTheRoot){
	      JSONArray jsonEdgeArray = jsonVertex.getJSONArray(2);
	      edges = Lists.newArrayListWithCapacity(jsonEdgeArray.length());
	      for (int i = 0; i < jsonEdgeArray.length(); ++i) {
	        int successor = jsonEdgeArray.getInt(i);
	        edges.add(EdgeFactory.create(new IntWritable(successor),
	            new DoubleWritable( jsonVertex.getJSONArray(1).getDouble(successor))));
	      }
      }  
      */
      return edges;
    }

    @Override
    protected Vertex<IntWritable, PointValue, DoubleWritable> handleException(Text line, JSONArray jsonVertex, JSONException e) {
      throw new IllegalArgumentException(
          "Couldn't get vertex from line " + line, e);
    }

  }
}
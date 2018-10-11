package ru.simple.giraph.project.org;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.giraph.master.MasterCompute;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Writable;



public class EmptyMasterCompute extends MasterCompute {
	
	private int iteration = 0;
	private static final int MAX_ITERARIONS = 20;
	private static final int SUPER_STEPS_PER_ITERATION = 5;

	

	@Override
	public void compute() {
		
		if (getSuperstep() == 1) {
			broadcast("numVertices", new LongWritable(getTotalNumVertices()));
		}
	}

	@Override
	public void initialize() throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		
	}


	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}


	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}
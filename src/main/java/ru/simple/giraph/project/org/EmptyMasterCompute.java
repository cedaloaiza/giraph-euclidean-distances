package ru.simple.giraph.project.org;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.giraph.master.MasterCompute;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Writable;



public class EmptyMasterCompute extends MasterCompute {
	
	private int iteration = 0;
	private static final int MAX_ITERARIONS = 20;
	private static final int SUPER_STEPS_PER_ITERATION = 5;

	

	@Override
	public void compute() {
		
		int superStepPhase =  (int) getSuperstep() % SUPER_STEPS_PER_ITERATION;
		System.out.println("\n\n" + iteration );
		System.out.println("Iteration/Movement: " + iteration);
		System.out.println("***** Computation " +  superStepPhase + " *****");
		System.out.println("Number of vertices: " + getTotalNumVertices());
		System.out.println("***MASTER ***");
		if (iteration < MAX_ITERARIONS) {		
			switch (superStepPhase) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					iteration++;
					break;
			}
		} else {
			haltComputation();
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
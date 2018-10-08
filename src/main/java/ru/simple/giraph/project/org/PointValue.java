package ru.simple.giraph.project.org;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.ArrayPrimitiveWritable;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;


public class PointValue implements Writable{
	
	public static final int NONE_PARENT = -1;
	

	
	private double x;
	private double y;

	




	public PointValue(double x, double y) {
		this.x = x;
		this.y = y;

	}


	public PointValue() {
		super();
		this.x = 0;
		this.y = 0;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}

	

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeDouble(x);
		out.writeDouble(y);

	}

	@Override
	public void readFields(DataInput in) throws IOException {
		x = in.readDouble();
		y = in.readDouble();

		
	}


}

package ru.simple.giraph.project.org;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;



public class PointWritable implements Writable{
	
		
	private double x;
	private double y;
	private int id;


	public PointWritable(double x, double y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;

	}


	public PointWritable() {
		super();
		this.x = 0;
		this.y = 0;
		this.id = -1;
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
	
	public int getId() {
		return id;
	}


	public void setY(double y) {
		this.y = y;
	}


	public void write(DataOutput out) throws IOException {
		out.writeDouble(x);
		out.writeDouble(y);
		out.writeInt(id);

	}

	public void readFields(DataInput in) throws IOException {
		x = in.readDouble();
		y = in.readDouble();
		id = in.readInt();

		
	}


}
